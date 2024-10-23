import java.util.Scanner;
import java.util.ArrayList;

public class Medico {
    ArrayList<String> listaMedica = new ArrayList<>();
    private String nome;
    private String telefone;
    private String crm;
    private String periodoAtendimento;
    private String especialidade;
    Scanner input = new Scanner(System.in);

    public void cadastrarMedico() {
        System.out.println("Digite o nome do médico: ");
        this.nome = input.nextLine();
        System.out.println("Digite o CRM: ");
        this.crm = input.nextLine();
        System.out.println("Digite o especialidade: ");
        this.especialidade = input.nextLine();
        System.out.println("Digite o telefone: ");
        this.telefone = input.nextLine();
        System.out.println("Digite o periodo atendimento: ");
        this.periodoAtendimento = input.nextLine();
        listaMedica.add(toString());
    }

    @Override
    public String toString() {
        return "Medico" +
                "\nNome: " + nome +
                "\nTelefone: " + telefone +
                "\nCRM: " + crm +
                "\nPeriodo de Atendimento: " + periodoAtendimento +
                "\nEspecialidade: " + especialidade;
    }

    void getListaMedica() {
        for (int i = 0; i < listaMedica.size(); i++) {
            System.out.printf("\n\n" + this + "\n\n");
        }

    }


    public String getNome() {
        return this.nome;
    }
}
