package com.gameachievementsapi.GameAchievementsAPI.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
public class Game {
    @Id
    private String id;
    @NotBlank(message = "Display Name is required.")
    @Size(max = 50) // a short name of the game according to project specification
    private String displayName;
    @DateTimeFormat
    private ZonedDateTime created;
    @DateTimeFormat
    private ZonedDateTime updated;

    public Game() {
    }

    public Game(String id, String displayName, ZonedDateTime created, ZonedDateTime updated) {
        this.id = id;
        this.displayName = displayName;
        this.created = created;
        this.updated = updated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }
}