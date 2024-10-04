package org.first_class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GOLPane extends JComponent implements ActionListener, MouseListener {
    private GOLState state;
    private Timer timer;
    public GOLPane(GOLState state) {
        timer = new Timer(100, this);
        this.state = state;
        this.addMouseListener(this);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < state.points.length; i++) {
            for (int j = 0; j < state.points.length; j++) {
                g.setColor(state.points[i][j] ? Color.BLACK : Color.WHITE);
                g.fillRect(i*10, j*10, 10, 10);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        state = state.step();
        repaint();
    }

    public void toggle() {
        if (timer.isRunning()) {
            timer.stop();
            state = new GOLState(state.points.length);
        } else {
            timer.start();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        state.points[e.getX() / 10][e.getY() / 10] = !state.points[e.getX() / 10][e.getY() / 10];
        repaint();
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
class GOLState {
    public final boolean[][] points;
    public GOLState(int size) {
        points = new boolean[size][size];
    }
    public int getNumNeighbors(int i, int j) {
        int numNeighbors = 0;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                if (di == 0 && dj == 0) {
                    continue;
                }
                if (i+di > 0 && j+dj > 0 && i+di <= points.length-1 && j+dj <= points.length-1 &&  points[i + di][j + dj]) {
                    numNeighbors++;
                }
            }
        }
        return numNeighbors;
    }

    public GOLState step(){
        GOLState newState = new GOLState(points.length);
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                int numNeighbors =  getNumNeighbors(i, j);
                if (points[i][j]) {
                    newState.points[i][j] = numNeighbors >= 2 && numNeighbors <= 3;
                } else {
                    newState.points[i][j] = numNeighbors == 3;
                }
            }
        }
        return newState;
    }
}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game of life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 640);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame.getContentPane().add(panel);
        JButton button = new JButton("Start");
        panel.add(button);
        GOLState state = new GOLState(60);
        GOLPane golPane = new GOLPane(state);
        panel.add(golPane);
        button.addActionListener(e -> {
            button.setText(button.getText().equals("Start") ? "Stop" : "Start");
            golPane.toggle();
        });
        frame.setVisible(true);
    }
}