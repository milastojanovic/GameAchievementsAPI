package com.gameachievementsapi.GameAchievementsAPI.repositories;

import com.gameachievementsapi.GameAchievementsAPI.domain.Achievement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAchievementRepository extends JpaRepository<Achievement, String> {

    @Query(value = "SELECT MAX(displayOrder) FROM Achievement")
    Integer findFirstByGameId(String gameId);

    Page<Achievement> findAllByGameId(String gameId, Pageable pageable);

    Optional<Achievement> findByIdAndGameId(String achievementId, String gameId);

    Optional<List<Achievement>> findByGameIdAndDisplayOrderGreaterThan(String gameId, Integer displayOrder);
}
