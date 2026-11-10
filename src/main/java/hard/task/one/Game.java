package hard.task.one;

import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private final CardDesk desk;

    public Game(Scanner scanner, CardDesk desk) {
        this.scanner = scanner;
        this.desk = desk;
    }

    public void play() {
        Hand player = new Hand();
        Hand dealer = new Hand();

        for (int i = 0; i < 2; i++) {
            player.add(desk.draw());
            dealer.add(desk.draw());
        }

        System.out.println("Дилер получил две карты.");

        while (true) {
            int playerPoints = player.points();
            System.out.println("Ваши карты: " + player + " — очков: " + playerPoints);

            if (playerPoints > 21) {
                showDealer(dealer);
                System.out.println("Перебор. Ваша ставка проиграла. Дилер победил.");
                return;
            }

            System.out.print("1 — взять карту, 2 — остановиться: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Ввод завершён.");
                return;
            }

            switch (scanner.nextLine().trim()) {
                case "1":
                    player.add(desk.draw());
                    break;
                case "2":
                    finishRound(playerPoints, dealer);
                    return;
                default:
                    System.out.println("Введите 1 или 2.");
            }
        }
    }

    private void finishRound(int playerPoints, Hand dealer) {
        showDealer(dealer);
        int dealerPoints = dealer.points();

        if (dealerPoints > 21 || playerPoints > dealerPoints) {
            System.out.println("Вы победили.");
        } else if (playerPoints == dealerPoints) {
            System.out.println("Ничья.");
        } else {
            System.out.println("Дилер победил.");
        }
    }

    private void showDealer(Hand dealer) {
        System.out.println("Карты дилера: " + dealer + " — очков: " + dealer.points());
    }
}
