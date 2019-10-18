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
package tk.souriland.pazaak;

import javax.swing.SwingUtilities;
import tk.souriland.pazaak.domain.Player;
import tk.souriland.pazaak.domain.preset.DefaultDeck;
import tk.souriland.pazaak.game.PazaakGame;
import tk.souriland.pazaak.gui.UserInterface;

/**
 *
 * @author souris
 */
public class Main {
    public static void main(String[] args) {
        Player player1 = new Player(new DefaultDeck());
        Player player2 = new Player(new DefaultDeck());

        PazaakGame game = new PazaakGame(player1, player2);
        game.start();

        UserInterface ui = new UserInterface(game);
        SwingUtilities.invokeLater(ui);

        while (ui.getUpdatable() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("The table hasn't been set yet.");
            }
        }

        game.setUpdatable(ui.getUpdatable());
    }
}
