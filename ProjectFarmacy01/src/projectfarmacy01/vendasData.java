/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfarmacy01;

import java.sql.Date;

/**
 *
 * @author user
 */
public class vendasData {
    
    private Integer venda_id; 
    private String classificacao;
    private Integer farmaco_id; 
    private String brand;
    private String nome;
    private Integer quantidade; 
    private Double preco; 
    private Date data;

    public vendasData(Integer venda_id, String clssificacao, Integer farmaco_id, String brand, String nome, Integer quantidade, Double preco, Date data) {
        this.venda_id = venda_id;
        this.classificacao = clssificacao;
        this.farmaco_id = farmaco_id;
        this.brand = brand;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.data = data;
    }

    public Integer getVenda_id() {
        return venda_id;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public Integer getFarmaco_id() {
        return farmaco_id;
    }

    public String getBrand() {
        return brand;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public Date getData() {
        return data;
    }
    
    
    
    
}



