package com.logmein.deskcards.service.impl;

import com.logmein.deskcards.domain.Deck;
import com.logmein.deskcards.domain.Game;
import com.logmein.deskcards.domain.GameDeck;
import com.logmein.deskcards.repository.DeckRepository;
import com.logmein.deskcards.repository.GameDeckRepository;
import com.logmein.deskcards.service.DeckService;
import org.springframework.stereotype.Service;

/**
 * Deck Service
 */
@Service
public class DeckServiceImpl implements DeckService {

    private DeckRepository deckRepository;
    private GameDeckRepository gameDeckRepository;

    public DeckServiceImpl(DeckRepository deckRepository,GameDeckRepository gameDeckRepository){
        this.deckRepository=deckRepository;
        this.gameDeckRepository=gameDeckRepository;
    }

    @Override
    public Deck create(Deck deck) {
        return deckRepository.save(deck);
    }

    @Override
    public GameDeck addDeckToGameDeck(Deck deck, Game game) {
        GameDeck gameDeck=new GameDeck();
        gameDeck.setDeck(deck);
        gameDeck.setGame(game);
        return gameDeckRepository.save(gameDeck);

    }

    @Override
    public Deck shuffle(Deck deck, Game game) {
        GameDeck gameDeck=gameDeckRepository.findByGameAndDeck(game.getId(),deck.getId());
        deck.initDeck();
        deck.shuffleDeck();
        gameDeck.setDeck(deck);
        gameDeck.setNumberOfCall(gameDeck.getNumberOfCall()+1);
        deckRepository.save(deck);
        gameDeckRepository.save(gameDeck);
        return deck;
    }
}
