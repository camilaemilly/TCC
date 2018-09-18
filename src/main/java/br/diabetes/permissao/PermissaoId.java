package br.diabetes.permissao;

import javax.persistence.Embeddable;

import br.diabetes.BaseId;

@Embeddable
public class PermissaoId extends BaseId {
	private static final long serialVersionUID = 8965550305250511524L;
	
	public PermissaoId() {
		super();
	}
	public PermissaoId(String value) {
		super(value);
	}
}