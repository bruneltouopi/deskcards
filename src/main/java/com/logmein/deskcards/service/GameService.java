package com.logmein.deskcards.service;

import com.logmein.deskcards.card.Card;
import com.logmein.deskcards.card.Suit;
import com.logmein.deskcards.domain.Game;
import com.logmein.deskcards.domain.GameDeck;
import com.logmein.deskcards.domain.Player;
import com.logmein.deskcards.dto.PlayerRandDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Game Service
 */
public interface GameService {
    Game create(Game game);
    void delete(Game game);
    void addPlayer(Player player,Game game);
    void removePlayer(Player player,Game game);
    List<PlayerRandDto> getRandOfPlayer(Game game);
    Map<Suit,Integer> totalCardsPerSuitUndealt(Game game);
    List<Card> getListCardsOfPlayer(GameDeck gameDeck,Player player);


}
