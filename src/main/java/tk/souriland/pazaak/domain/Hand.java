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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tk.souriland.pazaak.domain.card.Card;

/**
 *
 * @author souris
 */
public class Hand implements ActionListener {
    private final Card[] cards = new Card[4];

    private final Player player;

    public Hand(Player player) {
        this.player = player;
    }

    public Card[] getCards() {
        return cards;
    }

    public void fill(SideDeck deck) {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.take();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!player.getGame().canPlayCard()) {
            return;
        }

        int cardIndex = -1;
        try {
            cardIndex = Integer.valueOf(e.getActionCommand());
        } catch (NumberFormatException nfe) {
            return;
        }

        if (cardIndex < 0 || cardIndex >= cards.length) {
            return;
        }

        Card card = cards[cardIndex];
        if (card == null) {
            return;
        }

        cards[cardIndex] = null;

        player.playCard(card);
    }
}
