package school_management_sys.exception;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import school_management_sys.dto.ResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestServiceAlertException.class)
    public ResponseEntity<ResponseDTO> handleBadRequestServiceAlertException (final BadRequestServiceAlertException exception, WebRequest webRequest) {
        ResponseDTO responseDTO = new ResponseDTO();
        exception.printStackTrace();
        responseDTO.setMessage(exception.getMessage());
        responseDTO.setStatusCode(400);
        responseDTO.setData(null);
        return ResponseEntity.ok().body(responseDTO);
    }
}
