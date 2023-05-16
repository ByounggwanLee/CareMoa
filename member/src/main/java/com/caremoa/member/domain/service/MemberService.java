package com.caremoa.member.domain.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.caremoa.member.domain.model.Member;
import com.caremoa.member.domain.model.MemberRole;
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
* @description    : CareMoa Member Service
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
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
			throw new ApiException(HttpStatus.NOT_FOUND, String.format("Member id=[{}]bNot Found", id));
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
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Member id=[{}]bNot Found", id));
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
					if(oldData.getAddress() != null ) newData.setId(oldData.getId());
					if(oldData.getName() != null ) newData.setName(oldData.getName());
					if(oldData.getNickname() != null ) newData.setNickname(oldData.getNickname());
					if(oldData.getPassword() != null ) newData.setPassword(oldData.getPassword());
					if(oldData.getStatus() != null ) newData.setStatus(oldData.getStatus());
					if(oldData.getUserScore() != null ) newData.setUserScore(oldData.getUserScore());
					return repository.save(newData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Member id=[{}]bNot Found", id));
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
		roleRepository.deleteByMemberId(id);
		repository.deleteById(id);
	}
}
