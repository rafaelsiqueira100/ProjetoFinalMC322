
package DAOs;

import main.MeuPreparedStatement;


/**
 * @author Rafael Andre Siqueira
 *  Superclasse de todas as classes DAO do projeto 
 */
public class Dao {
    protected MeuPreparedStatement bd;
    
    /**
     * inicializa o objeto meuPreparedStatement que ser� utilizado em todos os acessos ao BD
     * @throws Exception  devido ao m�todo meu prepared statement
     */
    public Dao() throws Exception {
        this.bd = new MeuPreparedStatement ("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://Regulus:1433;databasename=PRII16191 ", "PRII16191", "PRII16191");            
    }
    /**
     * Pega o atributo MeuPreparedStatement do objeto DAO
     * @return o atributo MeuPreparedStatement do objeto DAO. Visto que o atributo � de n�vel
     * private, este m�todo � o �nico acesso que as classes filhas ter�o ao objeto meuPreparedStatement
     */
    public MeuPreparedStatement getBd() {
        return this.bd;
    }
}

