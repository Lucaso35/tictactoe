/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.players;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.Board;
import tictactoe.Cell;
import tictactoe.Player;

/**
 * Annoying player, always plays at position 0,0
 * @author beh01
 */
public class beh01 implements Player{

    @Override
    public String getName() {
        return "beh01";
    }

    @Override
    public Point makeMove(Board board, Cell cell) {
        try {
            Thread.sleep(1000);
            return new Point(0,0);
        } catch (InterruptedException ex) {
            
        }
        return null;
    }
    
}
