import java.time.ZonedDateTime;
import java.util.*;



class Position {
    byte x;
    byte y;

    Position() {

    }

//    Position(int xd, int yd) {
//        x = (byte)xd;
//        y = (byte)yd;
//    }
//
//    @Override
//    public String toString() {
//        return "(" + String.valueOf(x) + "," + String.valueOf(y) +  ")";
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public byte[] convertToByteArray()
//    {
//        byte[] temp = new byte[2];
//        temp[0] = x;
//        temp[1] = y;
//        return temp;
//    }

}

class Shape {
    byte x = 0;
    byte y = 0;
    byte[] relativePosition = new byte[]{x,y};


    ArrayList<Position> innerPieceList = new ArrayList<Position>();
    //make an arraylist of positions
    byte[] innerArray;

    //for shapes made of three pieces
    Shape( byte xx, byte yy, byte xxx, byte yyy, byte xxxx, byte yyyy)
    {

        innerArray = new byte[6];
        innerArray[0] = xx;
        innerArray[1] = yy;
        innerArray[2] = xxx;
        innerArray[3] = yyy;
        innerArray[4] = xxxx;
        innerArray[5] = yyyy;
    }

    //for shapes with 4
    Shape(byte x, byte y, byte xx, byte yy, byte xxx, byte yyy, byte xxxx, byte yyyy)
    {

        innerArray = new byte[8];
        innerArray[0] = x;
        innerArray[1] = y;
        innerArray[2] = xx;
        innerArray[3] = yy;
        innerArray[4] = xxx;
        innerArray[5] = yyy;
        innerArray[6] = xxxx;
        innerArray[7] = yyyy;

    }


}

//this is a node in the graph
class GameState {

    GameState prev;

    byte[] state = new byte[22];

    GameState()
    {

    }

    GameState(GameState _prev) {


        prev = _prev;
        state = new byte[22];
        for (int i = 0; i < 22; i++) {
            byte tempPosition;
            tempPosition = _prev.state[i];
            state[i] = tempPosition;
        }

    }

    public void setState(byte[] currentState)
    {
        state = currentState;
    }

    public String printState()
    {
        byte[][] twoD = Main.makeXy(state);
        StringBuilder stringBuilder = new StringBuilder();
        String tempString = "";
        for (int i = 0; i < twoD.length; i++) {
            stringBuilder.append("(");
            stringBuilder.append((String.valueOf(twoD[i][0])));
            stringBuilder.append(",");
            stringBuilder.append((String.valueOf(twoD[i][1])));
            stringBuilder.append(")");
        }


        tempString = stringBuilder.toString();
        return tempString;
    }
}



enum Direction
{
    up,down,left,right
}

class StateComparator implements Comparator<GameState> {

    public int compare(GameState a, GameState b)
    {
        for(int i = 0; i < a.state.length; i ++)
        {
            if(a.state[i] < b.state[i])
                return -1;
            else if(a.state[i] > b.state[i] )
                return 1;
        }
        return 0;

    }
}

class Main {

