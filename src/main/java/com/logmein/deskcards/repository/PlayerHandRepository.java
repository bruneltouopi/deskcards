package com.logmein.deskcards.repository;

import com.logmein.deskcards.domain.PlayerHand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Player Entity Repository
 */
public interface PlayerHandRepository extends JpaRepository<PlayerHand,Long>{

    @Query(value = "SELECT p FROM PlayerHand p WHERE p.player.id = :idPlayer")
    List<PlayerHand> getPlayerHandByPlayer(@Param("idPlayer") String playerId);

}
