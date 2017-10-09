package com.optimize.pontointeligente.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimize.pontointeligente.api.entities.Empresa;
import com.optimize.pontointeligente.api.repositories.EmpresaRepository;
import com.optimize.pontointeligente.api.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo empresa: {}", empresa);
		return this.empresaRepository.save(empresa);
	}

	@Override
	public Optional<List<Empresa>> findAll() {
		log.info("Buscando todas as empresas");
		List<Empresa> empresas = empresaRepository.findAll();
		return Optional.ofNullable(empresas);
	}

	@Override
	public Optional<List<Empresa>> findByRazaoSocialLike(String razaoSocial) {
		log.info("Buscando todas as empresas");
		List<Empresa> empresas = empresaRepository.findByRazaoSocialContaining(razaoSocial);
		return Optional.ofNullable(empresas);
	}

	

}
