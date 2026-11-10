package hard.task.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Field {
    public static final int SIZE = 10;
    private static final int WATER = 0;
    private static final int SHIP = 1;
    private static final int HIT = 2;
    private static final int MISS = 3;

    private final int[][] cells = new int[SIZE][SIZE];
    private int remainingShipCells;

    public enum ShotResult {
        HIT, MISS, REPEAT
    }

    public boolean placeShip(int length, int row1, int col1, int row2, int col2) {
        if (!canPlaceShip(length, row1, col1, row2, col2)) {
            return false;
        }

        for (int row = Math.min(row1, row2); row <= Math.max(row1, row2); row++) {
            for (int col = Math.min(col1, col2); col <= Math.max(col1, col2); col++) {
                cells[row][col] = SHIP;
            }
        }
        remainingShipCells += length;
        return true;
    }

    private boolean canPlaceShip(int length, int row1, int col1, int row2, int col2) {
        if (!inside(row1, col1) || !inside(row2, col2)) {
            return false;
        }
        if (row1 != row2 && col1 != col2) {
            return false;
        }
        if (Math.abs(row2 - row1) + Math.abs(col2 - col1) + 1 != length) {
            return false;
        }

        for (int row = Math.min(row1, row2); row <= Math.max(row1, row2); row++) {
            for (int col = Math.min(col1, col2); col <= Math.max(col1, col2); col++) {
                if (cells[row][col] != WATER) {
                    return false;
                }
            }
        }
        return true;
    }

    void placeRandomShip(int length, Random random) {
        List<int[]> positions = new ArrayList<>();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int endCol = col + length - 1;
                if (canPlaceShip(length, row, col, row, endCol)) {
                    positions.add(new int[]{row, col, row, endCol});
                }

                int endRow = row + length - 1;
                if (length > 1 && canPlaceShip(length, row, col, endRow, col)) {
                    positions.add(new int[]{row, col, endRow, col});
                }
            }
        }

        if (positions.isEmpty()) {
            throw new IllegalStateException("Нет места для корабля длиной " + length);
        }
        int[] position = positions.get(random.nextInt(positions.size()));
        placeShip(length, position[0], position[1], position[2], position[3]);
    }

    public ShotResult shoot(int row, int col) {
        if (!inside(row, col)) {
            throw new IllegalArgumentException("Координаты вне поля");
        }
        if (cells[row][col] == SHIP) {
            cells[row][col] = HIT;
            remainingShipCells--;
            return ShotResult.HIT;
        }
        if (cells[row][col] == WATER) {
            cells[row][col] = MISS;
            return ShotResult.MISS;
        }
        return ShotResult.REPEAT;
    }

    public boolean hasShipsLeft() {
        return remainingShipCells > 0;
    }

    public void print(boolean showShips) {
        System.out.print("   ");
        for (int col = 1; col <= SIZE; col++) {
            System.out.printf("%2d ", col);
        }
        System.out.println();

        for (int row = 0; row < SIZE; row++) {
            System.out.printf("%2d ", row + 1);
            for (int col = 0; col < SIZE; col++) {
                System.out.printf("%2c ", symbol(cells[row][col], showShips));
            }
            System.out.println();
        }
    }

    private char symbol(int cell, boolean showShips) {
        if (cell == HIT) {
            return 'X';
        }
        if (cell == MISS) {
            return 'o';
        }
        return cell == SHIP && showShips ? '#' : '.';
    }

    private boolean inside(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }
}
