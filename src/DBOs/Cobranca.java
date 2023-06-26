package DBOs;
import java.sql.Date;
import java.math.BigDecimal;
/**
*
* @author Rafael Andre Siqueira
*  Classe DBO que representa a entidade de Cobranca
*/
public class Cobranca implements Operacao{
    private int codCobranca;
    private int codTipoCobranca;
    private boolean foiPaga;
    private Date dataDeVencimento;
/**
 * Construtor do objeto DBO
 * @param codCobranca c�digo da cobran�a
 * @param codContaBancaria c�digo da conta banc�ria
 * @param codTipoCobranca c�digo do tipo da cobran�a
 * @param valor valor da cobran�a
 * @param foiPaga inicialmente falso, indica se a cobran�a foi paga
 * @param dataDeVencimento data de vencimento da cobran�a
 * @throws Exception caso algum par�metro seja inv�lido
 */
public Cobranca(int codCobranca , int codContaBancaria , int codTipoCobranca , boolean foiPaga, Date dataDeVencimento, BigDecimal valor )throws Exception {
    
 if(codCobranca <1){
	 throw new Exception("Cobranca: inicializa��o com c�digo da cobran�a inv�lido.");
 }
 if(codContaBancaria <1){
	 throw new Exception("Cobranca: inicializa��o com c�digo da conta banc�ria inv�lido.");}
 if(codTipoCobranca <1){
	 throw new Exception("Cobranca: inicializa��o com tipo da cobran�a inv�lido.");}

BigDecimal zero = new BigDecimal(0.0);
if(valor == null || valor.compareTo(zero)<=0){
	throw new Exception("Cobranca: inicializa��o com valor da cobran�a inv�lido.");
}
if(dataDeVencimento == null){
	throw new Exception("Cobranca: inicializa��o com data de vencimento inv�lida.");
	
}
    
    this.codCobranca   = codCobranca;
    this.codContaBancaria        = codContaBancaria;
    this.codTipoCobranca= codTipoCobranca;
    this.valor  = valor ;
    this.dataDeVencimento =dataDeVencimento;
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
        
        Cobranca aComparar = (Cobranca) obj;
        
        if (this.codContaBancaria != aComparar.getCodContaBancaria()) {
            return false;
        }
        
        if ( this.codCobranca!=(aComparar.getCodCobranca())) {
            return false;
        }
        
        if ( this.codTipoCobranca!=((aComparar.getCodTipoCobranca()))) {
            return false;
        }
        if ( this.foiPaga != aComparar.isPaga()) {
            return false;
        }
        if (! this.valor.equals((aComparar.getValor()))) {
            return false;
        }
        if (! this.dataDeVencimento.equals((aComparar.getDataDeVencimento()))) {
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
           
           Integer envoltorioCodContaBancaria    = new Integer (this.codContaBancaria);
           Integer envoltorioCodCobranca    = new Integer (this.codCobranca );
           Integer envoltorioCodTipoCobranca  = new Integer (this.codTipoCobranca);
           Boolean envoltorioFoiPaga = new Boolean(this.foiPaga);
           
          hashCode = PRIMO * hashCode + envoltorioCodContaBancaria.hashCode();

        hashCode = PRIMO * hashCode + envoltorioCodCobranca.hashCode();
        
        hashCode = PRIMO * hashCode + envoltorioCodTipoCobranca.hashCode();
        hashCode = PRIMO * hashCode + envoltorioFoiPaga.hashCode();
          hashCode = PRIMO * hashCode + this.valor.hashCode();
           hashCode = PRIMO * hashCode + this.dataDeVencimento.hashCode();
           
           return hashCode;
       }
       /**
        * M�todo tradicional toString
        * @return o objeto na forma de String
        */
           public  String toString() {
               return "Cobranca{" + "codContaBancaria=" + codContaBancaria + ", codCobranca=" + codCobranca + ", codTipoCobranca=" + codTipoCobranca +", valor=" +valor + ", dataDeVencimento=" + dataDeVencimento+'}';
           }
          
	 /*
 N�o tem construtor de c�pia, pois n�o tem setters
 
    public Cobranca(Cobranca aCopiar) throws Exception */
    /*
	N�o tem clone, pois n�o tem setters
    public Object clone() */
    /**
     * Getter do c�digo da conta banc�ria
     * @return o c�digo da conta banc�ria
     */
    public  int getCodContaBancaria() {
        return this.codContaBancaria;
    }
    /**
     * Getter do c�digo cobran�a
     * @return o c�digo cobran�a
     */
    public  int getCodCobranca() {
        return this.codCobranca;
    }
    /**
     * Getter do c�digo do tipo da cobran�a
     * @return o c�digo do tipo da cobran�a
     */
    public  int getCodTipoCobranca() {
      
        return this.codTipoCobranca;
    } /**
     * Getter do valor da cobran�a
     * @return o valor da cobran�a
     */
    public  BigDecimal getValor() {
        return this.valor;
    } /**
     * Getter da data de vencimento da cobran�a
     * @return a data de vencimento da cobran�a
     */
    public  Date getDataDeVencimento() {
        return this.dataDeVencimento;
    }/**
     * Getter do atributo de pagamento
     * @return true, se a cobran�a foi paga
     */
    public  boolean isPaga() {
        return this.foiPaga;
    }
}
