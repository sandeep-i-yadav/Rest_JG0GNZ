package com.soi.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

// Helper class to wrap movie ID in response
@XmlRootElement(name = "result")
public class MovieId {
    private int id;

    public MovieId() {};

    public MovieId(int id) {
        this.id = id;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
