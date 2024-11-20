package br.com.consultorio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        server.init();  // Inicializa a conexão com o servidor

        // Criar uma única instância do Scanner
        Scanner input = new Scanner(System.in);

        // Passa o Scanner para o Menu
        Menu menu = new Menu(input);
        menu.exibirMenu();
    }
}
