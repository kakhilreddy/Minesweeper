import java.util.Random;

/**
 * Created by ASUS on 9/25/2016.
 */
public class Board {
    Cell cell[][];
    int row;
    int col;
    int mineCount;
    int gameState;
    int remainingNonMines;
    int remainingMines;

    Board(int row,int col,int mineCount)
    {
        this.row=row;
        this.col=col;
        remainingNonMines=row*col-mineCount;
        remainingMines=mineCount;
        this.mineCount=mineCount;
        cell=new Cell[row][col];
        fillBombs(cell,mineCount);

        gameState=-1;
        updateNeighbourCountOfCells();


    }
    void resetBoard()
    { gameState=0;
        cell=new Cell[row][col];

            fillBombs(cell,mineCount);
        updateNeighbourCountOfCells();

    }
    boolean isMine(int i,int j)
    {
        return cell[i][j].isMine;
    }

    public void updateNeighbourCountOfCells() {
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
            {
               if(!cell[i][j].isMine)
                {
                    cell[i][j].mineCount=getCount(i,j);
                }
            }
    }

    private int getCount(int i, int j) {
        int count=0;
        for(int r=i-1;r<=i+1;r++)
            for(int c=j-1;c<=j+1;c++)
            {
                if(r>=0 && r<row && c>=0 && c<col && cell[r][c].isMine)
                    count++;
            }
            return  count;
    }

    int click(int i,int j)
    {
        if(cell[i][j].isFlag)
            return 0;

        if(cell[i][j].isOpen)
            return 0;

        cell[i][j].isOpen=true;
        remainingNonMines--;
        if(cell[i][j].isMine)
        {
            gameState=2;
            return 2;
        }
        if(remainingNonMines==0)
        {
            gameState=1;
            return 1;
        }
        if(cell[i][j].mineCount>0)
            return 0;

            for (int r = i - 1; r <= i + 1; r++)
                for (int c = j - 1; c <= j + 1; c++) {
                    if (r >= 0 && r < row && c >= 0 && c < col && !cell[r][c].isMine)
                        click(r, c);
                }

return 0;
    }
    void flagIt(int i,int j)
    {
        if(cell[i][j].isFlag) {
            cell[i][j].isFlag = false;
            remainingMines++;
        }
        else {
            cell[i][j].isFlag = true;
            remainingMines--;
        }


    }
    boolean isFlag(int i,int j)
    {
        return cell[i][j].isFlag;
    }
    boolean isOpen(int i,int j)
    {
        return cell[i][j].isOpen;
    }
    void printBoard()
    {
        for(int i=0;i<row;i++) {

            for (int j = 0; j < col; j++)
                if (cell[i][j].isMine)
                    System.out.print("* ");
                else {
                    if(cell[i][j].mineCount==0)
                    System.out.print("- ");
                    else
                        System.out.print(cell[i][j].mineCount+" ");

                }
            System.out.println();
        }

    }
    int getNeighbourCount(int i,int j)
    {
        return cell[i][j].mineCount;
    }
    void fillBombs(Cell cell[][],int n)
    {

        Random random=new Random();
for(int i=1;i<=n;)
{
    int x=random.nextInt(row);
    int y=random.nextInt(col);
    if(cell[x][y]==null)
    {
        i++;
        cell[x][y]=new Cell(x,y,true);
    }


}
for(int i=0;i<row;i++)
    for(int j=0;j<col;j++)
        if(cell[i][j]==null)
            cell[i][j]=new Cell(i,j,false);



    }

}
