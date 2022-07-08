import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Game {
   Board board ;
   Dice dice ;
   Player winner ;
   Deque<Player> playerlist = new LinkedList<>();

   public Game(){
       initializeGame();
   }

   public void initializeGame(){
       board = new Board(10,5,5) ;
       dice = new Dice(1);
       winner = null ;
       addPlayer();
   }

   public void addPlayer(){
       Player p1 = new Player("p1",0);
       Player p2 = new Player("p2",0);
       playerlist.add(p1);
       playerlist.add(p2);
   }

   public void startGame(){
       while (winner == null){
           Player currentPlayer = getCurrentPlayer();
           System.out.println("Player playing now with id "+ currentPlayer.getId() + "and position "+currentPlayer.getCurrentPosition());
           int diceSum =dice.rollDice() ; // we chose 1
           int playerNewPosition = currentPlayer.getCurrentPosition() + diceSum;
           int playerEndPosition = getPlayerEndPosition(playerNewPosition);
           System.out.println("Player "+currentPlayer.id+ " and new position is "+ playerEndPosition);
           if(playerEndPosition >= board.cells.length * board.cells.length -1){
               winner = currentPlayer;
           }
       }

       System.out.println("Winner is "+ winner.id);

   }

   public int getPlayerEndPosition(int PlayerNewPosition){

       if(PlayerNewPosition > board.cells.length * board.cells.length-1 ){
           return PlayerNewPosition;
       }


       Cell cell = board.getCell(PlayerNewPosition) ;// get cell row and column
       if(cell.jump !=null && cell.jump.start == PlayerNewPosition){
           return cell.jump.end;
       }

       return PlayerNewPosition ;
   }

   Player getCurrentPlayer(){
       Player playingNow  = playerlist.removeFirst();
       playerlist.addLast(playingNow);
       return playingNow;

   }
}
