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
* @fileName       : MemberRoleService.java
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
public class MemberRoleService {
	private final MemberRoleRepository repository;

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
	public Page<MemberRole> getAll(Pageable pageable) throws Exception, ApiException {
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
	public MemberRole getById(Long id) throws Exception, ApiException {
		Optional<MemberRole> data = repository.findById(id);

		if (data.isPresent()) {
			return data.get();
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, String.format("MemberRole id=[{}]bNot Found", id));
		}
	}
	
	/**
	 * @methodName    : getByMemberId
	 * @date          : 2023.05.14
	 * @description   : MemberRoleDto Repository의 memberId로 검색한다.
	 * @param memberId
	 * @return
	 * @throws Exception
	 * @throws ApiException
	*/
	@Transactional(readOnly=true)
	public Page<MemberRole> getByMemberId(Long memberId, Pageable pageable) throws Exception {
		return repository.findByMemberId(memberId, pageable);
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
	public MemberRole postData(MemberRole newData) throws Exception, ApiException {
		repository.save(newData);
		return newData;
	}
	

	/**
	 * @methodName    : putData
	 * @date          : 2023.05.14
	 * @description   : MemberRole를 수정한다
	 * @param newData
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	*/
	@Transactional
	public MemberRole putData(MemberRole newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					newData.setId(oldData.getId());
					return repository.save(newData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("MemberRole id=[{}]bNot Found", id));
				});
	}
	
	/**
	 * @methodName    : patchData
	 * @date          : 2023.05.14
	 * @description   : MemberRole를 수정한다.(전달된 값만[Null 제외])
	 * @param newData
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	*/
	@Transactional
	public MemberRole patchData(MemberRole newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					if(oldData.getRole() != null ) newData.setRole(oldData.getRole());
					return repository.save(newData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("MemberRoleDto id=[{}]bNot Found", id));
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
		repository.deleteById(id);
	}
}
