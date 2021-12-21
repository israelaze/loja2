package br.com.loja.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPostDTO {

	private String marca;
	private String nome;
	private String codigo;
	private String categoria;
	private Double preco;
	private String dataCompra;
	private String dataFabricacao;
	private String dataVencimento;

}
