package br.com.loja.dtos;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoGetDTO extends RepresentationModel<ProdutoGetDTO>{

	private Integer idProduto;
	private String marca;
	private String nome;
	private String codigo;
	private String categoria;
	private Double estoque;
	private Double preco;
	private Date dataFabricacao;
	private Date dataVencimento;

	
}
