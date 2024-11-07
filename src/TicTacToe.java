import java.util.*;

/*
   All comments are of sections of modified code
*/
public class TicTacToe {

    public static void main(String[] args) {
        AIplayer AI= new AIplayer();
        Board b = new Board();
        Point p = new Point(0, 0);
        
        b.displayBoard();

        System.out.println("Who makes first move? (1)Computer (2)User: ");
        int choice = b.scan.nextInt();
        if(choice == 1){
            AI.callMinimax(0, 1, b);
            for (PointsAndScores pas : AI.rootsChildrenScores) {
            	System.out.println("Point: " + pas.point + " Score: " + pas.score);
            }
            b.placeAMove(AI.returnBestMove(), 1);
            b.displayBoard();
        }
        
        while (!b.isGameOver()) { //If the game is not over yet
            System.out.println("Your move: line (1, 2, 3, 4 or 5) column (1, 2, 3, 4, 5)");//extended to 5x5
            Point userMove = new Point(b.scan.nextInt()-1, b.scan.nextInt()-1);
            while (b.getState(userMove)!=0) {
            	System.out.println("Invalid move. Make your move again: ");
            	userMove.x=b.scan.nextInt()-1; 
            	userMove.y=b.scan.nextInt()-1;
            }
            b.placeAMove(userMove, 2);
            b.displayBoard();
            
            if (b.isGameOver()) {
                break;
            } 
            
            AI.callMinimax(0, 1, b);
            for (PointsAndScores pas : AI.rootsChildrenScores) {
                System.out.println("Point: " + pas.point + " Score: " + pas.score);
            }
            b.placeAMove(AI.returnBestMove(), 1);
            b.displayBoard();
        }
        
        if (b.hasXWon()) {
            System.out.println("Unfortunately, you lost!");
        } else if (b.hasOWon()) {
            System.out.println("You win!");
        } else {
            System.out.println("It's a draw!");
        }
    }    
}