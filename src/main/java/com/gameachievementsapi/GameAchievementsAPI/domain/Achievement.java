package com.gameachievementsapi.GameAchievementsAPI.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
@DynamicUpdate
public class Achievement {
    @Id
    private String id;
    @NotBlank(message = "Display Name is required.")
    @Size(max = 100, message = "Display Name can be max 100 characters long.")
    private String displayName;
    @NotBlank(message = "Description is required.")
    @Size(max = 500, message = "Description can be max 500 characters long.")
    private String description;
    @URL(message = "Provide a valid URL.")
    private String icon;
    private Integer displayOrder; // 2,147,483,647 different values available
    @DateTimeFormat
    private ZonedDateTime created;
    @DateTimeFormat
    private ZonedDateTime updated;
    @ManyToOne
    private Game game;

    public Achievement() {

    }

    public Achievement(String id, String displayName, String description, String icon, Integer displayOrder, ZonedDateTime created, ZonedDateTime updated, Game game) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.icon = icon;
        this.displayOrder = displayOrder;
        this.created = created;
        this.updated = updated;
        this.game = game;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}