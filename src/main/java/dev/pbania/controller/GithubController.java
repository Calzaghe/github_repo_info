package dev.pbania.controller;

import dev.pbania.service.GithubService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/github")
public class GithubController {

    @Inject
    GithubService githubService;


    @GET
    @Path("/repos/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getUserRepositories(@PathParam("username") String username) {
        return githubService.getUserRepositoriesInfo(username);
    }





}

