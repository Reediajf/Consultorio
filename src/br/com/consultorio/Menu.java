package br.com.consultorio;

import java.util.Scanner;

public class Menu {
    int opcao;
    Scanner input;  // Scanner agora é um atributo da classe

    // Construtor que recebe o Scanner
    public Menu(Scanner input) {
        this.input = input;  // Atribui a instância do Scanner
    }

    public void exibirMenu() {
        do {
            System.out.println("""
                    Escolha uma opção:
                    1. Para Médico
                    2. Para Paciente
                    0. Para Sair""");

            opcao = input.nextInt();  // Usando o Scanner passado como parâmetro
            switch (opcao) {
                case 1:
                    Medico medico = new Medico(input);  // Passa o Scanner para o Medico
                    medico.Menu();
                    break;
                case 2:
                    Paciente paciente = new Paciente(input);  // Passa o Scanner para o Paciente
                    paciente.Menu();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        System.out.println("Obrigado por utilizar.");
    }
}
