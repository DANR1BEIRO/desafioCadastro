package service;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                    Address address = new Address(
                            InputUtil.getHouseNumberInput("Número da casa: ", scanner),
                            InputUtil.getStringAddress("Nome da rua: ", scanner),
                            InputUtil.getStringAddress("Nome da cidade: ", scanner));

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
                    String breed = InputUtil.getPetBreed(scanner);
                    novoPet.setBreed(breed);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        petRepository.salvar(novoPet);
    }

    public void buscarPetsCadastrados() {
        List<Pet> petList = selecionarPets();
        for (
                Pet pet : petList) {
            System.out.println(pet);
        }
    }

    private List<Pet> selecionarPets() {

        List<Pet> pets = petRepository.buscarTodos();
        String name = null;
        String breed = null;
        Gender gender = null;
        Address address = null;
        Integer age = null;
        Integer weight = null;

        System.out.println("Escolha o tipo do pet (obrigatória):");
        Type type = InputUtil.getPetType(scanner);
        int counter = 0;

        while (true) {
            System.out.println("Adicionar mais um critério de busca: \n1 - Sim\n2 - Não");
            int input = scanner.nextInt();

            if (input == 2) {
                break;
            }

            if (counter >= 2) {
                break;
            }

            if (input == 1) {
                System.out.println("1 - Nome\n2 - Raça\n3 - Idade\n4 - Sexo\n5 - Peso\n6 - Endereço");
                int inputCriterio = scanner.nextInt();

                switch (inputCriterio) {
                    case 1 -> name = InputUtil.getPetName(scanner);
                    case 2 -> breed = InputUtil.getPetBreed(scanner);
                    case 3 -> age = InputUtil.getPetAge(scanner).intValue();
                    case 4 -> gender = InputUtil.getPetGender(scanner);
                    case 5 -> weight = InputUtil.getPetWeight(scanner).intValue();
                    case 6 -> address = new Address(
                            InputUtil.getHouseNumberInput("Número da casa: ", scanner),
                            InputUtil.getStringAddress("Nome da rua: ", scanner),
                            InputUtil.getStringAddress("Cidade: ", scanner));
                    default -> System.out.println("Opção inválida");
                }
                counter++;
            } else {
                System.out.println("Opção inválida! 1 para SIM e 2 para NÃO");
            }
        }

        List<Pet> filteredPets = filterByType(pets, type);
        if (name != null) {
            filteredPets = filterByName(filteredPets, name);
        }

        if (breed != null) {
            filteredPets = filterByBreed(filteredPets, breed);
        }

        if (age != null) {
            filteredPets = filterByAge(filteredPets, age);
        }

        if (weight != null) {
            filteredPets = filterByWeight(filteredPets, weight);
        }

        if (gender != null) {
            filteredPets = filterByGender(filteredPets, gender);
        }

        if (address != null) {
            filteredPets = filterByAddress(filteredPets, address.toString());
        }

        return filteredPets;
    }

    public void editarPet() {
        List<Pet> petList = selecionarPets();
        Pet petAtIndex;

        if (petList.size() == 0) {
            System.out.println("Não há pets com os critérios selecionados");
            return;
        }

        while (true) {

            for (int i = 0; i < petList.size(); i++) {
                System.out.println(i + 1 + " - " + petList.get(i));
            }

            System.out.println("Selecione o pet que será alterado:");
            int option = scanner.nextInt();

            if (option < 1 || option > petList.size()) {
                System.out.println("Opção inválida!");
                continue;
            }

            petAtIndex = petList.get(option - 1);
            break;
        }

        while (true) {
            System.out.println("Selecione o campo que será alterado:" +
                    "\n1 - Nome" +
                    "\n2 - Idade" +
                    "\n3 - Peso" +
                    "\n4 - Raça" +
                    "\n5 - Endereço" +
                    "\n6 - Salvar e voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> petAtIndex.setName(InputUtil.getPetName(scanner));
                case 2 -> petAtIndex.setAge(InputUtil.getPetAge(scanner));
                case 3 -> petAtIndex.setWeight(InputUtil.getPetWeight(scanner));
                case 4 -> petAtIndex.setBreed(InputUtil.getPetBreed(scanner));
                case 5 -> petAtIndex.setAddress(new Address(
                        InputUtil.getHouseNumberInput("Número da casa: ", scanner),
                        InputUtil.getStringAddress("Nome da rua: ", scanner),
                        InputUtil.getStringAddress("Cidade: ", scanner)));
                case 6 -> {
                    petRepository.salvar(petAtIndex);
                    System.out.println("Informações atualizadas!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private List<Pet> filterByType(List<Pet> petList, Type type) {
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : petList) {
            if (pet.getType().equals(type)) {
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }

    private List<Pet> filterByName(List<Pet> petList, String name) {
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : petList) {
            if (pet.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }

    private List<Pet> filterByBreed(List<Pet> petList, String breed) {
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : petList) {
            if (pet.getBreed().toLowerCase().contains(breed.toLowerCase()))
                filteredPets.add(pet);
        }
        return filteredPets;
    }

    private List<Pet> filterByAge(List<Pet> petList, Integer age) {
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : petList) {
            if (pet.getAge().intValue() == age) {
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }

    private List<Pet> filterByWeight(List<Pet> petList, Integer weight) {
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : petList) {
            if (pet.getWeight().intValue() == weight) {
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }

    private List<Pet> filterByGender(List<Pet> petList, Gender gender) {
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : petList) {
            if (pet.getGender() == gender) {
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }

    private List<Pet> filterByAddress(List<Pet> petList, String address) {

        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : petList) {
            if (pet.getAddress().toString().toLowerCase().contains(address.toLowerCase())) {
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }


}

/*Cadastrar ok
 * edita ok
 * deleta
 * lista ok */

