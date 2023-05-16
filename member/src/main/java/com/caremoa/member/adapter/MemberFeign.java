package com.caremoa.member.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caremoa.member.domain.dto.MemberDto;

@FeignClient(name="Member", url="${prop.test.url}")
public interface MemberFeign {

	@GetMapping("/members")
	Page<MemberDto> getAll(Pageable pageable);

	@GetMapping("/members/{id}")
	MemberDto getById(@RequestParam("id") long id);

	@PostMapping("/members")
	MemberDto postData(MemberDto memberDto);

	@PutMapping("/members/{id}")
	MemberDto putData(MemberDto newData, @PathVariable("id") Long id);

	@PatchMapping("/members/{id}")
	MemberDto patchData(MemberDto newData, @PathVariable("id") Long id);

	@DeleteMapping("/members/{id}")
	void deleteData(@PathVariable("id") Long id);
}
