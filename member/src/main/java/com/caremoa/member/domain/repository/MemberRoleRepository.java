package com.caremoa.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caremoa.member.domain.model.MemberRole;
import com.caremoa.member.domain.model.RoleType;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
	MemberRole findByMemberIdAndRole(Long memberId, RoleType role);
}