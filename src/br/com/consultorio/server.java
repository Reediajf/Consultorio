package br.com.consultorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class server {

    private static final String URL = "jdbc:mysql://localhost:3306/consultorio";
    private static final String USER = "dt";
    private static final String PASSWORD = "admin";
    private static Connection conexao;

    // Metodo para inicializar a conexão ao banco de dados
    public static void init() {
        if (conexao == null) {
            conexao = conectar();
        }
    }

    // Metodo para conectar ao banco de dados
    public static Connection conectar() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexão estabelecida com sucesso!");
            }
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    // Metodo para buscar dados do banco
    public static ResultSet buscar(String sql) {
        try {
            init();  // Inicializa a conexão ao banco, se necessário
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar dados: " + e.getMessage());
            return null;
        }
    }

    // Metodo para salvar dados no banco
    public static boolean salvar(String sql) {
        try {
            init();  // Inicializa a conexão ao banco, se necessário
            PreparedStatement stmt = conexao.prepareStatement(sql);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            return false;
        }
    }

    // Metodo para atualizar dados no banco
    public static boolean atualizar(String sql) {
        return salvar(sql); // Reutiliza o método de salvar com SQL de atualização
    }

    // Metodo para deletar dados do banco
    public static boolean deletar(String sql) {
        return salvar(sql); // Reutiliza o método de salvar com SQL de deleção
    }

    // Método para consultar dados
    public static ResultSet consultar(String sql) {
        return buscar(sql);
    }

    // Metodo para fechar a conexão ao banco (opcional, quando a aplicação terminar)
    public static void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão fechada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
