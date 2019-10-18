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
import tk.souriland.pazaak.domain.card.Card;
import tk.souriland.pazaak.game.PazaakGame;

/**
 *
 * @author souris
 */
public class Player {
    private final SideDeck deck;
    private final Hand hand = new Hand(this);
    private final PlayerTable table = new PlayerTable();

    private PazaakGame game;

    public Player(SideDeck deck) {
        this.deck = deck;
    }

    public void setGame(PazaakGame game) {
        this.game = game;
    }

    public int getScore() {
        return table.getScore();
    }

    public void ready() {
        deck.ready();
        hand.fill(deck);
    }

    public Hand getHand() {
        return hand;
    }

    public PlayerTable getTable() {
        return table;
    }

    public PazaakGame getGame() {
        return game;
    }

    public void playCard(Card card) {
        table.put(card);
        game.playCard();

        if (getScore() == 20) {
            game.actionPerformed(new ActionEvent(card, 0, "Stand"));
        }
    }
}
