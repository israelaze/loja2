package br.com.loja.dtos;

import br.com.loja.entities.Produto;
import br.com.loja.helpers.DateHelper;

public class DtoToProduto {

	public static Produto postDtoToProduto(ProdutoPostDTO dto) {

		Produto produto = new Produto();

		produto.setMarca(dto.getMarca());
		produto.setNome(dto.getNome());
		produto.setCodigo(dto.getCodigo());
		produto.setCategoria(dto.getCategoria());
		produto.setPreco(dto.getPreco());
		produto.setDataCompra(DateHelper.toDate(dto.getDataCompra()));
		produto.setDataFabricacao(DateHelper.toDate(dto.getDataFabricacao()));
		produto.setDataVencimento(DateHelper.toDate(dto.getDataVencimento()));
		
		return produto;
	}
	
	public static Produto putDtoToProduto(Produto produto, ProdutoPutDTO dto) {
	
		produto.setMarca(dto.getMarca());
		produto.setNome(dto.getNome());
		produto.setCategoria(dto.getCategoria());
		produto.setPreco(dto.getPreco());
		
		return produto;
	}

}
