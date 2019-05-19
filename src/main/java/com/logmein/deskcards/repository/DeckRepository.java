package com.logmein.deskcards.repository;

import com.logmein.deskcards.domain.Deck;
import com.logmein.deskcards.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Deck Entity Repository
 */
public interface DeckRepository extends JpaRepository<Deck,Long>{
}
