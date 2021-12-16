package br.com.loja.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProdutoPOSTdto {

	private String marca;
	private String nome;
	private String codigo;
	private String categoria;
	private Double preco;
	private Double custo;
	private String dataCompra;
	private Double quantidade;
	private String dataFabricacao;
	private String dataVencimento;
	
	/*public ProdutoPOSTdto(Produto produto) {
		this.marca = produto.getMarca();
		this.nome = produto.getNome();
		this.codigo = produto.getCodigo();
		this.categoria = produto.getCategoria();
		this.preco = produto.getPreco();
		this.custo = produto.getCusto();
		this.dataCompra = produto.getDataCompra();
		this.quantidade = produto.getQuantidade();
		this.dataFabricacao = produto.getDataFabricacao();
		this.dataVencimento = produto.getDataVencimento();
		
	}
*/
}
