package br.com.consultorio;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Agendar {
    private int idAgendamento;

    // Construtor
    public Agendar() {
        // Inicializa as listas de médicos e pacientes diretamente a partir do banco de dados
        List<Medico> listaMedicos = Medico.getListaMedica();
        List<Paciente> listaPacientes = Paciente.getListaPaciente();

        // Verifica se as listas estão vazias, caso estejam, inicializa uma nova lista
        if (listaMedicos.isEmpty() || listaPacientes.isEmpty()) {
            System.out.println("Nenhum médico ou paciente encontrado no banco de dados.");
            return;
        }

        // Exibe os médicos disponíveis
        System.out.println("Para qual médico deseja atendimento?");
        for (int i = 0; i < listaMedicos.size(); i++) {
            System.out.println((i + 1) + ". " + listaMedicos.get(i).toString());
        }

        // Solicita ao usuário a escolha do médico
        Scanner input = new Scanner(System.in);
        int escolhaMedico = input.nextInt();
        Medico medico;
        if (escolhaMedico > 0 && escolhaMedico <= listaMedicos.size()) {
            medico = listaMedicos.get(escolhaMedico - 1);  // Subtrai 1 para ajustar o índice
        } else {
            System.out.println("Opção inválida! Tente novamente.");
            return; // Encerra o método em caso de erro
        }

        // Exibe os pacientes disponíveis
        System.out.println("Para qual paciente deseja agendar?");
        for (int i = 0; i < listaPacientes.size(); i++) {
            System.out.println((i + 1) + ". " + listaPacientes.get(i).toString());
        }

        // Solicita ao usuário a escolha do paciente
        int escolhaPaciente = input.nextInt();
        Paciente paciente;
        if (escolhaPaciente > 0 && escolhaPaciente <= listaPacientes.size()) {
            paciente = listaPacientes.get(escolhaPaciente - 1);  // Subtrai 1 para ajustar o índice
        } else {
            System.out.println("Opção inválida! Tente novamente.");
            return; // Encerra o método em caso de erro
        }

        // Exibe a confirmação do agendamento
        System.out.println("Agendamento realizado com sucesso!");
        System.out.println("Médico: " + medico.toString());
        System.out.println("Paciente: " + paciente.toString());

        // Inserir agendamento no banco de dados
        String sql = "INSERT INTO agendamento (idMedico, idPaciente) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/consultorio", "dt", "admin");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, medico.getIdMedico());
            stmt.setInt(2, paciente.getIdPaciente());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Agendamento registrado com sucesso no banco de dados!");
            } else {
                System.out.println("Problemas ao adicionar o agendamento no banco de dados.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao realizar o agendamento.");
        }
    }

    // Método para listar os agendamentos
    public static void listarAgendamentos() {
        String sql = "SELECT a.idAgendamento, m.nome AS medico, p.nome AS paciente " +
                "FROM agendamento a " +
                "JOIN medico m ON a.idMedico = m.idMedico " +
                "JOIN paciente p ON a.idPaciente = p.idPaciente";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/consultorio", "dt", "admin");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idAgendamento = rs.getInt("idAgendamento");
                String nomeMedico = rs.getString("medico");
                String nomePaciente = rs.getString("paciente");

                System.out.println("Agendamento ID: " + idAgendamento);
                System.out.println("Médico: " + nomeMedico);
                System.out.println("Paciente: " + nomePaciente);
                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar agendamentos.");
        }
    }

    // Getter e Setter para idAgendamento (se necessário)
    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }
}
