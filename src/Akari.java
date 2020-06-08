import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Akari implements ActionListener {

    private static Cell[][] board;
    Scanner s = new Scanner(System.in);
    int r;
    int c;

    public Akari() {

        //define cell border types
        Border sel = new LineBorder(Color.BLUE, 3);
        Border def = new LineBorder(Color.GRAY);

        //init r & c vars
        System.out.println("Enter level code: ");
        r = s.nextInt();
        //System.out.println("Enter number of columns: ");
        c = s.nextInt();
        System.out.println();

        //init board 2D array of custom JButton objects
        board = new Cell[r][c];

        //init frame
        JFrame f = new JFrame("Grid Tests");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(750, 750);
        f.setResizable(false);
        f.setLayout(new GridLayout(r, c));

        //fill board with Cell objects & display on frame
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j] = new Cell(new MyJButton(), i, j);
                f.add(board[i][j].getButton());
            }
        }

        f.setVisible(true);

        int boardIn;

        //fill board with user input
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                board[i][j].getButton().setBorder(sel);
                //System.out.println("Enter state for cell in row " + (i + 1) + " column " + (j + 1) + ": ");
                board[i][j].getButton().setFont(new Font("Arial", Font.PLAIN, 48));
                boardIn = s.nextInt();

                if (boardIn <= 4) {
                    board[i][j].getButton().setBackground(Color.BLACK);
                    board[i][j].getButton().setForeground(Color.WHITE);
                    board[i][j].setType("block" + boardIn);
                    board[i][j].getButton().setText("" + boardIn);
                } else if (boardIn == 5) {
                    board[i][j].getButton().setBackground(Color.BLACK);
                    board[i][j].setType("block");
                } else {
                    board[i][j].getButton().setBackground(Color.WHITE);
                    board[i][j].setType("empty");

                    int finalJ = j;
                    int finalI = i;
                    board[i][j].getButton().addActionListener(e -> {

                        //System.out.println("x = " + board[finalI][finalJ].getCol() + ", y = " + board[finalI][finalJ].getRow());

                        if (board[finalI][finalJ].getType().equals("empty")) {

                            board[finalI][finalJ].getButton().setBackground(Color.YELLOW);
                            board[finalI][finalJ].getButton().setText("O");
                            board[finalI][finalJ].setType("bulb");
                            addRays(board[finalI][finalJ]);

                        } else if (board[finalI][finalJ].getType().equals("bulb")) {

                            board[finalI][finalJ].getButton().setBackground(Color.WHITE);
                            board[finalI][finalJ].getButton().setText("");
                            board[finalI][finalJ].setType("empty");
                            subRays(board[finalI][finalJ]);

                        }

                    });
                }

                board[i][j].getButton().setBorder(def);
            }
        }
        playGame();
    }

    public static void main(String[] args) {
        new Akari();
    }

    private void addRays(Cell cell) {
        cell.addLight();
        int y = cell.getRow();
        int x = cell.getCol();

        while (y > 0) {
            y--;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) {
                board[y][x].addLight();
            } else {
                y = 0;
            }
        }
        y = cell.getRow();

        while (x > 0) {
            x--;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) {
                board[y][x].addLight();
            } else {
                x = 0;
            }
        }
        x = cell.getCol();

        while (y < r - 1) {
            y++;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) {
                board[y][x].addLight();
            } else {
                y = r;
            }
        }
        y = cell.getRow();

        while (x < c - 1) {
            x++;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) {
                board[y][x].addLight();
            } else {
                x = c;
            }
        }

        updateBoard();
    }

    private void subRays(Cell cell) {
        cell.subLight();
        int y = cell.getRow();
        int x = cell.getCol();

        while (y > 0) {
            y--;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) {
                board[y][x].subLight();
            } else {
                y = 0;
            }
        }
        y = cell.getRow();

        while (x > 0) {
            x--;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) {
                board[y][x].subLight();
            } else {
                x = 0;
            }
        }
        x = cell.getCol();

        while (y < r - 1) {
            y++;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) {
                board[y][x].subLight();
            } else {
                y = r;
            }
        }
        y = cell.getRow();

        while (x < c - 1) {
            x++;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) {
                board[y][x].subLight();
            } else {
                x = c;
            }
        }

        updateBoard();
    }

    public void updateBoard() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j].getLightLevel() == 0 && board[i][j].getType().equals("lit")) {
                    board[i][j].getButton().setBackground(Color.WHITE);
                    board[i][j].setType("empty");
                }
                if (board[i][j].getLightLevel() > 0 && board[i][j].getType().equals("empty")) {
                    board[i][j].getButton().setBackground(Color.YELLOW);
                    board[i][j].setType("lit");
                }
            }
        }
    }

    public void playGame() {
        System.out.println("Left click to place bulbs, right click to place x's");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j].getButton().setEnabled(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