    public static byte[][] makeXy(byte[] positions)
    {
        byte[][] temp = new byte[11][2];

        int q =0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 2; j++) {
                temp[i][j] = positions[q];
                q++;

            }
        }
        return temp;
    }

    public static byte[] to1D(byte[][] twoD)
    {
        byte[] temp = new byte[22];

        {
            int q =0;
            for (int i = 0; i < 11;i++) {
                for (int j = 0; j <2; j++) {
                    temp[q] = twoD[i][j];
                            q++;
                }

            }
            return temp;
        }
    }

    static boolean applyOffset(byte[] position)
    {
//        return position.x == 4 && position.y == -2;
        return position[0] == 0 && position[1] == 1;
    }

    static void log(String message)
    {
//       System.out.println(message);
    }

    public static void main(String args[]) {

        final String initialString = "(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)(0,0)";

        //create initial position
        byte[] initialPosition = new byte[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

//        for (int i = 0; i < 11; i++) {
//            initialPosition.add(new Position(0, 0));
//        }

        Board board = new Board();
        board.wipeBoard();
        board.initializeShapes();
        board.printBoard();


        GameState initialGameState = new GameState();

        //get state of every shape to start with
        initialGameState.setState(initialPosition);

        StateComparator gameStateComparator = new StateComparator();
        TreeSet<GameState> seenIt= new TreeSet<>(gameStateComparator);
        Queue<GameState> todo = new LinkedList<>(); //FIFO counter

        todo.add(initialGameState);
        log("Push:" + initialGameState.printState());


        //push initial state onto the stack
//        System.out.println("Push: " + initialGameState.printState());

        GameState currentLevel = new GameState();

        boolean xDirection;
        boolean yDirection;
        //while the queue is empty:

        final ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now.toLocalDateTime().toString());


        while(!todo.isEmpty())
        {
            currentLevel = todo.poll();
            log("Pop:" + currentLevel.printState());

            //check if current level is the goal
            if(applyOffset(currentLevel.state))
            {
                currentLevel.printState();
                break;
            }

            byte[][] tempGamestateTwoD;

            for (int i = 0; i < 11; i++) {

                GameState tempGamestate = new GameState(currentLevel);
                tempGamestateTwoD = makeXy((tempGamestate.state));
                tempGamestateTwoD[i][0] += 1;
                tempGamestate.state = to1D(tempGamestateTwoD);

                if(board.validateBoard(tempGamestate.state ) && !seenIt.contains(tempGamestate))
                {
                    todo.add(tempGamestate);
                    seenIt.add(tempGamestate);
                    log("Push:" + tempGamestate.printState());
                }
            }

            for (int i = 0; i < 11; i++) {

                GameState tempGamestate = new GameState(currentLevel);
                tempGamestateTwoD = makeXy((tempGamestate.state));
                tempGamestateTwoD[i][0] -= 1;
                tempGamestate.state = to1D(tempGamestateTwoD);

                if(board.validateBoard(tempGamestate.state ) && !seenIt.contains(tempGamestate))
                {
                    todo.add(tempGamestate);
                    seenIt.add(tempGamestate);
                    log("Push:" + tempGamestate.printState());
                }
            }

            for (int i = 0; i < 11; i++) {

                GameState tempGamestate = new GameState(currentLevel);
                tempGamestateTwoD = makeXy((tempGamestate.state));
                tempGamestateTwoD[i][1] -= 1;
                tempGamestate.state = to1D(tempGamestateTwoD);

                if(board.validateBoard(tempGamestate.state) && !seenIt.contains(tempGamestate))
                {
                    todo.add(tempGamestate);
                    seenIt.add(tempGamestate);
                    log("Push:" + tempGamestate.printState());

                }
            }

            for (int i = 0; i < 11; i++) {

                GameState tempGamestate = new GameState(currentLevel);
                tempGamestateTwoD = makeXy((tempGamestate.state));
                tempGamestateTwoD[i][1] += 1;
                tempGamestate.state = to1D(tempGamestateTwoD);

                if(board.validateBoard(tempGamestate.state) && !seenIt.contains(tempGamestate))
                {
                    todo.add(tempGamestate);
                    seenIt.add(tempGamestate);
                    log("Push:" + tempGamestate.printState());

                }
            }

            seenIt.add(currentLevel);

            log(String.valueOf(seenIt.size()));
        }

        Iterator xs = seenIt.iterator();
        while(xs.hasNext()){
            GameState element = (GameState)xs.next();
            System.out.println(element.printState());
        }
        log("");

        ZonedDateTime later = ZonedDateTime.now();
        System.out.println(later.toLocalDateTime().toString());

        LinkedList<GameState> temp = new LinkedList<>();
        temp.add(currentLevel);
        while(currentLevel.prev != null)
        {
//            System.out.println(currentLevel.printState());
            currentLevel = currentLevel.prev;
            temp.add(currentLevel);

        }

        Iterator x = temp.descendingIterator();

        while(x.hasNext())
        {
            GameState element = (GameState)x.next();
            System.out.println(element.printState());

        }
    }
}
















