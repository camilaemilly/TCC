package br.diabetes.alarmes.remedio;

import javax.persistence.Embeddable;

import br.diabetes.BaseId;

@Embeddable
public class AlarmeRemedioId extends BaseId {
	private static final long serialVersionUID = 8965550305250511524L;
	
	public AlarmeRemedioId() {
		super();
	}
	public AlarmeRemedioId(String value) {
		super(value);
	}
}