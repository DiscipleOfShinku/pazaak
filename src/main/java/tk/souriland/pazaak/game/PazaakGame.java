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
package tk.souriland.pazaak.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import tk.souriland.pazaak.domain.MainDeck;
import tk.souriland.pazaak.domain.Player;
import tk.souriland.pazaak.gui.Updatable;

/**
 *
 * @author souris
 */
public class PazaakGame implements ActionListener {
    private final MainDeck mainDeck = new MainDeck();

    private final Player player1;
    private final Player player2;

    private boolean inProgress = true;

    private boolean isFirstPlayerTurn = true;
    private boolean cardPlayed = false;

    private boolean isFirstPlayerStands = false;
    private boolean isSecondPlayerStands = false;

    private final Random randomizer = new Random();

    private Updatable updatable;

    public PazaakGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getFirstPlayer() {
        return player1;
    }

    public Player getSecondPlayer() {
        return player2;
    }

    private Player getActivePlayer() {
        return isFirstPlayerTurn ? player1 : player2;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public boolean canPlayCard() {
        return !cardPlayed;
    }

    public void start() {
        player1.setGame(this);
        player2.setGame(this);

        player1.ready();
        player2.ready();
        mainDeck.ready();

        chooseTurnsOrder();
        putCardFromMainDeck();
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public void playCard() {
        cardPlayed = true;
        updatable.update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Stand")) {
            standPlayer();
        } else if (e.getActionCommand().equals("End Turn")) {
            endPlayerTurn();
        }

        if (hasRoundResult()) {
            endRound();
        } else {
            putCardFromMainDeck();
        }

        updatable.update();
    }

    private void chooseTurnsOrder() {
        putCardFromMainDeck();
        endPlayerTurn();
        putCardFromMainDeck();

        if (player1.getScore() == player2.getScore()) {
            isFirstPlayerTurn = randomizer.nextBoolean();
        } else {
            isFirstPlayerTurn = player1.getScore() > player2.getScore();
        }
    }

    private void putCardFromMainDeck() {
        getActivePlayer().getTable().put(mainDeck.take());

        if (getActivePlayer().getScore() == 20) {
            actionPerformed(new ActionEvent(this, 0, "Stand"));
        }
    }

    private void standPlayer() {
        if (isFirstPlayerTurn) {
            isFirstPlayerStands = true;
        } else {
            isSecondPlayerStands = true;
        }

        endPlayerTurn();
    }

    private void endPlayerTurn() {
        cardPlayed = false;

        if (isFirstPlayerTurn && isSecondPlayerStands) {
            return;
        }

        if (!isFirstPlayerTurn && isFirstPlayerStands) {
            return;
        }

        isFirstPlayerTurn = !isFirstPlayerTurn;
    }

    private boolean hasRoundResult() {
        if (isFirstPlayerStands && isSecondPlayerStands) {
            return true;
        }

        if (player1.getScore() > 20 || player2.getScore() > 20) {
            return true;
        }

        if (player1.getScore() == 20 && player2.getScore() == 20) {
            return true;
        }

        return false;
    }

    private void endRound() {
        inProgress = false;
    }
}
