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
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.xmedika.br.AbstractControllerTest;
import com.xmedika.br.domain.Enderecos;
import com.xmedika.br.service.EnderecosService;

@Transactional
public class EnderecosControllerMocksTest extends AbstractControllerTest {

	@Mock
	private EnderecosService service;

	@InjectMocks
	private EnderecosController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		setUp(controller);
	}

	@Test
	public void testCreate() throws Exception {
		Long id = new Long(28);
		Enderecos entity = getEntityStubData();
		when(service.create(Matchers.anyLong(), any(Enderecos.class))).thenReturn(entity);

		String uri = "/pessoa/" + id + "/endereco";
		String inputJson = super.mapToJson(entity);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		verify(service, times(1)).create(Matchers.anyLong(), any(Enderecos.class));
		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

		Enderecos createdEntity = super.mapFromJson(content, Enderecos.class);

		Assert.assertNotNull("failure - expected entity not null", createdEntity);
		Assert.assertNotNull("failure - expected id attribute not null", createdEntity.getId());
		Assert.assertEquals("failure - expected text attribute match", entity.getBairro(), createdEntity.getBairro());
	}

	@Test
	public void testDelete() throws Exception {
		Long idPessoa = new Long(28);
		Long idEndereco = new Long(31);
		String uri = "/pessoa/" + idPessoa + "/endereco/{idEndereco}";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, idEndereco)).andReturn();
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		verify(service, times(1)).delete(idPessoa, idEndereco);
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to be empty", content.trim().length() == 0);

	}

	@Test
	public void testUpdate() throws Exception {
		Enderecos entity = getEntityStubData();
		entity.setBairro(entity.getBairro() + " test");
		Long id = new Long(1);
		when(service.update(any(Enderecos.class))).thenReturn(entity);
		String uri = "/endereco";
		String inputJson = super.mapToJson(entity);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri, id).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		verify(service, times(1)).update(any(Enderecos.class));

		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

		Enderecos updatedEntity = super.mapFromJson(content, Enderecos.class);

		Assert.assertNotNull("failure - expected entity not null", updatedEntity);
		Assert.assertEquals("failure - expected id attribute unchanged", entity.getId(), updatedEntity.getId());
		Assert.assertEquals("failure - expected text attribute match", entity.getBairro(), updatedEntity.getBairro());

	}

	@Test
	public void testfindAll() throws Exception {
		Long idPessoa = new Long(28);
		List<Enderecos> list = getEntityListStubData();
		when(service.findAllByPessoaId(idPessoa)).thenReturn(list);

		String uri = "/pessoa/" + idPessoa + "/endereco";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		// Extract the response status and body
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		verify(service, times(1)).findAllByPessoaId(idPessoa);

		// Perform standard JUnit assertions on the response
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

	}

	private Enderecos getEntityStubData() {
		Enderecos entity = new Enderecos();
		entity.setId(1L);
		entity.setBairro("hello");
		return entity;
	}

	private List<Enderecos> getEntityListStubData() {
		List<Enderecos> list = new ArrayList<Enderecos>();
		list.add(getEntityStubData());
		return list;
	}

}
