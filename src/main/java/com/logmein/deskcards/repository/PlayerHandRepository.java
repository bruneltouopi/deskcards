package com.logmein.deskcards.repository;

import com.logmein.deskcards.domain.PlayerHand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Player Entity Repository
 */
public interface PlayerHandRepository extends JpaRepository<PlayerHand,Long>{
}
