package com.logmein.deskcards.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player")
public class Player implements Serializable {

    private static final long serialVersionUID = -1384636077333014255L;

    @JsonIgnore
    @Column(name = "player_id")
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @Column(unique = true)
    private String name;
    private boolean sittingOut;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSittingOut() {
        return sittingOut;
    }

    public void setSittingOut(boolean sittingOut) {
        this.sittingOut = sittingOut;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Player)) {
            return false;
        }
        Player p = (Player) o;
        if (this.getId() == null) {
            return this.getName().equals(p.getName());
        }
        return this.getId().equals(p.getId());
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return name.hashCode();
        }
        return id.hashCode();
    }
}
