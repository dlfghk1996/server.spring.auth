package server.spring.auth.common.config.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.spring.auth.common.dto.Response;

@Slf4j
@RestControllerAdvice(basePackages = "server.spring.auth")
public class ControllerAdvice {

  @ExceptionHandler(ServiceException.class)
  public Response<Object> handleCustom(ServiceException ce) {
    Response<Object> response = new Response<>(ce.getResponseCode());
    return response;
  }

//    if (ce.getExtra() != null) {
//      response.setData(ce.getExtra());
//    }
//
//    if (ce.getResponseCode() == ResponseCode.ERROR) {
//      log.error("err: ", ce);
//    } else {
//      log.info("err: {}", ce.getResponseCode().getLabel());
//    }
//    return response;
//  }
//
//  @ExceptionHandler(UnexpectedTypeException.class)
//  public Response<Object> handleUnexpectedTypeException(UnexpectedTypeException e) {
//    log.error("err: ", e);
//    return new Response<>(ResponseCode.INVALID_REQUEST);
//  }
//
//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  public Response<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//    log.error("err: ", e);
//    return new Response<>(ResponseCode.INVALID_REQUEST);
//  }
//
//  @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
//  public Response<Object> handleAccessDeniedException(
//      org.springframework.security.access.AccessDeniedException e) {
//    return new Response<>(ResponseCode.ERROR_NO_ACCESS_DENIED);
//  }
//
//  @ExceptionHandler(Exception.class)
//  public Response<Object> handleException(Exception e) {
//    log.error("err: ", e);
//    return new Response<>(ResponseCode.ERROR);
//  }
//
//  @ExceptionHandler(EntityNotFoundException.class)
//  public Response<Object> handleENFException(EntityNotFoundException e) {
//    log.error("err: ", e);
//    return new Response<>(ResponseCode.ERROR_NO_MATCH_REQUEST_PARAM);
//  }
  }
