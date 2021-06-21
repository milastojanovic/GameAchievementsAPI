package com.gameachievementsapi.GameAchievementsAPI.service;

import com.gameachievementsapi.GameAchievementsAPI.domain.Achievement;
import com.gameachievementsapi.GameAchievementsAPI.repositories.IAchievementRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AchievementService {

    @Autowired
    public IAchievementRepository achievementRepository;

    private final Integer achievementsPerPage = 2;

    @Transactional
    public Achievement createAchievement(Achievement newAchievement) {
        // create id
        String id = UUID.randomUUID().toString();
        // if the id exists in the database, generate a new id
        while (achievementRepository.findById(id).isPresent()) {
            id = UUID.randomUUID().toString();
        }
        newAchievement.setId(id);

        Integer displayOrder = achievementRepository.findFirstByGameId(newAchievement.getGame().getId());
        if (displayOrder == null) {
            displayOrder = 0;
        }
        newAchievement.setDisplayOrder(displayOrder + 1);

        ZonedDateTime created = ZonedDateTime.now();
        newAchievement.setCreated(created);
        newAchievement.setUpdated(created);

        return achievementRepository.save(newAchievement);
    }

    public Page<Achievement> getAllGameAchievements(String gameId, Integer pageNumber) {
        Sort sort = Sort.by("displayOrder").ascending();
        Pageable pageable = PageRequest.of(pageNumber, achievementsPerPage, sort);
        Page<Achievement> achievementsPage =achievementRepository.findAllByGameId(gameId, pageable);
        return achievementsPage;
    }

    public Optional<Achievement> getAchievement(String gameId, String achievementId) {
        return achievementRepository.findByIdAndGameId(achievementId, gameId);
    }

    public Achievement updateAchievement(String gameId, Achievement modifiedAchievement) throws NotFoundException {
        Optional<Achievement>achievementOpt = achievementRepository.findByIdAndGameId(modifiedAchievement.getId(), gameId);
        if (achievementOpt.isEmpty()) {
            throw new NotFoundException("Achievement not found.");
        }
        Achievement achievement = achievementOpt.get();

        // set modified fields
        achievement.setDisplayName(modifiedAchievement.getDisplayName());
        achievement.setDescription(modifiedAchievement.getDescription());
        achievement.setIcon(modifiedAchievement.getIcon());
        // set the updated field
        achievement.setUpdated(ZonedDateTime.now());

        return achievementRepository.save(achievement);
    }

    @Transactional
    public void deleteAchievement(String gameId, String achievementId) throws NotFoundException {
        Optional<Achievement> achievementOpt = achievementRepository.findByIdAndGameId(achievementId, gameId);
        if (achievementOpt.isEmpty()) {
            throw new NotFoundException("Achievement not found.");
        }
        Achievement achievement =achievementOpt.get();

        // displayOrders to update
        List<Achievement> achievements = null;
        Optional<List<Achievement>> achievementsOpt =
                achievementRepository.findByGameIdAndDisplayOrderGreaterThan(gameId, achievement.getDisplayOrder());
        if (achievementsOpt.isPresent()) {
            achievements = achievementsOpt.get();
        }
        if (achievements != null) {
            achievements.stream().forEach(s -> s.setDisplayOrder(s.getDisplayOrder() - 1));
        }

        achievementRepository.deleteById(achievementId);
        achievementRepository.saveAll(achievements); // update only displayOrder (@DynamicUpdate in Entity)
    }
}
