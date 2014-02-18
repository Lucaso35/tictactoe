package tictactoe;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Lucaso
 */
public class chl159 implements Player{

    @Override
    public String getName() {
        return "chl159";
    }

    private Board board;
    private Cell myCell, enemyCell;
    private ArrayList<StavList> pozitions;
    private ArrayList<StavPoint> possibleProblem;

    @Override
    public Point makeMove(Board board, Cell cell) {
        this.board = board;
        myCell = cell;

        if(myCell == Cell.CIRCLE) enemyCell = Cell.CROSS;
        else enemyCell = Cell.CIRCLE;

        pozitions = new ArrayList<StavList>();
        possibleProblem = new ArrayList<StavPoint>();
        Random r=new Random();
        Scan();
        if(pozitions.isEmpty() ){
        return new Point(board.getNumberOfRows() / 2, board.getNumberOfColumns() / 2);
        }
        for(int i=0;i<pozitions.size();i++){
            if(pozitions.get(i).clean()) i--;
        }
        if(pozitions.isEmpty()){
            for (int i = 0; i < board.getNumberOfRows(); i++) {
                for (int j = 0; j < board.getNumberOfColumns(); j++) {
                    if (board.getCell(i, j) == null) {
                        return new Point(i, j);
                    }
                }
            }
        }

        for(int i=0;i<pozitions.size();i++){
            pozitions.get(i).setImportance();
        }
        
        ArrayList<Integer> poleIndexu = new ArrayList<Integer>();
        int max=-1;
        for(int i=0;i<pozitions.size();i++){
            if(pozitions.get(i).importance > max){
            max = pozitions.get(i).importance;
            poleIndexu.clear();
            poleIndexu.add(i);
            continue;
            }
            if(pozitions.get(i).importance == max){
            poleIndexu.add(i);
            }
        }

        if(max<70){
            boolean mamPodel = false;
            int podelIndex = 0;
            for(int i=0; i<possibleProblem.size();i++){
                if(possibleProblem.get(i).mine == 1){
                    mamPodel = true;
                    podelIndex = i;
                }
            }
            if(mamPodel)
            {
                return new Point(possibleProblem.get(podelIndex).row, possibleProblem.get(podelIndex).column);
            }
                else if(!possibleProblem.isEmpty())
            {
                podelIndex = r.nextInt(possibleProblem.size());
                return new Point(possibleProblem.get(podelIndex).row, possibleProblem.get(podelIndex).column);
            }
        }

        int index = r.nextInt(poleIndexu.size());
        for(StavPoint p: pozitions.get(poleIndexu.get(index)).list){
            if(p.stav == 0) return new Point(p.row, p.column);
        }


        return new Point(r.nextInt(board.getNumberOfRows()), r.nextInt(board.getNumberOfColumns()));
    }

    private void Scan() {
        for(int row=0; row<board.getNumberOfRows(); row++){
            for(int column=0; column<board.getNumberOfColumns(); column++){
                if(board.getCell(row, column)!=null){
                    scanPoint(row, column);
                } else{
                    scanPointNull(row, column);

                }
            }
        }
    }

