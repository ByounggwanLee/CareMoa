package com.caremoa.member.controller;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.caremoa.member.domain.dto.ContractCompleted;
import com.caremoa.member.domain.dto.MemberDto;
import com.caremoa.member.domain.service.MemberService;
import com.caremoa.member.exception.ApiException;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

	final private MemberService service;
	final private StreamBridge streamBridge;

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the members", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@GetMapping("/members")
	public ResponseEntity<Page<MemberDto>> getAll(Pageable pageable) {
		try {
			log.info("findAll");
			return ResponseEntity.ok().body(service.getAll(pageable).map(MemberDto::toDto));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}

	// @Operation(summary = "알림 수신 이력(Table) Key조회" , description = "알림 수신 이력(Table)
	// Primary Key로 조회" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the MemberDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "404", description = "MemberDto not found", content = @Content) })
	@GetMapping("/members/{id}")
	public ResponseEntity<MemberDto> getById(@PathVariable("id") Long id) {
		try {
		    return new ResponseEntity<>(MemberDto.toDto(service.getById(id)),HttpStatus.OK);
		}catch( ApiException apiEx ) {
		     return new ResponseEntity<>(null, apiEx.getCode());
	    }catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}

	
	// @Operation(summary = "알림 수신 이력(Table) 등록" , description = "알림 수신 이력(Table) 신규
	// 데이터 등록" )
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Create the MemberDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@PostMapping("/members")
	ResponseEntity<MemberDto> postData(@RequestBody MemberDto newData) {
		try {
			return new ResponseEntity<>(MemberDto.toDto(service.postData(newData.toModel())), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @Operation(summary = "알림 수신 이력(Table) 수정" , description = "알림 수신 이력(Table)
	// 데이터 수정" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update the MemberDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "404", description = "MemberDto not found", content = @Content) })
	@PutMapping("/members/{id}")
	ResponseEntity<MemberDto> putData(@RequestBody MemberDto newData,
			@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(MemberDto.toDto(service.putData(newData.toModel(),id)), HttpStatus.CREATED);
		}catch( ApiException apiEx ) {
		     return new ResponseEntity<>(null, apiEx.getCode());
	    } catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @Operation(summary = "알림 수신 이력(Table) 삭제" , description = "알림 수신 이력(Table)
	// Primary Key로 삭제" )
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Delete the MemberDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@DeleteMapping("/members/{id}")
	public ResponseEntity<HttpStatus> deleteData(@PathVariable("id") Long id) {
		try {
			service.deleteData(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/testkafka/{memberId}/{helperId}")
	public ResponseEntity<HttpStatus> test(@PathVariable("memberId") Long memberId, @PathVariable("helperId") Long helperId) {
		try {
			ContractCompleted xx = ContractCompleted.builder().helperId(helperId).memberId(memberId).build();
			String json = xx.toJson();
			log.info("before publish");
		    if( json != null ){
		          streamBridge.send("basicProducer-out-0", MessageBuilder.withPayload(json)
		      	 		.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
		     }
		    log.info("after publish");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.info("publish {}", e.getMessage());
			return ResponseEntity.internalServerError().body(null);
		}
	}
}
