package repository;

import model.Address;
import model.Gender;
import model.Pet;
import model.Type;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PetRepository {

    public void salvar(Pet pet) {

        // Cria uma pasta chamada "PetsCadastrados" na raíz do projeto
        File directory = new File("PetsCadastrados");
        if (!directory.exists()) directory.mkdirs();

        /*
        Essa parte fica responsável por criar o padrão de data/hora que será utilizado
        para compor o nome do arquivo txt onde cada pet será armazenado.
         */
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
            bufferedWriter.write("4 - " + pet.getAddress());
            bufferedWriter.newLine();
            bufferedWriter.write("5 - " + pet.getAge() + " anos");
            bufferedWriter.newLine();
            bufferedWriter.write("6 - " + pet.getWeight() + " kg");
            bufferedWriter.newLine();
            bufferedWriter.write("7 - " + pet.getBreed());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Pet> buscarTodos() {
        File diretorioDeArmazenamentoDosPets = new File("PetsCadastrados");
        File[] listaDePetsCadastrados = diretorioDeArmazenamentoDosPets.listFiles();
        List<Pet> listaDePets = new ArrayList<>();

        for (File arquivoDoPet : listaDePetsCadastrados) {
            Pet newPet = new Pet();
            newPet.setPetFilename(arquivoDoPet.getName());

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoDoPet))) {
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] split = line.trim().split(" - ");

                    String key = split[0];
                    String value = split[1];

                    switch (key) {
                        case "1" -> newPet.setName(value);
                        case "2" -> newPet.setType(Type.valueOf(value));
                        case "3" -> newPet.setGender(Gender.valueOf(value));
                        case "4" -> {
                            String[] endereco = value.split(", ");
                            Address address = new Address(endereco[0], endereco[1], endereco[2]);
                            newPet.setAddress(address);
                        }
                        case "5" -> {
                            String[] idade = value.split(" ");
                            if (idade[0].equals("null")) {
                                newPet.setAge(null);
                            } else {
                                newPet.setAge(Float.valueOf(idade[0]));
                            }
                        }
                        case "6" -> {
                            String[] weight = value.split(" ");
                            if (weight[0].equals("null")) {
                                newPet.setWeight(null);
                            } else {
                                newPet.setWeight(Float.valueOf(weight[0]));
                            }
                        }
                        case "7" -> newPet.setBreed(value);
                    }
                }
                listaDePets.add(newPet);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listaDePets;
    }

    public void atualizarPet(Pet pet) {
        /*
    Cria um arquivo com o mesmo caminho do arquivo que será deletado
    Se o arquivo existir, ele é deletado
    por fim, Salva o novo pet com os dados atualizados
     */

        File arquivoAntigo = new File("PetsCadastrados" + File.separator + pet.getFileName());

        if (arquivoAntigo.exists()) {
            arquivoAntigo.delete();
        }

        salvar(pet);
    }

    public void deletarPet(Pet pet) {

        File arquivoAntigo = new File("PetsCadastrados" + File.separator + pet.getFileName());

        if (arquivoAntigo.exists()) {
            arquivoAntigo.delete();
        }
    }
}