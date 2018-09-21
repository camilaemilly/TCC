package br.diabetes.conversor;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class ConverterHora {
	public static String converterHora(Time hora) {
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
		return formato.format(hora);
	}

	private ConverterHora() {
	}

}
