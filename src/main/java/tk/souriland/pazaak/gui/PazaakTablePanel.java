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
package tk.souriland.pazaak.gui;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tk.souriland.pazaak.domain.Hand;
import tk.souriland.pazaak.domain.PlayerTable;
import tk.souriland.pazaak.game.PazaakGame;

/**
 *
 * @author souris
 */
public class PazaakTablePanel extends JPanel implements Updatable {
    private final PazaakGame game;

    private final JFrame frame;

    private Label playerScore1;
    private Label playerScore2;

    private PlayerTablePanel playerTablePanel1;
    private PlayerTablePanel playerTablePanel2;

    private PlayerHandPanel playerHandPanel1;
    private PlayerHandPanel playerHandPanel2;

    private Button standButton;
    private Button endTurnButton;

    public PazaakTablePanel(PazaakGame game, JFrame frame) {
        this.game = game;
        this.frame = frame;
    }

    public void createComponents() {
        setLayout(new GridLayout(4, 2));

        createScoreIndicators();
        createPlayerTablePanels();
        createPlayerHandPanels();

        standButton = new Button("Stand");
        standButton.addActionListener(game);
        add(standButton);

        endTurnButton = new Button("End Turn");
        endTurnButton.addActionListener(game);
        add(endTurnButton);
    }

    @Override
    public void update() {
        playerScore1.setText(String.valueOf(game.getFirstPlayer().getScore()));
        playerScore2.setText(String.valueOf(game.getSecondPlayer().getScore()));

        playerTablePanel1.update();
        playerTablePanel2.update();

        playerHandPanel1.update();
        playerHandPanel2.update();

        if (!game.isInProgress()) {
            exitGame();
        }
    }

    private void createScoreIndicators() {
        playerScore1 = new Label();
        playerScore1.setText(String.valueOf(game.getFirstPlayer().getScore()));
        add(playerScore1);

        playerScore2 = new Label();
        playerScore2.setText(String.valueOf(game.getSecondPlayer().getScore()));
        add(playerScore2);
    }

    private void createPlayerTablePanels() {
        PlayerTable playerTable = game.getFirstPlayer().getTable();
        playerTablePanel1 = new PlayerTablePanel(playerTable);
        playerTablePanel1.createComponents();
        add(playerTablePanel1);

        playerTable = game.getSecondPlayer().getTable();
        playerTablePanel2 = new PlayerTablePanel(playerTable);
        playerTablePanel2.createComponents();
        add(playerTablePanel2);
    }

    private void createPlayerHandPanels() {
        Hand hand = game.getFirstPlayer().getHand();
        playerHandPanel1 = new PlayerHandPanel(hand);
        playerHandPanel1.createComponents();
        add(playerHandPanel1);

        hand = game.getSecondPlayer().getHand();
        playerHandPanel2 = new PlayerHandPanel(hand);
        playerHandPanel2.createComponents();
        add(playerHandPanel2);
    }

    private void exitGame() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println("Exiting...");
        }

        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
