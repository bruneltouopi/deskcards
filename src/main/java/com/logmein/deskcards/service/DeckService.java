package com.logmein.deskcards.service;

import com.logmein.deskcards.domain.Deck;
import com.logmein.deskcards.domain.Game;
import com.logmein.deskcards.domain.GameDeck;

/**
 * Created by fabrice on 5/19/19.
 */
public interface DeckService {

    Deck create(Deck deck);
    GameDeck addDeckToGameDeck(Deck deck,Game game);
    Deck shuffle(Deck deck,Game game);
}
