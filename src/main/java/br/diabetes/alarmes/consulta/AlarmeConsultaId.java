package br.diabetes.alarmes.consulta;

import javax.persistence.Embeddable;

import br.diabetes.BaseId;

@Embeddable
public class AlarmeConsultaId extends BaseId {
	private static final long serialVersionUID = 8965550305250511524L;
	
	public AlarmeConsultaId() {
		super();
	}
	public AlarmeConsultaId(String value) {
		super(value);
	}
}