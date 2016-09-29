import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Created by ASUS on 9/27/2016.
 */
public class MainFrame extends JFrame {

    JPanel panel;
    JToggleButton button[][];
int rows=9,cols=9;
    Board board;
    Image image;
    JTextField timeCounter;
    JTextField mineCounter;
    JButton start;
    MainFrame()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(500,600);
setTitle("Minesweeper");
        getContentPane().setBackground(Color.decode("#c5d4e7"));
        board=new Board(rows,cols,10);
      panel=new JPanel();
        Image wPic = null;
        Image wPic1=null;
        Image wPic2=null;
        try {
            wPic = ImageIO.read(this.getClass().getResource("Images/time.png"));
            wPic1 = ImageIO.read(this.getClass().getResource("Images/mine1.png"));
            wPic2 = ImageIO.read(this.getClass().getResource("Images/smiley.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        start=new JButton(new ImageIcon(wPic2));
        start.setMargin(new Insets(0,0,0,0));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(board.gameState!=-1) {
                    Image img=null;
                    try {
                        img=ImageIO.read(getClass().getResource("Images/smiley.png"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    start.setIcon(new ImageIcon(img));
                    board.resetBoard();
                    updateUI();
                }
                board.gameState=0;

                mineCounter.setText(String.valueOf(board.remainingMines));


                startTimer();



            }
        });
 timeCounter=new JTextField(5);
       timeCounter.setBackground(Color.decode("#2f5397"));
timeCounter.setForeground(Color.WHITE);
timeCounter.setEditable(false);
        timeCounter.setSize(10,30);
     //  setLayout(new BorderLayout());
        panel.setSize(rows*40,cols*40);
        setLayout(new FlowLayout());
        add(new JLabel(new ImageIcon(wPic)));
        add(timeCounter);
add(start);
         mineCounter=new JTextField(5);
                mineCounter.setBackground(Color.decode("#2f5397"));
        mineCounter.setForeground(Color.WHITE);
        mineCounter.setEditable(false);
        mineCounter.setSize(10,30);
        add(mineCounter);
        add(new JLabel(new ImageIcon(wPic1)));
        add(panel);

        panel.setLayout(new GridLayout(9,9));

        button=new JToggleButton[9][9];
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
            {
                try {
                    Image img = ImageIO.read(getClass().getResource("Images/Capture.png"));
                    Image newimg = img.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH );
                    button[i][j]=new JToggleButton(new ImageIcon(newimg));
                    button[i][j].setSize(40,40);

                    button[i][j].addMouseListener(new ButtonListener(i,j));
                    button[i][j].setMargin(new Insets(0,0,0,0));
                    panel.add(button[i][j]);




                } catch (IOException ex) {
                }


            }


    }
boolean startTimer=false;
    private void updateUILost() {
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
            {
                if(board.isMine(i,j))
                {
                    Image img = null;
                    try {
                        img = ImageIO.read(getClass().getResource("Images/mine.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                    button[i][j].setIcon(new ImageIcon(newimg));

                }
            }


    }


    private void updateUI() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (board.isOpen(i, j)) {
                    Image img = null;
                    try {
                        img = ImageIO.read(getClass().getResource("Images/"+board.getNeighbourCount(i, j) + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                    button[i][j].setIcon(new ImageIcon(newimg));

                } else if(board.isFlag(i,j))
                {
                    Image img = null;
                    try {
                        img = ImageIO.read(getClass().getResource("Images/flag.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                    button[i][j].setIcon(new ImageIcon(newimg));

                }
                else {

                    Image img = null;


                    try {
                        img = ImageIO.read(getClass().getResource("Images/Capture.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                    button[i][j].setIcon(new ImageIcon(newimg));


                }
            }
      /*  if(startTimer==false)
        {

            mineCounter.setText("");
            startTimer();
        }*/
    }

    private void startTimer() {
startTimer=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int c = 0;
                while (startTimer) {
                    timeCounter.setText(String.valueOf(c));
                    try {
                        Thread.sleep(1000);
                        c++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }
        }).start();

    }

    class ButtonListener implements MouseListener {
        int row,col;
        ButtonListener(int i,int j)
        {
            row=i;
            col=j;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (board.gameState == 0) {
                if (!board.isOpen(row, col)) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        int gameState = board.click(row, col);
                        mineCounter.setText(String.valueOf(board.remainingMines));
                        switch (gameState) {
                            case 0:
                                updateUI();
                                break;

                            case 1:
                                updateUI();
                                JOptionPane.showMessageDialog(MainFrame.this, "You Won!!", "Congragulations", JOptionPane.INFORMATION_MESSAGE);
                                startTimer = false;

                                break;
                            case 2:
                                startTimer = false;
                                updateUILost();
                                Image img = null;
                                try {
                                    img = ImageIO.read(getClass().getResource("Images/smileysad.png"));
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                start.setIcon(new ImageIcon(img));
                                JOptionPane.showMessageDialog(MainFrame.this, "You Lost!!", "Sorry", JOptionPane.INFORMATION_MESSAGE);

                                break;
                        }


                    } else {
                        try {
                            Image newimg;
                            Image img;


                            if (!board.isFlag(row, col)) {
                                img = ImageIO.read(getClass().getResource("Images/flag.png"));
                                newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

                            } else {
                                img = ImageIO.read(getClass().getResource("Images/Capture.png"));
                                newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                            }
                            board.flagIt(row, col);
                            mineCounter.setText(String.valueOf(board.remainingMines));

                            button[row][col].setIcon(new ImageIcon(newimg));

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }


            } else {

            }
        }


        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
