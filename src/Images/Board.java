import java.util.Random;

/**
 * Created by AKHIL on 9/25/2016.
 */

//This class represents Board in the game and does all required backend operations
public class Board {
    Cell cell[][];  //2D  array of cells
    int row;  //No of rows in board
    int col; //No of cols in board
    int mineCount; //No of mines present in board
    int gameState; // gameState : -1 game didnt start gameState : 0 - game in progress
    //gameState : 1 - game won gameState : 2 game lost
    int remainingNonMines; //remaining cells that are non Mines
    int remainingMines; // remaining mines in the game

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
    //To start new game
   public void resetBoard()
    { gameState=0;
        cell=new Cell[row][col];

            fillBombs(cell,mineCount);
        updateNeighbourCountOfCells();

    }
  
///Function to find the count of Neighbour mines
    private void updateNeighbourCountOfCells() {
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
            {
               if(!cell[i][j].isMine)
                {
                    cell[i][j].mineCount=getCount(i,j);
                }
            }
    }
//Helper function for updateNeighbourCountOfCells
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
    
//Function to respond to user clicks and update the game accordingly
    int click(int i,int j)
    {
        if(cell[i][j].isFlag)
            return 0;

        if(cell[i][j].isOpen)
            return 0;

        cell[i][j].isOpen=true;
        remainingNonMines--;
        if(cell[i][j].isMine) //GameOver
        {
            gameState=2;
            return 2;
        }
        if(remainingNonMines==0)// successfully completed the game
        {
            gameState=1;
            return 1;
        }
        // Recursively traverse the board only if the cell is empty -> cell[][].mineCount==0
        if(cell[i][j].mineCount>0)
            return 0;

            for (int r = i - 1; r <= i + 1; r++)
                for (int c = j - 1; c <= j + 1; c++) {
                    if (r >= 0 && r < row && c >= 0 && c < col && !cell[r][c].isMine)
                        click(r, c);
                }

return 0;
    }
    
    // To flag the cell when user right clicks the cell
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
     //TO check whether the cell[i][j] is flagged or not
    boolean isFlag(int i,int j)
    {
        return cell[i][j].isFlag;
    }
     //TO check whether the cell[i][j] is already open or not
    boolean isOpen(int i,int j)
    {
        return cell[i][j].isOpen;
    }
      //TO check whether the cell[i][j] contains mine or not
    boolean isMine(int i,int j)
    {
        return cell[i][j].isMine;
    }
    
    //To get no of mines that are neighbour to a cell
     int getNeighbourCount(int i,int j)
    {
        return cell[i][j].mineCount;
    }

   
    //Filling bombs randomly 
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
        cell[x][y]=new Cell(x,y,true); // creating a cell and setting isMine as true 
    }


}
        
for(int i=0;i<row;i++)
    for(int j=0;j<col;j++)
        if(cell[i][j]==null)
            cell[i][j]=new Cell(i,j,false);// creating a cell and setting isMine as false



    }

}
