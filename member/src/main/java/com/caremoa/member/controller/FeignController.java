package com.caremoa.member.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.caremoa.member.adapter.MemberFeign;
import com.caremoa.member.domain.dto.MemberDto;
import com.caremoa.member.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @packageName    : com.caremoa.member.controller
* @fileName       : MemberController.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
@Slf4j
@RestController
@Tag(name = "회원관리(Feign)", description = "CareMoa 회원관리(Feign)")
@RequiredArgsConstructor
public class FeignController {

	final private MemberFeign service;

	@Operation(summary = "회원정보  조회(Feign)" , description = "회원정보 조회" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the members", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@GetMapping("/feigns")
	public ResponseEntity<Page<MemberDto>> getAll(Pageable pageable) {
		try {
			log.info("getAll");
			return ResponseEntity.ok().body(service.getAll(pageable));
			// return ResponseEntity.ok().body(service.getAll(pageable));
		} catch (Exception e) {
			//log.error("{}", e.getStackTrace());
			return ResponseEntity.internalServerError().body(null);
		}
	}

	@Operation(summary = "회원정보 Key조회(Feign)" , description = "회원정보 Primary Key로 조회" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the MemberDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "404", description = "MemberDto not found", content = @Content) })
	@GetMapping("/feigns/{id}")
	public ResponseEntity<MemberDto> getById(@PathVariable("id") Long id) {
		try {
		    return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
		}catch( ApiException apiEx ) {
		    return new ResponseEntity<>(null, apiEx.getCode());
	    }catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}


	@Operation(summary = "회원정보 등록(Feign)" , description = "회원정보 신규 데이터 등록" )
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Create the MemberDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@PostMapping("/feigns")
	ResponseEntity<MemberDto> postData(@RequestBody MemberDto newData) {
		try {
			return new ResponseEntity<>(service.postData(newData), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "회원정보 수정(Feign)" , description = "회원정보 데이터 수정" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update the MemberDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "404", description = "MemberDto not found", content = @Content) })
	@PutMapping("/feigns/{id}")
	ResponseEntity<MemberDto> putData(@RequestBody MemberDto newData,
			@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(service.putData(newData,id), HttpStatus.CREATED);
		}catch( ApiException apiEx ) {
		     return new ResponseEntity<>(null, apiEx.getCode());
	    } catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "회원정보 삭제" , description = "회원정보 Primary Key로 삭제" )
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Delete the MemberDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@DeleteMapping("/feigns/{id}")
	public ResponseEntity<HttpStatus> deleteData(@PathVariable("id") Long id) {
		try {
			service.deleteData(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
