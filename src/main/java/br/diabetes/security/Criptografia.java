package br.diabetes.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class Criptografia {
	public static String criptografa(String senha) throws NoSuchAlgorithmException {
		String senhaCriptografada = null;
		MessageDigest digest;
		digest = MessageDigest.getInstance("MD5");
		digest.update(senha.getBytes(), 0, senha.length());
		senhaCriptografada = new BigInteger(1, digest.digest()).toString(16);
		return senhaCriptografada;
	}

	private Criptografia() {
	}

}
