package com.logmein.deskcards.service;

import com.logmein.deskcards.card.Card;
import com.logmein.deskcards.domain.Player;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Player Service
 */

public interface PlayerService {
    List<Card> getListOfCard(Player player);

}
