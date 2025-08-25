
import Util.InputUtil;
import Util.FilesUtil;
import repository.PetRepository;
import service.PetService;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetRepository petRepository = new PetRepository();
        FilesUtil.printMainMenuFromFile();
        int input = InputUtil.getIntMenuOptions("Escolha uma opção disponível: ", scanner);

        switch (input) {
            case 1:
                PetService petService = new PetService(scanner, petRepository);
                petService.cadastrarNovoPet();
        }
    }
}

