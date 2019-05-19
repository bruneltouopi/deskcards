package com.logmein.deskcards.repository;

import com.logmein.deskcards.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Player Entity Repository
 */
public interface PlayerRepository extends JpaRepository<Player,String>{
}
