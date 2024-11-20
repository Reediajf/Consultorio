package br.com.consultorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Medico {
    private static final ArrayList<String> listaMedica = new ArrayList<>();
    private static int contadorId = 1;
    private int idMedico;
    private String nome;
    private String telefone;
    private String crm;
    private String periodoAtendimento;
    private String especialidade;
    private final Scanner input = new Scanner(System.in);

    public void cadastrarMedico() {
        this.idMedico = contadorId++;
        input.nextLine();

        // Entrada para o nome
        while (true) {
            System.out.print("Digite o nome do médico: ");
            this.nome = input.nextLine();
            if (nome.isEmpty()) {
                System.out.println("Por favor, digite algo.");
            } else {
                break;
            }
        }

        // Entrada para o CRM
        while (true) {
            System.out.print("Digite o CRM: ");
            this.crm = input.nextLine();
            if (crm.isEmpty()) {
                System.out.println("Por favor, digite um CRM válido.");
            } else {
                break;
            }
        }

        // Entrada para a especialidade
        while (true) {
            System.out.print("Digite a especialidade: ");
            this.especialidade = input.nextLine();
            if (especialidade.isEmpty()) {
                System.out.println("Por favor, digite uma especialidade válida.");
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

        // Entrada para o período de atendimento
        while (true) {
            System.out.print("Digite o período de atendimento: ");
            this.periodoAtendimento = input.nextLine();
            if (periodoAtendimento.isEmpty()) {
                System.out.println("Por favor, digite um período de atendimento válido.");
            } else {
                break;
            }
        }

        listaMedica.add(toString());
        System.out.println("Médico cadastrado com sucesso!");
    }

    public void menuMedico() {
        int entrada;
        do {
            System.out.println("""
                    1. Para Cadastrar Médico
                    2. Para Excluir Médico
                    3. Para Consultar Médico
                    4. Para Alterar Médico
                    0. Para Voltar.""");
            entrada = input.nextInt();
            switch (entrada) {
                case 1:
                    cadastrarMedico();
                    break;
                case 2:
                    deletarMedico();
                    break;
                case 3:
                    getListaMedica();
                    break;
                case 4:
                    alterarMedico();
                    break;
            }
        } while (entrada != 0);
        System.out.println("Obrigado por utilizar.");
    }

    private void deletarMedico() {
        while (true) {
            System.out.println("Digite o número identificador para deletar(id): ");
            int deleteId = input.nextInt();
            if (deleteId >= listaMedica.size()) {
                System.out.println("Médico não encontrado");
            } else {
                listaMedica.remove(deleteId);
                System.out.println("Médico deletado com sucesso!");
                break;
            }
        }
    }

    private void alterarMedico() {
        while (true) {
            System.out.println("Digite o número identificador para alterar (ID): ");
            int alterarId = input.nextInt();
            input.nextLine(); // Limpa o buffer do scanner

            // Verifica se o ID é válido
            if (alterarId <= 0 || alterarId > listaMedica.size()) {
                System.out.println("Médico não encontrado");
            } else {
                // Recupera o médico correspondente ao ID
                String medicoString = listaMedica.get(alterarId - 1);
                String[] dadosMedico = medicoString.split("\n");

                // Exibe os dados atuais do médico
                System.out.println("Dados atuais do médico:");
                for (String dado : dadosMedico) {
                    System.out.println(dado);
                }

                // Menu para escolha do que alterar
                System.out.println("""
                        Escolha uma opção:
                        1. Para nome do médico
                        2. Para Telefone
                        3. Para CRM
                        4. Para Período de atendimento
                        5. Para Especialidade
                        0. Para Voltar""");

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
                            System.out.print("Digite o novo telefone: ");
                            this.telefone = input.nextLine();
                            if (telefone.isEmpty()) {
                                System.out.println("Por favor, digite o novo telefone.");
                            } else {
                                break;
                            }
                        }
                        break;

                    case 3:
                        while (true) {
                            System.out.print("Digite o novo CRM: ");
                            this.crm = input.nextLine();
                            if (crm.isEmpty()) {
                                System.out.println("Por favor, digite o novo CRM.");
                            } else {
                                break;
                            }
                        }
                        break;

                    case 4:
                        while (true) {
                            System.out.print("Digite o novo período de atendimento: ");
                            this.periodoAtendimento = input.nextLine();
                            if (periodoAtendimento.isEmpty()) {
                                System.out.println("Por favor, digite um novo período de atendimento.");
                            } else {
                                break;
                            }
                        }
                        break;

                    case 5:
                        while (true) {
                            System.out.print("Digite a nova especialidade: ");
                            this.especialidade = input.nextLine();
                            if (especialidade.isEmpty()) {
                                System.out.println("Por favor, digite uma nova especialidade.");
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

                // Atualiza a listaMedica
                listaMedica.set(alterarId - 1, toString());

                System.out.println("Médico alterado com sucesso!");
                break; // Sai do loop após a alteração
            }
        }
    }

    public static List<Medico> getListaMedica() {
        for (String medico : listaMedica) {
            System.out.printf("\n\n%s\n\n", medico);
        }
        return null;
    }


    @Override
    public String toString() {
        return "Médico ID: " + idMedico +
                "\nNome: " + nome +
                "\nTelefone: " + telefone +
                "\nCRM: " + crm +
                "\nPeríodo de Atendimento: " + periodoAtendimento +
                "\nEspecialidade: " + especialidade + "\n";
    }

}
