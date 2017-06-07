package br.com.netshoes.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper com uma lista de Enderecos e total de registros, convertido em Json.
 * 
 * <p>Teste: <strong>Questão 2</strong></p>
 * 
 * @author <a href="mailto:edermag@gmail.com">Eder Magalhães</a>
 */
public class ListaEndereco {

	private final List<Endereco> items = new ArrayList<>();
	
	private final Long total;
	
	public ListaEndereco(List<Endereco> items, Long total) {
		this.items.addAll(items);
		this.total = total;
	}
	
	public List<Endereco> getItems() {
		return items;
	}
	
	public Long getTotal() {
		return total;
	}
}
