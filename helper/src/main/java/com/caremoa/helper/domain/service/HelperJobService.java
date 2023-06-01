package com.caremoa.helper.domain.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.caremoa.helper.domain.model.HelperJob;
import com.caremoa.helper.domain.repository.HelperJobRepository;
import com.caremoa.helper.exception.ApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HelperJobService {
	private final HelperJobRepository repository;

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
	public Page<HelperJob> getAll(Pageable pageable) throws Exception, ApiException {
		log.info("getAll");
		return repository.findAll(pageable);
	}

	/**
	 * @methodName : getById
	 * @date : 2023.05.14
	 * @description : HelperJob Repository의 id로 검색한다.
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional(readOnly = true)
	public HelperJob getById(Long id) throws Exception, ApiException {
		Optional<HelperJob> data = repository.findById(id);

		if (data.isPresent()) {
			return data.get();
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, String.format("Member id=[%d] Not Found", id));
		}
	}

	/**
	 * @methodName : postData
	 * @date : 2023.05.14
	 * @description : HelperJob를 Repository에 등록한다.
	 * @param newData
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional
	public HelperJob postData(HelperJob newData) throws Exception, ApiException {
		newData = repository.save(newData);

		return newData;
	}

	/**
	 * @methodName : putData
	 * @date : 2023.05.14
	 * @description : HelperJob를 수정한다
	 * @param newData
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional
	public HelperJob putData(HelperJob newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					newData.setId(oldData.getId());
					return repository.save(newData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("HelperJob id=[%d] Not Found", id));
				});
	}

	/**
	 * @methodName : patchData
	 * @date : 2023.05.14
	 * @description : HelperJob를 수정한다.(전달된 값만[Null 제외])
	 * @param newData
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional
	public HelperJob patchData(HelperJob newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					oldData.setHelperId(newData.getHelperId() != null ? newData.getHelperId() : oldData.getHelperId());
					oldData.setJobType(newData.getJobType() != null ? newData.getJobType() : oldData.getJobType());
					oldData.setTakerAge(newData.getTakerAge() != null ? newData.getTakerAge() : oldData.getTakerAge());
					oldData.setJobArea1(newData.getJobArea1() != null ? newData.getJobArea1() : oldData.getJobArea1());
					oldData.setJobArea2(newData.getJobArea2() != null ? newData.getJobArea2() : oldData.getJobArea2());
					oldData.setJobArea3(newData.getJobArea3() != null ? newData.getJobArea3() : oldData.getJobArea3());
					oldData.setWorkingDays(
							newData.getWorkingDays() != null ? newData.getWorkingDays() : oldData.getWorkingDays());
					oldData.setWorkStartTime(newData.getWorkStartTime() != null ? newData.getWorkStartTime()
							: oldData.getWorkStartTime());
					oldData.setWorkEndTime(
							newData.getWorkEndTime() != null ? newData.getWorkEndTime() : oldData.getWorkEndTime());
					oldData.setWageType(newData.getWageType() != null ? newData.getWageType() : oldData.getWageType());
					oldData.setWageAmount(
							newData.getWageAmount() != null ? newData.getWageAmount() : oldData.getWageAmount());
					oldData.setOneLineMe(
							newData.getOneLineMe() != null ? newData.getOneLineMe() : oldData.getOneLineMe());
					oldData.setAboutMe(newData.getAboutMe() != null ? newData.getAboutMe() : oldData.getAboutMe());

					return repository.save(oldData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("HelperJob id=[%d] Not Found", id));
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
		Optional<HelperJob> data =  repository.findById(id);
		
		if (!data.isPresent()) return;
		
		data.get().setActiveFlag(false);
		repository.save(data.get());	
	}
}
