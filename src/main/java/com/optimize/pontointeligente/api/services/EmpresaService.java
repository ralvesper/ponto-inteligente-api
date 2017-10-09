package com.optimize.pontointeligente.api.services;

import java.util.List;
import java.util.Optional;

import com.optimize.pontointeligente.api.entities.Empresa;

public interface EmpresaService {

	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra uma nova empresa na base de dados.
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);
	
	
	/**
	 * Retorna todas as Empresas cadastradas
	 * @return Optional<List<Empresa>>
	 */
	Optional<List<Empresa>> findAll();
	
	/**
	 * Retorna todas as Empresas cadastradas por razao social
	 * @return Optional<List<Empresa>>
	 */
	Optional<List<Empresa>> findByRazaoSocialLike(String razaoSocial);
	
}
