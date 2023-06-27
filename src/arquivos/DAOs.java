package arquivos;
import arquivos.Clientes;
import arquivos.Bancos;
import arquivos.ContasBancarias;
import arquivos.Saques;
import arquivos.Depositos;
import arquivos.Emprestimos;
import arquivos.Cobrancas;
import arquivos.Boletos;
import arquivos.TipoCobrancas;
public class DAOs {
    private static Clientes        tabelaClientes;
    private static Bancos          tabelaBancos;
    private static ContasBancarias tabelaContasBancarias;
    private static Saques          tabelaSaques;
    private static Depositos       tabelaDepositos;
    private static Emprestimos     tabelaEmprestimos;
    private static Cobrancas       tabelaCobrancas;
    private static Boletos         tabelaBoletos;
    private static TipoCobrancas   tabelaTipoCobrancas;

    static {
        try {
            DAOs.tabelaClientes        = new Clientes();
            DAOs.tabelaBancos          = new Bancos();
            DAOs.tabelaContasBancarias = new ContasBancarias();
            DAOs.tabelaSaques          = new Saques();
            DAOs.tabelaDepositos       = new Depositos();
            DAOs.tabelaEmprestimos     = new Emprestimos();
            DAOs.tabelaCobrancas       = new Cobrancas();
            DAOs.tabelaBoletos         = new Boletos();
            DAOs.tabelaTipoCobrancas   = new TipoCobrancas();
            //um como esse para cada classe DAO
        } catch (Exception erro) {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
    }


    /**
     * Pega o dao de clientes
     * @return o dao que d� acesso a tabela de cliente
     */
    public static Clientes getTabelaClientes() {
        return tabelaClientes;
    }
    /**
     * Pega o dao de bancos
     * @return o dao que d� acesso a tabela de banco
     */
    public static Bancos getTabelaBancos() {
        return tabelaBancos;
    }
    /**
     * Pega o dao de contas banc�rias
     * @return o dao que d� acesso a tabela de conta bancaria
     */
    public static ContasBancarias getTabelaContasBancarias() {
        return tabelaContasBancarias;
    }
    /**
     * Pega o dao de saques
     * @return o dao que d� acesso a tabela de saque
     */
    public static Saques getTabelaSaques() {
        return tabelaSaques;
    }
    /**
     * Pega o dao de depositos
     * @return o dao que d� acesso a tabela de deposito
     */
    public static Depositos getTabelaDepositos() {
        return tabelaDepositos;
    }
    /**
     * Pega o dao de emprestimos
     * @return o dao que d� acesso a tabela de emprestimo
     */
    public static Emprestimos getTabelaEmprestimos() {
        return tabelaEmprestimos;
    }
    /**
     * Pega o dao de cobrancas
     * @return o dao que d� acesso a tabela de cobranca
     */
    public static Cobrancas getTabelaCobrancas() {
        return tabelaCobrancas;
    }
    /**
     * Pega o dao de boletos
     * @return o dao que d� acesso a tabela de boleto
     */
    public static Boletos getTabelaBoletos() {
        return tabelaBoletos;
    }    
    /**
     * Pega o dao de tipos de cobranças
     * @return o dao que d� acesso a tabela de boleto
     */
    public static TipoCobrancas getTabelaTipoCobrancas() {
        return tabelaTipoCobrancas;
    }
}

