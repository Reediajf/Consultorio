import java.util.Scanner;
import java.util.ArrayList;

public class Paciente {
    Scanner input = new Scanner(System.in);
    ArrayList<String> listaPaciente = new ArrayList<>();
    private String nome;
    private String cpf;
    private String sexo;
    private String nascimento;
    private String email;
    private String telefone;

    public void cadastraPaciente() {
        System.out.println("Digite o nome do paciente: ");
        this.nome = input.nextLine();
        System.out.println("Digite o CPF: ");
        this.cpf = input.nextLine();
        System.out.println("Digite o sexo: ");
        this.sexo = input.nextLine();
        System.out.println("Digite o telefone: ");
        this.telefone = input.nextLine();
        System.out.println("Digite a data de nascimento: ");
        this.nascimento = input.nextLine();
        System.out.println("Digite o email: ");
        this.email = input.nextLine();
        listaPaciente.add(toString());
    }

    @Override
    public String toString() {
        return "Paciente" +
                "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\nSexo: " + sexo +
                "\nData de Nascimento: " + nascimento +
                "\nEmail: " + email +
                "\nTelefone: " + telefone;
    }

    void getlistaPaciente() {
        for (int i = 0; i < listaPaciente.size(); i++) {
            System.out.printf("\n\n" + this + "\n\n");
        }
    }

    public String getNomePaciente() {
        return this.nome;
    }
}
