package DAOs;

import main.MeuPreparedStatement;

/**
 * Classe cujos atributos correspondem aos objetos de acesso ao BD
 * @author Rafael Andre Siqueira
 * @since 2017
 */
public class DAOs {
    private static MeuPreparedStatement bd;

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
            /*
            String drv,
                                 String strCon,
                                 String usr,
                                 String senha
             */
            DAOs.bd = new MeuPreparedStatement ("org.postgresql.Driver",
             "jdbc:postgresql:User=sammy;Password=668791470;Database=postgres;Server=127.0.0.1;Port=5432;",
               "sammy",
                "668791470");

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

    public static MeuPreparedStatement getBD () {
        return DAOs.bd;
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

