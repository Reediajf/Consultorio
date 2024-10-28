package br.com.hospital;
import java.util.Scanner;
import java.util.ArrayList;

public class Paciente {
    Scanner input = new Scanner(System.in);
    ArrayList<String> listaPaciente = new ArrayList<>();
    private static int contadorPaciente = 1;
    private int idPaciente;
    private String nome;
    private String cpf;
    private String sexo;
    private String nascimento;
    private String email;
    private String telefone;

    public void cadastraPaciente() {
        this.idPaciente = contadorPaciente++;

        // Entrada para o nome
        while (true) {
            System.out.print("Digite o nome do paciente: ");
            this.nome = input.nextLine();
            if (nome.isEmpty()) {
                System.out.println("Por favor, digite um nome válido.");
            } else {
                break; // Sai do loop se um nome válido for digitado
            }
        }

        // Entrada para o CPF
        while (true) {
            System.out.print("Digite o CPF: ");
            this.cpf = input.nextLine();
            if (cpf.isEmpty()) {
                System.out.println("Por favor, digite um CPF válido.");
            } else {
                break; // Sai do loop se um CPF válido for digitado
            }
        }

        // Entrada para o sexo
        while (true) {
            System.out.print("Digite o sexo: ");
            this.sexo = input.nextLine();
            if (sexo.isEmpty()) {
                System.out.println("Por favor, digite um sexo válido.");
            } else {
                break; // Sai do loop se um sexo válido for digitado
            }
        }

        // Entrada para o telefone
        while (true) {
            System.out.print("Digite o telefone: ");
            this.telefone = input.nextLine();
            if (telefone.isEmpty()) {
                System.out.println("Por favor, digite um telefone válido.");
            } else {
                break; // Sai do loop se um telefone válido for digitado
            }
        }

        // Entrada para a data de nascimento
        while (true) {
            System.out.print("Digite a data de nascimento: ");
            this.nascimento = input.nextLine();
            if (nascimento.isEmpty()) {
                System.out.println("Por favor, digite uma data de nascimento válida.");
            } else {
                break; // Sai do loop se uma data válida for digitada
            }
        }

        // Entrada para o email
        while (true) {
            System.out.print("Digite o email: ");
            this.email = input.nextLine();
            if (email.isEmpty()) {
                System.out.println("Por favor, digite um email válido.");
            } else {
                break; // Sai do loop se um email válido for digitado
            }
        }

        listaPaciente.add(toString());
        System.out.println("Paciente cadastrado com sucesso!");
    }

    @Override
    public String toString() {
        return "Paciente ID: " + idPaciente +
                "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\nSexo: " + sexo +
                "\nData de Nascimento: " + nascimento +
                "\nEmail: " + email +
                "\nTelefone: " + telefone;
    }

    public void getListaPaciente() {
        if (listaPaciente.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (String paciente : listaPaciente) {
            System.out.printf("\n\n%s\n\n", paciente);
        }
    }

}
