package com.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

import static javax.swing.BorderFactory.createLineBorder;

public class TicTacToeUI extends JFrame {

    public static Random r = new Random();
    public static final String TITLE_NAME = "Tic Tac Toe";
    public static final int numRows = 3;
    public static final int numCols = 3;
    public static final String XImageAddress = "C:\\Users\\Rahul\\IdeaProjects\\TicTacToe\\src\\resources\\x-image.png";
    public static final String OImageAddress = "C:\\Users\\Rahul\\IdeaProjects\\TicTacToe\\src\\resources\\o-image.png";
    public static final String player1Turn = "Player 1's turn";
    public static final String player2Turn = "Player 2's turn";
    public static final String matchDrawn = "Draw";


    private HashSet<JButton> clickedButtons = new HashSet<>();
    private JLabel displayText;
    private Map<JButton, Coordinate> jButtonCoordinateMap = new HashMap<>();
    private TicTacToeBackend ticTacToeBackend = new TicTacToeBackend();
    private boolean isGameEndedFlag = false;

    TicTacToeUI() {

        setLayout(new FlowLayout());
        setSize(700, 700);
        setTitle(TITLE_NAME);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel1 = new JPanel(new GridLayout(numRows, numCols));
        panel1.setPreferredSize(new Dimension(300, 300));
        panel1.setBorder(createLineBorder(Color.BLACK, 1));
        panel1.setVisible(true);
        panel1.setOpaque(true);

        JButton jButtons[] = new JButton[9];
        List<JButton> jButtonList = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                JButton jb = new JButton();
                jb.setPreferredSize(new Dimension(100, 100));
                jb.setSize(new Dimension(100, 100));
                jb.setVisible(true);
                //jb.setBackground(getColor());
                jb.setOpaque(true);

//                Consumer<ActionEvent> actionEventConsumer = this::actionPerformedOnGrid;
//                Consumer<ActionEvent> actionEventConsumer1 = (actionEvent) -> {
//                    ///LAMBDA EXPRESSION
//                };
//
//                jb.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//
//                    }
//                });
//
//                jb.addActionListener((actionEvent) -> {
//                    ///LAMBDA EXPRESSION
//                });

                jb.addActionListener(this::actionPerformedOnGrid);  //METHOD REFERENCE

                jButtons[i] = jb;
                jButtonList.add(jb);
                panel1.add(jb);
                Coordinate coordinate = new Coordinate(i, j);
                jButtonCoordinateMap.put(jb, coordinate);
            }
        }

        System.out.println("");

        JPanel panel2 = new JPanel(new FlowLayout());

        panel2.setVisible(true);
        panel2.setBorder(createLineBorder(Color.BLACK, 1));
        panel2.setOpaque(true);
        panel2.setBackground(getColor());
        panel2.setSize(300, 150);

        displayText = new JLabel(player1Turn);
        JButton replayButton = new JButton("Replay");
        replayButton.addActionListener(this::actionPerformedOnReplay);


        panel2.add(displayText);
        panel2.add(replayButton);

        add(panel1);
        add(panel2);

        setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        TicTacToeUI ui = new TicTacToeUI();
        int a[] = new int[5];
        List<Integer> l1 = new ArrayList<>();
    }

    private static int getRandomNumber() {
        return Math.abs(r.nextInt()) % 256;
    }

    private static Color getColor() {
        int r = getRandomNumber();
        int g = getRandomNumber();
        int b = getRandomNumber();
        int a = getRandomNumber();
        return new Color(r, g, b, a);
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }


    public void actionPerformedOnGrid(ActionEvent e) {

        JButton jButton = (JButton) e.getSource();

        if (clickedButtons.contains(jButton) || isGameEndedFlag)
            return;
        else
            clickedButtons.add(jButton);

        Coordinate coordinate = jButtonCoordinateMap.get(jButton);


        ticTacToeBackend.fillTheBox(coordinate.getX(), coordinate.getY());
        boolean isPlayerWinning = ticTacToeBackend.isPlayerWinning();
        int currentPlayer = ticTacToeBackend.getCurrentPlayer();

        Path imagepath = (currentPlayer == 1) ? Paths.get(XImageAddress) : Paths.get(OImageAddress);  // Ternary Operator

        ImageIcon icon = null;
        try {
            icon = new ImageIcon(imagepath.toUri().toURL());
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
            throw new RuntimeException("URL is malformed!!!!!", malformedURLException);
        }
        jButton.setIcon(resizeIcon(icon, jButton.getWidth(), jButton.getHeight()));

        if (isPlayerWinning) {
            isGameEndedFlag = true;
            displayText.setText("Player " + currentPlayer + " Wins");
        } else if (clickedButtons.size() == 9) {
            isGameEndedFlag = true;
            displayText.setText(matchDrawn);
        } else {
            if (currentPlayer == 1)
                displayText.setText(player2Turn);
            else
                displayText.setText(player1Turn);

            ticTacToeBackend.toggleCurrentPlayer();
        }
    }


    public void actionPerformedOnReplay(ActionEvent e) {
        /**
         FRONT END & BACK END to be RESET.
         **/
        // clear grid - remove images
        for (JButton jButton : clickedButtons){
            jButton.setIcon(null);
        }
        // clear clickedButons
        clickedButtons.clear();
        // isGameEnded = false
        isGameEndedFlag = false;
        // change displayText to player 1s turn
        displayText.setText(player1Turn);


        // clear data in box
        ticTacToeBackend = new TicTacToeBackend();
        //set current player = 1
    }
}
