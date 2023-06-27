/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.math.BigDecimal;

/**
 *
 * @author rafaelsiqueira
 */
public abstract class Conta {
    protected int codContaBancaria;
    protected int codCliente;
    protected int codBanco;
    protected String senha;
    protected BigDecimal saldo;
    protected String codAgencia;
    /**
     * Getter do c�digo da conta banc�ria
     * @return o c�digo da conta banc�ria
     */
    
    public  int getCodContaBancaria() {
        return this.codContaBancaria;
    }
    /**
     * Getter do c�digo cliente
     * @return o c�digo cliente
     */
    public  int getCodCliente() {
        return this.codCliente;
    }
    /**
     * Getter do c�digo banco
     * @return o c�digo do banco
     */
    public  int getCodBanco() {
      
        return this.codBanco;
    } /**
     * Getter da senha da conta banc�ria
     * @return a senha da conta banc�ria
     */
    public  String getSenha() {
        return this.senha;
    } /**
     * Getter do saldo da conta banc�ria
     * @return o saldo da conta banc�ria
     */
    public  BigDecimal getSaldo() {
        return this.saldo;
    }/**
     * Getter do c�digo da ag�ncia
     * @return o c�digo da ag�ncia
     */
    public  String getCodAgencia() {
        return this.codAgencia;
    }
    
}
