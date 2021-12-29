package br.com.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(tags = "Menu Produtos")
@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoServices produtoServices;

	@PostMapping
	@ApiOperation(value = "Cadastrar produtos")
	public ResponseEntity<String> cadastrar(@RequestBody ProdutoPostDTO dto) {

		try {
			String response = produtoServices.cadastrar(dto);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}

	}

	@GetMapping
	@ApiOperation(value = "Buscar todos os produtos")
	public ResponseEntity<CollectionModel<ProdutoGetDTO>> buscarProdutos() {

		try {
			CollectionModel<ProdutoGetDTO> list = produtoServices.buscarProdutos();
			return ResponseEntity.ok().body(list);

		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@GetMapping(value = "/id/{idProduto}")
	@ApiOperation(value = "Buscar um produto pelo id")
	public ResponseEntity<ProdutoGetDTO> buscarPorId(@PathVariable Integer idProduto) {

		try {
			ProdutoGetDTO getDto = produtoServices.buscarPorId(idProduto);
			
			return ResponseEntity.ok().body(getDto);
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@GetMapping(value = "/cod/{codigo}")
	@ApiOperation(value = "Buscar um produto pelo código")
	public ResponseEntity<ProdutoGetDTO> buscarPorCodigo(@PathVariable String codigo) {
		
		try {
			
			ProdutoGetDTO getDto = produtoServices.buscarPorCodigo(codigo);
			return ResponseEntity.ok().body(getDto);
			
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping(value = "/id/{idProduto}")
	@ApiOperation(value = "Atualizar um produto")
	public ResponseEntity<String> atualizar(@PathVariable Integer idProduto, @RequestBody ProdutoPutDTO dto) {

		try {
			String response = produtoServices.atualizar(idProduto ,dto);
			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@DeleteMapping(value = "/id/{idProduto}")
	@ApiOperation(value = "Excluir um produto")
	public ResponseEntity<String> excluir(@PathVariable Integer idProduto) {

		try {

			String response = produtoServices.excluir(idProduto);
			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}

	}

}
