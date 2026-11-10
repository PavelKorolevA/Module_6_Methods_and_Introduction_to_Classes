package middle.task.one;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Какое арифметическое действие хотите выполнить?");
        String inputVariable = scanner.nextLine();
        System.out.println("Введите первое число: ");
        int a = scanner.nextInt();
        System.out.println("Введите второе число: ");
        int b = scanner.nextInt();

        switch (inputVariable) {
            case "+": arithmeticOperations.addition(a, b);
            break;
            case "-": arithmeticOperations.subtraction(a, b);
            break;
            case "*": arithmeticOperations.multiplication(a, b);
            break;
            case "/": arithmeticOperations.division(a, b);
            break;
            default:
                System.out.println("Вы ввели неверное действие");
        }
    }
}
