package com.soi.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "movies")
public class MovieIdList {
    private List<Integer> movieIds;

    public MovieIdList() {}

    public MovieIdList(List<Integer> movieIds) {
        this.movieIds = movieIds;
    }

    @XmlElement(name = "id")
    public List<Integer> getMovieIds() {
        return movieIds;
    }
}

