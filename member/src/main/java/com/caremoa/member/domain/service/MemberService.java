package com.caremoa.member.domain.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.caremoa.member.domain.model.Member;
import com.caremoa.member.domain.model.MemberRole;
import com.caremoa.member.domain.model.MemberStatusType;
import com.caremoa.member.domain.model.RoleType;
import com.caremoa.member.domain.repository.MemberRepository;
import com.caremoa.member.domain.repository.MemberRoleRepository;
import com.caremoa.member.exception.ApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @packageName    : com.caremoa.member.domain.service
* @fileName       : MemberService.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository repository;
	private final MemberRoleRepository roleRepository;
	// @Transactional(propagation = , isolation = ,noRollbackFor = ,readOnly =
	// ,rollbackFor = ,timeout = )
	/**
	 * @methodName    : getAll
	 * @date          : 2023.05.14
	 * @description   : Member Repository의 모든 데이터를 Page단위로 검색한다.
	 * @param pageable
	 * @return
	 * @throws Exception
	 * @throws ApiException
	*/
	@Transactional(readOnly=true)
	public Page<Member> getAll(Pageable pageable) throws Exception, ApiException {
		log.info("getAll");
		return repository.findAll(pageable);
	}


	/**
	 * @methodName    : getById
	 * @date          : 2023.05.14
	 * @description   : Member Repository의 id로 검색한다.
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	*/
	@Transactional(readOnly=true)
	public Member getById(Long id) throws Exception, ApiException {
		Optional<Member> data = repository.findById(id);

		if (data.isPresent()) {
			return data.get();
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, String.format("Member id=[%d] Not Found", id));
		}
	}

	/**
	 * @methodName    : postData
	 * @date          : 2023.05.14
	 * @description   : Member를 Repository에 등록한다.
	 * @param newData
	 * @return
	 * @throws Exception
	 * @throws ApiException
	*/
	@Transactional
	public Member postData(Member newData) throws Exception, ApiException {
		newData = repository.save(newData);
		roleRepository.save(MemberRole.builder().memberId(newData.getId()).role(RoleType.USER).build());
		
		return newData;
	}

	/**
	 * @methodName    : putData
	 * @date          : 2023.05.14
	 * @description   : Member를 수정한다
	 * @param newData
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	*/
	@Transactional
	public Member putData(Member newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					newData.setId(oldData.getId());
					return repository.save(newData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Member id=[%d] Not Found", id));
				});
	}

	/**
	 * @methodName    : patchData
	 * @date          : 2023.05.14
	 * @description   : Member를 수정한다.(전달된 값만[Null 제외])
	 * @param newData
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	*/
	@Transactional
	public Member patchData(Member newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					if(newData.getAddress() != null ) oldData.setId(newData.getId());
					if(newData.getName() != null ) oldData.setName(newData.getName());
					if(newData.getNickname() != null ) oldData.setNickname(newData.getNickname());
					if(newData.getPassword() != null ) oldData.setPassword(newData.getPassword());
					if(newData.getStatus() != null ) oldData.setStatus(newData.getStatus());
					if(newData.getUserScore() != null ) oldData.setUserScore(newData.getUserScore());
					return repository.save(oldData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Member id=[%d] Not Found", id));
				});
	}

	// @Transactional
	/**
	 * @methodName    : deleteData
	 * @date          : 2023.05.14
	 * @description   : MemberRole과 Member를 삭제한다.
	 * @param id
	 * @throws Exception
	 * @throws ApiException
	*/
	public void deleteData(@PathVariable("id") Long id) throws Exception, ApiException {
		Optional<Member> data =  repository.findById(id);
		
		if (!data.isPresent()) return;
		
		switch(data.get().getStatus()) {
		case DELETED:
			repository.deleteById(id);
			roleRepository.deleteByMemberId(id);
			repository.deleteById(id);
			break;
		case DISABLED:
			data.get().setStatus(MemberStatusType.DELETED);
			repository.save(data.get());
			break;
		default :
			data.get().setStatus(MemberStatusType.DISABLED);
			repository.save(data.get());
			break;
		}
		
	}
	
	@Transactional
	public Member reflectionScore(Member newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					oldData.setUserScore(newData.getUserScore() + oldData.getUserScore());
					return repository.save(oldData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Member id=[%d] Not Found", id));
				});
	}
	
	public String Logon(String userId, String password) throws Exception, ApiException {
		Optional<Member> data =  repository.findByUserId(userId);
		
		if (!data.isPresent()) return null;
		if (!password.equals(data.get().getPassword())) return null;
		
		return "DDD";
	}
}
