package com.logmein.deskcards.controller;

import com.logmein.deskcards.card.Card;
import com.logmein.deskcards.card.Suit;
import com.logmein.deskcards.domain.*;
import com.logmein.deskcards.dto.PlayerRandDto;
import com.logmein.deskcards.repository.DeckRepository;
import com.logmein.deskcards.repository.GameDeckRepository;
import com.logmein.deskcards.repository.GameRepository;
import com.logmein.deskcards.repository.PlayerRepository;
import com.logmein.deskcards.service.DeckService;
import com.logmein.deskcards.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Game Controller
 */
@RestController
public class GameController {

    private final GameRepository gameRepository;
    private final DeckRepository deckRepository;
    private final PlayerRepository playerRepository;
    private final GameDeckRepository gameDeckRepository;
    private final GameService gameService;
    private final DeckService deckService;

    @Autowired
    public GameController(GameService gameService, DeckService deckService, GameRepository gameRepository
            , DeckRepository deckRepository, PlayerRepository playerRepository, GameDeckRepository gameDeckRepository) {
        this.gameService = gameService;
        this.deckService = deckService;
        this.gameRepository = gameRepository;
        this.deckRepository = deckRepository;
        this.playerRepository = playerRepository;
        this.gameDeckRepository = gameDeckRepository;
    }


    /**
     * Create a game
     *
     * @param game
     * @return
     */
    @PostMapping("/game")
    public Game createGame(@RequestBody Game game) {
        return gameService.create(game);
    }

    /**
     * Delete A game
     *
     * @param id
     * @return
     */
    @DeleteMapping("/game/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        Game game = gameRepository.getOne(id);
        if (game != null) {
            gameService.delete(game);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Create a Deck
     *
     * @param deck
     * @return
     */
    @PostMapping("/deck")
    public ResponseEntity<Deck> createDeck(@RequestBody Deck deck) {
        Deck DeckCreated = deckService.create(deck);
        return ResponseEntity.ok(DeckCreated);
    }


    /**
     * Add a Deck to a game deck
     *
     * @param idDeck
     * @param idGame
     * @return
     */
    @PostMapping("/deck/{idDeck}/game/{idGame}/gameDeck")
    public ResponseEntity<GameDeck> addDeckToGame(@PathVariable Long idDeck, @PathVariable Long idGame) {
        Optional<Deck> deck = deckRepository.findById(idDeck);
        Optional<Game> game = gameRepository.findById(idGame);

        if (deck.isPresent() && game.isPresent()) {
            GameDeck gameDeck = deckService.addDeckToGameDeck(deck.get(), game.get());
            return ResponseEntity.ok(gameDeck);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Add a player to a game
     *
     * @param idGame
     * @param player
     * @return
     */
    @PostMapping("/game/{idGame}/player")
    public ResponseEntity<Player> addPlayerToGame(@PathVariable Long idGame, @RequestBody Player player) {
        Optional<Game> game = gameRepository.findById(idGame);
        return Optional.ofNullable(game).map(game1 -> {
            Player p = gameService.addPlayer(player, game1.get());
            return ResponseEntity.ok(p);
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    /**
     * Remove a player from the game
     *
     * @param idGame
     * @param idPlayer
     * @return
     */
    @PostMapping("/game/{idGame}/player/{idPlayer}")
    public ResponseEntity<Void> removePlayerToGame(@PathVariable Long idGame, @PathVariable String idPlayer) {
        Optional<Game> game = gameRepository.findById(idGame);
        Optional<Player> player = playerRepository.findById(idPlayer);
        if (game.isPresent() && player.isPresent()) {
            gameService.removePlayer(player.get(), game.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    /**
     * Deal the cards To a Player
     *
     * @param idGame
     * @param idDeck
     * @param idPlayer
     * @return
     */
    @PostMapping("/game/{idGame}/deck/{idDeck}/player/{idPlayer}")
    public ResponseEntity<PlayerHand> dealCardsToPlayer(@PathVariable Long idGame, @PathVariable Long idDeck, @PathVariable String idPlayer) {
        GameDeck gameDeck = gameDeckRepository.findByGameAndDeck(idGame, idDeck);
        if (gameDeck != null) {
            Optional<Player> player = playerRepository.findById(idPlayer);
            if (player.isPresent()) {
                Optional<PlayerHand> playerHand = gameService.dealCardsToPlayer(gameDeck, player.get());
                if (playerHand.isPresent()) {
                    return ResponseEntity.ok(playerHand.get());
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    /**
     * Get the list of Cards for a player
     *
     * @param idPlayer
     * @return
     */
    @GetMapping("/player/{idPlayer}/cards")
    public ResponseEntity<List<Card>> getCardsOfPlayer(@PathVariable String idPlayer) {
        Optional<Player> player = playerRepository.findById(idPlayer);
        if (player.isPresent()) {
            return ResponseEntity.ok(gameService.getListOfCard(player.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Get the List of Players with to tatal of cards each player holds
     *
     * @param idGame
     * @return
     */
    @GetMapping("/game/{idGame}/playerRank")
    public ResponseEntity<List<PlayerRandDto>> getCardsOfPlayer(@PathVariable Long idGame) {
        Optional<Game> game = gameRepository.findById(idGame);
        if (game.isPresent()) {
            return ResponseEntity.ok(gameService.getRandOfPlayer(game.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Get the count of how many cards per suit are left undealt in the game deck
     *
     * @param idGame
     * @return
     */
    @GetMapping("/game/{idGame}/Deck/{idDeck}/suit")
    public ResponseEntity<Map<Suit, Integer>> getTheRemainingSuitCountInGameDesk(@PathVariable Long idGame, @PathVariable Long idDeck) {
        GameDeck gameDeck = gameDeckRepository.findByGameAndDeck(idGame, idDeck);
        if (gameDeck != null) {
            Map<Suit, Integer> mapSuits = gameService.totalCardsPerSuitUndealt(gameDeck);
            return ResponseEntity.ok(mapSuits);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Get the cards (suit and value) remaining in the game deck sorted by suit and value
     *
     * @param idGame
     * @return
     */
    @GetMapping("/game/{idGame}/Deck/{idDeck}/cards")
    public ResponseEntity<List<Card>> getTheRemainingCardsInGameDesk(@PathVariable Long idGame, @PathVariable Long idDeck) {
        GameDeck gameDeck = gameDeckRepository.findByGameAndDeck(idGame, idDeck);
        if (gameDeck != null) {
            List<Card> cardList = gameService.getListOfCardRemainding(gameDeck);
            return ResponseEntity.ok(cardList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    /**
     * Shuffle a Deck
     *
     * @param idDeck
     * @return
     */
    @PostMapping("/game/{idGame}/Deck/{idDeck}/Shuffle")
    public ResponseEntity<Void> shuffleDeck(@PathVariable Long idGame, @PathVariable Long idDeck) {
        GameDeck gameDeck = gameDeckRepository.findByGameAndDeck(idGame, idDeck);
        if (gameDeck != null) {
            deckService.shuffle(gameDeck.getDeck(), gameDeck.getGame());
        }
        return ResponseEntity.ok().build();
    }

}
