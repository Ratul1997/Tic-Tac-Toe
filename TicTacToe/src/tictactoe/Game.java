
package tictactoe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Game implements ActionListener {

    JFrame frame = new JFrame();
    JPanel p = new JPanel();
    JButton button[][] = new JButton[10][10];
    int count = 0;
    int[][] track = new int[10][10];
    int player = 1;

    Game() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe");
        frame.setSize(300, 300);
        frame.setLocation(200, 150);
        init();
        makeGrid();

        frame.setVisible(true);
    }

    private void init() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                track[i][j] = -1;
            }
        }
    }

    private void makeGrid() {
        p.setLayout(new GridLayout(3, 3));

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                button[i][j] = new JButton();
                button[i][j].addActionListener(this);

                p.add(button[i][j]);
            }
        }

        frame.add(p);
    }

    public void reset() {
        init();
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                button[i][j].setEnabled(true);
                button[i][j].setText("");

            }
        }
        player = 1;
    }

    private boolean checKWinOrNot(int row, int col) {

        if ((row == 1 && col == 1) || (row == 3 && col == 3)) {
            if (track[row][1] == track[row][2] && track[row][3] == track[row][2]) {
                return true;
            }
            if (track[1][1] == track[2][2] && track[2][2] == track[3][3]) {
                return true;
            }
            if (track[1][col] == track[2][col] && track[2][col] == track[3][col]) {
                return true;
            }
        } else if ((row == 3 && col == 1) || (row == 1 && col == 3)) {
            if (track[row][1] == track[row][2] && track[row][3] == track[row][2]) {
                return true;
            }
            if (track[3][1] == track[2][2] && track[2][2] == track[1][3]) {
                return true;
            }
            if (track[1][col] == track[2][col] && track[2][col] == track[3][col]) {
                return true;
            }
        } else {

            if (track[row][1] == track[row][2] && track[row][2] == track[row][3]) {
                return true;
            }
            if (track[1][col] == track[2][col] && track[2][col] == track[3][col]) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (e.getSource() == button[i][j]) {
                    button[i][j].setEnabled(false);

                    if (++count % 2 == 0) {
                        button[i][j].setText("X");
                        track[i][j] = 0;
                    } else {
                        button[i][j].setText("O");
                        track[i][j] = 1;
                    }
                    boolean f = checKWinOrNot(i, j);

                    if (count == 9) {
                        JOptionPane.showMessageDialog(null, "Match Drawn");
                        count = 0;
                        reset();
                    } else if (f) {
                        JOptionPane.showMessageDialog(null, "Player " + player + " win");
                        reset();
                        count = 0;
                    } else {
                        if (player == 1) {
                            player = 2;
                        } else {
                            player = 1;
                        }
                    }
                }
            }
        }
    }
}
