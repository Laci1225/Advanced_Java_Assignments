package org.third_class;

import java.util.Arrays;

public class Battleship {
    private int[][] table = new int[7][7];

    private final int HEIGHT = 7;
    private final int WIDTH = 7;
    private final int SHIP = 1;
    private final int HIT = 2;

    public Battleship() {
        for (int[] ints : table) {
            Arrays.fill(ints, 0);
        }
    }
    public void placeShip(int x, int y) {
        if (x < 0 || x > HEIGHT || y < 0 || y > WIDTH) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        table[x][y] = SHIP;
    }

    public void shoot(int x, int y) {
        if (x < 0 || x > HEIGHT || y < 0 || y > WIDTH) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        if (table[x][y] == SHIP) {
            table[x][y] = HIT;
        } else {
            table[x][y] = SHIP;
        }
    }
    public int hitCount() {
        int hitCount = 0;
        for (int[] row : table) {
            for (int cell : row) {
                if (cell == HIT) {
                    hitCount++;
                }
            }
        }
        return hitCount;
    }
    public int safeCount() {
        int safeCount = 0;
        for (int[] row : table) {
            for (int cell : row) {
                if (cell == SHIP) {
                    safeCount++;
                }
            }
        }
        return safeCount;
    }


    public int[][] getTable() {
        return table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }
}
