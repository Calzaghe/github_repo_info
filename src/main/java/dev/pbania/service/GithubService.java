package dev.pbania.service;

import dev.pbania.error.CustomError;
import dev.pbania.rest.GithubRestClient;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class GithubService {

    @RestClient
    GithubRestClient githubRestClient;

    public Uni<Response> getUserRepositoriesInfo(String username){
        return githubRestClient.listRepos(username)
                .onItem().transformToUni(repositories -> {

                    if (repositories == null || repositories.isEmpty()) {
                        return Uni.createFrom().item(Response.status(Response.Status.NOT_FOUND)
                                .entity(new CustomError(404, "No public repositories found for user "+username))
                                .build());
                    }

                    List<Uni<Map<String, Object>>> repoResponses = repositories.stream()
                            .filter(repo -> !repo.fork)
                            .map(repo -> githubRestClient.getBranches(username, repo.name)
                                    .onItem().transform(branches -> {

                                        Map<String, Object> repoMap = new HashMap<>();
                                        repoMap.put("repositoryName", repo.name);
                                        repoMap.put("ownerLogin", repo.owner.login);

                                        List<Map<String, String>> branchList = branches.stream()
                                                .map(branch -> {
                                                    Map<String, String> branchMap = new HashMap<>();
                                                    branchMap.put("name", branch.name);
                                                    branchMap.put("sha", branch.commit.sha);
                                                    return branchMap;
                                                })
                                                .collect(Collectors.toList());
                                        repoMap.put("branches", branchList);

                                        return repoMap;
                                    })
                                    .onFailure().recoverWithItem(e -> {
                                        // Zwracamy pustą listę gałęzi w przypadku błędu
                                        Map<String, Object> repoMap = new HashMap<>();
                                        repoMap.put("repositoryName", repo.name);
                                        repoMap.put("ownerLogin", repo.owner.login);
                                        repoMap.put("branches", new ArrayList<>());
                                        return repoMap;
                                    })
                            )
                            .collect(Collectors.toList());

                    // Czekamy na zakończenie pobierania wszystkich gałęzi i zwracamy wynik
                    return Uni.combine().all().unis(repoResponses)
                            .combinedWith(list -> Response.ok(list).build());
                })
                .onFailure().recoverWithItem(e -> Response.status(Response.Status.NOT_FOUND)
                        .entity(new CustomError(404, "User not found"))
                        .build());
    }
}
