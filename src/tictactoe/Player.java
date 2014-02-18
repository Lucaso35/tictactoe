/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Point;

/**
 *
 * @author beh01
 */
public interface Player {
    public String getName();
    public Point makeMove(Board board, Cell cell);
}
