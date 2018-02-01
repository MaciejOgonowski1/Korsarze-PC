package sample;



public class Board
{
    public Board()
    {
        ship = 0;
        for(int i=0; i<10; i++)
            for(int j=0; j<10; j++)
            {
                field[i][j] = 0;
            }
    }

    public Board(int ships)
    {
        ship = ships;
        for(int i=0; i<10; i++)
            for(int j=0; j<10; j++)
            {
                field[i][j] = 0;
            }
    }

    private int field[][] = new int[10][10];
    private int ship;


    public int incrementShipCounter()
    {
        ship++;
        if (ship>=20)
            return 1;
        else
            return 0;
    }

    public int decrementShipCounter()
    {
        ship--;
        if (ship<=0)
            return 1;
        else
            return 0;
    }

    public int getShip()
    {
        return ship;
    }

    public void setField(int value, int x, int y)
    {
         field[x][y] = value;
    }

    public int getField(int x, int y)
    {
        return field[x][y];
    }


}
