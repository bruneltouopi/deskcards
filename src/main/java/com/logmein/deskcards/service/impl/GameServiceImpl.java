package com.logmein.deskcards.service.impl;

import com.logmein.deskcards.card.Card;
import com.logmein.deskcards.card.Suit;
import com.logmein.deskcards.domain.*;
import com.logmein.deskcards.dto.PlayerRandDto;
import com.logmein.deskcards.repository.GameRepository;
import com.logmein.deskcards.repository.PlayerHandRepository;
import com.logmein.deskcards.repository.PlayerRepository;
import com.logmein.deskcards.service.GameService;
import com.logmein.deskcards.utils.CardComparator;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

/**
 * Game Service
 */
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final PlayerHandRepository playerHandRepository;



    public GameServiceImpl(GameRepository gameRepository,PlayerRepository playerRepository,
                           PlayerHandRepository playerHandRepository){
        this.gameRepository=gameRepository;
        this.playerRepository=playerRepository;
        this.playerHandRepository=playerHandRepository;
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
    public Player addPlayer(Player player, Game game) {
        Player p=playerRepository.save(player);
        game.getPlayers().add(p);
        gameRepository.save(game);
        return p;

    }

    @Override
    public void removePlayer(Player player, Game game) {
        game.getPlayers().remove(player);
        gameRepository.save(game);

    }

    @Override
    public Optional<PlayerHand> dealCardsToPlayer(GameDeck gameDeck,Player player) {
        Optional<Player> p=gameDeck.getGame().getCurrentHand().getPlayers().stream()
                .map(playerHand -> playerHand.getPlayer())
                .filter(player1 -> player1.equals(player)).findFirst();
        if (p.isPresent()){
            PlayerHand hand=new PlayerHand();
            hand.setCard1(gameDeck.getDeck().dealCard());
            hand.setCard2(gameDeck.getDeck().dealCard());
            hand.setPlayer(p.get());
            hand.setHandEntity(gameDeck.getGame().getCurrentHand());
           return Optional.of(playerHandRepository.save(hand));
        }
        return Optional.empty();


    }

    @Override
    public List<PlayerRandDto> getRandOfPlayer(Game game) {
        HandEntity handEntity=game.getCurrentHand();
        Set<PlayerHand> playerHands= handEntity.getPlayers();
        List<PlayerRandDto> playerRandDtos=new ArrayList<>();
        playerHands.forEach(playerHand -> {
            PlayerRandDto playerRandDto=new PlayerRandDto();
            playerRandDto.setPlayer(playerHand.getPlayer());
            playerRandDto.setTotalValueCard(playerHand.valueOfCard());
            playerRandDtos.add(playerRandDto);
        });
        Collections.sort(playerRandDtos);
        return playerRandDtos;
    }

    @Override
    public List<Card> getListOfCard(Player player) {
        List<PlayerHand> playerHands=playerHandRepository.getPlayerHandByPlayer(player.getId());
        List<Card> listCards=new ArrayList<>();
        playerHands.stream().forEach(playerHand -> {
            listCards.add(playerHand.getCard1());
            listCards.add(playerHand.getCard2());
        });

        return listCards;
    }

    @Override
    public Map<Suit, Integer> totalCardsPerSuitUndealt(GameDeck gameDeck) {
        List<Card> listCards=gameDeck.getDeck().exportDeck();
        Map<Suit,Integer> mapSuits=new HashMap<>();
        if (!listCards.isEmpty()){
            listCards.stream().forEach(card -> {
                mapSuits.put(card.getSuit(),mapSuits.get(card.getSuit())+1);
            });
        }
        return mapSuits;

    }

    @Override
    public List<Card> getListOfCardRemainding(GameDeck gameDeck) {
        List<Card> listCards=gameDeck.getDeck().exportDeck();
         Collections.sort(listCards, new CardComparator());
         return listCards;
    }
}
