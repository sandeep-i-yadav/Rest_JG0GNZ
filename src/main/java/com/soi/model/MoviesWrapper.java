package com.soi.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "movies")
public class MoviesWrapper {
    private List<Movie> movie;

    public MoviesWrapper() {

    }

    public MoviesWrapper(List<Movie> movie) {
        this.movie = movie;
    }

    @XmlElement
    public List<Movie> getMovie() {
        return movie;
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }
}
