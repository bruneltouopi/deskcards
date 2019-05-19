package com.logmein.deskcards.domain;


import com.logmein.deskcards.card.Card;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player_hand")
public class PlayerHand implements Serializable, Comparable<PlayerHand> {

    private static final long serialVersionUID = -5499451283824674842L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_hand_id")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne
    @JoinColumn(name = "hand_id")
    private HandEntity handEntity;

    @Column(name = "card1")
    private Card card1;

    @Column(name = "card2")
    private Card card2;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public HandEntity getHandEntity() {
        return handEntity;
    }

    public void setHandEntity(HandEntity hand) {
        this.handEntity = hand;
    }

    @Enumerated(EnumType.STRING)
    protected Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    @Column(name = "card2")
    @Enumerated(EnumType.STRING)
    protected Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }


    /**
     * Order the PlayerHand by point of card
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(PlayerHand o) {

        return (this.getCard1().getRank().getValue() + this.getCard2().getRank().getValue()) -
                (o.getCard1().getRank().getValue() + o.getCard2().getRank().getValue());
    }
}
