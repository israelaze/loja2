package br.com.loja.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.loja.dtos.ProdutoGETdto;
import br.com.loja.dtos.ProdutoPOSTdto;
import br.com.loja.entities.Produto;
import br.com.loja.services.ProdutoServices;
import br.com.loja.services.exceptions.ServiceException;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoServices produtoServices;
	
	@PostMapping
	public ResponseEntity<ProdutoGETdto> cadastrar(ProdutoPOSTdto dto) {
	
		try {
			ProdutoGETdto getDto = produtoServices.cadastrar(dto);
			
		
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idProduto}")
			.buildAndExpand(getDto.getIdProduto()).toUri();
			
			return ResponseEntity.created(uri).body(getDto);
			
					
		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoGETdto>> buscarProdutos(){
		
		try {
			List<ProdutoGETdto> list = produtoServices.buscarProdutos();
			return ResponseEntity.ok().body(list);
			
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().body(null);
		}
		 
	}
	
	@GetMapping(value = "/{idProduto}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Integer idProduto){
		Produto produto = produtoServices.buscarPorId(idProduto);
		return ResponseEntity.ok().body(produto);
		
	}
	
}
