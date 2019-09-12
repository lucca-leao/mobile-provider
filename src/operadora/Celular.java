package operadora;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ArrayList;
import java.time.LocalDate;
/**
 *
 * @author Lucca Leão
 */
public class Celular {
    private Cliente cliente;
    private ArrayList<Ligacao> listaLigacoes = new ArrayList<Ligacao>();
    private int numero;
    
    public Celular(Cliente c){
        Random rnd = new Random();
        cliente = c;
        numero = 10000000 + rnd.nextInt(89999999);
    }
    
    public int getNumero(){
        return numero;
    }
    
    public Cliente getCliente(){
        return cliente;
    }
    
    public ArrayList<Ligacao> getListaLigacoes(){
        return listaLigacoes;
    }
    
    public void registraLigacao(int d, int dia, int mes, int ano, int hora, int minuto) throws ExcecaoCreditosInsuficientes, ExcecaoCreditosVencidos{
        Ligacao lig = new Ligacao(d, dia, mes, ano, hora, minuto);
        listaLigacoes.add(lig);
    }
    
    public void adicionaCreditos(int cred){}
    
    public int getLigacoes(){return -1;}
    
    public int getCreditos(){return -1;}
    
    public LocalDate getVencimento(){return LocalDate.now();}
    
    @Override
    public String toString(){return "Celular sem plano.\n";}
    
    public ArrayList<Ligacao> getExtratoLigacoes(int dia_inicial, int mes_inicial, int ano_inicial){
        ArrayList<Ligacao> extrato = new ArrayList<Ligacao>();
        Ligacao lig;
        LocalDateTime hora;
        int dia, mes, ano;
        for(int i = 0; i < listaLigacoes.size(); i++){
            lig = listaLigacoes.get(i);
            hora = lig.getHorario();
            dia = hora.getDayOfMonth();
            mes = hora.getMonthValue();
            ano = hora.getYear();
            if(ano > ano_inicial){
                extrato.add(lig);
            }
            else if(ano == ano_inicial){
                if(mes > mes_inicial){
                    extrato.add(lig);
                }
                else if(mes == mes_inicial){
                    if(dia >= dia_inicial){
                        extrato.add(lig);
                    }
                }
            }
        }
        return extrato;
    }
    
    
    public static void main(String[] args){
        Cliente c = new Cliente("anderson", "0201009", "rua das rosas");
        Celular cell1 = new Celular(c);
        Celular cell2 = new Celular(c);
        Celular cell3 = new Celular(c);
        System.out.println(cell1.numero+"\n"+cell2.numero+"\n"+cell3.numero+"\n");
    }
}
