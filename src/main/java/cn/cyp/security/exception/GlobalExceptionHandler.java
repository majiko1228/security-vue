package cn.cyp.security.exception;


import cn.cyp.security.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */

// 用于定义@ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有@RequestMapping中。
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
//    @ExceptionHandler(value = AccessDeniedException.class)
//    public ResponseEntity errorHandler(Exception ex){
//        return new ResponseEntity(HttpStatus.FORBIDDEN);
//    }

    /**
     * 处理空指针的异常
     *
     * @param e
     * @return
     */
//    @ExceptionHandler(value = CustomException.class)
//    public Result exceptionHandler(CustomException e) {
//        System.out.println("全局捕捉异常");
//        return Result.fail(401,"登录已过期，请重新登录",null);
//    }

    @ExceptionHandler({CustomException.class})
    public Result TokenException(CustomException e) {
        return Result.fail(401,"登录已过期，请重新登录",null);
    }
}
