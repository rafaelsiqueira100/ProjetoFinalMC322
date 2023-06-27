package arquivos;

import DBOs.Cobranca;
import entidades.Banco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Cobrancas {
	/**
     * Construtor de objeto DAO
     * @throws Exception  por conta do construtor da superclasse lan�ar exce��o
     */
    private BufferedReader streamIn;
    private BufferedWriter streamOut;
    private static final String nomeArquivo = "cobrancas.csv";
    public Cobrancas() {    }

    public int quantasCobrancas(int codContaBancaria) throws Exception {
        if (codContaBancaria < 1) {
            throw new Exception("Cobrancas: busca por cobranças com código de conta bancária inválido");
        }
        return getCobrancas(codContaBancaria).length;
    }


    public Cobranca[] getCobrancas(int codContaBancaria) throws Exception {
        if(codContaBancaria < 1){
			throw new Exception("Cobrancas: busca por cobranças pagas com código de conta bancária inválido");
		}
        ArrayList<Cobranca> registros = new ArrayList<>();
        streamIn = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(',');
            boolean foiPaga = Boolean.parseBoolean(valores[3]);
            int codContaBancariaAtual = Integer.parseInt(valores[1]);
            if(codContaBancariaAtual == codContaBancaria){
                registros.add(
                    new Cobranca(Integer.parseInt(valores[0]),
                    codContaBancaria,
                    Integer.parseInt(valores[2]),
                    foiPaga, 
                    Date.converterData(valores[4]),
                    new BigDecimal(Float.parseFloat(valores[5]))
                ));
            }
        }
        streamIn.close();
		return registros;
    }

    public Cobranca[] getCobrancasPagas(int codContaBancaria)throws Exception{
		if(codContaBancaria < 1){
			throw new Exception("Cobrancas: busca por cobranças pagas com código de conta bancária inválido");
		}
        ArrayList<Cobranca> registros = new ArrayList<>();
        streamIn = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(',');
            boolean foiPaga = Boolean.parseBoolean(valores[3]);
            int codContaBancariaAtual = Integer.parseInt(valores[1]);
            if(foiPaga && codContaBancariaAtual == codContaBancaria){
                registros.add(
                    new Cobranca(Integer.parseInt(valores[0]),
                    codContaBancaria,
                    Integer.parseInt(valores[2]),
                    foiPaga, 
                    Date.converterData(valores[4]),
                    new BigDecimal(Float.parseFloat(valores[5]))
                ));
            }
        }
        streamIn.close();
		return registros;
	}
    public void pagar(int codCobranca) throws Exception {
        if (codCobranca < 1) {
            throw new Exception("Cobrancas: pagamento de cobrança com código inválido");
        }


    }
}
