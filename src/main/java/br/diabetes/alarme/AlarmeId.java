package br.diabetes.alarme;

import javax.persistence.Embeddable;

import br.diabetes.BaseId;

@Embeddable
public class AlarmeId extends BaseId {
	private static final long serialVersionUID = 8965550305250511524L;
	
	public AlarmeId() {
		super();
	}
	public AlarmeId(String value) {
		super(value);
	}
}