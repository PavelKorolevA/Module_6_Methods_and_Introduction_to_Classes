package hard.task.one;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardDesk desk = new CardDesk(new Random());
        Game game = new Game(scanner, desk);
        game.play();
    }
}
