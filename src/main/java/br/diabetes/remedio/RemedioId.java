package br.diabetes.remedio;

import javax.persistence.Embeddable;

import br.diabetes.BaseId;

@Embeddable
public class RemedioId extends BaseId {
	private static final long serialVersionUID = 8965550305250511524L;
	
	public RemedioId() {
		super();
	}
	public RemedioId(String value) {
		super(value);
	}
}