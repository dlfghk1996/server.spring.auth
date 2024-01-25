package server.spring.auth.common.config.exception;


import server.spring.auth.common.enums.ResponseCode;

// Custom business Exception (사용자 정의 예외)
public class ServiceException extends RuntimeException {
    private int errorCode;
    private ResponseCode responseCode;
    private Object detailMessage;

    public ServiceException() {
    }



    public ResponseCode getResponseCode() {
        return this.responseCode;
    }

//    public BaseException(String message, Throwable cause) {
//       super(message, cause); // 부모 생성자 호출
//      //  this.errorCode = ResponseCode.UNKNOWN.getCode();
//    }

    public ServiceException(ResponseCode responseCode) {
        super(responseCode.getLabel()); // 부모 생성자 호출
        this.errorCode = responseCode.getCode();
        this.responseCode = responseCode;
    }

//    public BaseException(String message, ResponseCode responseCode) {
//        super(message);
//        this.errorCode = responseCode.getCode();
//        this.responseCode = responseCode;
//    }
//
//    public BaseException(String message, ResponseCode responseCode, Object detailMessage) {
//        super(message);
//        this.errorCode = responseCode.getCode();
//        this.detailMessage = detailMessage;
//        this.responseCode = responseCode;
//    }


}
