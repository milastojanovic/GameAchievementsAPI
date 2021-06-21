package com.gameachievementsapi.GameAchievementsAPI.repositories;

import com.gameachievementsapi.GameAchievementsAPI.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends JpaRepository<Game, String> {
}
