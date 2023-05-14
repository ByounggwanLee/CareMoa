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

import com.caremoa.member.domain.dto.MemberRoleDto;
import com.caremoa.member.domain.service.MemberRoleService;
import com.caremoa.member.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "회원권한관리", description = "CareMoa 회원권한관리")
@RequiredArgsConstructor
public class MemberRoleController {
	final private MemberRoleService service;
	
	@Operation(summary = "회원권한정보 조회" , description = "회원권한정보 조회" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the memberroles", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRoleDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@GetMapping("/memberroles")
	public ResponseEntity<Page<MemberRoleDto>> getAll(Pageable pageable) {
		try {
			log.info("findAll");
			return ResponseEntity.ok().body(service.getAll(pageable).map(MemberRoleDto::toDto));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}

	@Operation(summary = "회원권한정보 Key조회" , description = "회원권한정보 Primary Key로 조회" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the MemberRoleDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRoleDto.class)) }),
			@ApiResponse(responseCode = "404", description = "MemberRoleDto not found", content = @Content) })
	@GetMapping("/memberroles/{id}")
	public ResponseEntity<MemberRoleDto> getById(@PathVariable("id") Long id) {
		try {
		    return new ResponseEntity<>(MemberRoleDto.toDto(service.getById(id)),HttpStatus.OK);
		}catch( ApiException apiEx ) {
		    return new ResponseEntity<>(null, apiEx.getCode());
	    }catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}

	@Operation(summary = "회원권한정보 회원ID로 조회" , description = "회원권한정보 회원ID로 조회" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the MemberRoleDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRoleDto.class)) }),
			@ApiResponse(responseCode = "404", description = "MemberRoleDto not found", content = @Content) })
	@GetMapping("/memberroles/memberid/{id}")
	public ResponseEntity<Page<MemberRoleDto>> getByMemberId(@PathVariable("memberId") Long memberId, Pageable pageable) {
		try {
		    return ResponseEntity.ok().body(service.getByMemberId(memberId, pageable).map(MemberRoleDto::toDto));
		 }catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}

	
	@Operation(summary = "회원권한정보 등록" , description = "회원권한정보 신규 데이터 등록" )
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Create the MemberRoleDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRoleDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@PostMapping("/memberroles")
	ResponseEntity<MemberRoleDto> postData(@RequestBody MemberRoleDto newData) {
		try {
			return new ResponseEntity<>(MemberRoleDto.toDto(service.postData(newData.toModel())), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "회원권한정보 수정" , description = "회원권한정보 데이터 수정" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update the MemberRoleDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRoleDto.class)) }),
			@ApiResponse(responseCode = "404", description = "MemberRoleDto not found", content = @Content) })
	@PutMapping("/memberroles/{id}")
	ResponseEntity<MemberRoleDto> putData(@RequestBody MemberRoleDto newData,
			@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(MemberRoleDto.toDto(service.putData(newData.toModel(),id)), HttpStatus.CREATED);
		}catch( ApiException apiEx ) {
		     return new ResponseEntity<>(null, apiEx.getCode());
	    } catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "회원권한정보 삭제" , description = "회원권한정보 Primary Key로 삭제" )
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Delete the MemberRoleDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRoleDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@DeleteMapping("/memberroles/{id}")
	public ResponseEntity<HttpStatus> deleteData(@PathVariable("id") Long id) {
		try {
			service.deleteData(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
