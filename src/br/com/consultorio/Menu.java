package br.com.consultorio;

import java.util.Scanner;

public class Menu {
    int opcao;
    Scanner input = new Scanner(System.in);
    Medico medico = new Medico();
    Paciente paciente = new Paciente();

    public Menu() {
        do {
            System.out.println("""
                    Escolha uma opção:
                    1. Para Médico
                    2. Para Paciente
                    0. Para Sair""");

            opcao = input.nextInt();
            switch (opcao) {
                case 1:
                    medico.menuMedico();
                    break;
                case 2:
                    paciente.menuPaciente();
                    break;
            }
        } while (opcao != 0);
        System.out.println("Obrigado por utilizar.");
        init();
    }

    public void init() {
        new Menu();
    }
}

