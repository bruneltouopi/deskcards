package com.logmein.deskcards.dto;

import com.logmein.deskcards.domain.Player;
import com.logmein.deskcards.domain.PlayerHand;

/**
 * Player Rand based on their Hand
 */
public class PlayerRandDto implements Comparable<PlayerRandDto>{
    private Player player;
    private int totalValueCard;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTotalValueCard() {
        return totalValueCard;
    }

    public void setTotalValueCard(int totalValueCard) {
        this.totalValueCard = totalValueCard;
    }

    //Order Descending
    @Override
    public int compareTo(PlayerRandDto o) {
        return o.totalValueCard-this.totalValueCard;
    }
}
