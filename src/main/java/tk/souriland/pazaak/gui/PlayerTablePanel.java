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

import java.awt.GridLayout;
import java.awt.Label;
import java.util.List;
import javax.swing.JPanel;
import tk.souriland.pazaak.domain.PlayerTable;
import tk.souriland.pazaak.domain.card.Card;

/**
 *
 * @author souris
 */
public class PlayerTablePanel extends JPanel implements Updatable {
    private final PlayerTable playerTable;

    private final Label[] labels = new Label[9];

    public PlayerTablePanel(PlayerTable playerTable) {
        this.playerTable = playerTable;
    }

    public void createComponents() {
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < labels.length; i++) {
            Label label = new Label();
            labels[i] = label;
            add(label);
        }

        update();
    }

    @Override
    public void update() {
        List<Card> cards = playerTable.getCards();

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            labels[i].setText(String.valueOf(card.getValue()));
        }
    }
}
