package br.com.hospital;
import java.util.Scanner;
import java.util.ArrayList;

public class Medico {
    ArrayList<String> listaMedica = new ArrayList<>();
    private static int contadorId = 1; // Contador estático para ID
    private int idMedico;
    private String nome;
    private String telefone;
    private String crm;
    private String periodoAtendimento;
    private String especialidade;
    Scanner input = new Scanner(System.in);

    public void cadastrarMedico() {
        this.idMedico = contadorId++; // Atribui o ID e incrementa o contador

        // Entrada para o nome
        while (true) {
            System.out.print("Digite o nome do médico: ");
            this.nome = input.nextLine();
            if (nome.isEmpty()) {
                System.out.println("Por favor, digite algo.");
            } else {
                break; // Sai do loop se um nome válido for digitado
            }
        }

        // Entrada para o CRM
        while (true) {
            System.out.print("Digite o CRM: ");
            this.crm = input.nextLine();
            if (crm.isEmpty()) {
                System.out.println("Por favor, digite um CRM válido.");
            } else {
                break; // Sai do loop se um CRM válido for digitado
            }
        }

        // Entrada para a especialidade
        while (true) {
            System.out.print("Digite a especialidade: ");
            this.especialidade = input.nextLine();
            if (especialidade.isEmpty()) {
                System.out.println("Por favor, digite uma especialidade válida.");
            } else {
                break; // Sai do loop se uma especialidade válida for digitada
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

        // Entrada para o período de atendimento
        while (true) {
            System.out.print("Digite o período de atendimento: ");
            this.periodoAtendimento = input.nextLine();
            if (periodoAtendimento.isEmpty()) {
                System.out.println("Por favor, digite um período de atendimento válido.");
            } else {
                break; // Sai do loop se um período válido for digitado
            }
        }

        listaMedica.add(toString());
        System.out.println("Médico cadastrado com sucesso!");
    }

    @Override
    public String toString() {
        return "Médico ID: " + idMedico +
                "\nNome: " + nome +
                "\nTelefone: " + telefone +
                "\nCRM: " + crm +
                "\nPeríodo de Atendimento: " + periodoAtendimento +
                "\nEspecialidade: " + especialidade;
    }

    public void getListaMedica() {
        for (String medico : listaMedica) {
            System.out.printf("\n\n%s\n\n", medico);
        }
    }
public void deletarMedico() {
    while (true) {
        System.out.println("Digite o número identificador para deletar(id): ");
        int deleteId = input.nextInt();
        if (deleteId >= listaMedica.size()) {
            System.out.println("Medico não encontrado");
        } else {
            listaMedica.remove(deleteId);
            System.out.println("Medico deletado com sucesso!");
            break;
        }
    }
}
}