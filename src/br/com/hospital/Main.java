package br.com.hospital;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opcao;
        Scanner input = new Scanner(System.in);
        Medico medico = new Medico();
        Paciente paciente = new Paciente();

        do {
            System.out.println("""
                    \nEscolha uma opção\
                    
                    1. Para cadastrar médico\
                    
                    2. Para cadastrar paciente\
                    
                    3. Para verificar os médico\
                    
                    4. Para verificar os pacientes\
                    
                    5. Para agendamento\
                    
                    6. Para deletar\
                    
                    . Para sair""");
            opcao = input.nextInt();
            switch (opcao) {
                case 1:
                    medico.cadastrarMedico();
                    break;
                case 2:
                    paciente.cadastraPaciente();
                    break;
                case 3:
                    medico.getListaMedica();
                    break;
                case 4:
                    paciente.getListaPaciente();
                    break;
                case 5:

                    break;
                case 6:
                    medico.deletarMedico();
                    break;
            }
        } while (opcao != 7);
        System.out.println("Obrigado por utilizar.");
    }
}