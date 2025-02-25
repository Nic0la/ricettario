package com.exercise.uno.models.dto;

import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.models.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

    /*
    Serve per identificare in modo univoco la versione di una classe serializzabile.
    Quando un oggetto viene serializzato (convertito in un flusso di byte) e poi deserializzato (ricostruito in memoria),
    Java usa il serialVersionUID per verificare che la classe originale
    e la classe in fase di deserializzazione siano compatibili.
    Se il valore del serialVersionUID della classe originale non corrisponde a quello della classe caricata,
    si genera un errore InvalidClassException, impedendo la deserializzazione.
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String login;

    private String username;

    private String password;

    @JsonIgnore
    private List<Recipe> recipes;

    public UserDTO(){}

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getUsername();
        this.recipes = user.getRecipes();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
