/**
 * Created by ASUS on 9/25/2016.
 */
public class Cell {
    int row;
    int col;
    boolean isMine;
    boolean isOpen;
    boolean isFlag;
    int mineCount;
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
