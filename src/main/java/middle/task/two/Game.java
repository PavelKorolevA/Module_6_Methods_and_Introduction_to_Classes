package middle.task.two;

public class Game {
    String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public void drawBoard() {
        System.out.println(board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---------");
        System.out.println(board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---------");
        System.out.println(board[6] + " | " + board[7] + " | " + board[8]);
    }

    public boolean makeMove(int cell, String symbol){
        if (cell > board.length || cell < 1) {
            System.out.println("Номер ячейки должен быть от 1 до 9");
            return false;
        }
        int index = cell - 1;
        if (board[index].equals("X") || board[index].equals("0")) {
            System.out.println("Ячейка уже занята");
            return false;
        }
        board[index] = symbol;
        return true;
    }

    public int findFreeCell() {
        for (int i = 0; i < board.length; i++) {
            boolean occupied = board[i].equals("X") || board[i].equals("0");
            if (!occupied) {
                return i+1;
            }
        }
        return -1;
    }

    public boolean hasWon(String symbol) {
        boolean topRow = matchesLine(0, 1, 2, symbol);
        boolean middleRow = matchesLine(3, 4, 5, symbol);
        boolean lowRow = matchesLine(6, 7, 8, symbol);
        boolean oneRow = matchesLine(0, 3, 6, symbol);
        boolean twoRow = matchesLine(1, 4, 7, symbol);
        boolean threeRow = matchesLine(2, 5, 8, symbol);
        boolean diagonalOne = matchesLine(0, 4, 8, symbol);
        boolean diagonalTwo = matchesLine(2, 4, 6, symbol);
        return topRow || middleRow || lowRow || oneRow || twoRow || threeRow || diagonalOne || diagonalTwo;
    }

    public boolean matchesLine(int a, int b, int c, String symbol) {
        boolean first = board[a].equals(symbol);
        boolean second = board[b].equals(symbol);
        boolean third = board[c].equals(symbol);
        return first && second && third;
    }
}
