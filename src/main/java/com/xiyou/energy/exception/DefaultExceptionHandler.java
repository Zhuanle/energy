package com.xiyou.energy.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xiyou.energy.util.Result;

@ControllerAdvice
public class DefaultExceptionHandler {
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Result processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		return Result.fail("ex");
	}
}
