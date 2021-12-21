package br.com.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.dtos.ProdutoGetDTO;
import br.com.loja.dtos.ProdutoPostDTO;
import br.com.loja.dtos.ProdutoPutDTO;
import br.com.loja.services.ProdutoServices;
import br.com.loja.services.exceptions.ServiceException;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoServices produtoServices;

	@PostMapping
	public ResponseEntity<String> cadastrar(ProdutoPostDTO dto) {

		try {
			String response = produtoServices.cadastrar(dto);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}

	}

	@GetMapping
	public ResponseEntity<List<ProdutoGetDTO>> buscarProdutos() {

		try {
			List<ProdutoGetDTO> list = produtoServices.buscarProdutos();
			return ResponseEntity.ok().body(list);

		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@GetMapping(value = "/id/{idProduto}")
	public ResponseEntity<ProdutoGetDTO> buscarPorId(@PathVariable Integer idProduto) {

		try {
			ProdutoGetDTO getDto = produtoServices.buscarPorId(idProduto);
			return ResponseEntity.ok().body(getDto);
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@GetMapping(value = "/cod/{codigo}")
	public ResponseEntity<ProdutoGetDTO> buscarPorCodigo(@PathVariable String codigo) {
		
		try {
			
			ProdutoGetDTO getDto = produtoServices.buscarPorCodigo(codigo);
			return ResponseEntity.ok().body(getDto);
			
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping
	public ResponseEntity<String> atualizar(ProdutoPutDTO dto) {

		try {
			String response = produtoServices.atualizar(dto);
			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@DeleteMapping(value = "/{idProduto}")
	public ResponseEntity<String> excluir(@PathVariable Integer idProduto) {

		try {

			String response = produtoServices.excluir(idProduto);
			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}

	}

}
