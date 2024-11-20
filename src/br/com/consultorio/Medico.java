package br.com.consultorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Medico {
    Scanner input;
    private int idMedico;
    private String nome;
    private String telefone;
    private String crm;
    private String periodoAtendimento;
    private String especialidade;


    // Metodo para estabelecer a conexão com o banco de dados
    private static Connection conexao;

    public Medico(Scanner input) {
        this.input = input;
    }

    public Medico() {

    }

    private static void init() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/consultorio",
                        "dt",
                        "admin"
                );
                System.out.println("Conexão estabelecida com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            }
        }
    }

    private static Connection getConnection() {
        init();
        return conexao;
    }

    // Metodo para cadastrar um médico no banco de dados
    public void cadastrarMedico() {
        this.nome = getStringInput("Digite o nome do médico: ");
        this.crm = getStringInput("Digite o CRM: ");
        this.especialidade = getStringInput("Digite a especialidade: ");
        this.telefone = getStringInput("Digite o telefone: ");
        this.periodoAtendimento = getStringInput("Digite o período de atendimento: ");

        String sql = "INSERT INTO medico (nomeMedico, telefone, crm, periodoAtendimento, especialidade) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, crm);
            stmt.setString(4, periodoAtendimento);
            stmt.setString(5, especialidade);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Médico cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar o médico!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para listar médicos cadastrados no banco de dados
    public static List<Medico> getListaMedica() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM medico";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.idMedico = rs.getInt("idMedico");
                medico.nome = rs.getString("nomeMedico");
                medico.crm = rs.getString("crm");
                medico.especialidade = rs.getString("especialidade");
                medico.telefone = rs.getString("telefone");
                medico.periodoAtendimento = rs.getString("periodoAtendimento");
                medicos.add(medico);
            }

            if (medicos.isEmpty()) {
                System.out.println("Nenhum médico cadastrado no banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    // Metodo para excluir médico do banco de dados
    public void deletarMedico() {
        int deleteId = getIntInput("Digite o ID do médico para deletar: ");
        String sql = "DELETE FROM medico WHERE idMedico = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, deleteId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Médico removido com sucesso!");
            } else {
                System.out.println("Médico não encontrado no banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para alterar dados do médico
    public void alterarMedico() {
        int alterarId = getIntInput("Digite o ID do médico para alterar: ");
        String nomeNovo = getStringInput("Digite o novo nome (deixe em branco para não alterar): ");
        String crmNovo = getStringInput("Digite o novo CRM (deixe em branco para não alterar): ");
        String especialidadeNova = getStringInput("Digite a nova especialidade (deixe em branco para não alterar): ");
        String telefoneNovo = getStringInput("Digite o novo telefone (deixe em branco para não alterar): ");
        String periodoNovo = getStringInput("Digite o novo período de atendimento (deixe em branco para não alterar): ");

        StringBuilder sql = new StringBuilder("UPDATE medico SET ");

        if (!nomeNovo.isEmpty()) sql.append("nomeMedico = ?, ");
        if (!crmNovo.isEmpty()) sql.append("crm = ?, ");
        if (!especialidadeNova.isEmpty()) sql.append("especialidade = ?, ");
        if (!telefoneNovo.isEmpty()) sql.append("telefone = ?, ");
        if (!periodoNovo.isEmpty()) sql.append("periodoAtendimento = ?, ");

        sql.delete(sql.length() - 2, sql.length());  // Remove a última vírgula
        sql.append(" WHERE idMedico = ?");

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (!nomeNovo.isEmpty()) stmt.setString(index++, nomeNovo);
            if (!crmNovo.isEmpty()) stmt.setString(index++, crmNovo);
            if (!especialidadeNova.isEmpty()) stmt.setString(index++, especialidadeNova);
            if (!telefoneNovo.isEmpty()) stmt.setString(index++, telefoneNovo);
            if (!periodoNovo.isEmpty()) stmt.setString(index++, periodoNovo);
            stmt.setInt(index, alterarId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Médico alterado com sucesso!");
            } else {
                System.out.println("Médico não encontrado no banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos auxiliares para obter input do usuário
    private String getStringInput(String prompt) {
        String inputStr = "";
        while (inputStr.isEmpty()) {
            System.out.print(prompt);
            inputStr = input.nextLine();
        }
        return inputStr;
    }

    private int getIntInput(String prompt) {
        int inputInt = -1;
        while (inputInt < 0) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                inputInt = input.nextInt();
            } else {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                input.nextLine(); // Limpa o buffer
            }
        }
        return inputInt;
    }

    // Metodo toString para exibir informações do médico
    @Override
    public String toString() {
        return "Médico ID: " + idMedico +
                "\nNome: " + nome +
                "\nTelefone: " + telefone +
                "\nCRM: " + crm +
                "\nPeríodo de Atendimento: " + periodoAtendimento +
                "\nEspecialidade: " + especialidade + "\n";
    }

    // Método para exibir o menu de opções
    public void Menu() {
        while (true) {
            System.out.println("""
                    Qual opção deseja?
                    1 - Cadastrar médico
                    2 - Alterar médico
                    3 - Deletar médico
                    4 - Mostrar médicos""");
            int opcaoMenu = input.nextInt();
            switch (opcaoMenu) {
                case 1:
                    cadastrarMedico();
                    break;
                case 2:
                    alterarMedico();
                    break;
                case 3:
                    deletarMedico();
                    break;
                case 4:
                    mostrarMedicos();
                    break;
                default:
                    System.out.println("Obrigado por usar!");
                    break;
            }
        }
    }

    // Método para exibir médicos cadastrados
    public static void mostrarMedicos() {
        List<Medico> medicos = getListaMedica();
        if (!medicos.isEmpty()) {
            System.out.println("\nID \t Nome \t\t CRM \t\t Especialidade \t\t Telefone \t\t Período");
            for (Medico medico : medicos) {
                System.out.println(medico);
            }
        }
    }

    public int getIdMedico() {
        return idMedico;
    }
}
