package com.logmein.deskcards.domain;


import com.logmein.deskcards.card.Card;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hand")
public class HandEntity implements Serializable {

    private static final long serialVersionUID = 4557343585146763024L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hand_id")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "handEntity", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<PlayerHand> players;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_to_act_id")
    private Player currentToAct;
    @ElementCollection(targetClass = Card.class)
    @JoinTable(name = "hand_deck", joinColumns = @JoinColumn(name = "hand_id"))
    @Column(name = "card", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Card> cardList;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<PlayerHand> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerHand> players) {
        this.players = players;
    }

    public Player getCurrentToAct() {
        return currentToAct;
    }

    public void setCurrentToAct(Player currentToAct) {
        this.currentToAct = currentToAct;
    }

    public List<Card> getCards() {
        return cardList;
    }

    public void setCards(List<Card> cards) {
        cardList = cards;
    }


    @Transient
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof HandEntity)) {
            return false;
        }
        return ((HandEntity) o).getId() == this.getId();
    }

    @Transient
    @Override
    public int hashCode() {
        return (int) this.getId();
    }
}
