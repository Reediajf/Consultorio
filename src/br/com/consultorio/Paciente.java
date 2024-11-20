package br.com.consultorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Paciente {
    Scanner input;
    private int idPaciente;
    private String nome;
    private String cpf;
    private String sexo;
    private String nascimento;
    private String email;
    private String telefone;

    Agendar agendamento = new Agendar();

    // Definir conexão única
    private static Connection conexao;

    public Paciente(Scanner input) {
        this.input = input;
    }

    public Paciente() {
        
    }

    // Inicializa a conexão ao banco de dados
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

    // Método para obter a conexão
    private static Connection getConnection() {
        init();
        return conexao;
    }

    // Método para cadastrar paciente no banco de dados
    public void cadastraPaciente() {
        this.nome = getStringInput("Digite o nome do paciente: ");
        this.cpf = getStringInput("Digite o CPF: ");
        this.sexo = getStringInput("Digite o sexo: ");
        this.nascimento = getStringInput("Digite a data de nascimento (yyyy-MM-dd): ");
        this.email = getStringInput("Digite o email: ");
        this.telefone = getStringInput("Digite o telefone: ");

        // Conectar ao banco e inserir os dados do paciente
        String sql = "INSERT INTO paciente (nomePaciente, cpf, sexo, nascimento, email, telefone) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, sexo);
            stmt.setString(4, nascimento);
            stmt.setString(5, email);
            stmt.setString(6, telefone);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Paciente cadastrado com sucesso no banco de dados!");
            } else {
                System.out.println("Erro ao cadastrar paciente no banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para listar pacientes do banco de dados
    public static List<Paciente> getListaPaciente() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.idPaciente = rs.getInt("idPaciente");
                paciente.nome = rs.getString("nome");
                paciente.cpf = rs.getString("cpf");
                paciente.sexo = rs.getString("sexo");
                paciente.nascimento = rs.getString("nascimento");
                paciente.email = rs.getString("email");
                paciente.telefone = rs.getString("telefone");
                pacientes.add(paciente);
            }

            if (pacientes.isEmpty()) {
                System.out.println("Nenhum paciente cadastrado no banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    // Método para alterar paciente
    public void alterarPaciente() {
        int alterarId = getIntInput("Digite o ID do paciente para alterar: ");
        String nomeNovo = getStringInput("Digite o novo nome (deixe em branco para não alterar): ");
        String cpfNovo = getStringInput("Digite o novo CPF (deixe em branco para não alterar): ");
        String sexoNovo = getStringInput("Digite o novo sexo (deixe em branco para não alterar): ");
        String nascimentoNovo = getStringInput("Digite a nova data de nascimento (deixe em branco para não alterar): ");
        String emailNovo = getStringInput("Digite o novo email (deixe em branco para não alterar): ");
        String telefoneNovo = getStringInput("Digite o novo telefone (deixe em branco para não alterar): ");

        StringBuilder sql = new StringBuilder("UPDATE paciente SET ");

        if (!nomeNovo.isEmpty()) sql.append("nome = ?, ");
        if (!cpfNovo.isEmpty()) sql.append("cpf = ?, ");
        if (!sexoNovo.isEmpty()) sql.append("sexo = ?, ");
        if (!nascimentoNovo.isEmpty()) sql.append("nascimento = ?, ");
        if (!emailNovo.isEmpty()) sql.append("email = ?, ");
        if (!telefoneNovo.isEmpty()) sql.append("telefone = ?, ");

        sql.delete(sql.length() - 2, sql.length());  // Remove a última vírgula
        sql.append(" WHERE idPaciente = ?");

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (!nomeNovo.isEmpty()) stmt.setString(index++, nomeNovo);
            if (!cpfNovo.isEmpty()) stmt.setString(index++, cpfNovo);
            if (!sexoNovo.isEmpty()) stmt.setString(index++, sexoNovo);
            if (!nascimentoNovo.isEmpty()) stmt.setString(index++, nascimentoNovo);
            if (!emailNovo.isEmpty()) stmt.setString(index++, emailNovo);
            if (!telefoneNovo.isEmpty()) stmt.setString(index++, telefoneNovo);
            stmt.setInt(index, alterarId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Paciente alterado com sucesso no banco de dados!");
            } else {
                System.out.println("Paciente não encontrado no banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar paciente
    public void deletarPaciente() {
        int deleteId = getIntInput("Digite o ID do paciente para deletar: ");
        String sql = "DELETE FROM paciente WHERE idPaciente = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, deleteId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Paciente removido com sucesso do banco de dados!");
            } else {
                System.out.println("Paciente não encontrado no banco de dados.");
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
        while (true) {
            System.out.println("""
                    Qual opção deseja?
                    1 - Cadastrar paciente
                    2 - Alterar paciente
                    3 - Deletar paciente
                    4 - Mostrar pacientes""");
            int opcaoMenu = input.nextInt();
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
                default:
                    System.out.println("Obrigado por usar!");
                    break;
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
        }
    }

    public int getIdPaciente() {
        return idPaciente;
    }
}
