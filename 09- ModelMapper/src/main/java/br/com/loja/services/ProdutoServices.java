package br.com.loja.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import br.com.loja.controllers.ProdutoController;
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
	
	@Autowired
	private ModelMapper modelMapper;

	public String cadastrar(ProdutoPostDTO dto) {

		// verificando se o produto já existe no banco
		if (produtoRepository.findByCodigo(dto.getCodigo()) != null) {
			throw new BadRequestException("Código já cadastrado!");
		} else {

			// transferindo os dados do Dto para o Produto
			Produto produto = new Produto();
			modelMapper.map(dto, produto);

			// salvando o novo produto no banco
			produtoRepository.save(produto);

			// Respondendo ao controlador
			String response = "Produto cadastrado com sucesso!";
			return response;
		}

	}

	public CollectionModel<ProdutoGetDTO> buscarProdutos() {

		// Buscando todos os produtos no banco e armazenando numa lista de produtos
		List<Produto> listaProduto = (List<Produto>) produtoRepository.findAll();

		// Criando uma lista de ProdutosGetDTO
		List<ProdutoGetDTO> listaGetDto = new ArrayList<ProdutoGetDTO>();

		// pegando cada produto na listaProduto e transferindo um a um para o getDto 
		for (Produto produto : listaProduto) {
			ProdutoGetDTO getDto = new ProdutoGetDTO();
			modelMapper.map(produto, getDto);

			// adicionando links HATEOAS para cada produto
			getDto.add(linkTo(methodOn(ProdutoController.class).buscarPorId(produto.getIdProduto())).withSelfRel());
			getDto.add(linkTo(methodOn(ProdutoController.class).buscarProdutos()).withRel("Lista de Produtos"));
			
			// adicionando na listaGetDto
			listaGetDto.add(getDto);

		}

		// adicionando links HATEOAS para o recurso e respondendo ao controlador
		return CollectionModel.of(listaGetDto).add(linkTo(methodOn(ProdutoController.class).buscarProdutos()).withSelfRel());
	}

	public ProdutoGetDTO buscarPorId(Integer idProduto) {

		// Buscando um produto específico no banco
		Optional<Produto> result = produtoRepository.findById(idProduto);

		if (result == null || result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado: " + idProduto);
		} else {

			// Trazendo o produto encontrado no banco
			Produto produto = result.get();

			// transferindo os dados do Produto para o GetDto
			ProdutoGetDTO getDto = new ProdutoGetDTO();
			modelMapper.map(produto, getDto);

			// adicionando links HATEOAS
			getDto.add(linkTo(methodOn(ProdutoController.class).buscarProdutos()).withRel("Lista de Produtos"));
			getDto.add(linkTo(methodOn(ProdutoController.class).buscarPorId(produto.getIdProduto())).withSelfRel());

			// Respondendo ao controlador
			return getDto;
		}

	}

	public ProdutoGetDTO buscarPorCodigo(String codigo) {

		// Buscando um produto específico no banco
		Produto produto = produtoRepository.findByCodigo(codigo);

		if (produto != null) {

			// transferindo os dados do Produto para o GetDto
			ProdutoGetDTO getDto = new ProdutoGetDTO();
			modelMapper.map(produto, getDto);

			// adicionando links HATEOAS
			getDto.add(linkTo(methodOn(ProdutoController.class).buscarProdutos()).withRel("Lista de Produtos"));
			getDto.add(linkTo(methodOn(ProdutoController.class).buscarPorCodigo(produto.getCodigo())).withSelfRel());

			// Respondendo ao controlador
			return getDto;

		} else {
			throw new EntityNotFoundException("Produto não encontrado: " + codigo);
		}

	}

	public String atualizar(Integer idProduto, ProdutoPutDTO dto) {

		// Buscando um produto específico no banco
		Optional<Produto> result = produtoRepository.findById(idProduto);

		if (result == null || result.isEmpty()) {
			throw new UnprocessableEntityException("Produto não encontrado: " + idProduto);
		} else {

			// Trazendo o produto encontrado no banco
			Produto produto = result.get();

			// transferindo os dados do dto para o produto e salvando alterações
			modelMapper.map(dto, produto);
			produtoRepository.save(produto);

			// Respondendo ao controlador
			String response = "Produto atualizado com sucesso!";
			return response;
		}

	}

	public String excluir(Integer idProduto) {

		// Buscando um produto específico no banco
		Optional<Produto> result = produtoRepository.findById(idProduto);

		if (result == null || result.isEmpty()) {
			throw new UnprocessableEntityException("Produto não encontrado!");
		} else {

			// Trazendo o produto encontrado no banco
			Produto produto = result.get();

			// Excluindo o produto
			produtoRepository.delete(produto);

			// Respondendo ao controlador
			String response = "Produto excluído com sucesso!";
			return response;
		}

	}

}
