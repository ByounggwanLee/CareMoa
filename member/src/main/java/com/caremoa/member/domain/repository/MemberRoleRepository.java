package com.caremoa.member.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caremoa.member.domain.model.MemberRole;
import com.caremoa.member.domain.model.RoleType;

/**
* @packageName    : com.caremoa.member.domain.repository
* @fileName       : MemberRoleRepository.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : MemberRoleDto Repository
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
	MemberRole findByMemberIdAndRole(Long memberId, RoleType role);
	Page<MemberRole> findByMemberId(Long memberId, Pageable pageable);
//	List<MemberRole> findByMemberId(Long memberId);
	void deleteByMemberId(Long memberId);
}