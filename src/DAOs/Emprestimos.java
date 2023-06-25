package DAOs;

import java.math.BigDecimal;

public class Emprestimos  extends Dao {
	/**
     * Construtor de objeto DAO
     * @throws Exception  por conta do construtor da superclasse lan�ar exce��o
     */
    public Emprestimos() throws Exception {
        super();
    }
    
    private int codContaBancaria;
	private int mesesParaPagar;
	private BigDecimal valorParaQuitar;
	private BigDecimal valorOriginal;

    public int inserir(int codContaBancaria, int mesesParaPagar, BigDecimal valorOriginal) throws Exception {
        if (codContaBancaria < 1) {
            throw new Exception("Emprestimos: inserção com código de conta bancária inválido");
        }
        
        if (mesesParaPagar < 1) {
            throw new Exception("Emprestimos: inserção com número de meses para pagar inválido");
        }
        
        if (valorOriginal == null || valorOriginal.equals(BigDecimal.ZERO)) {
            throw new Exception("Emprestimos: inserção com valor original inválido");
        }
        
        String sql = "INSERT INTO Emprestimo VALUES (?, ?, ?, ?, ?)";
        
        this.bd.prepareStatement(sql);
        
        this.bd.setInt       (1, codContaBancaria);
        this.bd.setInt       (2, codContaBancaria); //Inicialmente, a conta que pagará o empréstimo é a mesma conta na qual ele será depositado
        this.bd.setInt       (3, mesesParaPagar);
        //this.bd.setBigDecimal(4, valorOriginal);  Calcular o valor para quitar
        this.bd.setBigDecimal(5, valorOriginal);
        
        int resultado = this.bd.executeUpdate();
        
        this.bd.commit();
        
        return resultado;
    }
}
