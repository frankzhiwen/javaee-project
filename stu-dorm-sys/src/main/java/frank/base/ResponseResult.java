package frank.base;

import com.github.pagehelper.Page;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
@Setter
public class ResponseResult {

    private boolean success;

    private String code;

    private String message;

    private Long total;

    private Object data;

    private String stackTrace;

    private ResponseResult(){}

    public static ResponseResult ok(Object data){
        ResponseResult result = new ResponseResult();
        result.setSuccess(true);
        result.setCode("200");
        result.setMessage("操作成功");
        if(data instanceof Page){
            Page page = (Page) data;
            result.total = page.getTotal();
        }
        result.setData(data);
        return result;
    }

    public static ResponseResult error(){
        return error(null);
    }

    public static ResponseResult error(Throwable t){
        return error("000000", "未知错误", t);
    }

    public static ResponseResult error(String code, String message){
        return error(code, message, null);
    }

    public static ResponseResult error(String code, String message, Throwable t){
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setMessage(message);
        if(t != null){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            result.setStackTrace(sw.toString());
        }
        return result;
    }

    public static ResponseResult badRequest(String message){
        return badRequest(message, null);
    }

    public static ResponseResult badRequest(String message, Throwable t){
        return error("400", "客户端异常：" + message, t);
    }

    public static ResponseResult notAllowed(String message){
        return notAllowed(message, null);
    }

    public static ResponseResult notAllowed(String message, Throwable t){
        return error("405", "请求方法不支持：" + message, t);
    }

    public static ResponseResult notFound(Throwable t){
        return error("404", "找不到资源", t);
    }
}
