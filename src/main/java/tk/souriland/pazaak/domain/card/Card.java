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
package tk.souriland.pazaak.domain.card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tk.souriland.pazaak.gui.Updatable;

/**
 *
 * @author souris
 */
public abstract class Card implements ActionListener {
    private final CardType type;
    protected int value;

    protected Updatable updatable;

    public Card(CardType type, int value) {
        this.type = type;
        this.value = value;
    }

    public CardType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public boolean isToggleable() {
        return false;
    }

    public boolean isFlippable(boolean isLowFlip) {
        return isLowFlip ? value == 2 || value == 4
                         : value == 3 || value == 6;
    }

    public Card getFlippedCard() {
        return this;
    }

    public void doubleValue() {
        value *= 2;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
