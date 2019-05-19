package com.logmein.deskcards.repository;

import com.logmein.deskcards.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Player Entity Repository
 */
public interface PlayerRepository extends JpaRepository<Player,String>{
    Optional<Player> getPlayerByName(String name);
}
