package com.caremoa.authority.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Mydata FeignErrorDecoder 클래스
 *
 * @name_ko Mydata FeignErrorDecoder 클래스
 * @author 이병관
 */
@Slf4j
public class FeignErrorDecoder  implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		//String message = "Feign Client Call Error : " + methodKey ;

		log.warn("global error code {}", response.status());

		return new ResponseStatusException(HttpStatus.valueOf(response.status()),
				"Feign Client Call Error : " + methodKey );
//		switch (response.status()) {
//		case 400:
//			return new ApiCodeException(message, ApiCode.BAD_REQUEST);
//		case 403:
//			return new ApiCodeException(message, ApiCode.FORBIDDEN);
//		case 404:
//		if(methodKey.contains("getOrders")){
//            return new ResponseStatusException(HttpStatus.valueOf(response.status()),
//                            "User's orders is empty.");
//        }
//			return new ApiCodeException(message, ApiCode.NOT_FOUND);
//		case 500:
//		case 501:
//		case 502:
//		case 503:
//		case 504:
//			return new ApiCodeException(message, ApiCode.INTERNAL_SERVER_ERROR);
//		}
//		return null;
	}
}
