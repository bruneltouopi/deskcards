package com.logmein.deskcards.controller;

import com.logmein.deskcards.domain.Deck;
import com.logmein.deskcards.domain.Game;
import com.logmein.deskcards.domain.GameDeck;
import com.logmein.deskcards.repository.DeckRepository;
import com.logmein.deskcards.repository.GameRepository;
import com.logmein.deskcards.service.DeckService;
import com.logmein.deskcards.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Game Controller
 */
@RestController
public class GameController {

    private final GameRepository gameRepository;
    private final DeckRepository deckRepository;
    private final GameService gameService;
    private final DeckService deckService;

    @Autowired
    public GameController(GameService gameService,DeckService deckService, GameRepository gameRepository,DeckRepository deckRepository) {
        this.gameService = gameService;
        this.deckService=deckService;
        this.gameRepository = gameRepository;
        this.deckRepository = deckRepository;
    }

    /**
     * Create a game
     * @param game
     * @return
     */
    @PostMapping("/game")
    public Game createGame(@RequestBody Game game) {
        return gameService.create(game);
    }

    /**
     * Delete A game
     * @param id
     * @return
     */
    @DeleteMapping("/game/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        Game game = gameRepository.getOne(id);
        if (game != null) {
            gameService.create(game);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Create a Deck
     * @param deck
     * @return
     */
    @PostMapping("/deck")
    public ResponseEntity<Deck> createDeck(@RequestBody Deck deck) {
        Deck DeckCreated=deckService.create(deck);
        return ResponseEntity.ok(DeckCreated);
    }


    /**
     * Add a Deck to a game deck
     * @param idDeck
     * @param idGame
     * @return
     */
    @PostMapping("/deck/{idDeck}/game/{idGame}/gameDeck")
    public ResponseEntity<GameDeck> addDeckToGame(@PathVariable Long idDeck,@PathVariable Long idGame) {
       Optional<Deck> deck =deckRepository.findById(idDeck);
       Optional<Game> game=gameRepository.findById(idGame);

       if(deck.isPresent() && game.isPresent()){
           GameDeck gameDeck=deckService.addDeckToGameDeck(deck.get(),game.get());
           return ResponseEntity.ok(gameDeck);
       }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }






}
