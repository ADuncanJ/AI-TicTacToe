import java.util.*;

/*
   All comments are of sections of modified code
*/
class AIplayer { 
    List<Point> availablePoints;
    List<PointsAndScores> rootsChildrenScores;
    Board b = new Board();
    
    //constructor
    public AIplayer() {
    }
    

    public Point returnBestMove() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < rootsChildrenScores.size(); ++i) { 
            if (MAX < rootsChildrenScores.get(i).score) {
                MAX = rootsChildrenScores.get(i).score;
                best = i;
            }
        }
        return rootsChildrenScores.get(best).point;
    }


    public int returnMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public int returnMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }
 

    public void callMinimax(int depth, int turn, Board b){
        rootsChildrenScores = new ArrayList<>(); 
        minimax(depth, turn, Integer.MIN_VALUE, Integer.MAX_VALUE, b);
    }

    public int minimax(int depth, int turn, int alpha, int beta, Board b) {
        if (b.hasXWon()) return Integer.MAX_VALUE; //now returns maximum integer value
        if (b.hasOWon()) return Integer.MIN_VALUE; //now returns minimum integer value
        if (depth > 6) return b.getStateScore(); //returns the value of the game state
        List<Point> pointsAvailable = b.getAvailablePoints();
        if (pointsAvailable.isEmpty()) return 0;

        List<Integer> scores = new ArrayList<>();
        
        int temp;
        if (turn ==1) temp = Integer.MIN_VALUE;
        else temp = Integer.MAX_VALUE;
            
        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);

            if (turn == 1) {
                b.placeAMove(point, 1);
                int currentScore = minimax(depth + 1, 2, alpha, beta, b);
                scores.add(currentScore);
                temp = Math.max(temp, currentScore);
                alpha = Math.max(alpha, temp);
                if (depth == 0)
                    rootsChildrenScores.add(new PointsAndScores(currentScore, point));
            } else if (turn == 2) {
                b.placeAMove(point, 2);
                int currentScore = minimax(depth + 1, 1, alpha, beta, b);
                scores.add(currentScore);
                temp = Math.min(temp, currentScore);
                beta = Math.min(beta, temp);
            }
            b.placeAMove(point, 0);
            if (alpha >= beta) {
                temp = pointsAvailable.size()-i-1;
                System.out.println("Number of nodes that have not been evaluated here : "+temp);
                break; 
            }
        }
        return turn == 1 ? returnMax(scores) : returnMin(scores);
    }    
}
