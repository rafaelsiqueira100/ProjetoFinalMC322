package arquivos;
import arquivos.ArquivoClientes;
import arquivos.ArquivoBancos;
import arquivos.ArquivoContasBancarias;
import arquivos.ArquivoSaques;
import arquivos.ArquivoDepositos;
import arquivos.ArquivoEmprestimos;
import arquivos.ArquivoCobrancas;
import arquivos.ArquivoTipoCobrancas;
public class DAOs {
    private static ArquivoClientes        tabelaClientes;
    private static ArquivoBancos          tabelaBancos;
    private static ArquivoContasBancarias tabelaContasBancarias;
    private static ArquivoSaques          tabelaSaques;
    private static ArquivoDepositos       tabelaDepositos;
    private static ArquivoEmprestimos     tabelaEmprestimos;
    private static ArquivoCobrancas       tabelaCobrancas;
    private static ArquivoTipoCobrancas   tabelaTipoCobrancas;

    static {
        try {
            DAOs.tabelaClientes        = new ArquivoClientes();
            DAOs.tabelaBancos          = new ArquivoBancos();
            DAOs.tabelaContasBancarias = new ArquivoContasBancarias();
            DAOs.tabelaSaques          = new ArquivoSaques();
            DAOs.tabelaDepositos       = new ArquivoDepositos();
            DAOs.tabelaEmprestimos     = new ArquivoEmprestimos();
            DAOs.tabelaCobrancas       = new ArquivoCobrancas();
            DAOs.tabelaTipoCobrancas   = new ArquivoTipoCobrancas();
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
    public static ArquivoClientes getTabelaClientes() {
        return tabelaClientes;
    }
    /**
     * Pega o dao de bancos
     * @return o dao que d� acesso a tabela de banco
     */
    public static ArquivoBancos getTabelaBancos() {
        return tabelaBancos;
    }
    /**
     * Pega o dao de contas banc�rias
     * @return o dao que d� acesso a tabela de conta bancaria
     */
    public static ArquivoContasBancarias getTabelaContasBancarias() {
        return tabelaContasBancarias;
    }
    /**
     * Pega o dao de saques
     * @return o dao que d� acesso a tabela de saque
     */
    public static ArquivoSaques getTabelaSaques() {
        return tabelaSaques;
    }
    /**
     * Pega o dao de depositos
     * @return o dao que d� acesso a tabela de deposito
     */
    public static ArquivoDepositos getTabelaDepositos() {
        return tabelaDepositos;
    }
    /**
     * Pega o dao de emprestimos
     * @return o dao que d� acesso a tabela de emprestimo
     */
    public static ArquivoEmprestimos getTabelaEmprestimos() {
        return tabelaEmprestimos;
    }
    /**
     * Pega o dao de cobrancas
     * @return o dao que d� acesso a tabela de cobranca
     */
    public static ArquivoCobrancas getTabelaCobrancas() {
        return tabelaCobrancas;
    }
    /**
     * Pega o dao de tipos de cobranças
     * @return o dao que d� acesso a tabela de boleto
     */
    public static ArquivoTipoCobrancas getTabelaTipoCobrancas() {
        return tabelaTipoCobrancas;
    }
}

