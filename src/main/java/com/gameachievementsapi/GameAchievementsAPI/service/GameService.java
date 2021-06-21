package com.gameachievementsapi.GameAchievementsAPI.service;

import com.gameachievementsapi.GameAchievementsAPI.domain.Game;
import com.gameachievementsapi.GameAchievementsAPI.repositories.IGameRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private IGameRepository gameRepository;

    private final Integer gamesPerPage = 2;

    public Game createGame(Game newGame) {
        // create id
        String id = UUID.randomUUID().toString();
        // if the id exists in the database, generate a new id
        while (gameRepository.findById(id).isPresent()) {
            id = UUID.randomUUID().toString();
        }
        newGame.setId(id);
        ZonedDateTime created = ZonedDateTime.now();

        newGame.setCreated(created);
        newGame.setUpdated(created);
        return gameRepository.save(newGame);
    }

    public Page<Game> getAllGames(final Integer pageNumber) {
        Sort sort = Sort.by("displayName").ascending();
        Pageable pageable = PageRequest.of(pageNumber, gamesPerPage, sort);
        Page<Game> gamesPage = gameRepository.findAll(pageable);
        return gamesPage;
    }

    public Optional<Game> getGame(String id) {
        return gameRepository.findById(id);
    }

    public Game updateGame(Game modifiedGame) throws NotFoundException {
        Optional<Game> gameOpt = gameRepository.findById(modifiedGame.getId());
        if (gameOpt.isEmpty()) {
            throw new NotFoundException("Game not found.");
        }
        Game game = gameOpt.get();
        // set modified fields
        game.setDisplayName(modifiedGame.getDisplayName());
        ZonedDateTime updated = ZonedDateTime.now();
        game.setUpdated(updated);

        return gameRepository.save(game);
    }

    public void deleteGame(String id) throws NotFoundException {
        Optional<Game> gameOpt = gameRepository.findById(id);
        if (gameOpt.isEmpty()) {
            throw new NotFoundException("Game not found.");
        }
        gameRepository.deleteById(id);
    }
}