package com.optimize.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.optimize.pontointeligente.api.entities.Empresa;;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest
public class EmpresaRepositoryTest {
	
	private static final String RASAO_SOCIAL = "Empresa de exemplo";
	private static final String CNPJ = "51463645000100";

	@Autowired
	private EmpresaRepository empresaRepository;
	

	@Before
	public void setUp() throws Exception {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial(RASAO_SOCIAL);
		empresa.setCnpj(CNPJ);
		this.empresaRepository.save(empresa);
	}
	
	@After
    public final void tearDown() { 
		this.empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarPorCnpj() {
		Empresa empresa = this.empresaRepository.findByCnpj(CNPJ);
		assertEquals(CNPJ, empresa.getCnpj());
	}
	
	@Test
	public void testBuscarEmpresasPorRazaoSocialLike(){
		List<Empresa> empresas = this.empresaRepository.findByRazaoSocialContaining("exemplo");
		assertNotNull(empresas);
		
	}
	
	@Test
	public void testBuscarTodasAsEmpresas(){
		List<Empresa> empresas = this.empresaRepository.findAll();
		assertNotNull(empresas);
	}

}

