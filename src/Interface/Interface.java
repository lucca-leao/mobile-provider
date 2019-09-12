package Interface;
import operadora.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
/**
 *
 * @author Lucca Leão
 */
public class Interface {
    public void menu(Operadora o) throws Exception, ExcecaoCreditosInsuficientes, ExcecaoCreditosVencidos, ExcecaoNumeroInexistente{
        Scanner sc = new Scanner(System.in);
        String continuar = new String();
        int seletor;
        do{
            seletor = 0;
            System.out.println("Bem vindo a operadora "+o.getNomeOperadora());
            System.out.println("Selecione a opcao desejada:");
            System.out.println("[1]Cadastrar um novo cliente.");
            System.out.println("[2]Habilitar um novo celular.");
            System.out.println("[3]Excluir um celular.");
            System.out.println("[4]Adicionar creditos a um celular do tipo cartao.");
            System.out.println("[5]Registrar uma ligacao.");
            System.out.println("[6]Listar valor da fatura de um celular do tipo assinatura.");
            System.out.println("[7]Listar valor dos creditos de um celular do tipo cartao.");
            System.out.println("[8]Listar extrato de ligacoes.");
            System.out.println("[9]Listar clientes.");
            System.out.println("[10]Listar celulares.");
            System.out.println("[11]Informativo de vencimento.");
            System.out.println("[12]Sair do sistema.");
            seletor = sc.nextInt();
            switch(seletor){
                case(1):
                    cadastraCliente(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(2):
                    habilitaCelular(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(3):
                    excluiCelular(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(4):
                    adicionaCreditos(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(5):
                    registraLigacao(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(6):
                    listaConta(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(7):
                    listaCreditos(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(8):
                    listaExtrato(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(9):
                    listaClientes(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(10):
                    listaCelulares(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(11):
                    informativoVencimento(o);
                    System.out.println("Pressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
                case(12):
                    System.out.println("Saindo . . . \nPressione qualquer tecla para continuar.");
                    return;
                default:
                    System.out.println("Entrada Invalida.\nPressione qualquer tecla para continuar.");
                    continuar = sc.next();
                    break;
            }
        }while(true);
    }
    
    public void cadastraCliente(Operadora o){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nome do cliente:\n");
        String nome = sc.nextLine();
        System.out.println("CPF/CNPJ do cliente:\n");
        String cpf_cnpj = sc.nextLine();
        System.out.println("Endereco do cliente:\n");
        String endereco = sc.nextLine();
        Cliente c = new Cliente(nome, cpf_cnpj, endereco);
        o.cadastrarCliente(c);
    }
    
    public void habilitaCelular(Operadora o){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual plano voce deseja se cadastrar:");
        System.out.println("[1]Pre Pago");
        System.out.println("[2]Pos Pago");
        String cpf_cnpj;
        Cliente c;
        int seletor = sc.nextInt();
        switch(seletor){
            case(1):
                System.out.println("Informe o CPF/CNPJ do cliente:");
                cpf_cnpj = sc.next();
                for(int i = 0; i < o.getListaClientes().size(); i++){
                    if(o.getListaClientes().get(i).getCpfCnpj().equals(cpf_cnpj)){
                        c = o.getListaClientes().get(i);
                        o.habilitarCelularPrePago(c, 0);
                        System.out.println("Celular habilitado com sucesso.");
                        return;
                    }
                }
                System.out.println("Cliente nao encontrado.");
                break;
            case(2):
                System.out.println("Informe o CPF/CNPJ do cliente:");
                cpf_cnpj = sc.next();
                for(int i = 0; i < o.getListaClientes().size(); i++){
                    if(o.getListaClientes().get(i).getCpfCnpj().equals(cpf_cnpj)){
                        c = o.getListaClientes().get(i);
                        System.out.println("Escolha o dia de vencimento da fatura: ");
                        int dia = sc.nextInt();
                        o.habilitarCelularPosPago(c, dia);
                        System.out.println("Celular habilitado com sucesso.");
                        return;
                    }
                }
                System.out.println("Cliente nao encontrado.");
                break;
            default:
                System.out.println("Entrada invalida.\nEntre com qualquer tecla para continuar.");
                String continuar = sc.next();
        }
    }
    
    public void excluiCelular(Operadora o) throws ExcecaoNumeroInexistente, Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o numero do celular para ser excluido:");
        int num = sc.nextInt();
        o.excluirCelular(num);
    }
    
    public void adicionaCreditos(Operadora o) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe quantos creditos devem ser inseridos:");
        int cred = sc.nextInt();
        System.out.println("Informe o numero de celular para adicionar os creditos:");
        int num = sc.nextInt();
        o.adicionaCreditos(num, cred);
    }
    
    public void registraLigacao(Operadora o) throws ExcecaoCreditosInsuficientes, ExcecaoCreditosVencidos, ExcecaoNumeroInexistente{
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o celular para registrar a ligacao: ");
        int num = sc.nextInt();
        System.out.println("Informe a duracao da ligacao em minutos: ");
        int d = sc.nextInt();
        System.out.println("Informe o dia em que a ligacao ocorreu:");
        int dia = sc.nextInt();
        System.out.println("Informe o mes em que a ligacao ocorreu:");
        int mes = sc.nextInt();
        System.out.println("Informe o ano em que a ligacao ocorreu:");
        int ano = sc.nextInt();
        System.out.println("Informe a hora em que a ligacao ocorreu:");
        int hora = sc.nextInt();
        System.out.println("Informe o minuto da hora em que a ligacao ocorreu");
        int minuto = sc.nextInt();
        o.regLigacao(num, d, dia, mes, ano, hora, minuto);
    }
    
    public void listaConta(Operadora o) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o numero do celular para obter o valor da conta: ");
        int num = sc.nextInt();
        int conta = o.getValorConta(num);
        System.out.println("Valo da conta: "+conta);
    }
    
    public void listaCreditos(Operadora o) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o numero do celular para obter o valor dos creditos:");
        int num = sc.nextInt();
        int cred = o.getValorCreditos(num);
        System.out.println("Valor dos creditos: "+cred);
    }
    
    public void listaExtrato(Operadora o) throws ExcecaoNumeroInexistente{
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o numero de celular para obter o extrato: ");
        int num = sc.nextInt();
        System.out.println("Informe a data inicial do extrato:");
        int dia = sc.nextInt();
        System.out.println("Informe o mes inicial do extrato:");
        int mes = sc.nextInt();
        System.out.println("Informe o ano inicial do extrato:");
        int ano = sc.nextInt();
        ArrayList<Ligacao> extrato = o.getExtrato(num, dia, mes, ano);
        for(int i = 0; i < extrato.size(); i++){
            System.out.println(extrato.get(i).toString());
        }
    }
    
    public void listaClientes(Operadora o){
        for(int i = 0; i < o.getListaClientes().size(); i++){
            System.out.println(o.getListaClientes().get(i).toString());
        }
    }
    
    public void listaCelulares(Operadora o){
        for(int i = 0; i < o.getListaCelulares().size(); i++){
            System.out.println(o.getListaCelulares().get(i).toString());
        }
    }
    
    public void informativoVencimento(Operadora o){
        for(int i = 0; i < o.getListaCelulares().size(); i++){
            if(o.getListaCelulares().get(i).getVencimento().isBefore(LocalDate.now()));
            System.out.println(o.getListaCelulares().get(i).toString());
        }
    }
    
    public static void main(String[] args){
        Operadora o = new Operadora("VIVO");
        Interface i = new Interface();
        try{
            i.menu(o);
        }
        catch(ExcecaoNumeroInexistente e){e.printStackTrace();}
        catch(ExcecaoCreditosVencidos e){e.printStackTrace();}
        catch(ExcecaoCreditosInsuficientes e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
    }
}
