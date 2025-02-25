package com.exercise.uno.models.dto;

public class CategoryDTO {

    private Long id;

    private String name;

    //private Set<RecipeDTO> recipes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
