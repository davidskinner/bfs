import javafx.geometry.Pos;

import java.lang.reflect.Array;
import java.util.*;

class Position {
    byte x;
    byte y;

    Position() {

    }

    Position(int xd, int yd) {
        x = (byte)xd;
        y = (byte)yd;
    }

    @Override
    public String toString() {
        return "(" + String.valueOf(x) + "," + String.valueOf(y) +  ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

class Shape {
    byte x = 0;
    byte y = 0;
    Position relativePosition = new Position(x, y);

    ArrayList<Position> innerPieceList = new ArrayList<Position>();

}

//this is a node in the graph
class GameState {

    GameState prev;

    ArrayList<Position> state = new ArrayList<>();

    GameState()
    {

    }

    GameState(GameState _prev) {


        prev = _prev;
        state = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            Position tempPosition = new Position(_prev.state.get(i).x,_prev.state.get(i).y);
            state.add(tempPosition);
        }

    }

    public void setState(ArrayList<Position> currentState)
    {
        state = currentState;
    }

    public String printState()
    {
        String tempString = "";
        for (Position aState : state) {
            tempString = tempString + aState.toString();
        }
        return tempString;
    }
}



enum Direction
{
    up,down,left,right
}

class GameStateComparator implements Comparator<GameState> {

    public int compare(GameState a, GameState b)
    {
        for(int i = 0; i < a.state.size(); i ++)
        {
            if(a.state.get(i).x < b.state.get(i).x || a.state.get(i).y < b.state.get(i).y)
                return -1;
            else if(a.state.get(i).x > b.state.get(i).x || a.state.get(i).y > b.state.get(i).y)
                return 1;
        }
        return 0;

    }

}

class Main {

    static boolean applyOffset(Position position)
    {
        return position.x == 4 && position.y == -2;
    }

    static void log(String message)
    {
//       System.out.println(message);
    }

    public static void main(String args[]) {

        final String initialString = "(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)";

        //create initial position
        ArrayList<Position> initialPosition = new ArrayList<>(11);

        for (int i = 0; i < 11; i++) {
            initialPosition.add(new Position(0, 0));
        }

        Board board = new Board();
        board.wipeBoard();
        board.initializeShapes();
        board.printBoard();


        GameState initialGameState = new GameState();

        ArrayList<Position> tempPosition = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
             tempPosition.add(new Position(0,0));
        }

        //get state of every shape to start with
        initialGameState.setState(tempPosition);

        if (!initialGameState.printState().equals(initialString)) {
            System.out.println(initialGameState.printState());
            System.out.println(initialString);
            throw new RuntimeException("initial string is wrong");
        }

        GameStateComparator gameStateComparator = new GameStateComparator();
        TreeSet<GameState> seenIt= new TreeSet<>(gameStateComparator);
        Queue<GameState> todo = new LinkedList<>(); //FIFO counter

        todo.add(initialGameState);

        //push initial state onto the stack
//        System.out.println("Push: " + initialGameState.printState());

        GameState currentLevel = new GameState();

        //while the queue is empty:
        while(!todo.isEmpty())
        {
            currentLevel = todo.poll();
            log("Pop:" + currentLevel.printState());


            //check if current level is the goal
            if(applyOffset(currentLevel.state.get(0)))
            {
                currentLevel.printState();
                break;
            }

//            System.out.println("plusX");
            for (int i = 0; i < currentLevel.state.size(); i++) {

                GameState tempGamestate = new GameState(currentLevel);
                tempGamestate.state.get(i).x += 1;
                if(board.validateBoard(tempGamestate.state) && !seenIt.contains(tempGamestate))
                    {
                    todo.add(tempGamestate);
                    seenIt.add(tempGamestate);
                        log("Push:" + tempGamestate.printState());
                }

            }

            for (int i = 0; i < currentLevel.state.size(); i++) {

                GameState tempGamestate = new GameState(currentLevel);
                tempGamestate.state.get(i).x -= 1;
                if(board.validateBoard(tempGamestate.state) && !seenIt.contains(tempGamestate))
                {
                    todo.add(tempGamestate);
                    seenIt.add(tempGamestate);
                    log("Push:" + tempGamestate.printState());

                }
            }

//            System.out.println("plusY");
            for (int i = 0; i < currentLevel.state.size(); i++) {

                GameState tempGamestate = new GameState(currentLevel);
                tempGamestate.state.get(i).y += 1;
                if(board.validateBoard(tempGamestate.state) && !seenIt.contains(tempGamestate))
                {
                    todo.add(tempGamestate);
                    seenIt.add(tempGamestate);
                    log("Push:" + tempGamestate.printState());

                }
            }

            for (int i = 0; i < currentLevel.state.size(); i++) {

                GameState tempGamestate = new GameState(currentLevel);
                tempGamestate.state.get(i).y -= 1;
                if(board.validateBoard(tempGamestate.state) && !seenIt.contains(tempGamestate))
                {
                    todo.add(tempGamestate);
                    seenIt.add(tempGamestate);
                    log("Push:" + tempGamestate.printState());

                }
            }
            seenIt.add(currentLevel);
        }

        //todo : flip around
        while(currentLevel.prev != null)
        {
            System.out.println(currentLevel.printState());
            currentLevel = currentLevel.prev;
        }


    }
}
















