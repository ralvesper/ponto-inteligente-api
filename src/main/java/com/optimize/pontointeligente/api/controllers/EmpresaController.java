package com.optimize.pontointeligente.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimize.pontointeligente.api.dtos.EmpresaDto;
import com.optimize.pontointeligente.api.entities.Empresa;
import com.optimize.pontointeligente.api.response.Response;
import com.optimize.pontointeligente.api.services.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

	@Autowired
	private EmpresaService empresaService;

	public EmpresaController() {
	}
	
	/**
	 * Busca todas as empresas cadastradas.
	 * @return ResponseEntity<Response<List<EmpresaDto>>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<EmpresaDto>>> buscarTodasEmpresas() {
		log.info("Buscando todas as empresas");
		Response<List<EmpresaDto>> response = new Response<>();
		Optional<List<Empresa>> empresas = empresaService.findAll();

		if (!empresas.isPresent()) {
			log.info("Nenhuma empresa cadastrada");
			response.getErrors().add("Nenhma empresa cadastrada");
			return ResponseEntity.ok().body(response);
		}

		response.setData(this.converterListaEmpresaDto(empresas.get()));
		return ResponseEntity.ok(response);

	}

	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@GetMapping(value = "/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
		log.info("Buscando empresa por CNPJ: {}", cnpj);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(cnpj);

		if (!empresa.isPresent()) {
			log.info("Empresa não encontrada para o CNPJ: {}", cnpj);
			response.getErrors().add("Empresa não encontrada para o CNPJ " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Busca empresas que combinam com a razao social pesquisada.
	 * @param razaoSocial
	 * @return ResponseEntity<Response<List<EmpresaDto>>>
	 */
	@GetMapping(value = "/razaoSocial/{razaoSocial}")
	public ResponseEntity<Response<List<EmpresaDto>>> buscarEmpresasPorRazaoSocial(
			@PathVariable("razaoSocial") String razaoSocial) {
		log.info("Buscando todas empresas por razão social ");
		Response<List<EmpresaDto>> response = new Response<>();
		Optional<List<Empresa>> empresas = empresaService.findByRazaoSocialLike(razaoSocial);

		if (!empresas.isPresent()) {
			log.info("Nenhuma empresa cadastrada com razao social {}", razaoSocial);
			response.getErrors().add("Nenhma empresa cadastrada com razao social " + razaoSocial);
			return ResponseEntity.ok().body(response);
		}

		response.setData(this.converterListaEmpresaDto(empresas.get()));
		return ResponseEntity.ok(response);

	}

	private List<EmpresaDto> converterListaEmpresaDto(List<Empresa> empresas) {
		List<EmpresaDto> empresasDtos = new ArrayList<>();
		empresas.forEach(empresa -> empresasDtos.add(this.converterEmpresaDto(empresa)));
		return empresasDtos;
	}

	/**
	 * Popula um DTO com os dados de uma empresa.
	 * 
	 * @param empresa
	 * @return EmpresaDto
	 */
	private EmpresaDto converterEmpresaDto(Empresa empresa) {
		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setId(empresa.getId());
		empresaDto.setCnpj(empresa.getCnpj());
		empresaDto.setRazaoSocial(empresa.getRazaoSocial());

		return empresaDto;
	}

}
