package DAOs;

import DBOs.Cliente;
import java.sql.ResultSet;

public class Clientes extends Dao {
	/**
     * Construtor de objeto DAO
     * @throws Exception  por conta do construtor da superclasse lan�ar exce��o
     */
    public Clientes() throws Exception {
        super();
    }

    /**
     * Método de inserção na tabela "Cliente"
     * @param nomeCliente o nome do cliente a ser inserido
     * @return o código do cliente que acabou de ser inserido, -1 se houve erro
     * @throws Exception caso o nome fornecido seja nulo ou haja algum erro de BD
     */
    public int inserir(String nomeCliente) throws Exception {
        if (nomeCliente == null || nomeCliente.equals("")) {
            throw new Exception("Clientes: inserção de cliente nulo");
        }            
        int proximoCodigoCliente = getProximoCodigo("Cliente", "codCliente");
        String sql = "INSERT INTO Cliente VALUES (?,?);";
        
        this.bd.prepareStatement(sql);
        
        this.bd.setInt(1, proximoCodigoCliente);
        this.bd.setString(2, nomeCliente);
        
        this.bd.executeUpdate(); 
        this.bd.commit();
        
        String consultaAoInserido = "SELECT codCliente FROM Cliente WHERE nome = ?;";
        
        this.bd.prepareStatement(consultaAoInserido);
        
        this.bd.setString(1, nomeCliente);
        
        ResultSet resultadoCliente = this.bd.executeQuery();
        if (resultadoCliente.next())
            return resultadoCliente.getInt(1); //O código do inserido
        
        return -1; //Houve erro
    }
}
