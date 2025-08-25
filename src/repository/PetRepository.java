package repository;
import model.Pet;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetRepository {

    public void salvar(Pet pet) {
        File directory = new File("PetsCadastrados");
        if (!directory.exists()) directory.mkdirs();

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataFormatada = localDateTime.format(dateTimeFormatter);

        File formularioDoPet = new File("PetsCadastrados" + File.separator + dataFormatada + "-" + pet.getName().toUpperCase().replace(" ", "") + ".txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(formularioDoPet))) {
            bufferedWriter.write("1 - " + pet.getName());
            bufferedWriter.newLine();
            bufferedWriter.write("2 - " + pet.getType());
            bufferedWriter.newLine();
            bufferedWriter.write("3 - " + pet.getGender());
            bufferedWriter.newLine();
            bufferedWriter.write("4 - " + pet.getAdress());
            bufferedWriter.newLine();
            bufferedWriter.write("5 - " + pet.getAge());
            bufferedWriter.newLine();
            bufferedWriter.write("6 - " + pet.getWeight());
            bufferedWriter.newLine();
            bufferedWriter.write("7 - " + pet.getBreed());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*Salva e lê pets da pasta PetsCadastrados*/