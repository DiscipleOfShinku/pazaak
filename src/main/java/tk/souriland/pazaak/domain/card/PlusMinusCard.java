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

/**
 *
 * @author souris
 */
public class PlusMinusCard extends Card {
    private boolean plus = true;

    public PlusMinusCard(int value) {
        super(CardType.PLUS_MINUS, value);
    }

    protected PlusMinusCard(CardType type, int value) {
        super(type, value);
    }

    @Override
    public boolean isToggleable() {
        return true;
    }

    public boolean isPlus() {
        return plus;
    }

    public void changeSign() {
        plus = !plus;
    }

    @Override
    public int getValue() {
        return plus ? super.getValue() : -super.getValue();
    }

    @Override
    public Card getFlippedCard() {
        changeSign();

        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Toggle")) {
            changeSign();
            updatable.update();
        }
    }
}
