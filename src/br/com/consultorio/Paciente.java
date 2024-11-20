package br.com.consultorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Paciente {
    private Scanner input;
    private int idPaciente;
    private String nome;
    private String cpf;
    private String sexo;
    private String nascimento;
    private String email;
    private String telefone;

    // Conexão estática compartilhada
    private static Connection connection;

    // Inicializar conexão ao banco de dados
    private static void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/consultorio", "dt", "admin"
            );
        }
    }

    // Fechar conexão ao banco (se necessário)
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

    // Construtores
    public Paciente(Scanner input) {
        this.input = input;
    }

    public Paciente() {
    }

    // Método para cadastrar paciente no banco
    public void cadastraPaciente() {
        try {
            initConnection(); // Inicializar conexão
            this.nome = getStringInput("Digite o nome do paciente: ");
            this.cpf = getStringInput("Digite o CPF: ");
            this.sexo = getStringInput("Digite o sexo: ");
            this.nascimento = getStringInput("Digite a data de nascimento (yyyy-MM-dd): ");
            this.email = getStringInput("Digite o email: ");
            this.telefone = getStringInput("Digite o telefone: ");

            String sql = "INSERT INTO paciente (nomePaciente, cpf, sexo, nascimento, email, telefone) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, cpf);
                stmt.setString(3, sexo);
                stmt.setString(4, nascimento);
                stmt.setString(5, email);
                stmt.setString(6, telefone);

                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected > 0 ? "Paciente cadastrado com sucesso!" : "Erro ao cadastrar paciente.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    // Método para listar pacientes do banco
    public static List<Paciente> getListaPaciente() {
        List<Paciente> pacientes = new ArrayList<>();
        try {
            initConnection(); // Inicializar conexão
            String sql = "SELECT * FROM paciente";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Paciente paciente = new Paciente();
                    paciente.idPaciente = rs.getInt("idPaciente");
                    paciente.nome = rs.getString("nomePaciente");
                    paciente.cpf = rs.getString("cpf");
                    paciente.sexo = rs.getString("sexo");
                    paciente.nascimento = rs.getString("nascimento");
                    paciente.email = rs.getString("email");
                    paciente.telefone = rs.getString("telefone");
                    pacientes.add(paciente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
        }
        return pacientes;
    }

    // Método para alterar paciente
    public void alterarPaciente() {
        try {
            initConnection(); // Inicializar conexão
            int alterarId = getIntInput("Digite o ID do paciente para alterar: ");
            String nomeNovo = getStringInput("Digite o novo nome (deixe em branco para não alterar): ");
            String cpfNovo = getStringInput("Digite o novo CPF (deixe em branco para não alterar): ");
            String sexoNovo = getStringInput("Digite o novo sexo (deixe em branco para não alterar): ");
            String nascimentoNovo = getStringInput("Digite a nova data de nascimento (deixe em branco para não alterar): ");
            String emailNovo = getStringInput("Digite o novo email (deixe em branco para não alterar): ");
            String telefoneNovo = getStringInput("Digite o novo telefone (deixe em branco para não alterar): ");

            StringBuilder sql = new StringBuilder("UPDATE paciente SET ");
            if (!nomeNovo.isEmpty()) sql.append("nomePaciente = ?, ");
            if (!cpfNovo.isEmpty()) sql.append("cpf = ?, ");
            if (!sexoNovo.isEmpty()) sql.append("sexo = ?, ");
            if (!nascimentoNovo.isEmpty()) sql.append("nascimento = ?, ");
            if (!emailNovo.isEmpty()) sql.append("email = ?, ");
            if (!telefoneNovo.isEmpty()) sql.append("telefone = ?, ");

            sql.delete(sql.length() - 2, sql.length()); // Remove a última vírgula
            sql.append(" WHERE idPaciente = ?");

            try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
                int index = 1;
                if (!nomeNovo.isEmpty()) stmt.setString(index++, nomeNovo);
                if (!cpfNovo.isEmpty()) stmt.setString(index++, cpfNovo);
                if (!sexoNovo.isEmpty()) stmt.setString(index++, sexoNovo);
                if (!nascimentoNovo.isEmpty()) stmt.setString(index++, nascimentoNovo);
                if (!emailNovo.isEmpty()) stmt.setString(index++, emailNovo);
                if (!telefoneNovo.isEmpty()) stmt.setString(index++, telefoneNovo);
                stmt.setInt(index, alterarId);

                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected > 0 ? "Paciente alterado com sucesso!" : "Paciente não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao alterar paciente: " + e.getMessage());
        }
    }

    // Método para deletar paciente
    public void deletarPaciente() {
        try {
            initConnection(); // Inicializar conexão
            int deleteId = getIntInput("Digite o ID do paciente para deletar: ");
            String sql = "DELETE FROM paciente WHERE idPaciente = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, deleteId);
                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected > 0 ? "Paciente deletado com sucesso!" : "Paciente não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar paciente: " + e.getMessage());
        }
    }

    // Métodos auxiliares para entrada do usuário
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                return input.nextInt();
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                input.nextLine(); // Limpar buffer
            }
        }
    }

    // Método toString para exibir informações do paciente
    @Override
    public String toString() {
        return "Paciente ID: " + idPaciente +
                "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\nSexo: " + sexo +
                "\nNascimento: " + nascimento +
                "\nEmail: " + email +
                "\nTelefone: " + telefone;
    }

    // Método para exibir o menu de opções
    public void Menu() {
        input = new Scanner(System.in);  // Inicializa o Scanner

        while (true) {
            System.out.println("""
                    Qual opção deseja?
                    1 - Cadastrar paciente
                    2 - Alterar paciente
                    3 - Deletar paciente
                    4 - Mostrar pacientes
                    5 - Sair""");
            int opcaoMenu = input.nextInt();
            input.nextLine(); // Limpar buffer após nextInt()

            switch (opcaoMenu) {
                case 1:
                    cadastraPaciente();
                    break;
                case 2:
                    alterarPaciente();
                    break;
                case 3:
                    deletarPaciente();
                    break;
                case 4:
                    mostrarpaciente();
                    break;
                case 5:
                    closeConnection();  // Fechar conexão ao sair
                    System.out.println("Obrigado por usar o sistema!");
                    return;  // Sai do loop e encerra o programa
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // Método para mostrar pacientes
    public void mostrarpaciente() {
        List<Paciente> pacientes = getListaPaciente();
        if (!pacientes.isEmpty()) {
            for (Paciente paciente : pacientes) {
                System.out.println(paciente);
            }
        } else {
            System.out.println("Nenhum paciente encontrado.");
        }
    }

    public int getIdPaciente() {
        return idPaciente;
    }
}
