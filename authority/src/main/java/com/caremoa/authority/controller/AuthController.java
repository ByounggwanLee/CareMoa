package com.caremoa.authority.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
* @packageName    : com.caremoa.authority
* @fileName       : AuthController.java
* @author         : 이병관
* @date           : 2023.06.07
* @description    :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.06.07        이병관       최초 생성
*/
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
        private final TokenProvider tokenProvider;
        private final AuthenticationManagerBuilder authenticationManagerBuilder;

        @Operation(summary = "로그인", description = "로그인 처리")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "login", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content) })
        @PostMapping("/authenticate")
        public ResponseEntity<TokenDto> authorize(
                        @Parameter(required = true, description = "아이디") @RequestParam String username,
                        @Parameter(required = true, description = "패스워드") @RequestParam String password) {

                log.info("Start");
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                username, password);
                log.info("authenticationToken {}" , authenticationToken.toString());
                Authentication authentication = authenticationManagerBuilder.getObject()
                                .authenticate(authenticationToken);
                log.info("authenticate {}" , authentication.toString());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = tokenProvider.createToken(authentication);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

                return new ResponseEntity<>(TokenDto.builder()
                                .grantType("bearer").accessToken(jwt)
                                .accessTokenExpireDate(tokenProvider.getTokenValidityInMilliseconds())
                                .refreshToken(tokenProvider.refreshToken()).build(), httpHeaders, HttpStatus.OK);
        }
}