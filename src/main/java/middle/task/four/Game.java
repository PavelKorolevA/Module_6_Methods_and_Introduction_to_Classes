package middle.task.four;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Scanner scanner;

    public Game(Player firstPlayer, Player secondPlayer, Scanner scanner) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.scanner = scanner;
    }

    public void play() {
        int target = new Random().nextInt(100) + 1;
        long sum = 0;
        Player currentPlayer = firstPlayer;
        Player opponent = secondPlayer;

        System.out.println("Компьютер загадал число от 1 до 100.");

        while (true) {
            int number = readMove(currentPlayer);
            if (number == 0) {
                System.out.println("Ввод завершён.");
                return;
            }

            sum += number;
            System.out.println("Общая сумма: " + sum);

            if (sum > target) {
                opponent.addWin();
                System.out.println("Загаданное число: " + target);
                System.out.println("Проиграл: " + currentPlayer.getName());
                System.out.println("Победитель: " + opponent.getName());
                System.out.println("Побед: " + opponent.getWins());
                return;
            }

            Player previousPlayer = currentPlayer;
            currentPlayer = opponent;
            opponent = previousPlayer;
        }
    }

    private int readMove(Player player) {
        while (true) {
            System.out.print(player.getName() + ", введите положительное целое число: ");
            if (!scanner.hasNext()) {
                return 0; // Ввод закончился.
            }

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number > 0) {
                    return number;
                }
            } else {
                scanner.next();
            }

            System.out.println("Введите положительное целое число.");
        }
    }
}
