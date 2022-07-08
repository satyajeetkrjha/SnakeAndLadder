

import java.util.concurrent.ThreadLocalRandom;
public class Board {

    Cell[][] cells ;
    Board(int size ,int snakesCount ,int ladderCount){
        initilizeCells(size);
        addSnakeAndLadders(snakesCount,ladderCount);
    }

    private void initilizeCells(int size){
       cells = new Cell[size][size];
       for(int i =0 ;i< size;i++){
           for(int j =0 ;j< size;j++){
               Cell obj = new Cell();
               cells[i][j] = obj;
           }
       }
    }

    private void addSnakeAndLadders(int snakesCount ,int ladderCount){

        // we add snakes
        while (snakesCount>0){
            int snakeHead = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(snakeHead<=snakeTail){
                 continue;
            }

            Jump snakeobj = new Jump();
            snakeobj.setStart(snakeHead);
            snakeobj.setEnd(snakeTail);

            Cell cell = getCell(snakeHead);
            cell.jump= snakeobj;
            snakesCount --;
        }


        while(ladderCount > 0) {
            int ladderStart = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            int ladderEnd = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(ladderStart >= ladderEnd) {
                continue;
            }

            Jump ladderObj = new Jump();
            ladderObj.setStart(ladderStart);
            ladderObj.setEnd(ladderEnd);

            Cell cell = getCell(ladderStart);
            cell.jump = ladderObj;

            ladderCount--;
        }

    }



    Cell getCell(int snakeHead){
        int row = snakeHead /cells.length;
        int column = snakeHead %  cells.length;
        return cells[row][column];
    }

}
