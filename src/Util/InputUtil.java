package Util;

import model.Pet;
import model.Type;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUtil {
    private InputUtil() {
    }

    public static String getPetName(Scanner scanner) {
        while (true) {
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                name = (ConstantForNoData.DADO_NAO_INFORMADO);
            } else {
                Pattern pattern = Pattern.compile("^[a-zA-Z]{3,}( {1,2}[a-zA-Z]{3,})*$");
                Matcher matcher = pattern.matcher(name);

                if (!matcher.matches()) {
                    System.out.println("Entrada inválida! Certifique-se de fornecer nome e sobrenome.");
                    continue;
                }
            }
            return name;
        }
    }

    public static Type getPetType(Scanner scanner) {
        while (true) {
            System.out.println("1 - Cachorro\n2 - Gato");
            String type = scanner.nextLine().trim();

            if (type.isEmpty()) {
                System.out.println("Campos vazios não são permitidos");

            } else {
                switch (type) {
                    case "1" -> {
                        return Type.CACHORRO;
                    }
                    case "2" -> {
                        return Type.GATO;
                    }
                    default -> {
                        System.out.println("Selecione uma das opções disponíveis");
                    }
                }
            }
        }
    }

    public static Float getPetAge(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().replace(",", ".");

            if (input.isEmpty()) {
                return null;
            }

            if (input.matches("\\d+(\\.\\d+)?")) {
                float age = Float.parseFloat(input);

                if (age > 20) {
                    throw new IllegalArgumentException("Não é possível definir a idade como maior do que 20");
                }
                if (age < 0) {
                    throw new IllegalArgumentException("Não é permitido definir a idade como menor do que 0");
                }
                if (age < 1) {
                    age *= 12;
                    System.out.printf("Idade convertida para meses: %.1f meses\n.", age);
                }

                return age;

            } else {
                System.out.println("Apenas números são permitidos para idade");
            }
        }
    }

    public static Float getPetWeight(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().replace(",", ".");

            if (input.isEmpty()) {
                return null;
            }

            if (input.matches("\\d+(\\.\\d+)?")) {
                float weight = Float.parseFloat(input);

                if (weight > 60) {
                    throw new IllegalArgumentException("Não é permitido definir o peso como maior do que 60kg");
                }
                if (weight < 0.5) {
                    throw new IllegalArgumentException("Não é permitido definir o peso como menor do que 0.5kg");
                }

                return weight;

            } else {
                System.out.println("Apenas números são permitidos para idade");
            }
        }
    }

    public static int getIntMenuOptions(String message, Scanner scanner) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input == null || input.isEmpty()) {
                System.out.println("Entradas vazias não são permitidas");
                continue;
            }
            if (!input.matches("[1-6]")) {
                System.out.println("Escolha uma das opcões disponíveis");
                continue;
            }
            return Integer.parseInt(input);
        }
    }

    public static String getStringAddress(String message, Scanner scanner) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                input = ConstantForNoData.DADO_NAO_INFORMADO;

            } else {
                Pattern pattern = Pattern.compile("^(\\p{L}[\\p{L}0-9.-]*)+( \\p{L}[\\p{L}0-9.-]{1,})*$", Pattern.UNICODE_CHARACTER_CLASS);
                Matcher matcher = pattern.matcher(input);

                if (!matcher.matches()) {
                    System.out.println("Nome inválido para endereço");
                    continue;
                }
            }
            return input;
        }
    }

    public static String getHouseNumberInput(String message, Scanner scanner) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                input = ConstantForNoData.DADO_NAO_INFORMADO;
            } else {
                Pattern pattern = Pattern.compile("^[\\p{L}0-9 .-]+$");
                Matcher matcher = pattern.matcher(input);

                if (!matcher.matches()) {
                    System.out.println("Entrada inválida para número da casa");
                }
            }
            return input;
        }
    }
}
