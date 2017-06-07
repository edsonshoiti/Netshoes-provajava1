package br.com.netshoes.web;

import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.netshoes.stream.NonRepeatedCharStream;
import br.com.netshoes.stream.Stream;


@Controller
@RequestMapping(value="/")
public class ViewController {
	
	private static final Logger log = LoggerFactory.getLogger(ViewController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "enderecos/list";
	}
	
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(@RequestParam(required=false) Long id, Model model) {
		if (id != null) {
			model.addAttribute("id", id);
		}
		return "enderecos/form";
	}
	
	@RequestMapping(params = "view", method = RequestMethod.GET)
	public String view(@RequestParam Long id, Model model) {
		if (id != null) {
			model.addAttribute("id", id);
		}
		return "enderecos/view";
	}
	
	@RequestMapping(value = "search-cep", method = RequestMethod.GET)
	public String searchCep() {
		return "ceps/search";
	}
	
	@RequestMapping(value = "stream", method = RequestMethod.GET)
	public String stream() {
		return "stream/form";
	}
	
	@RequestMapping(value = "stream", method = RequestMethod.POST)
	public @ResponseBody String postStream(@RequestParam String conteudo) {
		Stream stream = new NonRepeatedCharStream(conteudo);
		StringJoiner nonRepeated = new StringJoiner(", ");
		while (stream.hasNext()) {
			nonRepeated = nonRepeated.add(String.format("%s", stream.getNext()));
		}
		String resultado = nonRepeated.toString();
		
		if (log.isDebugEnabled()) {
			log.debug("Acionando NonRepeatedCharStream. Entrou: {} - Saiu: {}", conteudo, resultado);
		}
		
		return resultado;
	}
	
}
