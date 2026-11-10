package middle.task.two;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите ваше имя: ");
        String playerName = input.nextLine();

        Player player = new Player(playerName, "X");
        Player computer = new Player("Computer", "0");
        Game game = new Game();

        System.out.println(player.getName() + " играет за " + player.getSymbol());
        System.out.println(computer.getName() + " играет за " + computer.getSymbol());

        game.drawBoard();

        while (true) {
            boolean moveMade = false;
            while (!moveMade) {
                System.out.println("Введите номер ячейки от 1 до 9: ");
                int cell = input.nextInt();

                moveMade = game.makeMove(cell, player.getSymbol());
            }
            game.drawBoard();

            if (game.hasWon(player.getSymbol())) {
                System.out.println("Победитель " + player.getName());
                return;
            }

            int computerCell = game.findFreeCell();
            if (computerCell != -1) {
                game.makeMove(computerCell, computer.getSymbol());
                game.drawBoard();
                if (game.hasWon(computer.getSymbol())) {
                    System.out.println("Победитель " + computer.getName());
                    return;
                }
            } else {
                System.out.println("НИЧЬЯ!");
                return;
            }
        }
    }
}
