import javafx.geometry.Pos;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board
{
    boolean boardState[][] = new boolean[10][10];
    ArrayList<Shape> shapeList = new ArrayList<>();

    Board()
    {
    }


//    int innerPieceX = shapeList.get(i).innerPieceList.get(j).getX();
//    int currentLevelOffsetX = positions.get(i).getX();
//    int innerPieceY = shapeList.get(i).innerPieceList.get(j).getY();
//    int currentLevelOffsetY = positions.get(i).getY();

    public boolean validateBoard(byte[] positions, boolean xDirection)
    {
        this.wipeBoard();

        byte[][] positionsTwoD = makeXy(positions);


        //loop through every piece
        for (int i = 0; i < shapeList.size(); i++) {

            int x;
            int y;
            int shape;

            //move every inner piece in the offset direction
                for (int j = 0; j < shapeList.get(i).innerArray.length; j++) {



                    byte[][] shapeListTwoD = makeXy(shapeList.get(i).innerArray);

                    
                    shape = shapeList.get(i).innerArray[j];

                    if(xDirection && j % 2 == 0)
                    {
                        if(this.boardState[shape + positions[i]][ shape +  positions[i]])
                        {
                            printBoard();
                            return false;

                        }
                        else{
                            printBoard();
                            this.boardState[shape + positions[i]][ shape + positions[i]] = true;
                        }
                    }
                    else
                    {
                    }
                }
            }
            this.wipeBoard();
            return true;
    }

    public byte[][] makeXy(byte[] positions)
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

    public byte[][] makeXy3 (byte[] positions)
    {
        byte[][] temp = new byte[6][2];

        int q =0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                temp[i][j] = positions[q];
                q++;

            }
        }
        return temp;
    }

    public byte[][] makeXy4 (byte[] positions)
    {
        byte[][] temp = new byte[6][2];

        int q =0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 2; j++) {
                temp[i][j] = positions[q];
                q++;

            }
        }
        return temp;
    }


    public void printBoard()
    {
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j <10 ; j++) {
                if(boardState[j][i])
                    System.out.print("T ");
                else
                {
                    System.out.print("@ ");
                }
            }
            System.out.println("");
        }
    }

    public void wipeBoard()
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardState[i][j] = false;
            }
        }

        for (int i = 0; i < 10; i++) {
            boardState[0][i] = true;
            boardState[9][i] = true;
        }

        for (int i = 0; i < 10; i++) {
            boardState[i][0] = true;
            boardState[i][9] = true;
        }
        //initialize black squares

        this.boardState[1][1] = true;
        this.boardState[1][2] = true;
        this.boardState[1][7] = true;
        this.boardState[3][4] = true;
        this.boardState[4][3] = true;
        this.boardState[4][4] = true;
        this.boardState[1][8] = true;
        this.boardState[2][1] = true;
        this.boardState[2][8] = true;
        this.boardState[7][1] = true;
        this.boardState[7][8] = true;
        this.boardState[8][1] = true;
        this.boardState[8][2] = true;
        this.boardState[8][7] = true;
        this.boardState[8][8] = true;
    }

    public void initializeShapes()
    {
        ArrayList<Shape> tempShape = new ArrayList<>();
        //generate the shapes initial positions
//        Position tempPosition;
        Shape zero = new Shape((byte)1,(byte)3,(byte)2,(byte)3,(byte)1,(byte)4);
//        tempPosition = new Position(1, 3);
//        zero.innerPieceList.add(tempPosition);
//        tempPosition = new Position(2, 3);
//        zero.innerPieceList.add(tempPosition);
//        tempPosition = new Position(1, 4);
//        zero.innerPieceList.add(tempPosition);
//        tempPosition = new Position(2, 4);
//        zero.innerPieceList.add(tempPosition);
        tempShape.add(zero);



        Shape one = new Shape((byte)1,(byte)5,(byte)1,(byte)6, (byte)2,(byte)6);
//        tempPosition = new Position(1, 5);
//        one.innerPieceList.add(tempPosition);
//        tempPosition = new Position(1, 6);
//        one.innerPieceList.add(tempPosition);
//        tempPosition = new Position(2, 6);
//        one.innerPieceList.add(tempPosition);
        tempShape.add(one);


        Shape two = new Shape((byte)2, (byte)5, (byte)3, (byte)5, (byte)3, (byte)6);
//        tempPosition = new Position(2, 5);
//        two.innerPieceList.add(tempPosition);
//        tempPosition = new Position(3, 5);
//        two.innerPieceList.add(tempPosition);
//        tempPosition = new Position(3, 6);
//        two.innerPieceList.add(tempPosition);
        tempShape.add(two);


        Shape three = new Shape((byte)3,(byte)7,(byte)3,(byte)8,(byte)4,(byte)8);
//        tempPosition = new Position(3, 7);
//        three.innerPieceList.add(tempPosition);
//        tempPosition = new Position(3, 8);
//        three.innerPieceList.add(tempPosition);
//        tempPosition = new Position(4, 8);
//        three.innerPieceList.add(tempPosition);
        tempShape.add(three);


        Shape four = new Shape((byte)4,(byte)7,(byte)5,(byte)7,(byte)5,(byte)8);
//        tempPosition = new Position(4, 7);
//        four.innerPieceList.add(tempPosition);
//        tempPosition = new Position(5, 7);
//        four.innerPieceList.add(tempPosition);
//        tempPosition = new Position(5, 8);
//        four.innerPieceList.add(tempPosition);
        tempShape.add(four);


        Shape five = new Shape((byte)6,(byte)7,(byte)7,(byte)7,(byte)6,(byte)8);
//        tempPosition = new Position(6, 7);
//        five.innerPieceList.add(tempPosition);
//        tempPosition = new Position(7, 7);
//        five.innerPieceList.add(tempPosition);
//        tempPosition = new Position(6, 8);
//        five.innerPieceList.add(tempPosition);
        tempShape.add(five);

        Shape six = new Shape((byte)5,(byte)4,(byte)5,(byte)5,(byte)5,(byte)6,(byte)4,(byte)5);
//        tempPosition = new Position(5, 4);
//        six.innerPieceList.add(tempPosition);
//        tempPosition = new Position(5, 5);
//        six.innerPieceList.add(tempPosition);
//        tempPosition = new Position(5, 6);
//        six.innerPieceList.add(tempPosition);
//        tempPosition = new Position(4, 5);
//        six.innerPieceList.add(tempPosition);
        tempShape.add(six);

        Shape seven = new Shape((byte)6,(byte)4,(byte)6,(byte)5,(byte)6,(byte)6,(byte)7,(byte)5);
//        tempPosition = new Position(6, 4);
//        seven.innerPieceList.add(tempPosition);
//        tempPosition = new Position(6, 5);
//        seven.innerPieceList.add(tempPosition);
//        tempPosition = new Position(6, 6);
//        seven.innerPieceList.add(tempPosition);
//        tempPosition = new Position(7, 5);
//        seven.innerPieceList.add(tempPosition);
        tempShape.add(seven);

        Shape eight = new Shape((byte)8,(byte)5,(byte)8,(byte)6,(byte)7,(byte)6);
//        tempPosition = new Position(8, 5);
//        eight.innerPieceList.add(tempPosition);
//        tempPosition = new Position(8, 6);
//        eight.innerPieceList.add(tempPosition);
//        tempPosition = new Position(7, 6);
//        eight.innerPieceList.add(tempPosition);
        tempShape.add(eight);

        Shape nine = new Shape((byte)6,(byte)2,(byte)6,(byte)3,(byte)5,(byte)3);
//        tempPosition = new Position(6, 2);
//        nine.innerPieceList.add(tempPosition);
//        tempPosition = new Position(6, 3);
//        nine.innerPieceList.add(tempPosition);
//        tempPosition = new Position(5, 3);
//        nine.innerPieceList.add(tempPosition);
        tempShape.add(nine);

        Shape ten = new Shape((byte)5,(byte)1,(byte)6,(byte)1,(byte)5,(byte)2);
//        tempPosition = new Position(5, 1);
//        ten.innerPieceList.add(tempPosition);
//        tempPosition = new Position(6, 1);
//        ten.innerPieceList.add(tempPosition);
//        tempPosition = new Position(5, 2);
//        ten.innerPieceList.add(tempPosition);
        tempShape.add(ten);

        this.shapeList = tempShape;
    }
}
