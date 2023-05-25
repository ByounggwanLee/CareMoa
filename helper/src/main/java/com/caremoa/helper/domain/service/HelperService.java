package com.caremoa.helper.domain.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.caremoa.helper.domain.model.Helper;
import com.caremoa.helper.domain.model.HelperStausType;
import com.caremoa.helper.domain.repository.HelperRepository;
import com.caremoa.helper.exception.ApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HelperService {
	private final HelperRepository repository;

	// @Transactional(propagation = , isolation = ,noRollbackFor = ,readOnly =
	// ,rollbackFor = ,timeout = )
	/**
	 * @methodName : getAll
	 * @date : 2023.05.14
	 * @description : Helper Repository의 모든 데이터를 Page단위로 검색한다.
	 * @param pageable
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional(readOnly = true)
	public Page<Helper> getAll(Pageable pageable) throws Exception, ApiException {
		log.info("getAll");
		return repository.findAll(pageable);
	}

	/**
	 * @methodName : getById
	 * @date : 2023.05.14
	 * @description : Helper Repository의 id로 검색한다.
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional(readOnly = true)
	public Helper getById(Long id) throws Exception, ApiException {
		Optional<Helper> data = repository.findById(id);

		if (data.isPresent()) {
			return data.get();
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, String.format("Member id=[%d] Not Found", id));
		}
	}

	/**
	 * @methodName : postData
	 * @date : 2023.05.14
	 * @description : Helper를 Repository에 등록한다.
	 * @param newData
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional
	public Helper postData(Helper newData) throws Exception, ApiException {
		newData = repository.save(newData);

		return newData;
	}

	/**
	 * @methodName : putData
	 * @date : 2023.05.14
	 * @description : Helper를 수정한다
	 * @param newData
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional
	public Helper putData(Helper newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					newData.setId(oldData.getId());
					return repository.save(newData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Helper id=[%d] Not Found", id));
				});
	}

	/**
	 * @methodName : patchData
	 * @date : 2023.05.14
	 * @description : Helper를 수정한다.(전달된 값만[Null 제외])
	 * @param newData
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional
	public Helper patchData(Helper newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					oldData.setName(newData.getName() != null ? newData.getName() : oldData.getName());
					oldData.setNickname(newData.getNickname() != null ? newData.getNickname() : oldData.getNickname());
					oldData.setAddress(newData.getAddress() != null ? newData.getAddress() : oldData.getAddress());
					oldData.setBirthday(newData.getBirthday() != null ? newData.getBirthday() : oldData.getBirthday());
					oldData.setHelperScore(
							newData.getHelperScore() != null ? newData.getHelperScore() : oldData.getHelperScore());
					oldData.setCamcoderAgree(newData.getCamcoderAgree() != null ? newData.getCamcoderAgree()
							: oldData.getCamcoderAgree());
					oldData.setCareerPeriod(
							newData.getCareerPeriod() != null ? newData.getCareerPeriod() : oldData.getCareerPeriod());
					oldData.setStatus(newData.getStatus() != null ? newData.getStatus() : oldData.getStatus());
					return repository.save(oldData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Helper id=[%d] Not Found", id));
				});
	}
	
	/**
	 * @methodName    : deleteData
	 * @date          : 2023.05.14
	 * @description   : Helper를 삭제한다.
	 * @param id
	 * @throws Exception
	 * @throws ApiException
	*/
	@Transactional
	public void deleteData(@PathVariable("id") Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					switch(oldData.getStatus()) {
					case DELETED:
						repository.save(oldData);
						break;
					default :
						repository.save(oldData);
					}
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Helper id=[%d] Not Found", id));
				});
		
	}

	@Transactional
	public Helper reflectionScore(Helper newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					oldData.setHelperScore(newData.getHelperScore() + oldData.getHelperScore());
					return repository.save(oldData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Helper id=[%d] Not Found", id));
				});
	}
}
