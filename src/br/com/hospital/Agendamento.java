package br.com.hospital;
import br.com.hospital.Paciente;
import br.com.hospital.Medico;
public class Agendamento{
   private Paciente paciente;
   private Medico medico;
   public Agendamento(Paciente paciente, Medico medico){
       this.paciente = paciente;
       this.medico = medico;
       System.out.println("Os agendamentos são");
   }


}