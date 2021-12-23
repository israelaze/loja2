package br.com.loja.dtos;

import java.util.Date;

import br.com.loja.entities.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoGetDTO {

	private Integer idProduto;
	private String marca;
	private String nome;
	private String codigo;
	private String categoria;
	private Double estoque;
	private Double preco;
	private Double custo;
	private Date dataCompra;
	private Integer semVenda;
	private Double quantidade;
	private Date dataFabricacao;
	private Date dataVencimento;

	//Construtor para transferir os dados do Produto para o GetDto
	public ProdutoGetDTO(Produto produto) {
		this.idProduto = produto.getIdProduto();
		this.marca = produto.getMarca();
		this.nome = produto.getNome();
		this.codigo = produto.getCodigo();
		this.categoria = produto.getCategoria();
		this.estoque = produto.getEstoque();
		this.preco = produto.getPreco();
		this.custo = produto.getCusto();
		this.dataCompra = produto.getDataCompra();
		this.semVenda = produto.getSemVenda();
		this.quantidade = produto.getQuantidade();
		this.dataFabricacao = produto.getDataFabricacao();
		this.dataVencimento = produto.getDataVencimento();
		
	}
	
}
