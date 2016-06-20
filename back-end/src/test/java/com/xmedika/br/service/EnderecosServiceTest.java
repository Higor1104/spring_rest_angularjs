package com.xmedika.br.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmedika.br.AbstractTest;
import com.xmedika.br.domain.Enderecos;


@Transactional
public class EnderecosServiceTest extends AbstractTest {

	@Autowired
	private EnderecosService service;

	@Test
	public void testCreate() {
		Long idPessoa = new Long(101);
		Enderecos entity = new Enderecos();
		entity.setBairro("test");

		Enderecos createdEntity = service.create(idPessoa,entity);

		Assert.assertNotNull("failure - expected not null", createdEntity);
		Assert.assertNotNull("failure - expected id attribute not null", createdEntity.getId());
		Assert.assertEquals("failure - expected text attribute match", "test", createdEntity.getBairro());

		List<Enderecos> list = service.findAllByPessoaId(28);

		Assert.assertEquals("failure - expected size", 3, list.size());

	}
	
	@Test
	public void testFindAllByIdPessoa() {
		Long idPessoa = new Long(101);
		List<Enderecos> list = service.findAllByPessoaId(idPessoa);
		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - expected list size", 2, list.size());
	}
	
	@Test
	public void testDelete() {
		Long idPessoa = new Long(101);
		Long idEndereco = new Long(102);
		service.delete(idPessoa,idEndereco);
		List<Enderecos> list = service.findAllByPessoaId(idPessoa);
		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - expected list size", 1, list.size());
	}
	
	@Test
	public void testUpdate() {

		Long id = new Long(31);

		Enderecos entity = service.findById(id);

		Assert.assertNotNull("failure - expected not null", entity);

		String updatedText = entity.getBairro() + " test";
		entity.setBairro(updatedText);
		Enderecos updatedEntity = service.update(entity);

		Assert.assertNotNull("failure - expected not null", updatedEntity);
		/*
		 * Assert.assertEquals("failure - expected id attribute match", id,
		 * updatedEntity.getId());
		 */
		Assert.assertEquals("failure - expected text attribute match", updatedText, updatedEntity.getBairro());

	}

	@Test
	public void testUpdateNotFound() {

		Exception exception = null;

		Enderecos entity = new Enderecos();
		entity.setId(Long.MAX_VALUE);
		entity.setBairro("test");

		try {
			service.update(entity);
			Assert.assertNull("failure - expected exception", exception);
		} catch (NoResultException e) {
			exception = e;
			Assert.assertTrue("failure - expected NoResultException", exception instanceof NoResultException);

		}

	}

}
