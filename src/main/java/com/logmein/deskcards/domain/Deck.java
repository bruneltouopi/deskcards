package com.logmein.deskcards.domain;

import com.logmein.deskcards.card.Card;
import com.logmein.deskcards.card.Rank;
import com.logmein.deskcards.card.Suit;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Standard deck of cards
 */
@Entity
public class Deck implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@Enumerated
	@ElementCollection(targetClass = Card.class)
	private List<Card> cards;

	/**
	 * Construct a standard shuffled playing card deck.
	 */
	public Deck(){
		this(true);
	}
	

	public Deck(boolean shuffle){
		initDeck();
		if(shuffle){
			shuffleDeck();
		}
	}

	/**
	 * Initialize a deck using a pre existing list of cards
	 * @param cards List of cards, assumed correctly shuffled
	 */
	public Deck(List<Card> cards){
		this.cards = cards;
	}
	
	public void initDeck(){
		cards = new LinkedList<Card>();
		cards.addAll(Arrays.asList(Card.values()));
	}
	
	public void shuffleDeck(){
		Collections.shuffle(cards);
	}
	
	/**
	 * Returns the top card from the deck.  Removes the card from the deck.
	 * @return {@link Card}
	 */
	public Card dealCard(){
		return cards.remove(0);
	}
	
	/**
	 * Get the cards in the deck in the form of a list
	 * @return
	 */
	public List<Card> exportDeck(){
		return cards;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
