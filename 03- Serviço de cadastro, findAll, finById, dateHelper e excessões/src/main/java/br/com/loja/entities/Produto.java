package br.com.loja.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produtos")
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_produto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduto;
	
	@Column
	private String marca;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String codigo;
	
	@Column
	private String categoria;
	
	@Column
	private Double estoque;
	
	@Column
	private Double preco;
	
	@Column(nullable = false)
	private Double custo;
	
	@Column(name = "data_compra", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCompra;
	
	@Column(name = "dias_sem_venda")
	private Integer semVenda;
	
	@Column(nullable = false)
	private Double quantidade;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fabricacao", nullable = false)
	private Date dataFabricacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;

}
