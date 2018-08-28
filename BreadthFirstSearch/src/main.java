import java.util.*;

class Position {
    int x;
    int y;

    Position() {

    }

    Position(int xd, int yd) {
        x = xd;
        y = yd;
    }

    @Override
    public String toString() {
        return "(" + String.valueOf(x) + "," + String.valueOf(y) +  ")";
    }



    public void down()
    {
        y = y+1;
    }

    public void up()
    {
        y = y-1;
    }

    public void right()
    {
        x = x+1;
    }

    public void left()
    {
        x = x-1;
    }
}

class Shape {
    int x = 0;
    int y = 0;
    Position relativePosition = new Position(x, y);

    ArrayList<Position> innerPieceList = new ArrayList<Position>();

}

//this is a node in the graph
class GameState {

    GameState prev;

    Position[] state;

    GameState()
    {

    }

    GameState(GameState _prev) {
        prev = _prev;
        state = new Position[11];
    }

    public void setState(Position[] currentState)
    {
        state = currentState;
    }

    public String printState()
    {
        String tempString = "";
        for (int i = 0; i < state.length; i++) {
            tempString = tempString + state[i].toString();
        }
        return tempString;
    }
}

class Board
{
    static boolean board[][] = new boolean[10][10];

    public void initializeBoard()
    {
        //place in true fields
        board[3][1] = true;
        board[4][1] = true;
        board[3][2] = true;
        board[4][2] = true;
        board[2][2] = true;
        board[3][3] = true;
        board[7][2] = true;
        board[7][3] = true;
        board[7][4] = true;
        board[8][3] = true;
        board[8][4] = true;
        board[2][7] = true;
    }

    public void printBoard()
    {
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j <10 ; j++) {
                if(board[j][i])
                System.out.print("O ");
                else
                {
                    System.out.print("# ");
                }
            }
            System.out.println("");
        }
    }
}

class GameStateComparator implements Comparator<GameState> {

    public int compare(GameState a, GameState b)
    {
        for(int i = 0; i < a.state.length; i ++)
        {
            if(a.state[i].x < b.state[i].x || a.state[i].y < b.state[i].y)
                return -1;
            else if(a.state[i].x > b.state[i].x || a.state[i].y > b.state[i].y)
                return 1;
        }
        return 0;

    }

}

class Main {

