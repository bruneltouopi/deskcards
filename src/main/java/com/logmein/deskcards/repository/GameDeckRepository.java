package com.logmein.deskcards.repository;

import com.logmein.deskcards.domain.Game;
import com.logmein.deskcards.domain.GameDeck;
import com.logmein.deskcards.domain.GameDeckId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Game Entity Repository
 */
public interface GameDeckRepository extends JpaRepository<GameDeck,GameDeckId>{

    @Query(value = "SELECT g FROM GameDeck g WHERE g.game.id = :gameId AND g.deck.id = :deckId")
    public GameDeck findByGameAndDeck(@Param("gameId") Long gameId,@Param("deckId")  Long deckId);
}
