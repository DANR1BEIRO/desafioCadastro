
import Util.InputUtil;
import Util.FilesUtil;
import repository.PetRepository;
import service.PetService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetRepository petRepository = new PetRepository();
        PetService petService = new PetService(scanner, petRepository);

        FilesUtil.printMainMenuFromFile();
        int input = InputUtil.getIntMenuOptions("Escolha uma opção disponível: ", scanner);

        switch (input) {
            case 1 -> petService.cadastrarNovoPet();
            case 2 -> petService.editarPet();
            case 3 -> petService.deletarPetCadastrado();
            case 4 -> petService.listarTodosOsPets();
            case 5 -> petService.listarPetsPorCriterio();
            //case 6 -> sair do programa

        }
    }
}

