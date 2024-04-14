package com.soi.controller;

import com.soi.model.Movie;
import com.soi.model.MovieId;
import com.soi.model.MovieIdList;
import com.soi.service.MovieService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@Path("/movies")
public class MovieController {

    private final MovieService movieService = new MovieService();
    private static final Logger LOGGER = Logger.getLogger(MovieController.class.getName());

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllMovies() {
        LOGGER.log(Level.INFO,"Fetching all movies data");
        return Response.ok(movieService.getAllMovies()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMovie(@PathParam("id") int id) {
        LOGGER.log(Level.INFO,"Fetching movie by id: {}", id);
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return Response.ok(movie).build();
        } else {
            LOGGER.log(Level.WARNING,"Movie with given id: {} not found", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/find")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMoviesByYearSorted(@QueryParam("year") int year,
                                          @QueryParam("orderBy") @DefaultValue("title") String orderBy) {
        LOGGER.log(Level.INFO, "Fetching movies by year: {0} and sort by: {1}", new Object[]{year, orderBy});
        List<Integer> movieIds = movieService.getMoviesByYearSorted(year, orderBy);
        if (movieIds != null){
            MovieIdList movieIdList = new MovieIdList(movieIds);
            return Response.ok(movieIdList).build();
        } else {
            LOGGER.log(Level.WARNING, "Movie with given search criteria not found");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createMovie(Movie movie) {
        LOGGER.log(Level.INFO,"Adding movie to the Database {}", movie);
        int id = movieService.createMovie(movie);
        return Response.ok(new MovieId(id)).build();
    }


    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateMovie(Movie movie, @PathParam("id") int id) {
        LOGGER.log(Level.INFO,"Updating movie Id: {} with content movie: "+movie, id);
        movieService.updateMovie(movie, id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") int id) {
        LOGGER.log(Level.INFO,"Deleting movie with ID: " + id);
        movieService.deleteMovie(id);
        return Response.noContent().build();
    }
}


