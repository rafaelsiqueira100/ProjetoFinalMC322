/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 *
 * @author rafaelsiqueira
 */
public abstract class Registro {
    public abstract int getProximoCodigo() throws Exception;
    protected BufferedReader streamIn;
    protected BufferedWriter streamOut;  
    
}
