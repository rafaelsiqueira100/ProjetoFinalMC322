package arquivos;

import entidades.Deposito;
import entidades.Cliente;
import entidades.ContaBancaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Depositos extends Registro{

	private BufferedReader streamIn;
    private BufferedWriter streamOut;
    private static final String nomeArquivo = "/home/rafaelsiqueira/ProjetoFinalMC322/src/arquivos/depositos.csv";
    public Depositos() throws Exception {
        super();
    }
	public int getProximoCodigo() throws Exception{
        ArrayList<Deposito> depositos = getDepositos();
        int codigoMaximo = 0;
        for(Deposito d: depositos){
            if(d.getCodDeposito() > codigoMaximo)
                codigoMaximo = d.getCodContaBancaria();
        }
        return codigoMaximo + 1;
    }

    public int inserir(int codContaBancaria, BigDecimal valor) throws Exception {
        if (codContaBancaria < 0) {
            throw new Exception("Saques: inclusão de saque com código de conta bancária inválido");
        }

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Saques: inclusão de saque com valor inválido");
        }

		try{
            int proximoCodigoDeposito = getProximoCodigo();
            streamOut = new BufferedWriter(new FileWriter(nomeArquivo));
            streamOut.write(
                Integer.toString(proximoCodigoDeposito) +","+
                Integer.toString(codContaBancaria) +","+
                valor.toString()
            );
            streamOut.close();
        }
        catch(Exception e){
            return -1;
        }
        return 1;
    }

    public int quantosDepositos(int codContaBancaria) throws Exception {
	        if (codContaBancaria < 1) {
	            throw new Exception("Depositos: busca por depósitos com código de conta bancária inválido");
	        }
	        return this.getDepositos(codContaBancaria).size();
    }

	public ArrayList<Deposito> getDepositos() throws Exception {
		streamIn = new BufferedReader( new FileReader(nomeArquivo));
		String linha;
		ArrayList<Deposito> registros = new ArrayList<Deposito>();
		while((linha = streamIn.readLine()) != null){
			String[] valores = linha.split(",");
			registros.add(
				new Deposito(Integer.parseInt(valores[0]),
				Integer.parseInt(valores[1]),
				new BigDecimal(Float.parseFloat(valores[2]))
			));
		}
		streamIn.close();
		return registros;

    }

    public ArrayList<Deposito> getDepositos(int codContaBancaria) throws Exception {
	        if (codContaBancaria < 1) {
	            throw new Exception("Depositos: busca por depósitos com código de conta bancária inválido");
	        }
			streamIn = new BufferedReader( new FileReader(nomeArquivo));
			String linha;
			ArrayList<Deposito> registros = new ArrayList<Deposito>();
			while((linha = streamIn.readLine()) != null){
				String[] valores = linha.split(",");
				int codContaBancariaAtual = Integer.parseInt(valores[1]);
				if(codContaBancaria == codContaBancariaAtual){
					registros.add(
						new Deposito(Integer.parseInt(valores[0]),
						Integer.parseInt(valores[1]),
						new BigDecimal(Float.parseFloat(valores[2]))
					));
				}
			}
			streamIn.close();
			return registros;
    }
}
