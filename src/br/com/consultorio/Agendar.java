package br.com.consultorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agendar {
    private int idAgendamento;
    private Medico medico;
    private Paciente paciente;
    private Scanner input = new Scanner(System.in);
    // Inicializa as listas de médicos e pacientes
    List<Medico> listaMedicos = Medico.getListaMedica();
    List<Paciente> listaPacientes = Paciente.getListaPaciente();
    Menu m = new Menu();
    // Construtor
    public Agendar() {
        if (listaMedicos == null || listaPacientes == null) {
            listaMedicos = new ArrayList<>();
            listaPacientes = new ArrayList<>();
            m.init();
        }

        // Exibe os médicos disponíveis
        System.out.println("Para qual médico deseja atendimento?");
        for (int i = 0; i < listaMedicos.size(); i++) {
            System.out.println(i + 1 + ". " + listaMedicos.get(i).toString()); // Considerando que Medico tem o método getNome()
        }
            // Solicita ao usuário a escolha do médico
            int escolhaMedico = input.nextInt();
            if (escolhaMedico > 0 && escolhaMedico <= listaMedicos.size()) {
                this.medico = listaMedicos.get(escolhaMedico - 1);  // Subtrai 1 para ajustar o índice
            } else {
                System.out.println("Opção inválida! Tente novamente.");
                return; // Encerra o método em caso de erro
            }

            // Exibe os pacientes disponíveis
            System.out.println("Para qual paciente deseja agendar?");
            for (int i = 0; i < listaPacientes.size(); i++) {
                System.out.println(i + 1 + ". " + listaPacientes.get(i).toString()); // Considerando que Paciente tem o método getNome()
            }

            // Solicita ao usuário a escolha do paciente
            int escolhaPaciente = input.nextInt();
            if (escolhaPaciente > 0 && escolhaPaciente <= listaPacientes.size()) {
                this.paciente = listaPacientes.get(escolhaPaciente - 1);  // Subtrai 1 para ajustar o índice
            } else {
                System.out.println("Opção inválida! Tente novamente.");
                return; // Encerra o método em caso de erro
            }

            // Exibe a confirmação do agendamento
            System.out.println("Agendamento realizado com sucesso!");
            System.out.println("Médico: " + this.medico.toString());
            System.out.println("Paciente: " + this.paciente.toString());
        }


    // Getter e Setter para idAgendamento (se necessário)
    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

}
