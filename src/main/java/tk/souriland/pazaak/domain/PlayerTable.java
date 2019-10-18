/*
 * The MIT License
 *
 * Copyright 2019 souris.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package tk.souriland.pazaak.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import tk.souriland.pazaak.domain.card.Card;
import tk.souriland.pazaak.domain.card.CardType;
import tk.souriland.pazaak.domain.card.FlipCard;

/**
 *
 * @author souris
 */
public class PlayerTable {
    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return cards.stream()
                    .map(card -> card.getValue())
                    .reduce(0, Integer::sum);
    }

    public void put(Card card) {
        if (card.getType() == CardType.FLIP) {
            flipCards((FlipCard) card);
        } else if (card.getType() == CardType.DOUBLE) {
            doubleLastCard();
        }

        cards.add(card);
    }

    public boolean hasTiebreaker() {
        if (cards.isEmpty()) {
            return false;
        }

        Card lastCard = cards.get(cards.size() - 1);

        return lastCard.getType() == CardType.TIEBREAKER;
    }

    private void flipCards(FlipCard flipCard) {
        ListIterator<Card> iterator = cards.listIterator();

        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.isFlippable(flipCard.isLow())) {
                iterator.set(card.getFlippedCard());
            }
        }
    }

    private void doubleLastCard() {
        if (cards.isEmpty()) {
            return;
        }

        Card lastCard = cards.get(cards.size() - 1);
        lastCard.doubleValue();
    }
}
