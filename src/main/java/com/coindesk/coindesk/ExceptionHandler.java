package com.coindesk.coindesk;

import com.coindesk.coindesk.vo.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    /** springboot validation 約束驗證 */
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ( (FieldError) error ).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        logger.error("ExceptionHandlerForValidation exceptions :" + e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMsg(ResponseCode.PARAM_ISSUE.getValue(), errors.values().toString()));
    }

    /** springboot validation 約束驗證 */
    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingRequestParamException(MissingServletRequestParameterException e) {
        String message = "[" + e.getParameterName() + "屬性不可為null]";
        logger.error("handleMissingRequestParamException exceptions : " + message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMsg(ResponseCode.PARAM_ISSUE.getValue(), message));
    }

    /** Optional 元素找不到 */
    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        logger.error("NoSuchElementException : " + e );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMsg(ResponseCode.DATA_ISSUE.getValue(), ResponseCode.DATA_ISSUE.getMessage()));
    }

    /** Http Method 例外 */
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("HttpRequestMethodNotSupportedException : " + e );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMsg(ResponseCode.PARAM_ISSUE.getValue(), ResponseCode.PARAM_ISSUE.getMessage()));
    }

    /** 參數型別錯誤 */
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("handlIllegalArgumentException : " + e );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMsg(ResponseCode.PARAM_ISSUE.getValue(), ResponseCode.PARAM_ISSUE.getMessage()));
    }

    /** 參數型別錯誤 */
    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException e) {
        logger.error("handleBindException : " + e );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMsg(ResponseCode.PARAM_ISSUE.getValue(), ResponseCode.PARAM_ISSUE.getMessage()));
    }

    /** 未知例外 */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {
        logger.error("Exception : " + e );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMsg(ResponseCode.INTERNAL_ERROR.getValue(), e.getMessage()));
    }

}
