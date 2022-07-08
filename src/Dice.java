


// we can have more than one dice
import java.util.concurrent.ThreadLocalRandom;
public class Dice {

    private int diceCount;
    private int min = 1;
    private int max = 6;
    public Dice(int diceCount){
        this.diceCount = diceCount;
    }

    public int rollDice(){
        int totalSum = 0 ;
        int usedDices = diceCount;
        while(usedDices>0){
            totalSum += ThreadLocalRandom.current().nextInt(min,max+1);
            usedDices--;
        }
        return totalSum;
    }

}
