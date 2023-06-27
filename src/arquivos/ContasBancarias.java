package arquivos;

import DBOs.Banco;
import DBOs.ContaBancaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.ResultSet;

public class ContasBancarias {
    private BufferedReader streamIn;
    private BufferedWriter streamOut;
    private static final String nomeArquivo = "contasbancarias.csv";
    public ContasBancarias() {    }
    
    public int getProximoCodigo() throws Exception{
        ContaBancaria[] contasBancarias = getContaBancaria();
        int codigoMaximo = -1;
        for(ContaBancaria c: contasBancarias){
            if(c.getCodContaBancaria() > codigoMaximo)
                codigoMaximo = c.getCodContaBancaria();
        }
        return codigoMaximo + 1;
    }
    public int inserir(int codCliente, int codBanco, String senha, int codAgencia) throws Exception {
        if (codCliente < 1) {
            throw new Exception("ContasBancarias: inclusão com código de cliente inválido");
        }
        
        if (codBanco < 1) {
            throw new Exception("ContasBancarias: inclusão com código de banco inválido");
        }        
        
        if (senha == null || senha.equals("")) {
            throw new Exception("ContasBancarias: inclusão com senha inválida");
        }
        
        if (codAgencia < 1) {
            throw new Exception("ContasBancarias: inclusão com código de agência inválido");
        }
        
        int proximoCodigoContaBancaria = getProximoCodigo();

        /*String sql = "INSERT INTO ContaBancaria VALUES (?, ?, ?, ?, ?, ?);";
        
        this.bd.prepareStatement(sql);
        this.bd.setInt       (1, proximoCodigoContaBancaria);
        this.bd.setInt       (2, codCliente);
        this.bd.setInt       (3, codBanco);
        this.bd.setString    (4, senha);
        this.bd.setBigDecimal(5, BigDecimal.ZERO);
        this.bd.setInt       (6, codAgencia);
        
        int resultado = this.bd.executeUpdate();
        
        this.bd.commit();
        
        return resultado;*/
        try{
            int proximoCodigo = getProximoCodigo();
            streamOut = new BufferedWriter( new FileReader(nomeArquivo));
            streamOut.write(
                Integer.toString(proximoCodigo) +","+
                nome +","+
                Float.toString(jurosPoupanca)+","+
                Float.toString(jurosEmprestimo)+","+
                Integer.toString(mesesEmprestimo)+","+
                BigDecimal.toString(emprestimoMinimo)+","+
                BigDecimal.toString(emprestimoMaximo)
            );
            streamOut.close();
        }
        catch(Exception e){
            return -1;
        }
        return 1;
    }
    
    public ContaBancaria getContaBancaria(String senha) throws Exception {
        if (senha == null || senha.equals("")) {
            throw new Exception("ContasBancarias: busca por conta com senha inválida");
        }        
        
        String sql = "SELECT * FROM ContaBancaria WHERE senha = ?;";
        
        this.bd.prepareStatement(sql);
        
        this.bd.setString(1, senha);
        
        ResultSet resultadoConta = this.bd.executeQuery();
        
        ContaBancaria aRetornar = null;
        if (resultadoConta.next()) {
            aRetornar = new ContaBancaria(resultadoConta.getInt(1), resultadoConta.getInt(2), resultadoConta.getInt(3), resultadoConta.getString(4), resultadoConta.getBigDecimal(5), resultadoConta.getString(6));
        }
        
        return aRetornar;
    }
    
    public Banco getBanco(ContaBancaria conta) throws Exception {
        if (conta == null) {
            throw new Exception("ContasBancarias: consulta ao Banco de ContaBancária nula");
        }
        
        String sql = "SELECT * FROM Banco WHERE codBanco = (SELECT codBanco FROM ContaBancaria WHERE codContaBancaria = ?);";
        
        this.bd.prepareStatement(sql);
        
        this.bd.setInt(1, conta.getCodContaBancaria());
        
        ResultSet resultadoBanco = this.bd.executeQuery();
        
        Banco aConsultar = null;
        if (resultadoBanco.next()) {
            aConsultar = new Banco(resultadoBanco.getInt(1), resultadoBanco.getString(2), resultadoBanco.getFloat(3), resultadoBanco.getFloat(4), resultadoBanco.getBigDecimal(5), resultadoBanco.getBigDecimal(6), resultadoBanco.getInt(7));
        }            
        
        return aConsultar;
    }
    
    public int descontar(ContaBancaria aDescontar, BigDecimal valorParaDescontar) throws Exception {
        if (aDescontar == null) {
            throw new Exception("ContasBancarias: desconto em ContaBancária nula");
        }
        
        if (valorParaDescontar == null || valorParaDescontar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("ContasBancarias: desconto de valor inválido em ContaBancária");
        }
        
        String sql = "UPDATE ContaBancaria SET saldo = saldo - ? WHERE codContaBancaria = ?;";
        
        this.bd.prepareStatement(sql);
        
        this.bd.setBigDecimal(1, valorParaDescontar);
        this.bd.setInt       (2, aDescontar.getCodContaBancaria());
        
        int resultado = this.bd.executeUpdate();
        
        this.bd.commit();
        
        return resultado;
    }
    
    public int incrementar(ContaBancaria aIncrementar, BigDecimal valorParaIncrementar) throws Exception {
        if (aIncrementar == null) {
            throw new Exception("ContasBancarias: desconto em ContaBancária nula");
        }
        
        if (valorParaIncrementar == null || valorParaIncrementar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("ContasBancarias: desconto de valor inválido em ContaBancária");
        }
        
        String sql = "UPDATE ContaBancaria SET saldo = saldo + ? WHERE codContaBancaria = ?;";
        
        this.bd.prepareStatement(sql);
        
        this.bd.setBigDecimal(1, valorParaIncrementar);
        this.bd.setInt       (2, aIncrementar.getCodContaBancaria());
        
        int resultado = this.bd.executeUpdate();
        
        this.bd.commit();
        
        return resultado;
    }
}
