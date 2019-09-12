package operadora;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Lucca Leão
 */
public class Operadora {
    private String nome;
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    private ArrayList<Celular> listaCelulares = new ArrayList<Celular>();
    
    public Operadora(String n){
        nome = n;
    }
    
    public Celular buscaCelular(int numero) throws ExcecaoNumeroInexistente{
        Celular encontrado = null;
        for(int i = 0; i < listaCelulares.size(); i++){
            if(listaCelulares.get(i).getNumero() == numero){
                encontrado = listaCelulares.get(i);
            }
        }
        if(encontrado == null){
            throw new ExcecaoNumeroInexistente("O numero de telefone nao foi encontrado\n");
        }
        else
            return encontrado;
    }
    
    public void cadastrarCliente(Cliente c){
        listaClientes.add(c);
    }
    
    public void habilitarCelularPosPago(Cliente c, int dia){
        CelularPosPago cell = new CelularPosPago(c, dia);
        listaCelulares.add(cell);
    }
    
    public void habilitarCelularPrePago(Cliente c, int cred){
        CelularPrePago cell = new CelularPrePago(c, cred);
        listaCelulares.add(cell);
    }
    
    public void excluirCelular(int numero) throws ExcecaoNumeroInexistente, Exception{
        int k=-1;
        for(int i = 0; i < listaCelulares.size(); i++){
            if(listaCelulares.get(i).getNumero() == numero){
                k = i;
            }
        }
        if(k == -1){
            throw new ExcecaoNumeroInexistente("Celular nao encontrado\n");
        }
        else{
            if((listaCelulares.get(k) instanceof CelularPrePago)&&(listaCelulares.get(k).getCreditos() != 0)){
                throw new Exception("Impossivel excluir o celular: possui creditos na conta\n");
            }
            else if((listaCelulares.get(k) instanceof CelularPosPago)&&(listaCelulares.get(k).getLigacoes() != 0)){
                throw new Exception("Impossivel excluir o celular: fatura nao foi paga\n");
            }
            else{
                listaCelulares.remove(k);
            }
        }
    }
    
    public void adicionaCreditos(int numero, int cred) throws Exception{
        if (buscaCelular(numero).getNumero() == numero){
            if(buscaCelular(numero) instanceof CelularPrePago){
                buscaCelular(numero).adicionaCreditos(cred);
            }
            else{
                throw new Exception("Plano de telefonia invalido\n");
            }
        }
    }
    
    public void regLigacao(int numero, int d, int dia, int mes, int ano, int hora, int minuto) throws ExcecaoCreditosInsuficientes, ExcecaoCreditosVencidos, ExcecaoNumeroInexistente{
        buscaCelular(numero).registraLigacao(d, dia, mes, ano, hora, minuto);
    }
    
    public int getValorConta(int numero) throws Exception{
        int i = -1;
        if(buscaCelular(numero) instanceof CelularPosPago){
            i = buscaCelular(numero).getLigacoes();
        }
        else{
            throw new Exception("Plano de telefonia invalido");
        }
        return i;
    }
    
    public int getValorCreditos(int numero) throws Exception{
        int c = -1;
        if(buscaCelular(numero) instanceof CelularPrePago){
            c = buscaCelular(numero).getCreditos();
        }
        else{
            throw new Exception("Plano de telefonia invalido");
        }
        return c;
    }
    
    public LocalDate getDataVencimento(int numero) throws ExcecaoNumeroInexistente{
        return buscaCelular(numero).getVencimento();
    }
    
    public ArrayList<Ligacao> getExtrato(int numero, int dia, int mes, int ano) throws ExcecaoNumeroInexistente{
        return buscaCelular(numero).getExtratoLigacoes(dia, mes, ano);
    }
    
    public ArrayList<Cliente> getListaClientes(){
        return listaClientes;
    }
    
    public ArrayList<Celular> getListaCelulares(){
        return listaCelulares;
    }
    
    public String getNomeOperadora(){
        return nome;
    }
    
    public static void main(String[] args){
        Operadora o = new Operadora("vivo");
        Cliente c1 = new Cliente("lucca","02105059743","rua das flores");
        o.cadastrarCliente(c1);
        System.out.println(o.getListaClientes().get(0));
        o.habilitarCelularPosPago(c1, 15);
        int n = o.getListaCelulares().get(0).getNumero();
        try{
          o.adicionaCreditos(n, 50);
          System.out.println(o.getListaCelulares().get(0).toString());
        }
        catch(Exception e){e.printStackTrace();}
    }
}
