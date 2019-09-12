package operadora;
import java.time.LocalDateTime;
import java.time.LocalDate;
/**
 *
 * @author Lucca Leão
 */
public class Ligacao {
    private LocalDateTime horario;
    private int duracao;
    
    public Ligacao(int d, int dia, int mes, int ano, int hora, int minuto){
        LocalDate diaLig = LocalDate.of(ano, mes, dia);
        horario = diaLig.atTime(hora, minuto);
        duracao = d;
    }
    
    public float getDuracao(){
        return duracao;
    }
    public LocalDateTime getHorario(){
        return horario;
    }
    
    @Override
    public String toString(){
        String ligacaoString;
        ligacaoString = "Data e hora: "+horario+"\nDuracao:"+duracao+" minutos\n";
        return ligacaoString;
    }
    
    public static void main(String[] args){
    }
}
