package service;

import Util.ConstantForNoData;
import Util.FilesUtil;
import Util.InputUtil;
import model.Address;
import model.Gender;
import model.Pet;
import model.Type;
import repository.PetRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetService {
    private Scanner scanner;
    private PetRepository petRepository;

    public PetService(Scanner scanner, PetRepository petRepository) {
        this.scanner = scanner;
        this.petRepository = petRepository;
    }

    public void cadastrarNovoPet() {
        Pet novoPet = new Pet();

        File cadastroMenu = FilesUtil.createPetRegistrationFormFile();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(cadastroMenu))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                System.out.println(line);

                if (line.startsWith("Digite nome e sobrenome do pet:")) {
                    String name = InputUtil.getPetName(scanner);
                    novoPet.setName(name);
                }

                if (line.startsWith("Tipo do pet:")) {
                    Type type = InputUtil.getPetType(scanner);
                    novoPet.setType(type);
                }

                if (line.startsWith("Sexo do pet:")) {
                    Gender gender = InputUtil.getPetGender(scanner);
                    novoPet.setGender(gender);
                }

                if (line.startsWith("Endereço que o pet foi encontrado:")) {
                    Address address = new Address();
                    String houseNumber = InputUtil.getHouseNumberInput("Número da casa: ", scanner);
                    String street = InputUtil.getStringAddress("Nome da rua: ", scanner);
                    String city = InputUtil.getStringAddress("Nome da cidade: ", scanner);

                    address.setHouseNumber(houseNumber);
                    address.setStreet(street);
                    address.setCity(city);
                    novoPet.setAddress(address);
                }

                if (line.startsWith("Idade aproximada do pet em anos (decimal para meses. Ex: 0.5 ano = 6 meses")) {
                    Float age = InputUtil.getPetAge(scanner);
                    novoPet.setAge(age);
                }

                if (line.startsWith("Peso aproximado do pet:")) {
                    Float weight = InputUtil.getPetWeight(scanner);
                    novoPet.setWeight(weight);
                }

                if (line.startsWith("Raça do pet:")) {
                    String breed = scanner.nextLine();

                    if (breed.isEmpty()) {
                        breed = ConstantForNoData.DADO_NAO_INFORMADO;
                    }
                    Pattern pattern = Pattern.compile("^\\p{L}{2,}$", Pattern.UNICODE_CHARACTER_CLASS);
                    Matcher matcher = pattern.matcher(breed);

                    if (matcher.matches()) {
                        novoPet.setBreed(breed);
                    } else {
                        throw new IllegalArgumentException("Entrada inválida! Apenas letras são permitidas para raça");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        petRepository.salvar(novoPet);
    }
}


/*Cadastrac ok
 * edita
 * deleta
 * lista*/

