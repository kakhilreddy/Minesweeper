/**
 * Created by AKHIL on 9/25/2016.
 */
//This class represents each cell present on the board
public class Cell {
    int row;
    int col;
    boolean isMine;
    boolean isOpen;
    boolean isFlag;
    int mineCount; //neighbour mines count
    Cell(int row,int col,boolean isMine)
    {
        this.col=col;
        this.row=row;
        this.isMine=isMine;
        isOpen=false;
        isFlag=false;
        mineCount=0;

    }
}
