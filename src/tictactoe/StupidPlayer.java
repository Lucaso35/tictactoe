/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author beh01
 */
public class StupidPlayer implements Player {

    Random random=new Random();
    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public Point makeMove(Board board, Cell cell) {
        for(;;) {
            int x=random.nextInt(board.getNumberOfRows());
            int y=random.nextInt(board.getNumberOfColumns());
            if (board.getCell(x, y)==null) return new Point(x,y);
        }
    }
    
}
