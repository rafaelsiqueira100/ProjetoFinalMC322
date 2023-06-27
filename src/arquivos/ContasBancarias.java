package arquivos;

import entidades.Banco;
import entidades.ContaBancaria;
import entidades.Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContasBancarias {
    private BufferedReader streamIn;
    private BufferedWriter streamOut;
    private static final String nomeArquivo = "contasbancarias.csv";
    private static final String nomeArquivoBanco = "bancos.csv";
    public ContasBancarias() {    }
    
    public int getProximoCodigo() throws Exception{
        ContaBancaria[] contasBancarias = getContasBancaria();
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
        try{
            int proximoCodigo = getProximoCodigo();
            streamOut = new BufferedWriter( nomeArquivo);
            streamOut.write(
                Integer.toString(proximoCodigo) +","+
                Integer.toString(codCliente) +","+
                Integer.toString(codBanco)+","+
                senha+","+
                "0.0,"+
                Integer.toString(codAgencia)
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
        streamIn = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            if(valores[3].equals(senha)){
                streamIn.close();
                return new ContaBancaria(
                    Integer.parseInt(valores[0]),
                    Integer.parseInt(valores[1]),
                    Integer.parseInt(valores[2]),
                    valores[3],
                    new BigDecimal(Float.parseFloat(valores[4])),
                    valores[5]
              );
            }
        }
        return null;
    }
    
    public Banco getBanco(ContaBancaria conta) throws Exception {
        if (conta == null) {
            throw new Exception("ContasBancarias: consulta ao Banco de ContaBancária nula");
        }
        streamIn = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        int codBanco;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(',');
            int codContaBancaria = Integer.parseInt(valores[0]);
            if(codContaBancaria == conta.getCodContaBancaria()){
                codBanco = Integer.parseInt(valores[2]);
            }
        }
        streamIn.close();

        streamIn = new BufferedReader( new FileReader(nomeArquivoBanco));
        String linha;
        ArrayList<Banco> registros = new ArrayList<Banco>();
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(',');
            int codBancoAtual = valores[0];
            if(codBancoAtual == codBanco){
                registros.add(
                    new Banco(Integer.parseInt(valores[0]),
                    valores[1],
                    Float.parseFloat(valores[2]),
                    Float.parseFloat(valores[3]),
                    new BigDecimal(Float.parseFloat(valores[4])),
                    new BigDecimal(Float.parseFloat(valores[5])),
                    Integer.parseInt(valores[6])));
            }
        }
        streamIn.close();
        return registros.toArray();
    }
    
    public int descontar(ContaBancaria aDescontar, BigDecimal valorParaDescontar) throws Exception {
        if (aDescontar == null) {
            throw new Exception("ContasBancarias: desconto em ContaBancária nula");
        }
        
        if (valorParaDescontar == null || valorParaDescontar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("ContasBancarias: desconto de valor inválido em ContaBancária");
        }
        
        /*String sql = "UPDATE ContaBancaria SET saldo = saldo - ? WHERE codContaBancaria = ?;";
        
        this.bd.prepareStatement(sql);
        
        this.bd.setBigDecimal(1, valorParaDescontar);
        this.bd.setInt       (2, aDescontar.getCodContaBancaria());
        
        int resultado = this.bd.executeUpdate();
        
        this.bd.commit();
        
        return resultado; */

        codContaBancariaAtual

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
