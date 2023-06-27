package arquivos;
import arquivos.ArquivoClientes;
import arquivos.ArquivoBancos;
import arquivos.ArquivoContas;
import arquivos.ArquivoSaques;
import arquivos.ArquivoDepositos;
import arquivos.ArquivoEmprestimos;
public class DAOs {
    private static ArquivoClientes        tabelaClientes;
    private static ArquivoBancos          tabelaBancos;
    private static ArquivoContas          tabelaContas;
    private static ArquivoSaques          tabelaSaques;
    private static ArquivoDepositos       tabelaDepositos;
    private static ArquivoEmprestimos     tabelaEmprestimos;

    static {
        try {
            DAOs.tabelaClientes        = new ArquivoClientes();
            DAOs.tabelaBancos          = new ArquivoBancos();
            DAOs.tabelaContas          = new ArquivoContas();
            DAOs.tabelaSaques          = new ArquivoSaques();
            DAOs.tabelaDepositos       = new ArquivoDepositos();
            DAOs.tabelaEmprestimos     = new ArquivoEmprestimos();
            //um como esse para cada classe DAO
        } catch (Exception erro) {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
    }


    public static ArquivoClientes getTabelaClientes() {
        return tabelaClientes;
    }
    
    public static ArquivoBancos getTabelaBancos() {
        return tabelaBancos;
    }
    
    public static ArquivoContas getTabelaContas() {
        return tabelaContas;
    }
    
    public static ArquivoSaques getTabelaSaques() {
        return tabelaSaques;
    }
    
    public static ArquivoDepositos getTabelaDepositos() {
        return tabelaDepositos;
    }
    
    public static ArquivoEmprestimos getTabelaEmprestimos() {
        return tabelaEmprestimos;
    }
}

