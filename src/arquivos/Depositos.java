package arquivos;

import entidades.Deposito;
import entidades.ContaBancaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;

public class Depositos {

	private BufferedReader streamIn;
    private BufferedWriter streamOut;
    private static final String nomeArquivo = "depositos.csv";
    public Depositos() throws Exception {
        super();
    }
	public int getProximoCodigo() throws Exception{
        Deposito[] depositos = getDepositos();
        int codigoMaximo = -1;
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

	        /*int quantosDepositos = -1;

	        String sql = "SELECT COUNT(codDeposito) FROM Deposito WHERE codContaBancaria = ?;";

	        this.bd.prepareStatement(sql);

	        this.bd.setInt(1, codContaBancaria);

	        ResultSet resultadoQuantosDepositos = this.bd.executeQuery();

	        if (resultadoQuantosDepositos.next()) {
	            quantosDepositos = resultadoQuantosDepositos.getInt(1);
	        }

	        return quantosDepositos;*/

    }

	public Deposito[] getDepositos() throws Exception {

	        /*Deposito[] depositos = new Deposito[this.quantosDepositos(codContaBancaria)];

	        String sql = "SELECT * FROM Deposito WHERE codContaBancaria = ?;";

	        this.bd.prepareStatement(sql);

	        this.bd.setInt(1, codContaBancaria);

	        ResultSet resultadosDepositos = this.bd.executeQuery();

	        int indiceDeInclusao = 0;
	        while (resultadosDepositos.next()) {
	            depositos[indiceDeInclusao] = new Deposito(resultadosDepositos.getInt(1), resultadosDepositos.getInt(2), resultadosDepositos.getBigDecimal(3));

	            indiceDeInclusao++;
	        }

	        return depositos;*/

    }

    public Deposito[] getDepositos(int codContaBancaria) throws Exception {
	        if (codContaBancaria < 1) {
	            throw new Exception("Depositos: busca por depósitos com código de conta bancária inválido");
	        }

	        Deposito[] depositos = new Deposito[this.quantosDepositos(codContaBancaria)];

	        String sql = "SELECT * FROM Deposito WHERE codContaBancaria = ?;";

	        this.bd.prepareStatement(sql);

	        this.bd.setInt(1, codContaBancaria);

	        ResultSet resultadosDepositos = this.bd.executeQuery();

	        int indiceDeInclusao = 0;
	        while (resultadosDepositos.next()) {
	            depositos[indiceDeInclusao] = new Deposito(resultadosDepositos.getInt(1), resultadosDepositos.getInt(2), resultadosDepositos.getBigDecimal(3));

	            indiceDeInclusao++;
	        }

	        return depositos;
    }
}
