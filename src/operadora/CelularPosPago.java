package operadora;
import java.time.LocalDate;

/**
 *
 * @author Lucca Leão
 */
public class CelularPosPago extends Celular{
    private LocalDate vencimento;
    private int ligacoes;
    
    public CelularPosPago(Cliente c, int dia){
        super(c);
        ligacoes = 0;
        int mes = LocalDate.now().getMonthValue();
        if(mes == 12){
            mes = 1;
        }
        else{
            mes++;
        }
        vencimento = LocalDate.now().withMonth(mes).withDayOfMonth(dia);
    }
    
    @Override
    public void registraLigacao(int d, int dia, int mes, int ano, int hora, int minuto) throws ExcecaoCreditosVencidos, ExcecaoCreditosInsuficientes{
        if(LocalDate.now().isAfter(vencimento)){
            throw new ExcecaoCreditosVencidos("Impossivel registrar ligacao: Data da fatura vencida\n");
        }
        else{
            super.registraLigacao(d, dia, mes, ano, hora, minuto);
            ligacoes += d;  //incrementa os minutos
        }
    }
    public void setVencimento(LocalDate d){
        vencimento = d;
    }
    
    @Override
    public LocalDate getVencimento(){
        return vencimento;
    }
    
    @Override
    public int getLigacoes(){
        return ligacoes;
    }
    
    @Override
    public String toString(){
        String string_cell = getCliente().toString()+"\nNumero: "+getNumero()+"\nValor da fatura: "+ligacoes+"\nFatura vence em: "+getVencimento().toString()+"\nLigacoes:\n";
        for(int i = 0; i < getListaLigacoes().size(); i++){
            string_cell += getListaLigacoes().get(i).toString();
        }
        return string_cell;
    }
    
    public void pagaFatura(){
        setVencimento(LocalDate.now().plusDays(180));
        ligacoes = 0; //paga as ligacoes
    }
}
