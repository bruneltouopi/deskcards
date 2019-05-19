package com.logmein.deskcards.utils;

import com.logmein.deskcards.card.Card;

import java.util.Comparator;

/**
 * Compare Value of Card
 */
public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        int i =o1.getSuit().compareTo(o2.getSuit());
        if(i!=0)return i;
        return -(o1.getRank().getValue()-o2.getRank().getValue());
    }
}
