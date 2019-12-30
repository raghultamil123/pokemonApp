package com.example.pokemonapp.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pokemon implements Serializable {

    private Integer weight;
    private String name;
    private Integer baseExperience;
    private Integer height;
    private ArrayList<Result> moves;
    private ArrayList<Result> types;
    private ArrayList<Result> abilities;

    public ArrayList<Result> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Result> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<Result> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Result> types) {
        this.types = types;
    }

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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public ArrayList<Result> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Result> moves) {
        this.moves = moves;
    }
}
