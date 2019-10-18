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
import javax.swing.JPanel;
import tk.souriland.pazaak.domain.Hand;
import tk.souriland.pazaak.domain.card.Card;

/**
 *
 * @author souris
 */
public class PlayerHandPanel extends JPanel implements Updatable {
    private final Hand hand;

    private final Button[] buttons = new Button[4];

    public PlayerHandPanel(Hand hand) {
        this.hand = hand;
    }

    public void createComponents() {
        setLayout(new GridLayout(2, 4));

        for (Card card : hand.getCards()) {
            CardPanel cardPanel = new CardPanel(card);
            cardPanel.createComponents();
            add(cardPanel);
        }

        for (int i = 0; i < hand.getCards().length; i++) {
            Button playButton = new Button("Play");
            playButton.setActionCommand(String.valueOf(i));
            playButton.addActionListener(hand);

            add(playButton);
            buttons[i] = playButton;
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < hand.getCards().length; i++) {
            if (hand.getCards()[i] == null) {
                CardPanel cardPanel = new CardPanel(null);
                this.remove(i);
                this.add(cardPanel, i);

                buttons[i].setEnabled(false);
            }
        }
    }
}
