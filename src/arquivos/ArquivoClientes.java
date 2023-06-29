package arquivos;

import entidades.Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ArquivoClientes extends Registro{
	/**
     * Construtor de objeto DAO
     * @throws Exception  por conta do construtor da superclasse lan�ar exce��o
     */

    public ArquivoClientes() throws Exception {
        super();
    }
    private ArrayList<Cliente> getClientes() throws Exception {
        streamIn = new BufferedReader( new FileReader(NOMEARQUIVO));
        String linha;
        ArrayList<entidades.Cliente> registros = new ArrayList<>();
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            registros.add(
                new Cliente(Integer.parseInt(valores[0]),
                valores[1]
              ));
        }
        streamIn.close();
        return registros;
    }
    @Override
    public int getProximoCodigo() throws Exception{
        ArrayList<Cliente> clientes = getClientes();
        int codigoMaximo = 0;
        for(entidades.Cliente c: clientes){
            if(c.getCodCliente() > codigoMaximo)
                codigoMaximo = c.getCodCliente();
        }
        return codigoMaximo+1;
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
        try{
            int proximoCodigoCliente = getProximoCodigo();
            streamOut = new BufferedWriter( new FileWriter(NOMEARQUIVO));
            streamOut.write(
                Integer.toString(proximoCodigoCliente) +','+
                nomeCliente 
            );
            streamOut.close();
        }
        catch(Exception e){
            return -1;
        }
        return 1;
    }
    private static final String NOMEARQUIVO = new File("").getAbsolutePath().concat("/src/arquivos/clientes.csv");     
}
