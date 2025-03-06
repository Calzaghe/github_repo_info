package dev.pbania.rest;

import dev.pbania.model.GithubBranch;

import dev.pbania.model.GithubRepo;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;


@RegisterRestClient(configKey = "github-api")
public interface GithubRestClient {


    @GET
    @Path("/users/{username}/repos")
    Uni<List<GithubRepo>> listRepos(@PathParam("username") String username);

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    Uni<List<GithubBranch>> getBranches(@PathParam("owner") String owner, @PathParam("repo") String repo);

}
