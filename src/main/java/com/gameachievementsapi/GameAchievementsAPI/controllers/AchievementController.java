package com.gameachievementsapi.GameAchievementsAPI.controllers;

import com.gameachievementsapi.GameAchievementsAPI.domain.Achievement;
import com.gameachievementsapi.GameAchievementsAPI.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/api/games")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @PostMapping("/{gameId}/achievements")
    public ResponseEntity<Object> createAchievement(@PathVariable String gameId,
                                                    @Valid @RequestBody Achievement newAchievement,
                                                    BindingResult result) {
        if ((gameId == null) || (!gameId.equals(newAchievement.getGame().getId()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal arguments.");
        }
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.OK);
        }
        Achievement achievement = achievementService.createAchievement(newAchievement);
        return new ResponseEntity<>(achievement, HttpStatus.OK);
    }

    @GetMapping(value = {"/{gameId}/achievements", "/{gameId}/achievements/page/{pageNumber}"})
    public ResponseEntity<Page<Achievement>> getAllGameAchievements(@PathVariable String gameId,
                                                                    @PathVariable @Nullable Integer pageNumber) {
        if (pageNumber != null && pageNumber <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal argument.");
        }
        if (pageNumber == null) {
            pageNumber = 1;
        }
        Page<Achievement> achievementsPage = achievementService.getAllGameAchievements(gameId, pageNumber - 1);
        return new ResponseEntity<>(achievementsPage, HttpStatus.OK);
    }

    @GetMapping(value = {"/{gameId}/achievements/{id}"})
    public ResponseEntity<Achievement> getAchievement(@PathVariable String gameId, @PathVariable String id) {
        Optional<Achievement> achievementOpt = achievementService.getAchievement(gameId, id);
        if (achievementOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found.");
        }
        return new ResponseEntity<>(achievementOpt.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/{gameId}/achievements/{id}")
    public ResponseEntity<Object> updateAchievement(@PathVariable String gameId,
                                                    @PathVariable String id,
                                                    @Valid @RequestBody Achievement modifiedAchievement,
                                                    BindingResult result) {
        if ((!id.equals((modifiedAchievement.getId())))
                || (modifiedAchievement.getGame() == null)
                || (!gameId.equals(modifiedAchievement.getGame().getId()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request.");//Illegal arguments.
        }
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            Achievement achievement = achievementService.updateAchievement(gameId, modifiedAchievement);
            return new ResponseEntity<>(achievement, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);//Achievement not found.
        }
    }

    @DeleteMapping(value = {"/{gameId}/achievements/{id}"})
    public ResponseEntity deleteAchievement(@PathVariable String gameId, @PathVariable String id) {
        try {
            achievementService.deleteAchievement(gameId, id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found.", e);
        }
    }
}
