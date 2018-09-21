package br.diabetes.conversor;

import java.text.Normalizer;
import org.springframework.stereotype.Component;

@Component
public class TermoDeBusca {

	private TermoDeBusca() {
	}

	private static String deAccent(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public static Boolean searchTerm(String filtro, String searchTerm) {
		filtro = deAccent(filtro);
		searchTerm = deAccent(searchTerm);
		return filtro.toLowerCase().contains(searchTerm.toLowerCase());
	}
}
