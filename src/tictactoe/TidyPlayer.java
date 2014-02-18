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
public class TidyPlayer implements Player{

    @Override
    public String getName() {
        return "TidyPlayer";
    }

    @Override
    public Point makeMove(Board board, Cell cell) {
           for (int i = 0; i < board.getNumberOfRows(); i++) {
            for (int j = 0; j < board.getNumberOfColumns(); j++) {
                if (board.getCell(i, j) == null) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }
    
}
