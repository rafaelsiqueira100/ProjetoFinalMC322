package entidades;

import java.math.BigDecimal;

/**
*
* @author Rafel Andre Siqueira
*  Classe DBO que representa a entidade de Banco
*/
public class Banco {
    private int        codBanco;
    private String     nome;
    private float      jurosPoupanca;
    private float      jurosEmprestimo;    
    private BigDecimal emprestimoMinimo;
    private BigDecimal emprestimoMaximo;
    private int        mesesEmprestimo;
    
    /**
     * Construtor do objeto DBO
     * @param codBanco c�digo do banco
     * @param nome nome do banco
     * @param jurosPoupanca juros mensal do Banco aplicado na poupan�a
     * @param jurosEmprestimo juros mensal do Banco aplicado no empr�stimo
     * @param mesesEmprestimo ínterim máximo, em meses, no qual um cliente pode quitar uma dívida
     * @param emprestimoMinimo valor mínimo do qual um cliene pode selicitar um empréstimo
     * @param emprestimoMaximo valor máximo do qual um cliene pode selicitar um empréstimo
     * @throws Exception caso algum par�metro seja inv�lido
     */
    public Banco(int codBanco, String nome, float jurosPoupanca, float jurosEmprestimo, BigDecimal emprestimoMinimo, BigDecimal emprestimoMaximo, int mesesEmprestimo) throws Exception {
        if (nome == null || nome.equals("")) {
            throw new Exception ("Banco: inicializa��o com nome inv�lido.");
        }
        
        if(codBanco < 1){
            throw new Exception("Banco: inicializa��o com c�digo banco inv�lido");
        }
        
        if(jurosPoupanca<=0 || jurosEmprestimo <= 0){
            throw new Exception("Banco: inicializa��o com juros inv�lido");
        }
        
        if (mesesEmprestimo < 1) {
            throw new Exception("Banco; inicialização com quantidade inválida para pagamento de empréstimo");
        }
        
        if (emprestimoMinimo == null) {
            throw new Exception("Banco: inicialização com valor mínimo de empréstimo nulo.");
        }
        
        if (emprestimoMaximo == null) {
            throw new Exception("Banco: inicialização com valor máximo de empréstimo nulo.");
        }

        this.codBanco         = codBanco;
        this.nome             = nome;
        this.jurosPoupanca    = jurosPoupanca;
        this.jurosEmprestimo  = jurosEmprestimo;
        this.emprestimoMinimo = emprestimoMinimo;
        this.emprestimoMaximo = emprestimoMaximo;
        this.mesesEmprestimo  = mesesEmprestimo;
    }
    
    /**
     * M�todo tradicional equals
     * @param obj objeto que ser� comparado
     * @return true, se os conte�dos dos dois objetos s�o compat�veis e exatamente iguais
     */
    public  boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        
        if (obj == this) {
            return true;
        }
        
        Banco aComparar = (Banco) obj;
        
        if (this.codBanco != aComparar.getCodBanco()) {
            return false;
        }
        
        if (! this.nome.equals(aComparar.getNome())) {
            return false;
        }
        
        if ( this.jurosPoupanca != aComparar.getJurosPoupanca()) {
            return false;
        }
        
        if ( this.jurosEmprestimo != aComparar.getJurosEmprestimo()) {
            return false;
        }
        
        if (this.mesesEmprestimo != aComparar.mesesEmprestimo) {
            return false;
        }
        
        if (! this.emprestimoMinimo.equals(aComparar.emprestimoMinimo)) {
            return false;
        }
        
        if (! this.emprestimoMaximo.equals(aComparar.emprestimoMaximo)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * M�todo tradicional hashCode
     * @return o c�digo de hash do objeto
     */
    public  int hashCode() {
        final int INICIAL = 2;
        final int PRIMO   = 13;

        int hashCode = INICIAL;


        hashCode = PRIMO * hashCode + this.nome.hashCode();
        hashCode = PRIMO * hashCode + this.emprestimoMinimo.hashCode();
        hashCode = PRIMO * hashCode + this.emprestimoMaximo.hashCode();

        return hashCode;
    }
       
    /**
     * M�todo tradicional toString
     * @return o objeto na forma de String
     */
    public  String toString() {
        return "Banco{" + "codBanco=" + codBanco + ", nome=" + nome + ", jurosEmprestimo=" + jurosEmprestimo +", jurosPoupanca=" +  jurosPoupanca + ", mesesEmprestimo=" + mesesEmprestimo + ", emprestimoMinimo=" + emprestimoMinimo + ", emprestimoMaximo=" + emprestimoMaximo + '}';
    }
          
    /*
    N�o tem construtor de c�pia, pois n�o tem setters

       public Produto (Produto aCopiar) throws Exception */
       /*
         N�o tem clone, pois n�o tem setters
       public Object clone() */
       /**
        * Getter do c�digo banco
        * @return o c�digo banco
        */
    public  int getCodBanco() {
        return this.codBanco;
    }
    /**
     * Getter do nome do banco
     * @return o nome do banco
     */
    public  String getNome() {
        return this.nome;
    }
    /**
     * Getter dos juros para empr�stimo do banco
     *  @returnos juros para empr�stimo do banco */
    public  float getJurosEmprestimo() {

        return this.jurosEmprestimo;
    }
    /**
     * Getter dos juros para poupan�a do banco
     *  @returnos juros para poupan�a do banco */
    public  float getJurosPoupanca() {
        return this.jurosPoupanca;
    }     
    /**
     * Getter do máximo de meses no qual um cliente pode quitar um empréstimo
     * @return o máximo de meses no qual um cliente pode quitar um empréstimo
     */
    public int mesesEmprestimo() {
        return this.mesesEmprestimo;
    }
    /**
     * Getter do menor valor de empréstimo que um cliente pode solicitar
     * @return o menor valor de empréstimo que um cliente pode solicitar
     */
    public BigDecimal getEmprestimoMinimo() {
        return this.emprestimoMinimo;
    }
    /**
     * Getter do maior valor de empréstimo que um cliente pode solicitar
     * @return o maior valor de empréstimo que um cliente pode solicitar
     */
    public BigDecimal getEmprestimoMaximo() {
        return this.emprestimoMaximo;
    }
}
