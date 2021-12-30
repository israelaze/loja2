package br.com.loja.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPutDTO {

	private String marca;
	private String nome;
	private String categoria;
	private Double preco;

}
