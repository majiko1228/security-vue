package cn.cyp.security.common;

import cn.cyp.security.exception.CustomException;
import cn.cyp.security.exception.CustomExceptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
@NoArgsConstructor//使用后创建一个无参构造函数
public class Result {

    public static final int CODE_SUCCESS=0;
    public static final int CODE_FAIL=1;
    public static final String MSG_SUCCESS="操作成功";
    public static final String MSG_FAIL="操作失败";


    private int code;
    private String message;
    private Object data;


    public static Result success(Object data){
        Result result = new Result();
        result.setCode(200);
        result.setMessage(MSG_SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result success(int code,String message, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result success(){
        Result result = new Result();
        result.setCode(200);
        result.setMessage(MSG_SUCCESS);
        return result;
    }

    public static Result fail(Object data){
        Result result = new Result();
        result.setCode(400);
        result.setMessage(MSG_FAIL);
        result.setData(data);
        return result;
    }
    public static Result fail(int code,String message,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result fail(){
        Result result = new Result();
        result.setCode(400);
        result.setMessage(MSG_FAIL);
        return result;
    }

    //请求出现异常时的响应数据封装
    public static Result error(CustomException e) {

        Result result = new Result();
        result.setMessage(MSG_FAIL);
        result.setCode(e.getCode());
        if(e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()){
            result.setMessage(e.getMessage());
        }else if(e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()){
            result.setMessage(e.getMessage() + ",系统出现异常");
        }else{
            result.setMessage("系统出现未知异常");
        }
        return result;
    }

}
