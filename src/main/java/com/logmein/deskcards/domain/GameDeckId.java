package com.logmein.deskcards.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class GameDeckId implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "Game_id")
    private Long gameId;

    @Column(name = "Deck_id")
    private Long deckId;

    public GameDeckId(Long gameId, Long deckId) {
        this.gameId = gameId;
        this.deckId = deckId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDeckId that = (GameDeckId) o;
        return Objects.equals(gameId, that.gameId) &&
                Objects.equals(deckId, that.deckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, deckId);
    }
}
