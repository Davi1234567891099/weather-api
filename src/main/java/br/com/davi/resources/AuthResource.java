package br.com.davi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.constants.RolesConstants;
import br.com.davi.models.security.AccountCredentialsDTO;
import br.com.davi.models.security.TokenDTO;
import br.com.davi.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User management and authentication endpoints")
@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	private AuthService authServices;
	
	@Operation(summary = "Authenticates a user and returns a token")
	@PostMapping(value = "/signin")
	public ResponseEntity<TokenDTO> signin(@RequestBody AccountCredentialsDTO data) {
		return authServices.signin(data);
	}
	
	@Operation(summary = "Refresh token for authenticated user and returns a token")
	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity<TokenDTO> refreshToken(@PathVariable("username") String username,
			@RequestHeader("Authorization") String refreshToken) {
		return authServices.refreshToken(username, refreshToken);
	}

	@Operation(summary = "Creates a new account")
	@PostMapping(value = "/createAccount")
	public ResponseEntity<Void> createAccount(@RequestBody AccountCredentialsDTO data){
		authServices.createAccount(data, RolesConstants.COMMON_USER_PERMISSION);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Creates a new administrator account(only permitted for administrator users)")
	@PostMapping(value = "/createAdminAccount")
	public ResponseEntity<Void> createAdminAccount(@RequestBody AccountCredentialsDTO data){
		authServices.createAccount(data, RolesConstants.ADMIN_USER_PERMISSION);
		return ResponseEntity.noContent().build();
	}
}
