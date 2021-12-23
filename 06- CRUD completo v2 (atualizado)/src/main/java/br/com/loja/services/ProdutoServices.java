package br.com.loja.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja.dtos.DtoToProduto;
import br.com.loja.dtos.ProdutoGetDTO;
import br.com.loja.dtos.ProdutoPostDTO;
import br.com.loja.dtos.ProdutoPutDTO;
import br.com.loja.entities.Produto;
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
			Produto produto = DtoToProduto.postDtoToProduto(dto);
			
			//salvando o novo produto no banco
			produtoRepository.save(produto);
			
			//Respondendo ao controlador
			String response = "Produto cadastrado com sucesso!";
			return response;
		}

	}

	public List<ProdutoGetDTO> buscarProdutos() {
		
		//Buscando todos os produtos no banco
		List<Produto> list = (List<Produto>) produtoRepository.findAll();
		
		//mapeia cada produto da lista, transfere os dados do Produto para o GetDto, 
		//coleta os dados numa nova lista(GetDto) e retorna para o controlador 
		return list.stream().map(x -> new ProdutoGetDTO(x)).collect(Collectors.toList());
	}

	public ProdutoGetDTO buscarPorId(Integer idProduto) {
		
		//Buscando um produto específico no banco
		Optional<Produto> result = produtoRepository.findById(idProduto);
		
		if(result == null || result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado: " + idProduto);
		}else {
			
			//Trazendo o produto encontrado no banco
			Produto produto = result.get();
			
			//transferindo os dados do Produto para o GetDto
			return new ProdutoGetDTO(produto);
		}
		
	}

	public ProdutoGetDTO buscarPorCodigo(String codigo) {
		
		//Buscando um produto específico no banco
		Produto produto = produtoRepository.findByCodigo(codigo);
		
		if (produto != null) {
			
			//transferindo os dados do produto para o GetDto e retornando ao controlador
			return new ProdutoGetDTO(produto);
			
		} else {
			throw new EntityNotFoundException("Produto não encontrado: " + codigo);
		}

	}

	public String atualizar(ProdutoPutDTO dto) {

		//Buscando um produto específico no banco
		Optional<Produto> result = produtoRepository.findById(dto.getIdProduto());

		if (result == null || result.isEmpty()) {
			throw new UnprocessableEntityException("Produto não encotrado: " + dto.getIdProduto());
		} else {

			//Trazendo o produto encontrado no banco
			Produto produto = result.get();
	
			//transferindo os dados do dto para o produto e salvando alterações
			DtoToProduto.putDtoToProduto(produto, dto);
			produtoRepository.save(produto);
			
			//Respondendo ao controlador
			String response = "Produto atualizado com sucesso!";
			return response;
		}

	}

	public String excluir(Integer idProduto) {
		
		//Buscando um produto específico no banco
		Optional<Produto> result = produtoRepository.findById(idProduto);
		
		if(result == null || result.isEmpty()) {
			throw new UnprocessableEntityException("Produto não encontrado!");
		}else {		
			
			//Trazendo o produto encontrado no banco
			Produto produto = result.get();
			
			//Excluindo o produto
			produtoRepository.delete(produto);
			
			//Respondendo ao controlador
			String response = "Produto excluído com sucesso!";
			return response;
		}
			
	}

}
