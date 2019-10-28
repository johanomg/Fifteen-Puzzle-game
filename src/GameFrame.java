import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

public class GameFrame extends JFrame /*implements ActionListener*/ {

    int SIZE = 4;
    int SQUARESIZE = SIZE * SIZE;
    /*int i0 = 0;
    int j0 = 0;
    int iKlick = 0;
    int jKlick = 0;*/
    JButton newGame = new JButton("NEW GAME");
    private JButton[][] board = new JButton[SIZE][SIZE];
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();


//
    public GameFrame() {
        initiateBoard();
        manageLayout();
        addButtons();  //det var rePaint!
        // add(panel);

    }

    public void initiateBoard() {
        // The list is used for create a shuffled order of 1-16
        ArrayList<Integer> intialList = new ArrayList<Integer>(SIZE);
        for (int i = 0; i < SQUARESIZE; i++) {
            intialList.add(i, i); // i, i is for index and value
        }
        // make the shuffle
        Collections.shuffle(intialList);

        // place JButtons in the board array with shuffled text(0-16) on them
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new JButton(intialList.get(counter).toString());
                board[i][j].setPreferredSize(new Dimension(100,100));

                counter++;
            }
        }
    }


    public void manageLayout() {
        setTitle("Fifteen Puzzle Game");
        setSize(420,470);

        setResizable(false);
        panel.setLayout(new GridLayout(4, 4));
        panel2.setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel, BorderLayout.NORTH );
        add(panel2, BorderLayout.SOUTH );
        panel.setSize(400, 400);
        panel2.setSize(400,40);


    }


    // maybe change name for this method, it doesnt really repaint the board
    private void addButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                panel.add(board[i][j]);
                hideZero(board, i, j);
                board[i][j].addMouseListener(m1);
            }
        }
        panel2.add(newGame).setPreferredSize(new Dimension(200,20));
        newGame.addMouseListener(m2);
    }

    private void hideZero (JButton board[][], int i, int j){
        if (board[i][j].getText().equals("0")) {
            board[i][j].setVisible(false);
        } else {
            board[i][j].setVisible(true);
        }
    }




    MouseAdapter m1 = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            JButton tempButton = (JButton) e.getComponent();
            Move move = new Move(tempButton, board, SIZE);

        }
    };
    MouseAdapter m2 = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            panel.removeAll();
            initiateBoard();
            manageLayout();
            addButtons();
            //kanske bättre om vi stänger frame och använder new GameFrame();

        }
    };




}




