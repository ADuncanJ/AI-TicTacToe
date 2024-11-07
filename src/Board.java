import java.util.*;

/*
   All comments are of sections of modified code
*/
class Point {
    int x, y;


    public Point(int x, int y) { 
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() { 
        return "[" + (x+1) + ", " + (y+1) + "]";
    }
}


class PointsAndScores {
    int score;
    Point point;

    PointsAndScores(int score, Point point) { 
        this.score = score;
        this.point = point;
    }
}


class Board { 
    List<Point> availablePoints;
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[5][5]; //2d Array extended to 5x5

    public Board() {
    }

    public boolean isGameOver() {
        return (hasXWon() || hasOWon() || getAvailablePoints().isEmpty());
    }

    public boolean hasXWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == board[3][3] && board[0][0] == board[4][4]&& board[0][0] == 1) || (board[0][4] == board[1][3] && board[0][4] == board[2][2] && board[0][4] == board[3][1] && board[0][4] == board[4][0]&& board[0][4] == 1)) {
            return  true;
        }// now checks the five long diagonals

        for (int i = 0; i < 5; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == board[i][3] && board[i][0] == board[i][4] && board[i][0] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == board[3][i] && board[0][i] == board[4][i] && board[0][i] == 1)) {
                return true;
            }
        } //now checks the 5 long rows and columns

        return false;
    }


    public boolean hasOWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == board[3][3] && board[0][0] == board[4][4]&& board[0][0] == 2) || (board[0][4] == board[1][3] && board[0][4] == board[2][2] && board[0][4] == board[3][1] && board[0][4] == board[4][0]&& board[0][4] == 2)) {
             return true;
        } // now checks the five long diagonals
        for (int i = 0; i < 5; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == board[i][3] && board[i][0] == board[i][4] && board[i][0] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == board[3][i] && board[0][i] == board[4][i] && board[0][i] == 2)) {
                return true;
            }
        } //now checks the 5 long rows and columns
        return false;
    }


    // method for evaluating state
    public int getStateScore(){
        int StateScore = 0; // initializes score to 0
        //loop counts the number of x and o in each row
        for (int i = 0; i < 5; i++) {//for loop checks each row
            int n = 0;// variable used to track number of x and o in row
            int m = 0;
            for (int j = 0; j < 5; j++){// then each column in a row
                if(board[i][j] == 1){// if there is an x ...
                    n += 1;// add 1 to n
                }
                else if(board[i][j] == 2){//if there is an o ...
                    m += 1;//add 1 to m
                }
            }
            //assigns scores based number of x and o in row
            if(n == 5) return Integer.MAX_VALUE;
            if(m == 5) return Integer.MIN_VALUE;
            else if(n == 4) StateScore += 10000;
            else if(n == 3) StateScore += 1000;
            else if(n == 2) StateScore += 100;
            else if(n == 1) StateScore += 10;
            if(m == 4) StateScore -= 10000;
            else if(m == 3) StateScore -= 1000;
            else if(m == 2) StateScore -= 100;
            else if(m == 1) StateScore -= 10;
        }
        //loop counts the number of x and o in each column
        for (int j = 0; j < 5; j++) {//for loop checks each column
            int n = 0;// variable used to track number of x and o in column
            int m = 0;
            for (int i = 0; i < 5; i++){// then each row in a column
                if(board[i][j] == 1){// if there is an x ...
                    n += 1;// add 1 to n
                }
                else if(board[i][j] == 2){//if there is an o ...
                    m += 1;//add 1 to m
                }
            }
            //assigns scores based number of x and o in row
            if(n == 5) return Integer.MAX_VALUE;
            if(m == 5) return Integer.MIN_VALUE;
            else if(n == 4) StateScore += 10000;
            else if(n == 3) StateScore += 1000;
            else if(n == 2) StateScore += 100;
            else if(n == 1) StateScore += 10;
            if(m == 4) StateScore -= 10000;
            else if(m == 3) StateScore -= 1000;
            else if(m == 2) StateScore -= 100;
            else if(m == 1) StateScore -= 10;
        }
        int n = 0;
        int m = 0;
        //loop checks top left to bottom right diagonal
        for (int i = 0; i < 5; i++) {
            if (board[i][i] == 1) {// if there is an x ...
                n += 1;// add 1 to n
            } else if (board[i][i] == 2) {//if there is an o ...
                m += 1;//add 1 to m
            }
        }
        //assigns scores based number of x and o in row
        if(n == 5) return Integer.MAX_VALUE;
        if(m == 5) return Integer.MIN_VALUE;
        else if(n == 4) StateScore += 10000;
        else if(n == 3) StateScore += 1000;
        else if(n == 2) StateScore += 100;
        else if(n == 1) StateScore += 10;
        if(m == 4) StateScore -= 10000;
        else if(m == 3) StateScore -= 1000;
        else if(m == 2) StateScore -= 100;
        else if(m == 1) StateScore -= 10;

        n = 0;
        m = 0;
        //loop checks top right to bottom left diagonal
        for (int i = 0; i < 5; i++) {
            if (board[i][4-i] == 1) {// if there is an x ...
                n += 1;// add 1 to n
            } else if (board[i][4-i] == 2) {//if there is an o ...
                m += 1;//add 1 to m
            }
        }
        //assigns scores based number of x and o in row
        if(n == 5) return Integer.MAX_VALUE;
        if(m == 5) return Integer.MIN_VALUE;
        else if(n == 4) StateScore += 10000;
        else if(n == 3) StateScore += 1000;
        else if(n == 2) StateScore += 100;
        else if(n == 1) StateScore += 10;
        if(m == 4) StateScore -= 10000;
        else if(m == 3) StateScore -= 1000;
        else if(m == 2) StateScore -= 100;
        else if(m == 1) StateScore -= 10;

        return StateScore;
    }


    public List<Point> getAvailablePoints() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public int getState(Point point){
    	return board[point.x][point.y];
    }

    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;   
    }


    public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
 		        if (board[i][j]==1)
                    System.out.print("X ");
                else if (board[i][j]==2)
                    System.out.print("O ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}
