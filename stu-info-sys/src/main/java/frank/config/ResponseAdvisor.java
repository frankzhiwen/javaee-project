package frank.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import frank.base.ResponseResult;
import frank.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

//@ControllerAdvice
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseAdvisor.class);

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 拦截并调用beforeBodyWrite()
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        LOGGER.debug("beforeBodyWrite================================");
        LOGGER.debug(body.getClass().getName()+": "+body);
        if(body instanceof ResponseResult)
            return body;
        ResponseResult result = ResponseResult.ok(body);
        if(!(body instanceof String))
            return result;
        try {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new SystemException("000", "JSON序列化失败："+body);
        }
    }
}
