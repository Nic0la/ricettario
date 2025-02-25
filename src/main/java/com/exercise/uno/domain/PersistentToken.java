package com.exercise.uno.domain;

import com.exercise.uno.models.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class PersistentToken implements Serializable {

    @Id
    private String series;

    @JsonIgnore
    private String tokenValue;

    @JsonIgnore
    @ManyToOne
    private User user;

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSeries() {
        return series;
    }




}
