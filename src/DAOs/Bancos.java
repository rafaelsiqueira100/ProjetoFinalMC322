package DAOs;

import DBOs.Banco;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bancos extends Dao {
	 /**
     * Construtor de objeto DAO
     * @throws Exception  por conta do construtor da superclasse lan�ar exce��o
     */
    public Bancos() throws Exception {
        super();
    }
    
    public int quantosBancos() throws SQLException {
        String sql = "SELECT COUNT(codBanco) FROM Banco;";
        
        this.bd.prepareStatement(sql);

        ResultSet resultadoQuantosBancos = this.bd.executeQuery();
        
        int quantosBancos = 0;
        if (resultadoQuantosBancos.next())
            quantosBancos = resultadoQuantosBancos.getInt(1);
        
        return quantosBancos;
    }
    
    public Banco[] getBancos() throws SQLException, Exception {
        Banco[] bancos = new Banco[this.quantosBancos()];
        
        String sql = "SELECT * FROM Banco;";
        
        this.bd.prepareStatement(sql);
        
        ResultSet resultadoBancos = this.bd.executeQuery();
        
        int indiceDeInclusao = 0;
        while (resultadoBancos.next()) {
            Banco aIncluir = new Banco(resultadoBancos.getInt(1), resultadoBancos.getString(2), resultadoBancos.getFloat(3), resultadoBancos.getFloat(4), resultadoBancos.getBigDecimal(5), resultadoBancos.getBigDecimal(6), resultadoBancos.getInt(7));
            
            bancos[indiceDeInclusao] = aIncluir;
            
            indiceDeInclusao++;
        }
        
        return bancos;
    }
    
    public int inserir(String nome, float jurosPoupanca, float jurosEmprestimo, int mesesEmprestimo, BigDecimal emprestimoMinimo, BigDecimal emprestimoMaximo) throws Exception {
        if (nome == null || nome.equals("")) {
            throw new Exception("Bancos: inserção com nome inválido");
        }
        
        if (jurosPoupanca < 0) {
            throw new Exception("Bancos: inserção com valor de juros sobre poupança inválido");
        }
        
        if (jurosEmprestimo < 0) {
            throw new Exception("Bancos: inserção com valor de juros sobre empréstimo inválido");
        }
        
        if (mesesEmprestimo < 1) {
            throw new Exception("Bancos: inserção com valor de meses para pagamento de empréstimo inválido");
        }
        
        if (emprestimoMinimo == null || emprestimoMinimo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Bancos: inserção com valor de empréstimo mínimo inválido");
        }
        
        if (emprestimoMaximo == null || emprestimoMaximo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Bancos: inserção com valor de empréstimo máximo inválido");
        }
        int proximoCodigoBanco = getProximoCodigo("Banco", "codBanco");
        String sql = "INSERT INTO Banco VALUES (?, ?, ?, ?, ?, ?, ?);";
        
        this.bd.prepareStatement(sql);
        this.bd.setInt       (1, proximoCodigoBanco);
        this.bd.setString    (2, nome);
        this.bd.setFloat     (3, jurosPoupanca);
        this.bd.setFloat     (4, jurosEmprestimo);        
        this.bd.setBigDecimal(5, emprestimoMinimo);
        this.bd.setBigDecimal(6, emprestimoMaximo);
        this.bd.setInt       (7, mesesEmprestimo);
        
        int resultado = this.bd.executeUpdate();
        
        this.bd.commit();
            
        return resultado;
    }
}
