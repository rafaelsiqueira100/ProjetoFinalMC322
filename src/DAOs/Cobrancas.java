package DAOs;

import DBOs.Cobranca;
import java.math.BigDecimal;
import java.sql.ResultSet;

public class Cobrancas extends Dao {
	/**
     * Construtor de objeto DAO
     * @throws Exception  por conta do construtor da superclasse lan�ar exce��o
     */
    public Cobrancas() throws Exception {
        super();
    }

    public int quantasCobrancas(int codContaBancaria) throws Exception {
        if (codContaBancaria < 1) {
            throw new Exception("Cobrancas: busca por cobranças com código de conta bancária inválido");
        }

        int quantasCobrancas = -1;

        String sql = "SELECT COUNT(codCobranca) FROM Cobranca WHERE codContaBancaria = ?;";

        this.bd.prepareStatement(sql);

        this.bd.setInt(1, codContaBancaria);

        ResultSet resultadoQuantasCobrancas = this.bd.executeQuery();

        if (resultadoQuantasCobrancas.next()) {
            quantasCobrancas = resultadoQuantasCobrancas.getInt(1);
        }

        return quantasCobrancas;
    }

    public Cobranca[] getCobrancas(int codContaBancaria) throws Exception {
        if (codContaBancaria < 1) {
            throw new Exception("Cobrancas: busca por cobranças com código de conta bancária inválido");
        }

        Cobranca[] cobrancas = new Cobranca[this.quantasCobrancas(codContaBancaria)];

        String sql = "SELECT * FROM Cobranca WHERE codContaBancaria = ?;";

        this.bd.prepareStatement(sql);

        this.bd.setInt(1, codContaBancaria);

        ResultSet resultadoCobrancas = this.bd.executeQuery();

        int indiceDeInclusao = 0;
        while (resultadoCobrancas.next()) {
            cobrancas[indiceDeInclusao] = new Cobranca(resultadoCobrancas.getInt(1), resultadoCobrancas.getInt(2), resultadoCobrancas.getInt(3), resultadoCobrancas.getBoolean(4), resultadoCobrancas.getDate(5), resultadoCobrancas.getBigDecimal(6));

            indiceDeInclusao++;
        }

        return cobrancas;
    }

    public Cobranca[] getCobrancasPagas(int codContaBancaria)throws Exception{
		if(codContaBancaria < 1){
			throw new Exception("Cobrancas: busca por cobranças pagas com código de conta bancária inválido");
		}
		Cobranca[] cobrancasPagas = new Cobranca[this.quantasCobrancas(codContaBancaria)];

		String sql = "SELECT * FROM Cobranca WHERE codContaBancaria = ? and foiPaga = ?;";

		this.bd.prepareStatement(sql);

		this.bd.setInt(1, codContaBancaria);

		this.bd.setBoolean(2, true);

		ResultSet resultadoCobrancasPagas = this.bd.executeQuery();

		int indiceDeInclusao = 0;
		while(resultadoCobrancasPagas.next()){
			cobrancasPagas[indiceDeInclusao] = new Cobranca(resultadoCobrancasPagas.getInt(1), resultadoCobrancasPagas.getInt(2), resultadoCobrancasPagas.getInt(3), resultadoCobrancasPagas.getBoolean(4), resultadoCobrancasPagas.getDate(5), resultadoCobrancasPagas.getBigDecimal(6));
		indiceDeInclusao++;
		}
		return cobrancasPagas;
	}
    public void pagar(int codCobranca) throws Exception {
        if (codCobranca < 1) {
            throw new Exception("Cobrancas: pagamento de cobrança com código inválido");
        }


    }
}
