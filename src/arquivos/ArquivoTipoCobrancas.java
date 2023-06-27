/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import entidades.TipoCobranca;
import entidades.Cobranca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ArquivoTipoCobrancas extends Registro {
    public ArquivoTipoCobrancas() {  }
    private BufferedReader streamIn;
    private BufferedWriter streamOut;
    private static final String nomeArquivo = "/home/rafaelsiqueira/ProjetoFinalMC322/src/arquivos/tipocobrancas.csv";
    
    public int getProximoCodigo(){
        return -1;
    }
    public TipoCobranca getTipoCobranca(int codTipoCobranca) throws Exception {
        if (codTipoCobranca < 1) {
            throw new Exception("TipoCobrancas: busca por tipo de cobrança com  código inválido");
        }
        TipoCobranca registro;
        streamIn = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
            int codTipoCobrancaAtual = Integer.parseInt(valores[0]);
            if(codTipoCobranca == codTipoCobrancaAtual){
                streamIn.close();
                return new TipoCobranca(Integer.parseInt(valores[0]), valores[1]);
            }
        }
        streamIn.close();
		return null;
    }
}
