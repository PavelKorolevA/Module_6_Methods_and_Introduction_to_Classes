package middle.task.four;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Имя первого игрока: ");
        if (!scanner.hasNextLine()) {
            return;
        }
        Player firstPlayer = new Player(scanner.nextLine().trim());

        System.out.print("Имя второго игрока: ");
        if (!scanner.hasNextLine()) {
            return;
        }
        Player secondPlayer = new Player(scanner.nextLine().trim());

        Game game = new Game(firstPlayer, secondPlayer, scanner);
        game.play();
    }
}
