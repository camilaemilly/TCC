package br.diabetes.glicose;

import javax.persistence.Embeddable;

import br.diabetes.BaseId;

@Embeddable
public class GlicoseId extends BaseId {
	private static final long serialVersionUID = 8965550305250511524L;
	
	public GlicoseId() {
		super();
	}
	public GlicoseId(String value) {
		super(value);
	}
}