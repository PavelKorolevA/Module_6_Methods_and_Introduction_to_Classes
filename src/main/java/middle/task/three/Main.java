package middle.task.three;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int sticksLeft = 20;
        drawSticks(sticksLeft);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ваше имя: ");
        String inputPlayerName = scanner.nextLine();

        Random random = new Random();

        while (sticksLeft > 1) {
            System.out.print("Сколько палочек снять? ");
            int inputNumber = scanner.nextInt();

            if (inputNumber >= 1 && inputNumber <= 3 && inputNumber < sticksLeft) {
                sticksLeft -= inputNumber;
                drawSticks(sticksLeft);
                if (sticksLeft == 1) {
                    System.out.println("Победитель " + inputPlayerName);
                    return;
                } else {
                    int max = Math.min(3, sticksLeft - 1);
                    int computerTake = random.nextInt(max) + 1;
                    sticksLeft -= computerTake;
                    System.out.println("Computer сходил " + computerTake);
                    drawSticks(sticksLeft);
                    if (sticksLeft == 1) {
                        System.out.println("Победитель Computer");
                    }
                }
            }
        }
    }

    private static void drawSticks(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("|");
        }
        System.out.println();
    }
}
