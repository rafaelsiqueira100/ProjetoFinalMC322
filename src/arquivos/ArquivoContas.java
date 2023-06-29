package arquivos;

import entidades.Banco;
import entidades.ContaBancaria;
import entidades.Conta;
import entidades.ContaUniversitaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ArquivoContas extends Registro{

    public ArquivoContas() {    }
    
    @Override
    public int getProximoCodigo() throws Exception{
        ArrayList<Conta> contas = getContas();
        int codigoMaximo = 0;
        for(Conta c: contas){
            if(c.getCodContaBancaria() > codigoMaximo)
                codigoMaximo = c.getCodContaBancaria();
        }
        return codigoMaximo + 1;
    }
    public int inserir(int codCliente, int codBanco, String senha, int codAgencia, String ra) throws Exception {
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
        try{
            int proximoCodigo = getProximoCodigo();
            streamOut = new BufferedWriter( new FileWriter(NOMEARQUIVO));
            if(ra == null || ra.equals("")){
                streamOut.write(
                    Integer.toString(proximoCodigo) +","+
                    Integer.toString(codCliente) +","+
                    Integer.toString(codBanco)+","+
                    senha+","+
                    "0.0,"+
                    Integer.toString(codAgencia)+","+
                    "false, "
                );
            }
            else{
                streamOut.write(
                    Integer.toString(proximoCodigo) +","+
                    Integer.toString(codCliente) +","+
                    Integer.toString(codBanco)+","+
                    senha+","+
                    "0.0,"+
                    Integer.toString(codAgencia)+","+
                    "true,"+
                    ra
                );
            }
            streamOut.close();
        }
        catch(Exception e){
            return -1;
        }
        return 1;
    }

    public ArrayList<Conta> getContas() throws Exception { 
        streamIn = new BufferedReader( new FileReader(NOMEARQUIVO));
        ArrayList<Conta> registros = new ArrayList<>();
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            boolean ehContaUniversitaria = Boolean.parseBoolean(valores[6]);
            if(!ehContaUniversitaria){
                registros.add( new ContaBancaria(
                        Integer.parseInt(valores[0]),
                        Integer.parseInt(valores[1]),
                        Integer.parseInt(valores[2]),
                        valores[3],
                        new BigDecimal(Float.parseFloat(valores[4])),
                        valores[5]
                  ));
            }
            else{
                registros.add( new ContaUniversitaria(
                        Integer.parseInt(valores[0]),
                        Integer.parseInt(valores[1]),
                        Integer.parseInt(valores[2]),
                        valores[3],
                        new BigDecimal(Float.parseFloat(valores[4])),
                        valores[5],
                        valores[7]
                  ));
            }
        }
        
        streamIn.close();
        return registros;
    }
    public Conta getConta(String senha) throws Exception {
        if (senha == null || senha.equals("")) {
            throw new Exception("ContasBancarias: busca por conta com senha inválida");
        }        
        streamIn = new BufferedReader( new FileReader(NOMEARQUIVO));
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            if(valores[3].equals(senha)){
                streamIn.close();
                boolean ehContaUniversitaria = Boolean.parseBoolean(valores[6]);
                if(!ehContaUniversitaria){
                    return new ContaBancaria(
                        Integer.parseInt(valores[0]),
                        Integer.parseInt(valores[1]),
                        Integer.parseInt(valores[2]),
                        valores[3],
                        new BigDecimal(Float.parseFloat(valores[4])),
                        valores[5]
                    );
                }
                else{
                    return new ContaUniversitaria(
                        Integer.parseInt(valores[0]),
                        Integer.parseInt(valores[1]),
                        Integer.parseInt(valores[2]),
                        valores[3],
                        new BigDecimal(Float.parseFloat(valores[4])),
                        valores[5],
                        valores[7]
                    );
                }
            }
        }
        return null;
    }
    
    public Conta getConta(int codContaBancaria) throws Exception {
        if (codContaBancaria < 0) {
            throw new Exception("ContasBancarias: busca por conta com senha inválida");
        }        
        streamIn = new BufferedReader( new FileReader(NOMEARQUIVO));
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            if(Integer.parseInt(valores[0]) == codContaBancaria){
                streamIn.close();
                boolean ehContaUniversitaria = Boolean.parseBoolean(valores[6]);
                if(!ehContaUniversitaria){
                    return new ContaBancaria(
                        Integer.parseInt(valores[0]),
                        Integer.parseInt(valores[1]),
                        Integer.parseInt(valores[2]),
                        valores[3],
                        new BigDecimal(Float.parseFloat(valores[4])),
                        valores[5]
                    );
                }
                else{
                    return new ContaUniversitaria(
                        Integer.parseInt(valores[0]),
                        Integer.parseInt(valores[1]),
                        Integer.parseInt(valores[2]),
                        valores[3],
                        new BigDecimal(Float.parseFloat(valores[4])),
                        valores[5],
                        valores[7]
                    );
                }
            }
        }
        return null;
    }
    
    public Banco getBanco(Conta conta) throws Exception {
        if (conta == null) {
            throw new Exception("ContasBancarias: consulta ao Banco de ContaBancária nula");
        }
        streamIn = new BufferedReader( new FileReader(NOMEARQUIVO));
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

        streamIn = new BufferedReader( new FileReader(NOMEARQUIVOBANCO));
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
    
    public int descontar(Conta aDescontar, BigDecimal valorParaDescontar) throws Exception {
        if (aDescontar == null) {
            throw new Exception("ContasBancarias: desconto em ContaBancária nula");
        }
        
        if (valorParaDescontar == null) {
            throw new Exception("ContasBancarias: desconto de valor inválido em ContaBancária");
        }
        try{
            streamIn = new BufferedReader( new FileReader(NOMEARQUIVO));
            File novoArquivo = new File("temp.csv");
            novoArquivo.createNewFile();
            streamOut = new BufferedWriter( new FileWriter("temp.csv"));

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
                            + valores[5] + ","
                            + valores[6] + ","
                            + valores[7]);
                }
                else{
                    streamOut.write(
                        valores[0] + "," 
                        + valores[1] + "," 
                        + valores[2] + ","
                        +valores[3] + "," 
                        + valores[4] + "," 
                        + valores[5] + ","
                        + valores[6] + ","
                        + valores[7]);
                }
            }
            streamIn.close();
            streamOut.close();
            File novo = new File("temp.csv");
            File antigo = new File(NOMEARQUIVO);
            novo.renameTo(antigo);
        }
        catch(IOException e){
            return -1;
        }

        return 0;


        

    }
    
    
    public int incrementar(Conta aIncrementar, BigDecimal valorParaIncrementar) throws Exception {        
        return descontar(aIncrementar, new BigDecimal(-valorParaIncrementar.floatValue()));
    }
    
    private static final String NOMEARQUIVO = "C:\\Users\\Rafael Siqueira\\OneDrive\\Área de Trabalho\\Projeto Final MC322\\Projeto Prática Profissional\\ProjetoPPII\\ProjetoFinalMC322\\src\\arquivos\\contas.csv";
    private static final String NOMEARQUIVOBANCO = "C:\\Users\\Rafael Siqueira\\OneDrive\\Área de Trabalho\\Projeto Final MC322\\Projeto Prática Profissional\\ProjetoPPII\\ProjetoFinalMC322\\src\\arquivos\\bancos.csv";
}
