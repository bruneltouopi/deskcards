package com.logmein.deskcards.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "game")
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private Set<Player> players;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "current_hand_id")
    private HandEntity currentHand;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }


    public HandEntity getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(HandEntity currentHand) {
        this.currentHand = currentHand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
