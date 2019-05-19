package com.logmein.deskcards.service.impl;

import com.logmein.deskcards.card.Card;
import com.logmein.deskcards.card.Suit;
import com.logmein.deskcards.domain.Game;
import com.logmein.deskcards.domain.GameDeck;
import com.logmein.deskcards.domain.Player;
import com.logmein.deskcards.dto.PlayerRandDto;
import com.logmein.deskcards.repository.GameRepository;
import com.logmein.deskcards.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Game Service
 */
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository){
        this.gameRepository=gameRepository;
    }


    @Override
    public Game create(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void delete(Game game) {
            gameRepository.delete(game);
    }

    @Override
    public void addPlayer(Player player, Game game) {
        game.getPlayers().add(player);
        gameRepository.save(game);
    }

    @Override
    public void removePlayer(Player player, Game game) {
        game.getPlayers().remove(player);
        gameRepository.save(game);

    }

    @Override
    public List<PlayerRandDto> getRandOfPlayer(Game game) {
        return null;
    }

    @Override
    public Map<Suit, Integer> totalCardsPerSuitUndealt(Game game) {
        return null;
    }

    @Override
    public List<Card> getListCardsOfPlayer(GameDeck gameDeck, Player player) {
        return null;
    }
}
