package com.gameachievementsapi.GameAchievementsAPI.controllers;

import com.gameachievementsapi.GameAchievementsAPI.domain.Achievement;
import com.gameachievementsapi.GameAchievementsAPI.domain.Game;
import com.gameachievementsapi.GameAchievementsAPI.repositories.IAchievementRepository;
import com.gameachievementsapi.GameAchievementsAPI.repositories.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZonedDateTime;

@Controller
@RequestMapping("/api/database")
public class DatabaseController {

    @Autowired
    private IGameRepository gameRepository;

    @Autowired
    private IAchievementRepository achievementRepository;

    @GetMapping("")
    public ResponseEntity insertion() {
        ZonedDateTime created = ZonedDateTime.now();
        Game game = new Game("ee2a2a57-9867-4e77-907c-7513ba2e6a85", "Game1", created.minusMinutes(20), created.minusMinutes(20));
        gameRepository.save(game);

        Achievement achievement = new Achievement("e5e2a52e-2125-4f8f-8ed7-18f773f61311",
                "g1a1", "g1d1",
                "http://www.example.com/example_icon.png", 1, created.minusMinutes(19), created.minusMinutes(19), game);
        achievementRepository.save(achievement);

        achievement = new Achievement("44c9a54e-5a2b-4f38-9007-c252b8bd1c87",
                "g1a2", "g1d2",
                "http://www.example.com/example_icon.png", 2, created.minusMinutes(18), created.minusMinutes(18), game);
        achievementRepository.save(achievement);

        achievement = new Achievement("12a03e03-62fc-4475-be39-43b2f6d7d876",
                "g1a3", "g1d3",
                "http://www.example.com/example_icon.png", 3, created.minusMinutes(17), created.minusMinutes(17), game);
        achievementRepository.save(achievement);

        achievement = new Achievement("2c3528a5-d5e3-4d1c-89fa-b147f6279804",
                "g1a4", "g1d4",
                "http://www.example.com/example_icon.png", 4, created.minusMinutes(16), created.minusMinutes(16), game);
        achievementRepository.save(achievement);

        achievement = new Achievement("e586a5a0-da9f-45b3-96e6-c9f3f1aa1fe0",
                "g1a5", "g1d5",
                "http://www.example.com/example_icon.png", 5, created.minusMinutes(15), created.minusMinutes(15), game);
        achievementRepository.save(achievement);

        achievement = new Achievement("35d7f800-a096-404c-a6a0-b63a7a1f1268",
                "g1a6", "g1d6",
                "http://www.example.com/example_icon.png", 6, created.minusMinutes(14), created.minusMinutes(14), game);
        achievementRepository.save(achievement);

        achievement = new Achievement("c4d71090-cf91-4a4e-a37b-325ed281bd09",
                "g1a7", "g1d7",
                "http://www.example.com/example_icon.png", 7, created.minusMinutes(13), created.minusMinutes(13), game);
        achievementRepository.save(achievement);

        game = new Game("ab20cc43-d95e-492a-a9e3-ca77b80da262", "Game2", created.minusMinutes(12), created.minusMinutes(12));
        gameRepository.save(game);

        achievement = new Achievement("2371ec47-2eb8-4c09-81a5-dc89ff9bb133",
                "g2a1", "g2d1",
                "http://www.example.com/example_icon.png", 1, created.minusMinutes(11), created.minusMinutes(11), game);
        achievementRepository.save(achievement);

        achievement = new Achievement("cd722d0d-dc7e-4fef-8e69-6e5b4692defe",
                "g2a2", "g2d2",
                "http://www.example.com/example_icon.png", 2, created.minusMinutes(10), created.minusMinutes(10), game);
        achievementRepository.save(achievement);

        achievement = new Achievement("29b535de-0d49-44b4-8c12-6b9770d1fde1",
                "g2a3", "g2d3",
                "http://www.example.com/example_icon.png", 3, created.minusMinutes(9), created.minusMinutes(9), game);
        achievementRepository.save(achievement);

        game = new Game("bce7bdbb-922e-42f2-a46d-95655fed3954", "Game3", created.minusMinutes(8), created.minusMinutes(8));
        gameRepository.save(game);

        game = new Game("e5e83fc8-13f5-4b7a-8316-ccd480eda3c4", "Game4", created.minusMinutes(7), created.minusMinutes(7));
        gameRepository.save(game);

        game = new Game("cea52505-3b5a-41f4-91f6-a04ff38d9434", "Game5", created.minusMinutes(6), created.minusMinutes(6));
        gameRepository.save(game);

        game = new Game("e332c7b5-d10e-4472-b9c3-46866b429fb2", "Game6", created.minusMinutes(5), created.minusMinutes(5));
        gameRepository.save(game);

        game = new Game("88f374d5-5513-4fb4-ae6e-ae9ea60b1613", "Game7", created.minusMinutes(4), created.minusMinutes(4));
        gameRepository.save(game);

        return new ResponseEntity(HttpStatus.OK);
    }
}
