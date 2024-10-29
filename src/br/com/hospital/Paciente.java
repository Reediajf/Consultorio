package br.com.hospital;

import java.util.ArrayList;
import java.util.Scanner;

public class Paciente {
    private final ArrayList<String> listaPaciente = new ArrayList<>();
    private static int contadorPaciente = 1;
    private int idPaciente;
    private String nome;
    private String cpf;
    private String sexo;
    private String nascimento;
    private String email;
    private String telefone;
    private final Scanner input = new Scanner(System.in);

    // Métodos públicos
    public void cadastraPaciente() {
        this.idPaciente = contadorPaciente++;
        input.nextLine();

        // Entrada para o nome
        while (true) {
            System.out.print("Digite o nome do paciente: ");
            this.nome = input.nextLine();
            if (nome.isEmpty()) {
                System.out.println("Por favor, digite um nome válido.");
            } else {
                break;
            }
        }

        // Entrada para o CPF
        while (true) {
            System.out.print("Digite o CPF: ");
            this.cpf = input.nextLine();
            if (cpf.isEmpty()) {
                System.out.println("Por favor, digite um CPF válido.");
            } else {
                break;
            }
        }

        // Entrada para o sexo
        while (true) {
            System.out.print("Digite o sexo: ");
            this.sexo = input.nextLine();
            if (sexo.isEmpty()) {
                System.out.println("Por favor, digite um sexo válido.");
            } else {
                break;
            }
        }

        // Entrada para a data de nascimento
        while (true) {
            System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
            this.nascimento = input.nextLine();
            if (nascimento.isEmpty()) {
                System.out.println("Por favor, digite uma data válida.");
            } else {
                break;
            }
        }

        // Entrada para o email
        while (true) {
            System.out.print("Digite o email: ");
            this.email = input.nextLine();
            if (email.isEmpty()) {
                System.out.println("Por favor, digite um email válido.");
            } else {
                break;
            }
        }

        // Entrada para o telefone
        while (true) {
            System.out.print("Digite o telefone: ");
            this.telefone = input.nextLine();
            if (telefone.isEmpty()) {
                System.out.println("Por favor, digite um telefone válido.");
            } else {
                break;
            }
        }

        listaPaciente.add(toString());
        System.out.println("Paciente cadastrado com sucesso!");
    }

    public void menuPaciente() {
        int entrada;
        do {
            System.out.println("""
                    1. Para cadastrar paciente
                    2. Para excluir paciente
                    3. Para lista de paciente
                    4. Para Alterar paciente
                    5. Para agendar consulta
                    6. Para listar agendamentos
                    0. Para sair.""");
            entrada = input.nextInt();
            switch (entrada) {
                case 1:
                    cadastraPaciente();
                    break;
                case 2:
                    deletarPaciente();
                    break;
                case 3:
                    getListaPaciente();
                    break;
                case 4:
                    alterarPaciente();
                    break;
                case 5:
                    // Aqui você precisa passar a lista de médicos que deseja agendar
                    break;
                case 6:
                    // Aqui você coloca o método listarAgendamento
                    break;
            }
        } while (entrada != 0);
        System.out.println("Obrigado por utilizar.");
    }

    public void getListaPaciente() {
        if (listaPaciente.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            for (String paciente : listaPaciente) {
                System.out.printf("\n\n\n\n" + this, paciente);
            }
        }
    }

    // Métodos privados
    private void deletarPaciente() {
        while (true) {
            System.out.println("Digite o número identificador para deletar (id): ");
            int deleteId = input.nextInt();
            if (deleteId < 1 || deleteId > listaPaciente.size()) {
                System.out.println("Paciente não encontrado");
            } else {
                listaPaciente.remove(deleteId - 1);
                System.out.println("Paciente deletado com sucesso!");
                break;
            }
        }
    }

    private void alterarPaciente() {
        while (true) {
            System.out.println("Digite o número identificador para alterar (ID): ");
            int alterarId = input.nextInt();
            input.nextLine(); // Limpa o buffer do scanner

            // Verifica se o ID é válido
            if (alterarId < 1 || alterarId > listaPaciente.size()) {
                System.out.println("Paciente não encontrado");
            } else {
                String pacienteString = listaPaciente.get(alterarId - 1);
                String[] dadosPaciente = pacienteString.split("\n");

                System.out.println("Dados atuais do paciente:");
                for (String dado : dadosPaciente) {
                    System.out.println(dado);
                }

                // Menu para escolha do que alterar
                System.out.println("""
                        Escolha uma opção:
                        1. Para nome do paciente
                        2. Para CPF
                        3. Para Sexo
                        4. Para Data de Nascimento
                        5. Para Email
                        6. Para Telefone
                        0. Para sair""");

                int entradaAlterar = input.nextInt();
                input.nextLine(); // Limpa o buffer do scanner

                switch (entradaAlterar) {
                    case 1:
                        while (true) {
                            System.out.print("Digite o novo nome: ");
                            this.nome = input.nextLine();
                            if (nome.isEmpty()) {
                                System.out.println("Por favor, digite o novo nome.");
                            } else {
                                break;
                            }
                        }
                        break;

                    case 2:
                        while (true) {
                            System.out.print("Digite o novo CPF: ");
                            this.cpf = input.nextLine();
                            if (cpf.isEmpty()) {
                                System.out.println("Por favor, digite o novo CPF.");
                            } else {
                                break;
                            }
                        }
                        break;

                    case 3:
                        while (true) {
                            System.out.print("Digite o novo sexo: ");
                            this.sexo = input.nextLine();
                            if (sexo.isEmpty()) {
                                System.out.println("Por favor, digite o novo sexo.");
                            } else {
                                break;
                            }
                        }
                        break;

                    case 4:
                        while (true) {
                            System.out.print("Digite a nova data de nascimento: ");
                            this.nascimento = input.nextLine();
                            if (nascimento.isEmpty()) {
                                System.out.println("Por favor, digite uma nova data válida.");
                            } else {
                                break;
                            }
                        }
                        break;

                    case 5:
                        while (true) {
                            System.out.print("Digite o novo email: ");
                            this.email = input.nextLine();
                            if (email.isEmpty()) {
                                System.out.println("Por favor, digite um novo email.");
                            } else {
                                break;
                            }
                        }
                        break;

                    case 6:
                        while (true) {
                            System.out.print("Digite o novo telefone: ");
                            this.telefone = input.nextLine();
                            if (telefone.isEmpty()) {
                                System.out.println("Por favor, digite um novo telefone.");
                            } else {
                                break;
                            }
                        }
                        break;

                    case 0:
                        return; // Sai do método
                    default:
                        System.out.println("Opção inválida.");
                }

                // Atualiza a listaPaciente
                listaPaciente.set(alterarId - 1, toString());
                System.out.println("Paciente alterado com sucesso!");
                break; // Sai do loop após a alteração
            }
        }
    }

    // Método toString
    @Override
    public String toString() {
        return "Paciente ID: " + idPaciente +
               "\nNome: " + nome +
               "\nCPF: " + cpf +
               "\nSexo: " + sexo +
               "\nNascimento: " + nascimento +
               "\nEmail: " + email +
               "\nTelefone:\n " + telefone + "\n";
    }
}
