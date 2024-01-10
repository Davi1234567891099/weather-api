package br.com.davi.services;

import org.springframework.http.ResponseEntity;

import br.com.davi.models.security.AccountCredentialsDTO;
import br.com.davi.models.security.TokenDTO;

public interface AuthService {

	ResponseEntity<TokenDTO> signin(AccountCredentialsDTO data);
	ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken);
	void createAccount(AccountCredentialsDTO data, String permissionType);
}