    static boolean applyOffset(Shape shape, GameState gameState)
    {
        Position position = shape.innerPieceList.get(0);
        if(position.toString().equals(gameState.state[0].toString()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String args[]) {

        final String initialString = "(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)";

        //create initial position
        ArrayList<Position> initialPosition = new ArrayList<>(11);

        for (int i = 0; i < 11; i++) {
            initialPosition.add(new Position(0, 0));
        }

        Board board = new Board();
        board.initializeBoard();
        board.printBoard();
        ArrayList<Shape> shapeList = new ArrayList<>();


        //generate the shapes initial positions
        Position tempPosition;
        Shape zero = new Shape();
        tempPosition = new Position(1, 3);
        zero.innerPieceList.add(tempPosition);
        tempPosition = new Position(2, 3);
        zero.innerPieceList.add(tempPosition);
        tempPosition = new Position(1, 4);
        zero.innerPieceList.add(tempPosition);
        tempPosition = new Position(2, 4);
        zero.innerPieceList.add(tempPosition);
        shapeList.add(zero);

        Shape one = new Shape();
        tempPosition = new Position(1, 5);
        one.innerPieceList.add(tempPosition);
        tempPosition = new Position(1, 6);
        one.innerPieceList.add(tempPosition);
        tempPosition = new Position(2, 6);
        one.innerPieceList.add(tempPosition);
        shapeList.add(one);


        Shape two = new Shape();
        tempPosition = new Position(2, 5);
        two.innerPieceList.add(tempPosition);
        tempPosition = new Position(3, 5);
        two.innerPieceList.add(tempPosition);
        tempPosition = new Position(3, 6);
        two.innerPieceList.add(tempPosition);
        shapeList.add(two);


        Shape three = new Shape();
        tempPosition = new Position(3, 7);
        three.innerPieceList.add(tempPosition);
        tempPosition = new Position(3, 8);
        three.innerPieceList.add(tempPosition);
        tempPosition = new Position(4, 8);
        three.innerPieceList.add(tempPosition);
        shapeList.add(three);


        Shape four = new Shape();
        tempPosition = new Position(4, 7);
        four.innerPieceList.add(tempPosition);
        tempPosition = new Position(5, 7);
        four.innerPieceList.add(tempPosition);
        tempPosition = new Position(5, 7);
        four.innerPieceList.add(tempPosition);
        shapeList.add(four);


        Shape five = new Shape();
        tempPosition = new Position(6, 7);
        five.innerPieceList.add(tempPosition);
        tempPosition = new Position(7, 7);
        five.innerPieceList.add(tempPosition);
        tempPosition = new Position(6, 8);
        five.innerPieceList.add(tempPosition);
        shapeList.add(five);

        Shape six = new Shape();
        tempPosition = new Position(5, 4);
        six.innerPieceList.add(tempPosition);
        tempPosition = new Position(5, 5);
        six.innerPieceList.add(tempPosition);
        tempPosition = new Position(5, 6);
        six.innerPieceList.add(tempPosition);
        tempPosition = new Position(4, 5);
        six.innerPieceList.add(tempPosition);
        shapeList.add(six);

        Shape seven = new Shape();
        tempPosition = new Position(6, 4);
        seven.innerPieceList.add(tempPosition);
        tempPosition = new Position(6, 5);
        seven.innerPieceList.add(tempPosition);
        tempPosition = new Position(6, 6);
        seven.innerPieceList.add(tempPosition);
        tempPosition = new Position(7, 5);
        seven.innerPieceList.add(tempPosition);
        shapeList.add(seven);

        Shape eight = new Shape();
        tempPosition = new Position(8, 5);
        eight.innerPieceList.add(tempPosition);
        tempPosition = new Position(8, 6);
        eight.innerPieceList.add(tempPosition);
        tempPosition = new Position(7, 6);
        eight.innerPieceList.add(tempPosition);
        shapeList.add(eight);

        Shape nine = new Shape();
        tempPosition = new Position(6, 2);
        nine.innerPieceList.add(tempPosition);
        tempPosition = new Position(6, 3);
        nine.innerPieceList.add(tempPosition);
        tempPosition = new Position(5, 3);
        nine.innerPieceList.add(tempPosition);
        shapeList.add(nine);

        Shape ten = new Shape();
        tempPosition = new Position(5, 1);
        ten.innerPieceList.add(tempPosition);
        tempPosition = new Position(6, 1);
        ten.innerPieceList.add(tempPosition);
        tempPosition = new Position(5, 2);
        ten.innerPieceList.add(tempPosition);
        shapeList.add(ten);



        GameState nullgameState = new GameState(null);
        GameState initialGameState = new GameState(nullgameState);

        //get state of every shape to start with
        Position[] temprPosition = new Position[11];

        for (int i = 0; i < shapeList.size(); i++) {
            temprPosition[i] = shapeList.get(i).relativePosition;
        }

        initialGameState.setState(temprPosition);

        if (!initialGameState.printState().equals(initialString)) {
            throw new RuntimeException("initial string is wrong");
        }

        GameStateComparator gameStateComparator = new GameStateComparator();
        TreeSet<GameState> seenIt= new TreeSet<>(gameStateComparator);
        Queue<GameState> todo = new LinkedList<>(); //FIFO counter

        todo.add(initialGameState);

        //push initial state onto the stack
        System.out.println("Push: " + initialGameState.printState());

        GameState finalState = new GameState();

        //while the queue is empty:
        while(!todo.isEmpty())
        {

            //applyoffset(pass in a Position)

            GameState _prev = todo.poll();
            GameState currentLevel = new GameState(_prev);

            //check if current level is the goal
            if(applyOffset(shapeList.get(0), _prev))
            {
                break;
            }

            //apply base case and generate nodes
            if(!seenIt.contains(currentLevel))
            {
                for (Shape shape : shapeList)
                {
                    for(Position position : shape.innerPieceList)
                    {
                        position.
                    }
                }
            }


            //if seenIt.contains(currentlevel) move on
            //if(currentLevel hasn't been seen) seenIt.add(currentLevel)
            //operate on currentLevel

            //keep list of stored states and check
            //check if we have seen before and check if it is illegal

            finalState = currentLevel;
        }

        while(finalState.prev != null)
        {
            System.out.println(finalState.printState());
            finalState = finalState.prev;
        }


    }
}
















