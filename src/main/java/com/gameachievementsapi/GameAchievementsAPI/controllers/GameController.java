package com.gameachievementsapi.GameAchievementsAPI.controllers;

import com.gameachievementsapi.GameAchievementsAPI.domain.Game;
import com.gameachievementsapi.GameAchievementsAPI.service.GameService;
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
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("")
    public ResponseEntity<Object> createGame(@Valid @RequestBody Game newGame, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.OK); //todo return only default messages
        }
        Game game = gameService.createGame(newGame);
        return new ResponseEntity<Object>(game, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/page/{pageNumber}"})
    public ResponseEntity<Page<Game>> getAllGames(@PathVariable @Nullable Integer pageNumber) {
        if (pageNumber != null && pageNumber <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal argument.");
        }
        if (pageNumber == null) {
            pageNumber = 1;
        }
        Page<Game> gamesPage = gameService.getAllGames(pageNumber - 1);
        return new ResponseEntity<>(gamesPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable String id) {
        Optional<Game> gameOpt = gameService.getGame(id);
        if (!gameOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found.");
        }
        return new ResponseEntity<>(gameOpt.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable String id,
                                             @Valid @RequestBody Game modifiedGame,
                                             BindingResult result) {
        if (!id.equals(modifiedGame.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal arguments.");
        }
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            Game game = gameService.updateGame(modifiedGame);
            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);//Game not found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGame(@PathVariable String id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal argument.");
        }
        try {
            gameService.deleteGame(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found.", e);
        }
    }
}
