/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author beh01
 */
public class Board {

    private Cell[][] board;

    public Board(int rows, int columns) {
        board = new Cell[rows][columns];
    }
    public void setCell(Cell cell, int row, int column){
        board[row][column]=cell;
    }
    public Cell getCell(int row, int column) {
        return board[row][column];
    }
    public int getNumberOfRows() {
        return  board.length;
    }
    public int getNumberOfColumns() {
        return board[0].length;
    }
}

