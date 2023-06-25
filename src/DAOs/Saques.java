package DAOs;

import DBOs.Saque;
import java.math.BigDecimal;
import java.sql.ResultSet;

public class Saques extends Dao {

	/**
     * Construtor de objeto DAO
     * @throws Exception  por conta do construtor da superclasse lan�ar exce��o
     */
    public Saques() throws Exception {
        super();
    }

    public int inserir(int codContaBancaria, BigDecimal valor) throws Exception {
        if (codContaBancaria < 0) {
            throw new Exception("Saques: inclusão de saque com código de conta bancária inválido");
        }

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Saques: inclusão de saque com valor inválido");
        }

        String sql = "INSERT INTO Saque VALUES (?, ?)";

        this.bd.prepareStatement(sql);

        this.bd.setInt       (1, codContaBancaria);
        this.bd.setBigDecimal(2, valor);

        int resultado = this.bd.executeUpdate();

        this.bd.commit();

        return resultado;
    }

    public int quantosSaques(int codContaBancaria) throws Exception {
		        if (codContaBancaria < 1) {
		            throw new Exception("Saques: busca por depósitos com código de conta bancária inválido");
		        }

		        int quantosSaques = -1;

		        String sql = "SELECT COUNT(codSaque) FROM Saque WHERE codContaBancaria = ?";

		        this.bd.prepareStatement(sql);

		        this.bd.setInt(1, codContaBancaria);

		        ResultSet resultadoQuantosSaques = this.bd.executeQuery();

		        if (resultadoQuantosSaques.next()) {
		            quantosSaques = resultadoQuantosSaques.getInt(1);
		        }

		        return quantosSaques;
	    }

	    public Saque[] getSaques(int codContaBancaria) throws Exception {
		        if (codContaBancaria < 1) {
		            throw new Exception("Saques: busca por depósitos com código de conta bancária inválido");
		        }

		        Saque[] saques = new Saque[this.quantosSaques(codContaBancaria)];

		        String sql = "SELECT * FROM Saque WHERE codContaBancaria = ?";

		        this.bd.prepareStatement(sql);

		        this.bd.setInt(1, codContaBancaria);

		        ResultSet resultadosSaques = this.bd.executeQuery();

		        int indiceDeInclusao = 0;
		        while (resultadosSaques.next()) {
		            saques[indiceDeInclusao] = new Saque(resultadosSaques.getInt(1), resultadosSaques.getInt(2), resultadosSaques.getBigDecimal(3));

		            indiceDeInclusao++;
		        }

		        return saques;
    }
}
