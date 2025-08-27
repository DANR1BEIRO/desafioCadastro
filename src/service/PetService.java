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
        List<Pet> pets = petRepository.buscarTodos();
        String name = null;
        String breed = null;
        Gender gender = null;
        Address address = null;
        Integer age = null;
        Integer weight = null;

        System.out.println("Escolha o tipo do pet (obrigatória):");
        Type type = InputUtil.getPetType(scanner);

        while (true) {
            System.out.println("Adicionar mais um critério de busca: \n1 - Sim\n2- Não");
            int input = scanner.nextInt();

            switch (input) {
                case 1 -> {
                    System.out.println("1 - Nome\n2 - Raça\n3 - Idade\n4 - Sexo\n5 - Peso\n6 - Endereço");
                    int inputCriterio = scanner.nextInt();

                    switch (inputCriterio) {
                        case 1 -> {
                            name = InputUtil.getPetName(scanner);
                        }
                        case 2 -> {
                            breed = InputUtil.getPetBreed(scanner);
                        }
                        case 3 -> {
                            age = InputUtil.getPetAge(scanner).intValue();
                        }
                        case 4 -> {
                            gender = InputUtil.getPetGender(scanner);
                        }
                        case 5 -> {
                            weight = InputUtil.getPetWeight(scanner).intValue();
                        }
                        case 6 -> {
                            address = new Address(
                                    InputUtil.getHouseNumberInput("Número da casa: ", scanner),
                                    InputUtil.getStringAddress("Nome da rua: ", scanner),
                                    InputUtil.getStringAddress("Cidade: ", scanner));
                        }
                    }
                }
                case 2 -> {
                    List<Pet> petsFiltrados = filtrarPorTipo(pets, type);
                    if (name != null) {
                        petsFiltrados = filtrarPorNome(petsFiltrados, name);
                    }

                    for (Pet petFiltrado : petsFiltrados) {
                        System.out.println(petFiltrado);
                    }
                    return;
                }
            }
        }
    }

    private List<Pet> filtrarPorTipo(List<Pet> todosOsPets, Type type) {
        List<Pet> petsFiltrados = new ArrayList<>();
        for (Pet pet : todosOsPets) {
            if (pet.getType().equals(type)) {
                petsFiltrados.add(pet);
            }
        }
        return petsFiltrados;
    }

    private List<Pet> filtrarPorNome(List<Pet> todosOsPets, String name) {
        List<Pet> petsFiltrados = new ArrayList<>();
        for (Pet pet : todosOsPets) {
            if (pet.getName().toLowerCase().contains(name.toLowerCase()))
                petsFiltrados.add(pet);
        }
        return petsFiltrados;
    }
}




/*Cadastrac ok
 * edita
 * deleta
 * lista*/

