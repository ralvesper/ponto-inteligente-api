package com.optimize.pontointeligente.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.optimize.pontointeligente.api.entities.Empresa;;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	@Transactional(readOnly = true)
	Empresa findByCnpj(String cnpj);
	
	@Transactional(readOnly = true)
	List<Empresa> findByRazaoSocialContaining(String razaoSocial);

}
