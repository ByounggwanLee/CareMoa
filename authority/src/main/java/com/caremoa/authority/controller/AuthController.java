package com.caremoa.authority.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caremoa.authority.adapter.MemberFeign;
import com.caremoa.authority.domain.dto.MemberDto;
import com.caremoa.authority.domain.dto.TokenDto;
import com.caremoa.authority.jwt.JwtFilter;
import com.caremoa.authority.jwt.TokenProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @packageName : com.caremoa.authority
 * @fileName : AuthController.java
 * @author : 이병관
 * @date : 2023.06.07
 * @description : ===========================================================
 *              DATE AUTHOR NOTE
 *              -----------------------------------------------------------
 *              2023.06.07 이병관 최초 생성
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final MemberFeign memberFeign;

	@Operation(summary = "로그인", description = "로그인 처리")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "login", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TokenDto.class)) }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content) })
	@PostMapping("/authenticate")
	public ResponseEntity<TokenDto> authorize(
			@Parameter(required = true, description = "아이디") @RequestParam String userId,
			@Parameter(required = true, description = "패스워드") @RequestParam String password) {

		log.info("Start");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId,
				password);
		log.info("authenticationToken {}", authenticationToken.toString());
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		log.info("authenticate {}", authentication.toString());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.createToken(authentication);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

		return new ResponseEntity<>(TokenDto.builder().grantType("bearer").accessToken(jwt)
				.accessTokenExpireDate(tokenProvider.getTokenValidityInMilliseconds())
				.refreshToken(tokenProvider.refreshToken()).build(), httpHeaders, HttpStatus.OK);
	}

	@Operation(summary = "아이디 존재 여부 확인", description = "아이디 존재 여부 확인")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "UserId found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)) }),
			@ApiResponse(responseCode = "404", description = "UserId not found", content = @Content) })
	@GetMapping("/findUserId/{userId}")
	public ResponseEntity<Boolean> findUserId(
			@Parameter(required = true, description = "아이디") @PathVariable String userId) {
		if (memberFeign.findUserId(userId) != null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}
	
	@Operation(summary = "회원정보 등록" , description = "회원정보 신규 데이터 등록" )
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Regist the Member", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@PostMapping("/registMember")
	ResponseEntity<MemberDto> postData(@RequestBody MemberDto newData) {
		try {
			return new ResponseEntity<>(memberFeign.postData(newData), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}