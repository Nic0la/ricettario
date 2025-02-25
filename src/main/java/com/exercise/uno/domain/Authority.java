package com.exercise.uno.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;


import java.io.Serializable;
import java.util.Objects;

@Entity
public class Authority implements Serializable, Persistable<String> {


    private String name;

    @Transient
    private boolean isPersisted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Authority name(String name) {
        this.setName(name);
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    //Getter and Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPersisted() {
        return isPersisted;
    }

    public Authority setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        return getName() != null && getName().equals(((Authority) o).getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Authority{" +
                "name=" + getName() +
                "}";
    }

    public void setId(Long id) {
        this.id = id;
    }

}
