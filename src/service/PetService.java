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
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        novoPet.setName(ConstantForNoData.DADO_NAO_INFORMADO);
                    } else {
                        Pattern pattern = Pattern.compile("^[a-zA-Z]{3,}( {1,2}[a-zA-Z]{3,})$");
                        Matcher matcher = pattern.matcher(name);
                        if (matcher.matches()) {
                            novoPet.setName(name);
                        } else {
                            throw new IllegalArgumentException("Entrada inválida! Certifique-se de fornecer nome e sobrenome.");
                        }
                    }
                }

                if (line.startsWith("Tipo do pet:")) {
                    System.out.println("1 - Cachorro\n2 - Gato");
                    String type = scanner.nextLine().trim();

                    if (type.isEmpty()) {
                        throw new IllegalArgumentException("Campos vazios não são permitidos");
                    } else {
                        switch (type) {
                            case "1" -> {
                                novoPet.setType(Type.CACHORRO);
                                System.out.println("Escolheu: " + Type.CACHORRO.getDescription());
                            }
                            case "2" -> {
                                novoPet.setType(Type.GATO);
                                System.out.println("Escolheu: " + Type.GATO.getDescription());
                            }
                            default -> throw new IllegalArgumentException("Selecione uma das opções disponíveis");
                        }
                    }
                }

                if (line.startsWith("Sexo do pet:")) {
                    System.out.println("1 - Macho\n2 - Fêmea");
                    String gender = scanner.nextLine().trim();

                    if (gender.isEmpty()) {
                        throw new IllegalArgumentException("Campos vazios não são permitidos");
                    } else {
                        switch (gender) {
                            case "1" -> {
                                novoPet.setGender(Gender.MACHO);
                                System.out.println("Escolheu: " + Gender.MACHO.getDescription());
                            }
                            case "2" -> {
                                novoPet.setGender(Gender.FEMEA);
                                System.out.println("Escolheu: " + Gender.FEMEA.getDescription());
                            }
                            default -> throw new IllegalArgumentException("Selecione uma das opções disponíveis");
                        }
                    }
                }

                if (line.startsWith("Endereço que o pet foi encontrado:")) {
                    Address addressModel = new Address();
                    String houseNumber = InputUtil.getStringAddress("Número da casa: ", scanner);
                    String street = InputUtil.getStringAddress("Nome da rua: ", scanner);
                    String city = InputUtil.getStringAddress("Nome da cidade: ", scanner);

                    addressModel.setHouseNumber(houseNumber);
                    addressModel.setStreet(street);
                    addressModel.setCity(city);

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

