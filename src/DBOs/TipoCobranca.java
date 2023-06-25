/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOs;

/**
 *
 * @author u16189
 */
public class TipoCobranca {
    private int    codTipoCobranca;
    private String nome;
    
    public TipoCobranca(int codTipoCobranca, String nome) throws Exception {
        if (codTipoCobranca < 1) {
            throw new Exception("TipoCobranca: inicialização com código de tipo de cobranca inválido");
        }
        
        if (nome == null || nome.equals("")) {
            throw new Exception("TipoCobranca: inicialização com nome inválido");
        }
        
        this.codTipoCobranca = codTipoCobranca;
        this.nome            = nome;
    }
    
    public int getCodTipoCobranca() {
        return this.codTipoCobranca;
    }
    
    public String getNome() {
        return this.nome;
    }
}
