package br.com.davi.services.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.stereotype.Service;

import br.com.davi.exceptions.InvalidAuthRequestException;
import br.com.davi.models.User;
import br.com.davi.models.security.AccountCredentialsDTO;
import br.com.davi.models.security.TokenDTO;
import br.com.davi.repositories.PermissionRepository;
import br.com.davi.repositories.UserRepository;
import br.com.davi.security.jwt.JwtTokenProvider;
import br.com.davi.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	public ResponseEntity<TokenDTO> signin(AccountCredentialsDTO data) {
		if (checkIfAccountCredentialsIsNull(data)) {
			throw new InvalidAuthRequestException("Invalid client request, check your credentials");
		}

		try {
			var username = data.getUsername();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			var user = userRepository.findByUsername(username);

			var tokenResponse = new TokenDTO();
			if (nonNull(user)) {
				tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + " not found!");
			}

			if (isNull(tokenResponse)) {
				throw new InvalidAuthRequestException("Invalid client request, check your credentials");
			}

			return ResponseEntity.ok(tokenResponse);
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}

	public ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken) {
		if (checkIfParamsIsNotNull(username, refreshToken)) {
			throw new InvalidAuthRequestException("Invalid client request, check your params");
		}

		var user = userRepository.findByUsername(username);

		var tokenResponse = new TokenDTO();
		if (nonNull(user)) {
			tokenResponse = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}

		if (isNull(tokenResponse)) {
			throw new InvalidAuthRequestException("Invalid client request, check your params");
		}

		return ResponseEntity.ok(tokenResponse);
	}

	public void createAccount(AccountCredentialsDTO data, String permissionType) {
		generateUser(data, permissionType);
	}

	private void generateUser(AccountCredentialsDTO data, String permissionType) {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000,
				SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		createNewUser(data, passwordEncoder, permissionType);
	}

	private void createNewUser(AccountCredentialsDTO data, DelegatingPasswordEncoder passwordEncoder,
			String description) {
		var user = new User();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(data.getPassword()));
		user.setPermissions(List.of(permissionRepository.findByDescription(description)));
		user.setUserName(data.getUsername());
		userRepository.save(user);
	}

	private static boolean checkIfParamsIsNotNull(String username, String refreshToken) {
		return isNull(username) || isNull(refreshToken) || isBlank(username) || isBlank(refreshToken);
	}

	private static boolean checkIfAccountCredentialsIsNull(AccountCredentialsDTO data) {
		return isNull(data) || isNull(data.getUsername()) || isNull(data.getPassword()) || isBlank(data.getUsername())
				|| isBlank(data.getPassword());
	}
}
