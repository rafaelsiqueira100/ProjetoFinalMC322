package entidades;
import java.sql.Date;
import java.math.BigDecimal;
/**
*
* @author Rafael Andre Siqueira
*  Classe DBO que representa a entidade de Boleto
*/
public class Boleto{
private int codCobranca;
private int codBoleto;
private String operacao;
private int codAutenticacao;
private Date dataDeGeracao;
private BigDecimal valor;
/**
 * Construtor do objeto DBO
 * @param codCobranca c�digo da cobran�a
 * @param codBoleto c�digo do boleto
 * @param codAutenticacao c�digo da autentica��o
 * @param valor valor do boleto
 * @param operacao operacao do boleto
 * @param dataDeGeracao data de gera��o do boleto
 * @throws Exception caso algum par�metro seja inv�lido
 */
public Boleto(int codCobranca , int codBoleto , int codAutenticacao , BigDecimal valor , String operacao, Date dataDeGeracao )throws Exception {

 if(codCobranca <1){
	 throw new Exception("Boleto: inicializa��o com c�digo da cobran�a inv�lido.");
 }
 if(codBoleto <1){
	 throw new Exception("Boleto: inicializa��o com c�digo do boleto inv�lido.");}
 if(codAutenticacao <1){
	 throw new Exception("Boleto: inicializa��o com c�digo da autentica��o inv�lido.");}

BigDecimal zero = new BigDecimal(0.0);
if(valor == null || valor.compareTo(zero)<=0){
	throw new Exception("Boleto: inicializa��o com valor do boleto inv�lido.");
}
if(dataDeGeracao == null){
	throw new Exception("Boleto: inicializa��o com data de gera��o inv�lida.");

}
if(operacao == null || operacao.equals("")){
	throw new Exception("Boleto: inicializa��o com opera��o inv�lida.");

}

    this.codCobranca   = codCobranca;
    this.codBoleto        = codBoleto;
    this.codAutenticacao= codAutenticacao;
    this.valor  = valor ;
    this.dataDeGeracao =dataDeGeracao;
    this.operacao = operacao;
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

        Boleto aComparar = (Boleto) obj;

        if (this.codBoleto != aComparar.getCodBoleto()) {
            return false;
        }

        if ( this.codCobranca!=(aComparar.getCodCobranca())) {
            return false;
        }

        if ( this.codAutenticacao!=((aComparar.getCodAutenticacao()))) {
            return false;
        }
        if (! this.operacao.equals((aComparar.getOperacao()))) {
            return false;
        }
        if (! this.valor.equals((aComparar.getValor()))) {
            return false;
        }
        if (! this.dataDeGeracao.equals((aComparar.getDataDeGeracao()))) {
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

        hashCode = PRIMO * hashCode + this.operacao.hashCode();
          hashCode = PRIMO * hashCode + this.valor.hashCode();
           hashCode = PRIMO * hashCode + this.dataDeGeracao.hashCode();

           return hashCode;
       }
       /**
        * M�todo tradicional toString
        * @return o objeto na forma de String
        */
           public  String toString() {
               return "Boleto{" + "codBoleto=" + codBoleto + ", codCobranca=" + codCobranca + ", codAutenticacao=" + codAutenticacao +", valor=" +valor + ", dataDeGeracao=" + dataDeGeracao+", operacao=" + operacao+'}';
           }

	 /*
 N�o tem construtor de c�pia, pois n�o tem setters

    public Boleto(Boleto aCopiar) throws Exception */
    /*
	N�o tem clone, pois n�o tem setters
    public Object clone() */
    /**
     * Getter do c�digo do boleto
     * @return o c�digo do boleto
     */
    public  int getCodBoleto() {
        return this.codBoleto;
    }
    /**
     * Getter do c�digo cobran�a
     * @return o c�digo cobran�a
     */
    public  int getCodCobranca() {
        return this.codCobranca;
    }
    /**
     * Getter do c�digo de autentica��o
     * @return o c�digo de autentica��o
     */
    public  int getCodAutenticacao() {

        return this.codAutenticacao;
    } /**
     * Getter do valor do boleto
     * @return o valor do boleto
     */
    public  BigDecimal getValor() {
        return this.valor;
    } /**
     * Getter da data de gera��o do boleto
     * @return a data de gera��o do boleto
     */
    public  Date getDataDeGeracao() {
        return this.dataDeGeracao;
    }/**
     * Getter do atributo de operacao
     * @return o tipo de opera��o que gerou boleto
     */
    public  String getOperacao() {
        return this.operacao;
    }
}
