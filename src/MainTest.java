import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by ASUS on 9/25/2016.
 */
public class MainTest {
   static void print(Board b,int rows,int cols)
    {
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                if (b.cell[i][j].isOpen) {
                    if(b.cell[i][j].isMine)
                        System.out.print("* ");
                  else  if (b.cell[i][j].mineCount == 0)
                        System.out.print("- ");
                                       else
                        System.out.print(b.cell[i][j].mineCount + " ");

                } else {
                    if (b.cell[i][j].isFlag)
                        System.out.print("F ");
                    else
                        System.out.print("C ");

                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    public static void main(String[] args) {
      /*  Board b=new Board(9,9,10);
        b.printBoard();
        System.out.print("\n");
        Scanner s=new Scanner(System.in);

        if(b.gameState==-1)
        {int i=s.nextInt();
            int j=s.nextInt();
            if(b.cell[i][j].isMine)
            {
                while (b.gameState==-1)
                {
                    Random random=new Random();
                    int x=random.nextInt(b.row);
                    int y=random.nextInt(b.col);
                    if(!b.cell[x][y].isMine)
                    {
                        b.gameState=0;
                        Cell temp=b.cell[i][j];
                        b.cell[i][j]=b.cell[x][y];
                        b.cell[x][y]=temp;
                        b.updateNeighbourCountOfCells();

                    }
                }
                b.click(i,j);
                print(b,b.row,b.col);
                b.printBoard();

            }


        }
        while(b.gameState==0)
        {

            int i=s.nextInt();
            int j=s.nextInt();

            b.click(i,j);
            print(b,b.row,b.col);
        }
        if(b.gameState==1)
            System.out.println("You Won");
        else
        {
            System.out.print("You Lost");
        }

    }*/
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
