
package arquivos;

import java.sql.ResultSet;
import main.MeuPreparedStatement;



public class Dao {
    protected MeuPreparedStatement bd;
    
    /**
     * inicializa o objeto meuPreparedStatement que ser� utilizado em todos os acessos ao BD
     * @throws Exception  devido ao m�todo meu prepared statement
     */
    public Dao() throws Exception {
        this.bd = new MeuPreparedStatement ("org.postgresql.Driver",
        "jdbc:postgresql:User=sammy;Password=668791470;Database=postgres;Server=127.0.0.1;Port=5432;",
        "sammy",
        "668791470");            
    }
    /**
     * Pega o atributo MeuPreparedStatement do objeto DAO
     * @return o atributo MeuPreparedStatement do objeto DAO. Visto que o atributo � de n�vel
     * private, este m�todo � o �nico acesso que as classes filhas ter�o ao objeto meuPreparedStatement
     */
    public MeuPreparedStatement getBd() {
        return this.bd;
    }

    public int getProximoCodigo(String tabela, String nomeColunaCodigo) throws Exception{
        
        String sql = "SELECT MAX("+nomeColunaCodigo+") FROM "+tabela+" ;";
        
        this.bd.prepareStatement(sql);
        
        ResultSet resultado = this.bd.executeQuery();

        if(resultado.next()){
            return resultado.getInt(1) + 1;
        }
        return -1;

    }
}