    private void scanPoint(int row, int column) {
        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                if(!(i==0 && j==0)) scanLine(row, column, i, j);
            }
        }
    }
    private boolean scanPointNull(int row, int column) {
        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                if(!(i==0 && j==0)){
                    scanLineNull(row, column, i, j);
                }
            }
        }
        return false;
    }

    private void scanLineNull(int row, int column, int rowMove, int columnMove){
        ArrayList<StavPoint> s = new ArrayList<StavPoint>();
        s.add(new StavPoint(row+1*rowMove, column+1*columnMove));
        s.add(new StavPoint(row+2*rowMove, column+2*columnMove));
        s.add(new StavPoint(row-1*rowMove, column-1*columnMove));
        s.add(new StavPoint(row-2*rowMove, column-2*columnMove));
        int enemy = 0;
        int my = 0;
        int stav= 0;
        for(int i=0; i<s.size();i++){
            if(s.get(i).stav == -2) return;
            if(s.get(i).stav == -1){
            enemy++;
            stav = -1;
            }
            if(s.get(i).stav == 1){
            my++;
            stav = 1;
            }
        }
        if(enemy*my != 0)return;

        if(enemy > 1 || my > 1)
        {
          for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                if((i==0 && j==0)) continue;
                if((i==rowMove && j==columnMove)) continue;
                if((i==-1*rowMove && j==-1*columnMove)) continue;
                if(findLine(row, column, i, j, stav)){
                       if( stav == 1 ){
                        possibleProblem.add(new StavPoint(row, column, 1));
                       }else{
                        possibleProblem.add(new StavPoint(row, column, -1));
                       }
                    }

            }
        }

        }
    }

    private boolean findLine(int row, int column, int rowMove, int columnMove, int type){
        ArrayList<StavPoint> s = new ArrayList<StavPoint>();
        s.add(new StavPoint(row+1*rowMove, column+1*columnMove));
        s.add(new StavPoint(row+2*rowMove, column+2*columnMove));
        s.add(new StavPoint(row-1*rowMove, column-1*columnMove));
        s.add(new StavPoint(row-2*rowMove, column-2*columnMove));
        int count = 0;
        for(int i=0; i<s.size();i++){
            if(s.get(i).stav == -2) return false;
            if(s.get(i).stav == -1*type ) return false;
            if(s.get(i).stav == type){
                count++;
            }
        }
        if(count > 1 )
        {
           return true;
        }
        return false;
    }


    private void scanLine(int row, int column, int rowMove, int columnMove){
        StavList list = new StavList();
        for(int i=0; i<5; i++){
            list.add(new StavPoint(row+i*rowMove, column+i*columnMove));
        }
        pozitions.add(list);
    }

    private boolean IsInBoard(int row, int column){
        if(
                row < board.getNumberOfRows() &&
                row >= 0 &&
                column < board.getNumberOfColumns() &&
                column >= 0
                )
            return true;
        return false;
    }

    private class StavList{

        private int countMy, countEnemy, countEmpty, importance;
        private ArrayList<StavPoint> list;

        private StavList() {
            countMy = 0;
            countEnemy = 0;
            countEmpty = 0;
            importance = 0;
            list= new ArrayList<StavPoint>();
        }

        private void add(StavPoint stavPoint) {
            list.add(stavPoint);
            switch(stavPoint.stav){
                case 0: countEmpty++; break;
                case 1: countMy++; break;
                case -1: countEnemy++; break;
            }
        }

        private void setImportance(){
        importance = 0;
        importance += checkLeftCorner(countEnemy, list);
        boolean closed = false;
        if(importance == -3) closed = true;

        if(countMy == 0){
            switch(countEnemy){
                case 1: importance += 10; break;
                case 2: importance += 30; importance += checkRightImportance(countEnemy, list, closed);break;
                case 3: importance += 70; importance += checkRightImportance(countEnemy, list, closed);break;
                case 4: importance += 90; break;
            }
        }
        if(countEnemy == 0){
            switch(countMy){
                case 1: importance += 20; break;
                case 2: importance += 40; importance += checkRightImportance(countEnemy, list, closed);break;
                case 3: importance += 80; importance += checkRightImportance(countEnemy, list, closed);break;
                case 4: importance += 100; break;
            }
        }

        }

        private int checkLeftCorner(int count, ArrayList<StavPoint> s ){
            // Zjisteni smeru
            int index = 0;
            for(int i=1; i< s.size(); i++){
                if(s.get(i).stav!=0){
                index = i;
                break;
                }
                }
             int x = s.get(0).row - s.get(index).row;
             int y = s.get(0).column - s.get(index).column;
             if(x>=0){
                if(x==0){
                    x=0;
                } else{
                    x=1;
                }
             }else{
                x=-1;
             }
             if(y>=0){
                if(y==0){
                    y=0;
                } else{
                    y=1;
                }
             }else{
                y=-1;
             }

             //smer v promennych x a y;
             StavPoint point = new StavPoint(s.get(0).row+x, s.get(0).column+y);
             if(x==0 && y==0) return 0;
             if(point.stav == 0) return 0;
             else return -3;


        }

        private int checkRightImportance(int count, ArrayList<StavPoint> s, boolean closed){
            //přehození n-tic 10011 na 11001
        if(count == 3 && s.get(1).stav == 0 && s.get(2).stav == 0){
        StavPoint pom;
        pom = s.get(3);
        s.set(3, s.get(1));
        s.set(1, pom);
        }

        if(count == 2){
            if(s.get(1).stav == 0 && s.get(2).stav == 0 ){
            return -3;
            }else if(s.get(1).stav == 0 && s.get(2).stav != 0){
            return 3;
            } else return 0;

        }
        if(count == 3 && s.get(1).stav != 0 && s.get(2).stav != 0 && closed) return -40;
        if(count == 3){
            if(s.get(2).stav == 0 && s.get(3).stav == 0){
                return -3;
            }else if(s.get(1).stav != 0 && s.get(2).stav == 0 || s.get(1).stav == 0 && s.get(2).stav != 0) return 3;
            else if(s.get(1).stav == 0 && s.get(3).stav == 0) return -2;
        }


        return count;
        }

        private boolean clean() {
            if(countEnemy>0 && countMy>0)
                    return pozitions.remove(this);
            for(StavPoint p:list){
                if(p.stav == -2){
                    return pozitions.remove(this);
                }
            }
            return false;
        }
    }

    private class StavPoint {
        private int row;
        private int column;
        private int stav;
        private int mine;
        /*
         * 0 = volno
         * 1 = vlastni
         * -1 = cizi
         * -2 = mimo
         */

        private StavPoint(int row, int column) {
            this.column = column;
            this.row = row;
            if(IsInBoard(row, column)){
                Cell c = board.getCell(row, column);
                if(c == null) stav = 0;
                if(c == myCell) stav = 1;
                if(c == enemyCell) stav = -1;
            }else
                    stav = -2;
        }

        private StavPoint(int row, int column, int mine) {
            this.column = column;
            this.row = row;
            this.mine = mine;
            if(IsInBoard(row, column)){
                Cell c = board.getCell(row, column);
                if(c == null) stav = 0;
                if(c == myCell) stav = 1;
                if(c == enemyCell) stav = -1;
            }else
                    stav = -2;
        }
    }
}
