package com.logmein.deskcards.service;

import com.logmein.deskcards.card.Card;
import com.logmein.deskcards.card.Suit;
import com.logmein.deskcards.domain.Game;
import com.logmein.deskcards.domain.GameDeck;
import com.logmein.deskcards.domain.Player;
import com.logmein.deskcards.domain.PlayerHand;
import com.logmein.deskcards.dto.PlayerRandDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Game Service
 */
public interface GameService {
    Game create(Game game);
    void delete(Game game);
    Player addPlayer(Player player,Game game);
    void removePlayer(Player player,Game game);
    Optional<PlayerHand> dealCardsToPlayer(GameDeck gameDeck, Player player);
    List<PlayerRandDto> getRandOfPlayer(Game game);
    List<Card> getListOfCard(Player player);
    Map<Suit,Integer> totalCardsPerSuitUndealt(GameDeck gameDeck);
    List<Card> getListOfCardRemainding(GameDeck gameDeck);


}
