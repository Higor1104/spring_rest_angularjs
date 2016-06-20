package com.xmedika.br.rest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.xmedika.br.AbstractControllerTest;
import com.xmedika.br.domain.Pessoas;
import com.xmedika.br.service.PessoasService;

@Transactional
public class PessoasControllerMocksTest extends AbstractControllerTest {

	@Mock
	private PessoasService service;

	@InjectMocks
	private PessoasController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		setUp(controller);
	}

	@Test
	public void testfindAll() throws Exception {
		// Create some test data
		List<Pessoas> list = getEntityListStubData();
		// Stub the findAllProducts method return value
		when(service.findAll()).thenReturn(list);

		String uri = "/pessoa";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		// Extract the response status and body
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		verify(service, times(1)).findAll();

		// Perform standard JUnit assertions on the response
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

	}

	@Test
	public void testCreate() throws Exception {
		Pessoas entity = getEntityStubData();
		when(service.create(any(Pessoas.class))).thenReturn(entity);

		String uri = "/pessoa";
		String inputJson = super.mapToJson(entity);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		verify(service, times(1)).create(any(Pessoas.class));
		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

		Pessoas createdEntity = super.mapFromJson(content, Pessoas.class);

		Assert.assertNotNull("failure - expected entity not null", createdEntity);
		Assert.assertNotNull("failure - expected id attribute not null", createdEntity.getId());
		Assert.assertEquals("failure - expected text attribute match", entity.getNome(), createdEntity.getNome());
	}

	@Test
	public void testUpdate() throws Exception {
		Pessoas entity = getEntityStubData();
		entity.setNome(entity.getNome() + " test");
		Long id = new Long(1);
		when(service.update(any(Pessoas.class))).thenReturn(entity);
		String uri = "/pessoa";
		String inputJson = super.mapToJson(entity);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri, id).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		verify(service, times(1)).update(any(Pessoas.class));

		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

		Pessoas updatedEntity = super.mapFromJson(content, Pessoas.class);

		Assert.assertNotNull("failure - expected entity not null", updatedEntity);
		Assert.assertEquals("failure - expected id attribute unchanged", entity.getId(), updatedEntity.getId());
		Assert.assertEquals("failure - expected text attribute match", entity.getNome(), updatedEntity.getNome());

	}

	@Test
	public void testDelete() throws Exception {
		Long id = new Long(28);
		String uri = "/pessoa/{idPessoa}";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, id)).andReturn();
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		verify(service, times(1)).delete(id);
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to be empty", content.trim().length() == 0);

	}

	private Pessoas getEntityStubData() {
		Pessoas entity = new Pessoas();
		entity.setId(1L);
		entity.setNome("hello");
		return entity;
	}

	private List<Pessoas> getEntityListStubData() {
		List<Pessoas> list = new ArrayList<Pessoas>();
		list.add(getEntityStubData());
		return list;
	}

}
