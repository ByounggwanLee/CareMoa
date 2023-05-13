package com.caremoa.member.controller;

import java.util.Optional;

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

import com.caremoa.member.domain.model.MemberRole;
import com.caremoa.member.domain.repository.MemberRoleRepository;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberRoleController {

	final private MemberRoleRepository repository;

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the MemberRoles", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRole.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@GetMapping("/memberRoles")
	public ResponseEntity<Page<MemberRole>> getAll(Pageable pageable) {
		try {
			log.info("findAll");
			return ResponseEntity.ok().body(repository.findAll(pageable));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}

	// @Operation(summary = "알림 수신 이력(Table) Key조회" , description = "알림 수신 이력(Table)
	// Primary Key로 조회" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the MemberRole", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRole.class)) }),
			@ApiResponse(responseCode = "404", description = "MemberRole not found", content = @Content) })
	@GetMapping("/memberRoles/{id}")
	public ResponseEntity<MemberRole> getById(@PathVariable("id") Long id) {
		Optional<MemberRole> data = repository.findById(id);

		if (data.isPresent()) {
			return ResponseEntity.ok().body(data.get());
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	// @Operation(summary = "알림 수신 이력(Table) 등록" , description = "알림 수신 이력(Table) 신규
	// 데이터 등록" )
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Create the MemberRole", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRole.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@PostMapping("/memberRoles")
	ResponseEntity<MemberRole> postData(@RequestBody MemberRole newData) {
		try {
			newData = repository.save(newData);
			return new ResponseEntity<>(newData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @Operation(summary = "알림 수신 이력(Table) 수정" , description = "알림 수신 이력(Table)
	// 데이터 수정" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update the MemberRole", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRole.class)) }),
			@ApiResponse(responseCode = "404", description = "MemberRole not found", content = @Content) })
	@PutMapping("/memberRoles/{id}")
	ResponseEntity<MemberRole> putData(@RequestBody MemberRole newData,
			@PathVariable("id") Long id) {
		return repository.findById(id) //
				.map(oldData -> {
					newData.setId(oldData.getId());
					return new ResponseEntity<>(repository.save(newData), HttpStatus.OK);
				}).orElseGet(() -> {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				});
	}

	// @Operation(summary = "알림 수신 이력(Table) 삭제" , description = "알림 수신 이력(Table)
	// Primary Key로 삭제" )
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Delete the MemberRole", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberRole.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@DeleteMapping("/memberRoles/{id}")
	public ResponseEntity<HttpStatus> deleteData(@PathVariable("id") Long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
