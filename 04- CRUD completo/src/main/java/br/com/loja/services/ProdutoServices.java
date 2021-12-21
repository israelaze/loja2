package br.com.loja.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja.dtos.ProdutoGetDTO;
import br.com.loja.dtos.ProdutoPostDTO;
import br.com.loja.dtos.ProdutoPutDTO;
import br.com.loja.entities.Produto;
import br.com.loja.helpers.DateHelper;
import br.com.loja.repositories.ProdutoRepository;
import br.com.loja.services.exceptions.BadRequestException;
import br.com.loja.services.exceptions.EntityNotFoundException;
import br.com.loja.services.exceptions.UnprocessableEntityException;

@Service
public class ProdutoServices {

	@Autowired
	private ProdutoRepository produtoRepository;

	public String cadastrar(ProdutoPostDTO dto) {

		if (produtoRepository.findByCodigo(dto.getCodigo()) != null) {
			throw new BadRequestException("Código já cadastrado!");
		} else {

			//transferindo os dados do Dto para o Produto
			Produto produto = new Produto();
			produto.setMarca(dto.getMarca());
			produto.setNome(dto.getNome());
			produto.setCodigo(dto.getCodigo());
			produto.setCategoria(dto.getCategoria());
			produto.setPreco(dto.getPreco());
			produto.setDataCompra(DateHelper.toDate(dto.getDataCompra()));
			produto.setDataFabricacao(DateHelper.toDate(dto.getDataFabricacao()));
			produto.setDataVencimento(DateHelper.toDate(dto.getDataVencimento()));

			produtoRepository.save(produto);

			String response = "Produto cadastrado com sucesso!";
			return response;
		}

	}

	public List<ProdutoGetDTO> buscarProdutos() {
		List<Produto> list = (List<Produto>) produtoRepository.findAll();
		return list.stream().map(x -> new ProdutoGetDTO(x)).collect(Collectors.toList());
	}

	public ProdutoGetDTO buscarPorId(Integer idProduto) {
		
		Optional<Produto> result = produtoRepository.findById(idProduto);
		
		if(result == null || result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado: " + idProduto);
		}else {
			
			Produto produto = result.get();
			
			//transferindo os dados do Produto para o GetDto
			return new ProdutoGetDTO(produto);
		}
		
	}

	public ProdutoGetDTO buscarPorCodigo(String codigo) {
		
		Produto produto = produtoRepository.findByCodigo(codigo);
		if (produto != null) {
			//transferindo os dados do Produto para o GetDto
			return new ProdutoGetDTO(produto);
		} else {
			throw new EntityNotFoundException("Produto não encontrado: " + codigo);
		}

	}

	public String atualizar(ProdutoPutDTO dto) {

		Optional<Produto> result = produtoRepository.findById(dto.getIdProduto());

		if (result == null || result.isEmpty()) {
			throw new UnprocessableEntityException("Produto não encotrado: " + dto.getIdProduto());
		} else {

			//transferindo os dados do Dto para o produto
			Produto produto = result.get();
			produto.setMarca(dto.getMarca());
			produto.setNome(dto.getNome());
			produto.setCategoria(dto.getCategoria());
			produto.setPreco(dto.getPreco());

			produtoRepository.save(produto);
			
			String response = "Produto atualizado com sucesso!";

			return response;
		}

	}

	public String excluir(Integer idProduto) {
		
		Optional<Produto> result = produtoRepository.findById(idProduto);
		String response = null;
		
		if(result == null || result.isEmpty()) {
			throw new UnprocessableEntityException("Produto não encontrado!");
		}else {		
			Produto produto = result.get();
			produtoRepository.delete(produto);
			response = "Produto excluído com sucesso!";
			return response;
		}
			
	}

}
