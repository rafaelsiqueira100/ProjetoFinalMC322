package arquivos;

import entidades.Banco;
import entidades.ContaBancaria;
import entidades.Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArquivoContasBancarias extends Registro{
    private BufferedReader streamIn;
    private BufferedWriter streamOut;
    private static final String nomeArquivo = "/home/rafaelsiqueira/ProjetoFinalMC322/src/arquivos/contasbancarias.csv";
    private static final String nomeArquivoBanco = "/home/rafaelsiqueira/ProjetoFinalMC322/src/arquivos/bancos.csv";
    public ArquivoContasBancarias() {    }
    
    public int getProximoCodigo() throws Exception{
        ArrayList<ContaBancaria> contasBancarias = getContasBancarias();
        int codigoMaximo = 0;
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
            streamOut = new BufferedWriter( new FileWriter(nomeArquivo));
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

    public ArrayList<ContaBancaria> getContasBancarias() throws Exception { 
        streamIn = new BufferedReader( new FileReader(nomeArquivo));
        ArrayList<ContaBancaria> registros = new ArrayList<ContaBancaria>();
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            registros.add( new ContaBancaria(
                    Integer.parseInt(valores[0]),
                    Integer.parseInt(valores[1]),
                    Integer.parseInt(valores[2]),
                    valores[3],
                    new BigDecimal(Float.parseFloat(valores[4])),
                    valores[5]
              ));
        }
        
        streamIn.close();
        return registros;
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
    
    public ContaBancaria getContaBancaria(int codContaBancaria) throws Exception {
        if (codContaBancaria < 0) {
            throw new Exception("ContasBancarias: busca por conta com senha inválida");
        }        
        streamIn = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            if(Integer.parseInt(valores[0]) == codContaBancaria){
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
        int codBanco = -1;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            int codContaBancaria = Integer.parseInt(valores[0]);
            if(codContaBancaria == conta.getCodContaBancaria()){
                codBanco = Integer.parseInt(valores[2]);
            }
        }
        streamIn.close();

        streamIn = new BufferedReader( new FileReader(nomeArquivoBanco));
        ArrayList<Banco> registros = new ArrayList<Banco>();
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            int codBancoAtual = Integer.parseInt(valores[0]);
            if(codBancoAtual == codBanco){
                streamIn.close();
                    return new Banco(Integer.parseInt(valores[0]),
                    valores[1],
                    Float.parseFloat(valores[2]),
                    Float.parseFloat(valores[3]),
                    new BigDecimal(Float.parseFloat(valores[4])),
                    new BigDecimal(Float.parseFloat(valores[5])),
                    Integer.parseInt(valores[6]));
            }
        }
        streamIn.close();
        return null;
    }
    
    public int descontar(ContaBancaria aDescontar, BigDecimal valorParaDescontar) throws Exception {
        if (aDescontar == null) {
            throw new Exception("ContasBancarias: desconto em ContaBancária nula");
        }
        
        if (valorParaDescontar == null) {
            throw new Exception("ContasBancarias: desconto de valor inválido em ContaBancária");
        }
        try{
            streamIn = new BufferedReader( new FileReader(nomeArquivo));
            File novoArquivo = new File("temp.csv");
            novoArquivo.createNewFile();
            streamOut = new BufferedWriter( new FileWriter("temp.csv"));

            ContaBancaria contaAtual;
            String linha;
            while((linha = streamIn.readLine()) != null){
                String[] valores = linha.split(",");
                int codContaBancaria = Integer.parseInt(valores[0]);
                if(codContaBancaria == aDescontar.getCodContaBancaria()){
                    streamOut.write(
                        valores[0] + "," 
                        + valores[1] + "," 
                        + valores[2] + ","
                        +valores[3] + "," 
                        + Float.toString((Float.parseFloat(valores[4])) - valorParaDescontar.floatValue())+ "," 
                        + valores[5]);
                }
                else{
                    streamOut.write(
                        valores[0] + "," 
                        + valores[1] + "," 
                        + valores[2] + ","
                        +valores[3] + "," 
                        + valores[4] + "," 
                        + valores[5]);
                }
            }
            streamIn.close();
            streamOut.close();
            File novo = new File("temp.csv");
            File antigo = new File(nomeArquivo);
            novo.renameTo(antigo);
        }
        catch(IOException e){
            return -1;
        }

        return 0;


        

    }
    
    
    public int incrementar(ContaBancaria aIncrementar, BigDecimal valorParaIncrementar) throws Exception {        
        return descontar(aIncrementar, new BigDecimal(-valorParaIncrementar.floatValue()));
    }
}
