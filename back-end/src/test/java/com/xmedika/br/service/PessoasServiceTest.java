package com.xmedika.br.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.xmedika.br.AbstractTest;
import com.xmedika.br.domain.Pessoas;

@Transactional
public class PessoasServiceTest extends AbstractTest {

	@Autowired
	private PessoasService service;

	@Test
	public void testCreate() {

		Pessoas entity = new Pessoas();
		entity.setNome("test");

		Pessoas createdEntity = service.create(entity);

		Assert.assertNotNull("failure - expected not null", createdEntity);
		Assert.assertNotNull("failure - expected id attribute not null", createdEntity.getId());
		Assert.assertEquals("failure - expected text attribute match", "test", createdEntity.getNome());

		List<Pessoas> list = service.findAll();

		Assert.assertEquals("failure - expected size", 3, list.size());

	}

	@Test
	public void testFindAll() {
		List<Pessoas> list = service.findAll();
		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - expected list size", 2, list.size());
	}

	@Test
	public void testFindOne() {
		Long id = new Long(28);
		Pessoas entity = service.findById(id);
		Assert.assertNotNull("failure - expected not null", entity);
		Assert.assertEquals("failure - expected id attribute match", 28, entity.getId());
	}

	@Test
	public void testDelete() {
		Long id = new Long(28);
		service.delete(id);
		List<Pessoas> list = service.findAll();
		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - expected list size", 1, list.size());
	}

	@Test
	public void testFindOneNotFound() {
		Long id = Long.MAX_VALUE;
		Pessoas entity = service.findById(id);
		Assert.assertNull("failure - expected null", entity);
	}

	@Test
	public void testUpdate() {

		Long id = new Long(28);

		Pessoas entity = service.findById(id);

		Assert.assertNotNull("failure - expected not null", entity);

		String updatedText = entity.getNome() + " test";
		entity.setNome(updatedText);
		Pessoas updatedEntity = service.update(entity);

		Assert.assertNotNull("failure - expected not null", updatedEntity);
		/*
		 * Assert.assertEquals("failure - expected id attribute match", id,
		 * updatedEntity.getId());
		 */
		Assert.assertEquals("failure - expected text attribute match", updatedText, updatedEntity.getNome());

	}

	@Test
	public void testUpdateNotFound() {

		Exception exception = null;

		Pessoas entity = new Pessoas();
		entity.setId(Long.MAX_VALUE);
		entity.setNome("test");

		try {
			service.update(entity);
			Assert.assertNull("failure - expected exception", exception);
		} catch (NoResultException e) {
			exception = e;
			Assert.assertTrue("failure - expected NoResultException", exception instanceof NoResultException);

		}

	}

}
