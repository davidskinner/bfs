public class Board
{
    boolean boardState[][] = new boolean[10][10];
    Shape[] shapeList = new Shape[11];

    Board()
    {
    }


    public boolean validateBoard(byte[] positions)
    {
        this.wipeBoard();

        byte[][] positionsTwoD = Main.makeXy(positions);

        int x;
        int y;
        byte[][]shapeListTwoD;

        //loop through every piece as long as piece doesn't move invalid
        for (int i = 0; i < 11; i++) {

            int PosX = positionsTwoD[i][0];
            int PosY = positionsTwoD[i][1];

            //move every inner piece in the offset direction, if an inner piece makes an invalid move..jump out of loop for pie
                for (int j = 0; j < shapeList[i].innerArray.length/2; j++) {

                    if(shapeList[i].innerArray.length/2 == 3)
                    {
                         shapeListTwoD = makeXy3(shapeList[i].innerArray);
                    }
                    else
                    {
                         shapeListTwoD = makeXy4(shapeList[i].innerArray);
                    }

                    x = shapeListTwoD[j][0];
                    y = shapeListTwoD[j][1];


                        if(this.boardState[x + PosX][ y + PosY])
                        {
//                           printBoard();
                            return false;

                        }
                        else{
//                           printBoard();
                            this.boardState[x + PosX][ y + PosY] = true;
                        }
                }
            }
            this.wipeBoard();
            return true;
    }

    public byte[][] makeXy3 (byte[] positions)
    {
        byte[][] temp = new byte[6][2];

        int q =0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                temp[i][j] = positions[q];
                q++;

            }
        }
        return temp;
    }

    public byte[][] makeXy4 (byte[] positions)
    {
        byte[][] temp = new byte[8][2];

        int q =0;
        for (int i = 0; i < 4; i++) {
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
        System.out.println("");

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
        Shape[] tempShape = new Shape[11];
        Shape zero = new Shape((byte)1,(byte)3,(byte)2,(byte)3,(byte)1,(byte)4,(byte)2,(byte)4);
        tempShape[0] =zero;

        Shape one = new Shape((byte)1,(byte)5,(byte)1,(byte)6, (byte)2,(byte)6);
        tempShape[1] = (one);

        Shape two = new Shape((byte)2, (byte)5, (byte)3, (byte)5, (byte)3, (byte)6);
        tempShape[2] = (two);

        Shape three = new Shape((byte)3,(byte)7,(byte)3,(byte)8,(byte)4,(byte)8);
        tempShape[3] =(three);

        Shape four = new Shape((byte)4,(byte)7,(byte)5,(byte)7,(byte)5,(byte)8);
        tempShape[4] = (four);

        Shape five = new Shape((byte)6,(byte)7,(byte)7,(byte)7,(byte)6,(byte)8);
        tempShape[5] = (five);

        Shape six = new Shape((byte)5,(byte)4,(byte)5,(byte)5,(byte)5,(byte)6,(byte)4,(byte)5);
        tempShape[6] = (six);

        Shape seven = new Shape((byte)6,(byte)4,(byte)6,(byte)5,(byte)6,(byte)6,(byte)7,(byte)5);
        tempShape[7] = (seven);

        Shape eight = new Shape((byte)8,(byte)5,(byte)8,(byte)6,(byte)7,(byte)6);
        tempShape[8] = (eight);

        Shape nine = new Shape((byte)6,(byte)2,(byte)6,(byte)3,(byte)5,(byte)3);
        tempShape[9] = (nine);

        Shape ten = new Shape((byte)5,(byte)1,(byte)6,(byte)1,(byte)5,(byte)2);
        tempShape[10] = (ten);

        this.shapeList = tempShape;
    }
}
