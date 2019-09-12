package operadora;
import java.time.LocalDate;
/**
 *
 * @author Lucca Leão
 */
public class CelularPrePago extends Celular{
    private int creditos;
    private LocalDate vencimento;
    
    public CelularPrePago(Cliente c, int cred){
        super(c);
        creditos = cred;
        vencimento = LocalDate.now().plusDays(180);
    }
    
    public void setVencimento(LocalDate v){
        vencimento = v;
    }
    
    @Override
    public void registraLigacao(int d, int dia, int mes, int ano, int hora, int minuto) throws ExcecaoCreditosInsuficientes, ExcecaoCreditosVencidos{
        if(d > creditos){
            throw new ExcecaoCreditosInsuficientes("Impossivel registrar ligacao: Creditos insuficientes\n");
        }
        else if(LocalDate.now().isAfter(vencimento)){
            throw new ExcecaoCreditosVencidos("Impossivel completar a ligacao: Data de vencimento expirada\n");
        }
        else{
            super.registraLigacao(d, dia, mes, ano, hora, minuto);
        }
    }
    
    @Override
    public void adicionaCreditos(int cred){
        creditos += cred;
        vencimento = LocalDate.now().plusDays(180);
    }
    
    @Override
    public int getCreditos(){
        return creditos;
    }
    
    @Override
    public LocalDate getVencimento(){
        return vencimento;
    }
    
    @Override
    public String toString(){
        String string_cell = getCliente().toString()+"\nNumero: "+getNumero()+"\nCreditos: "+creditos+"\nCreditos vencem em: "+getVencimento().toString()+"\nLigacoes:\n";
        for(int i = 0; i < getListaLigacoes().size(); i++){
            string_cell += getListaLigacoes().get(i).toString();
        }
        return string_cell;
    }
    
    public static void main(String[] args){
        Cliente lu = new Cliente("lucca", "02150865203", "rua das floes");
        CelularPrePago c = new CelularPrePago(lu, 50);
        try{
            c.registraLigacao(60,3,3,3,3,3);
        }
        catch(ExcecaoCreditosInsuficientes e){System.out.println("deu ruim");}
        catch(ExcecaoCreditosVencidos e){System.out.println("de novo");}
    }
}
