package br.diabetes.consulta;

import javax.persistence.Embeddable;

import br.diabetes.BaseId;

@Embeddable
public class ConsultaId extends BaseId {
	private static final long serialVersionUID = 8965550305250511524L;
	
	public ConsultaId() {
		super();
	}
	public ConsultaId(String value) {
		super(value);
	}
}