package operadora;

/**
 *
 * @author Lucca Leão
 */
public class Cliente {
    private String cpfCnpj;
    private String nome;
    private String endereco;
    
    public Cliente(String n, String cpf_cnpj, String end){
        cpfCnpj = cpf_cnpj;
        nome = n;
        endereco = end;
    }
    public String getCpfCnpj(){
        return cpfCnpj;
    }
    public String getNome(){
        return nome;
    }
    public String getEndereco(){
        return endereco;
    }
    public void setCpfCnpj(String cpf_cnpj){
        cpfCnpj = cpf_cnpj;
    }
    public void setNome(String n){
         nome = n;
   }
    public void setEndereco(String end){
        endereco = end;
    }
    
    @Override
    public String toString(){
        String clientString = nome+"\n"+cpfCnpj+"\n"+endereco+"\n";
        return clientString;
    }
}
