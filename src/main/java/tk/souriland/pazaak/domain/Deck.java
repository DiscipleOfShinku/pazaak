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

import tk.souriland.pazaak.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author souris
 */
public abstract class Deck {
    private final List<Card> cards = new ArrayList<>();

    public void empty() {
        cards.clear();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card take() {
        if (cards.isEmpty()) {
            return null;
        }

        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }

    public abstract void ready();
}
