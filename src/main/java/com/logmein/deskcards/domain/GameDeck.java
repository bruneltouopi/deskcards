package com.logmein.deskcards.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Game Deck
 */

@Entity
public class GameDeck implements Serializable {

    @EmbeddedId
    private GameDeckId gameDeckId;

   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;*/

    @ManyToOne
    @JoinColumn(name = "Game_id", insertable = false, updatable = false)
    private Game game;
    @ManyToOne
    @JoinColumn(name = "Deck_id", insertable = false, updatable = false)
    private Deck deck;


    /**
     * Use to count the number of call to Deal for a deck
     */
    private Long numberOfCall;


    @PreRemove
    public void preventDeletion() {
        throw new PersistenceException("Cannot remove a Deck in the game");
    }

    public GameDeckId getGameDeckId() {
        return gameDeckId;
    }

    public void setGameDeckId(GameDeckId gameDeckId) {
        this.gameDeckId = gameDeckId;
    }

   /* public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public Long getNumberOfCall() {
        return numberOfCall;
    }

    public void setNumberOfCall(Long numberOfCall) {
        this.numberOfCall = numberOfCall;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDeck gameDeck = (GameDeck) o;
        return Objects.equals(gameDeckId, gameDeck.gameDeckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameDeckId);
    }
}
