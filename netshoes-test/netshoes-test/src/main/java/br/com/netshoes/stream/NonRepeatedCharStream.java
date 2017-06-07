package br.com.netshoes.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;


public class NonRepeatedCharStream implements Stream {

	private Iterator<Character> itNonRepeated;
	
	public NonRepeatedCharStream(String content) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(content), 
				"Informe o conteúdo corretamente!");
		Map<Character, Integer> caracteresQtde = new LinkedHashMap<>();
		for (char c: content.toCharArray()) {
			int qtd = caracteresQtde.containsKey(c) ? caracteresQtde.get(c) + 1 : 1;
			caracteresQtde.put(c, Integer.valueOf(qtd));
		}
		itNonRepeated = caracteresQtde
				.keySet()
				.stream()
				.filter(c -> caracteresQtde.get(c) == 1)
				.iterator();
	}
	
	@Override
	public char getNext() {
		return itNonRepeated.next();
	}
	
	@Override
	public boolean hasNext() {
		return itNonRepeated.hasNext();
	}
		
}
