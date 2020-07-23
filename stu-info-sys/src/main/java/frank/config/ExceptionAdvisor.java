package frank.config;

import frank.exception.BaseException;
import frank.base.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdvisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvisor.class);

    /**
     * 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleBindException(BindException e){
        LOGGER.debug("================================");
        LOGGER.debug("@Valid校验失败", e);
        String message = e.getBindingResult()
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining());
        return ResponseResult.error("400", "客户端请求错误："+message, e);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleConstraintViolationException(ConstraintViolationException e){
        LOGGER.debug("================================");
        LOGGER.debug("@RequestParam校验失败", e);
        String message = e.getConstraintViolations()
                            .stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining());
        return ResponseResult.badRequest(message, e);
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        LOGGER.debug("================================");
        LOGGER.debug("@RequestBody校验失败", e);
        String message = e.getBindingResult()
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining());
        return ResponseResult.badRequest(message, e);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        LOGGER.debug("================================");
        LOGGER.debug("Controller方法参数类型转换错误", e);
        String message = e.getName() + "格式错误";
        return ResponseResult.badRequest(message, e);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleMethodNotAllowedException(MethodNotAllowedException e){
        LOGGER.debug("================================");
        LOGGER.debug("Controller提供的http方法不支持", e);
        return ResponseResult.notAllowed(e.getHttpMethod(), e);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleNoHandlerFoundException(NoHandlerFoundException e){
        LOGGER.debug("================================");
        LOGGER.debug("找不到http请求处理器", e);
        return ResponseResult.notFound(e);
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleBaseException(BaseException e){
        LOGGER.debug("================================");
        LOGGER.debug("自定义异常", e);
        BaseException be = (BaseException) e;
        return ResponseResult.error(be.getCode(), be.getMessage(), be);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(Exception e){
        LOGGER.debug("================================");
        LOGGER.debug("其他未知异常", e);
        return ResponseResult.error(e);
    }

}
