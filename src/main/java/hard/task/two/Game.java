package hard.task.two;

import java.util.*;

public class Game {
    private static final int[] FLEET = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

    private final Field playerField = new Field();
    private final Field computerField = new Field();
    private final Scanner scanner;
    private final Random random;

    public Game(Scanner scanner, Random random) {
        this.scanner = scanner;
        this.random = random;
    }

    public void play() {
        for (int length : FLEET) {
            computerField.placeRandomShip(length, random);
        }
        if (!placePlayerFleet()) {
            return;
        }

        List<Integer> computerShots = new ArrayList<>();
        for (int cell = 0; cell < Field.SIZE * Field.SIZE; cell++) {
            computerShots.add(cell);
        }
        Collections.shuffle(computerShots, random);
        int nextShot = 0;
        boolean playerTurn = true;

        System.out.println("Поле компьютера. Вы стреляете первым.");
        computerField.print(false);

        while (true) {
            if (playerTurn) {
                int[] shot = readCoordinates("Ваш выстрел (строка столбец): ", 2);
                if (shot == null) {
                    return;
                }

                Field.ShotResult result = computerField.shoot(shot[0], shot[1]);
                if (result == Field.ShotResult.REPEAT) {
                    System.out.println("Вы уже стреляли в эту клетку.");
                    continue;
                }

                System.out.println(result == Field.ShotResult.HIT ? "Попадание!" : "Промах.");
                computerField.print(false);
                if (!computerField.hasShipsLeft()) {
                    System.out.println("Вы победили! Все корабли компьютера поражены.");
                    return;
                }
                playerTurn = result == Field.ShotResult.HIT;
            } else {
                int cell = computerShots.get(nextShot++);
                int row = cell / Field.SIZE;
                int col = cell % Field.SIZE;
                Field.ShotResult result = playerField.shoot(row, col);

                System.out.printf("Компьютер: %d %d — %s%n", row + 1, col + 1,
                        result == Field.ShotResult.HIT ? "попадание" : "промах");
                playerField.print(true);
                if (!playerField.hasShipsLeft()) {
                    System.out.println("Вы проиграли. Компьютер поразил все ваши корабли.");
                    return;
                }
                playerTurn = result == Field.ShotResult.MISS;
            }
        }
    }

    private boolean placePlayerFleet() {
        System.out.println("Введите начало и конец каждого корабля: строка столбец строка столбец.");
        System.out.println("Например, 1 1 1 4 — корабль из четырёх клеток в первой строке.");

        for (int length : FLEET) {
            while (true) {
                int[] position = readCoordinates("Корабль длиной " + length + ": ", 4);
                if (position == null) {
                    return false;
                }
                if (playerField.placeShip(length, position[0], position[1], position[2], position[3])) {
                    playerField.print(true);
                    break;
                }
                System.out.println("Проверьте длину, направление и пересечения кораблей.");
            }
        }
        return true;
    }

    private int[] readCoordinates(String prompt, int count) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.out.println("Ввод завершён.");
                return null;
            }
            int[] coordinates = parseCoordinates(scanner.nextLine(), count);
            if (coordinates != null) {
                return coordinates;
            }
            System.out.println("Введите " + count + " чисел от 1 до 10 через пробел.");
        }
    }

    private int[] parseCoordinates(String line, int count) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length != count) {
            return null;
        }

        int[] coordinates = new int[count];
        try {
            for (int i = 0; i < count; i++) {
                int number = Integer.parseInt(parts[i]);
                if (number < 1 || number > Field.SIZE) {
                    return null;
                }
                coordinates[i] = number - 1;
            }
        } catch (NumberFormatException exception) {
            return null;
        }
        return coordinates;
    }

}
