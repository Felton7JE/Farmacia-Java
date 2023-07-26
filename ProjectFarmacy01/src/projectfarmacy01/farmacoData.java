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
public class farmacoData {


        private Integer farmaco_id;
        private String farmaco_nome; 
        private String farmaco_brand; 
        private String farmaco_classificacao; 
        private String farmaco_status;
        private Double farmaco_preco;
        private Date farmaco_validade; 
        private String farmaco_imagem;

    public Integer getFarmaco_id() {
        return farmaco_id;
    }

    public Double getFarmaco_preco() {
        return farmaco_preco;
    }

    public String getFarmaco_nome() {
        return farmaco_nome;
    }

    public String getFarmaco_status() {
        return farmaco_status;
    }

    public String getFarmaco_brand() {
        return farmaco_brand;
    }

    public String getFarmaco_classificacao() {
        return farmaco_classificacao;
    }

    public Date getFarmaco_validade() {
        return farmaco_validade;
    }
    public String getFarmaco_imagem() {
        return farmaco_imagem;
    }

    
    public farmacoData(Integer farmaco_id, String farmaco_nome, String farmaco_brand, String farmaco_classificacao, String farmaco_status, Double farmaco_preco, Date farmaco_validade, String farmaco_imagem) {
        this.farmaco_id = farmaco_id;
        this.farmaco_nome = farmaco_nome;
        this.farmaco_brand = farmaco_brand;
        this.farmaco_classificacao = farmaco_classificacao;
        this.farmaco_status = farmaco_status;
        this.farmaco_preco = farmaco_preco;
        this.farmaco_validade = farmaco_validade;
        this.farmaco_imagem = farmaco_imagem;
    }

    
    
    
}
