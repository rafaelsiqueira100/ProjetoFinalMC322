package arquivos;

import entidades.Cobranca;
import entidades.Banco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArquivoCobrancas extends Registro{
	/**
     * Construtor de objeto DAO
     * @throws Exception  por conta do construtor da superclasse lan�ar exce��o
     */
    
    private BufferedReader streamIn;
    private BufferedWriter streamOut;
    private static final String nomeArquivo = "/home/rafaelsiqueira/ProjetoFinalMC322/src/arquivos/cobrancas.csv";
    public int getProximoCodigo(){
        return -1;
    }
    public ArquivoCobrancas() {    }

    public int quantasCobrancas(int codContaBancaria) throws Exception {
        if (codContaBancaria < 1) {
            throw new Exception("Cobrancas: busca por cobranças com código de conta bancária inválido");
        }
        return getCobrancas(codContaBancaria).size();
    }


    public ArrayList<Cobranca> getCobrancas(int codContaBancaria) throws Exception {
        if(codContaBancaria < 1){
			throw new Exception("Cobrancas: busca por cobranças pagas com código de conta bancária inválido");
		}
        ArrayList<Cobranca> registros = new ArrayList<>();
        streamIn = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            boolean foiPaga = Boolean.parseBoolean(valores[3]);
            int codContaBancariaAtual = Integer.parseInt(valores[1]);
            if(codContaBancariaAtual == codContaBancaria){
                registros.add(
                    new Cobranca(Integer.parseInt(valores[0]),
                    codContaBancaria,
                    Integer.parseInt(valores[2]),
                    foiPaga, 
                    converterData(valores[4]),
                    new BigDecimal(Float.parseFloat(valores[5]))
                ));
            }
        }
        streamIn.close();
		return registros;
    }

    public ArrayList<Cobranca> getCobrancasPagas(int codContaBancaria)throws Exception{
		if(codContaBancaria < 1){
			throw new Exception("Cobrancas: busca por cobranças pagas com código de conta bancária inválido");
		}
        ArrayList<Cobranca> registros = new ArrayList<>();
        streamIn = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            boolean foiPaga = Boolean.parseBoolean(valores[3]);
            int codContaBancariaAtual = Integer.parseInt(valores[1]);
            if(foiPaga && codContaBancariaAtual == codContaBancaria){
                registros.add(
                    new Cobranca(Integer.parseInt(valores[0]),
                    codContaBancaria,
                    Integer.parseInt(valores[2]),
                    foiPaga, 
                    converterData(valores[4]),
                    new BigDecimal(Float.parseFloat(valores[5]))
                ));
            }
        }
        streamIn.close();
		return registros;
	}
    //método que converte data dd/mm/YYYY
    public static Date converterData(String dataString){
            //localDate date;
            //String dataSplitted[] = dataString.split("/");
            //birth = LocalDate.of(Integer.valueOf(dataSplitted[2]), Integer.valueOf(dataSplitted[1]), Integer.valueOf(dataSplitted[0]));
            //String strLocalDate2 = localDate.format();
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
            try{
                    Date data = df.parse(dataString);
                    return data;
            }
            catch(Exception e){
                    return null;
            }
    }
    public void pagar(int codCobranca) throws Exception {
        if (codCobranca < 1) {
            throw new Exception("Cobrancas: pagamento de cobrança com código inválido");
        }


    }
}
