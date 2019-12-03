package com.example.pokemonapp.DTO;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {

    private Integer weight;
    private String name;
    private Integer baseExperience;
    private List<Result> moves;


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }
}
