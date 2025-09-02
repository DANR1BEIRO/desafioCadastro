package Util;

import java.io.*;

public class FilesUtil {

    public static final String PERGUNTA_NOME = "Digite nome e sobrenome do pet:";
    public static final String PERGUNTA_TIPO = "Tipo do pet:";
    public static final String PERGUNTA_SEXO = "Sexo do pet:";
    public static final String PERGUNTA_ENDERECO = "Endereço que o pet foi encontrado:";
    public static final String PERGUNTA_IDADE = "Idade aproximada do pet em anos (decimal para meses. Ex: 0.5 ano = 6 meses):";
    public static final String PERGUNTA_PESO = "Peso aproximado do pet:";
    public static final String PERGUNTA_RACA = "Raça do pet:";
    private FilesUtil() {
    }

    public static File createMainMenuFile() {
        File menu = new File("MenuOptions.txt");
        if (!menu.exists()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(menu))) {
                bufferedWriter.write("1. Cadastrar um novo pet");
                bufferedWriter.newLine();
                bufferedWriter.write("2. Alterar os dados do pet cadastrado");
                bufferedWriter.newLine();
                bufferedWriter.write("3. Deletar um pet cadastrado");
                bufferedWriter.newLine();
                bufferedWriter.write("4. Listar todos os pets cadastrados");
                bufferedWriter.newLine();
                bufferedWriter.write("5. Listar por critério(idade, nome, raça)");
                bufferedWriter.newLine();
                bufferedWriter.write("6. Sair");
                bufferedWriter.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return menu;
    }

    public static void printMainMenuFromFile() {
        File menu = FilesUtil.createMainMenuFile();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(menu))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File createPetRegistrationFormFile() {
        File file = new File("FormularioDeCadastroDoPet.txt");
        if (!file.exists()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write(PERGUNTA_NOME);
                bufferedWriter.newLine();
                bufferedWriter.write(PERGUNTA_TIPO);
                bufferedWriter.newLine();
                bufferedWriter.write(PERGUNTA_SEXO);
                bufferedWriter.newLine();
                bufferedWriter.write(PERGUNTA_ENDERECO);
                bufferedWriter.newLine();
                bufferedWriter.write(PERGUNTA_IDADE);
                bufferedWriter.newLine();
                bufferedWriter.write(PERGUNTA_PESO);
                bufferedWriter.newLine();
                bufferedWriter.write(PERGUNTA_RACA);
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}


