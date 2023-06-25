package DAOs;

import DBOs.Deposito;
import java.math.BigDecimal;
import java.sql.ResultSet;

public class Depositos  extends Dao {
	/**
     * Construtor de objeto DAO
     * @throws Exception  por conta do construtor da superclasse lan�ar exce��o
     */
    public Depositos() throws Exception {
        super();
    }

    public int inserir(int codContaBancaria, BigDecimal valor) throws Exception {
        if (codContaBancaria < 0) {
            throw new Exception("Saques: inclusão de saque com código de conta bancária inválido");
        }

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Saques: inclusão de saque com valor inválido");
        }

        String sql = "INSERT INTO Deposito VALUES (?, ?)";

        this.bd.prepareStatement(sql);

        this.bd.setInt       (1, codContaBancaria);
        this.bd.setBigDecimal(2, valor);

        int resultado = this.bd.executeUpdate();

        this.bd.commit();

        return resultado;
    }

    public int quantosDepositos(int codContaBancaria) throws Exception {
	        if (codContaBancaria < 1) {
	            throw new Exception("Depositos: busca por depósitos com código de conta bancária inválido");
	        }

	        int quantosDepositos = -1;

	        String sql = "SELECT COUNT(codDeposito) FROM Deposito WHERE codContaBancaria = ?";

	        this.bd.prepareStatement(sql);

	        this.bd.setInt(1, codContaBancaria);

	        ResultSet resultadoQuantosDepositos = this.bd.executeQuery();

	        if (resultadoQuantosDepositos.next()) {
	            quantosDepositos = resultadoQuantosDepositos.getInt(1);
	        }

	        return quantosDepositos;
    }

    public Deposito[] getDepositos(int codContaBancaria) throws Exception {
	        if (codContaBancaria < 1) {
	            throw new Exception("Depositos: busca por depósitos com código de conta bancária inválido");
	        }

	        Deposito[] depositos = new Deposito[this.quantosDepositos(codContaBancaria)];

	        String sql = "SELECT * FROM Deposito WHERE codContaBancaria = ?";

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
