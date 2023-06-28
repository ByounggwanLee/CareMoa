package com.caremoa.authority.adapter;

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

import com.caremoa.authority.domain.dto.LoginDto;
import com.caremoa.authority.domain.dto.MemberDto;

@FeignClient(name="Member", url="${prop.member.url}")
public interface MemberFeign {
	@GetMapping("/login")
	public LoginDto findUserId(@RequestParam("userId") String userId);
	
	@PostMapping("/members")
	MemberDto postData(MemberDto newData);
}
