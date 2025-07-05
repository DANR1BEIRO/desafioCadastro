import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Form {

    private Form() {
    }

    public static File createFormFile() {
        File file = new File("Formulario.txt");
        if (!file.exists()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write("1 - Qual o nome e sobrenome do pet?");
                bufferedWriter.newLine();
                bufferedWriter.write("2 - Qual o tipo do pet(cachorro/gato)?");
                bufferedWriter.newLine();
                bufferedWriter.write("3 - Qual o sexo do animal?");
                bufferedWriter.newLine();
                bufferedWriter.write("4 - Qual o endereço e bairro que ele foi encontrado?");
                bufferedWriter.newLine();
                bufferedWriter.write("5 - Qual a idade aproximada do pet?");
                bufferedWriter.newLine();
                bufferedWriter.write("6 - Qual o peso aproximado do pet?");
                bufferedWriter.newLine();
                bufferedWriter.write("7 - Qual a raça do pet?");
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
