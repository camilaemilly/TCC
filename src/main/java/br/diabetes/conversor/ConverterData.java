package br.diabetes.conversor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ConverterData {
	public static String converterData(Long data) {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		return formato.format(data);
	}

	private ConverterData() {

	}

	public static Date converterDataVencimentoSalvar(String string) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
		return formato.parse(string);
	}

	public static String converterDataVencimento(Long data) {
		SimpleDateFormat formato = new SimpleDateFormat("MM-yyyy");
		return formato.format(data);
	}
}
