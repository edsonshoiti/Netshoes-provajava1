package br.com.netshoes.web.tests;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.netshoes.boot.Application;
import br.com.netshoes.domain.Campanha;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class CampanhaWebServiceTest extends BaseWebServiceTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void isServiceAlive() throws Exception {
		this.mvc.perform(get("/ceps/health")
				.accept(MediaType.APPLICATION_JSON)
				)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("ok")));
	}
	
	@Test
	public void findCepValido() throws Exception {
		String chave = "09726121";
		this.mvc.perform(get(String.format("/ceps/%s", chave))
				.accept(MediaType.APPLICATION_JSON)
				)
			.andExpect(status().isOk())
			.andDo(setContentType("charset=utf-8"))	
			.andExpect(jsonPath("cidade", equalTo("São Bernardo do Campo")))
			.andExpect(jsonPath("estado", equalTo("SP")));
	}
	
	@Test
	public void findCepValidoComJson() throws Exception {
		Campanha c = new Campanha("01/10/2017");
		this.mvc.perform(get("/campanhas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(c))
				.accept(MediaType.APPLICATION_JSON)
				)
			.andDo(setContentType("charset=utf-8"))	
			.andExpect(status().isOk())
			.andExpect(jsonPath("nomecompleto", equalTo("Edson Shoiti")))
			.andExpect(jsonPath("timecoracao", equalTo("Palmeiras")));
	}
	
	@Test
	public void findCepValidoComTraco() throws Exception {
		String chave = "01/10/2017 ";
		this.mvc.perform(get(String.format("/campanhas/%s", chave))
					.accept(MediaType.APPLICATION_JSON)
					)
				.andDo(setContentType("charset=utf-8"))	
				.andExpect(status().isOk())
				.andExpect(jsonPath("nomecompleto", equalTo("Edson Shoiti")))
				.andExpect(jsonPath("timecoracao", equalTo("Palmeiras")));
	}
	
	@Test
	public void findCepValidoComJsonETraco() throws Exception {
		Campanha c = new Campanha("01/10/2017 ");
		this.mvc.perform(get("/campanhas")
					.contentType(MediaType.APPLICATION_JSON)
					.content(convertObjectToJsonBytes(c))
					.accept(MediaType.APPLICATION_JSON)
					)
				.andDo(setContentType("charset=utf-8"))	
				.andExpect(status().isOk())
				.andExpect(jsonPath("nomecompleto", equalTo("Edson Shoiti")))
				.andExpect(jsonPath("timecoracao", equalTo("Palmeiras")));
	}
	
	@Test
	public void findCepProximidadePaulistaComJson() throws Exception {
		Campanha c = new Campanha("03/10/2017 ");
		this.mvc.perform(get("/campanhas")
					.contentType(MediaType.APPLICATION_JSON)
					.content(convertObjectToJsonBytes(c))
					.accept(MediaType.APPLICATION_JSON)
					)
				.andDo(setContentType("charset=utf-8"))	
				.andExpect(status().isOk())
				.andExpect(jsonPath("nomecompleto", equalTo("Paulo")))
				.andExpect(jsonPath("email", equalTo("paulo@gmail.com")))
				.andExpect(jsonPath("datanascimento", equalTo("05/09/1984")))
                                .andExpect(jsonPath("timecoracao", equalTo("São Paulo")))
				.andExpect(jsonPath("chave", not(c.getChave())));
	}
	
	@Test
	public void findCepInvalido() throws Exception {
		String chave = "qualquerUm";
		this.mvc.perform(get(String.format("/campanhas/%s", chave))
					.accept(MediaType.APPLICATION_JSON)
					)
				.andExpect(status().isBadRequest())
				.andDo(print());
	}
	
	@Test
	public void findCepSemChave() throws Exception {
		this.mvc.perform(get("/ceps/")
				.accept(MediaType.APPLICATION_JSON)
				)
			.andExpect(status().isUnsupportedMediaType())
			.andDo(print());
	}
	
	@Test
	public void findCampanhaInexistente() throws Exception {
		String chave = "00/00/0000";
		this.mvc.perform(get(String.format("/campanha/%s", chave))
				.accept(MediaType.APPLICATION_JSON)
				)
			.andExpect(status().isNotFound())
			.andDo(print());
	}
	
}
