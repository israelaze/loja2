package br.com.loja.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja.dtos.ProdutoGETdto;
import br.com.loja.dtos.ProdutoPOSTdto;
import br.com.loja.entities.Produto;
import br.com.loja.helpers.DateHelper;
import br.com.loja.repositories.ProdutoRepository;
import br.com.loja.services.exceptions.EntityNotFoundException;
import br.com.loja.services.exceptions.ServiceException;

@Service
public class ProdutoServices {

	@Autowired
	private ProdutoRepository produtoRepository;

	public ProdutoGETdto cadastrar(ProdutoPOSTdto dto) {

		if (produtoRepository.findByCodigo(dto.getCodigo()) != null) {
			throw new ServiceException("Produto já cadastrado!");
		} else {

			Produto produto = new Produto();
			produto.setMarca(dto.getMarca());
			produto.setNome(dto.getNome());
			produto.setCodigo(dto.getCodigo());
			produto.setCategoria(dto.getCategoria());
			produto.setPreco(dto.getPreco());
			produto.setCusto(dto.getCusto());
			produto.setDataCompra(DateHelper.toDate(dto.getDataCompra()));
			produto.setQuantidade(dto.getQuantidade());
			produto.setDataFabricacao(DateHelper.toDate(dto.getDataFabricacao()));
			produto.setDataVencimento(DateHelper.toDate(dto.getDataVencimento()));

			produtoRepository.save(produto);

			return new ProdutoGETdto(produto);
		}

	}

	public List<ProdutoGETdto> buscarProdutos() {
		List<Produto> list = (List<Produto>) produtoRepository.findAll();
		return list.stream().map(x -> new ProdutoGETdto(x)).collect(Collectors.toList());
	}
	
	public Produto buscarPorId(Integer idProduto) {
		return produtoRepository.findById(idProduto).orElseThrow(
				() -> new EntityNotFoundException("Id não encontrado: " + idProduto));
		
	}

}
