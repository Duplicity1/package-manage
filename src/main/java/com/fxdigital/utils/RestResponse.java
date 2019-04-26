package com.fxdigital.utils;

/**
 * @author:liqq
 * @Date:2018/11/15
 * @Description: REST请求的响应对象
 **/
public class RestResponse<T> {

    public enum CODE {SUCCESS, FAIL}

    private String code;
    private T data;
    private String reserve;

    public RestResponse() {

    }

//	public RestResponse(String code, T data){
//		this(code,data,null);
//	}
//
//	public RestResponse(String code, T data, String reserve){
//		this.code=code;
//		this.data=data;
//		this.reserve=reserve;
//	}

    /**
     * 响应标识码
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 响应数据。
     * 只有成功情况下才有数据，否则为null，而且只能为null
     *
     * @return
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    /**
     * 获取保留信息。
     * 保留信息是为了扩展 性考虑，比如有异常时，可以在该reserve中存异常标识码。
     *
     * @return
     */
    public String getReserve() {
        return reserve;
    }

    public RestResponse<T> setReserve(String reserve) {
        this.reserve = reserve;
        return this;
    }


    public static <T> RestResponse<T> success(T data) {
        RestResponse rep = new RestResponse();
        rep.setCode(CODE.SUCCESS.name());
        rep.setData(data);
        return rep;
    }

    public static <T> RestResponse<T> success() {
        RestResponse rep = new RestResponse();
        rep.setCode(CODE.SUCCESS.name());
        return rep;
    }

//	public static <T> RestResponse<List<T>> success(List<T> data){
//		RestResponse<List<T>> rep=new RestResponse<List<T>>();
//		rep.setCode(CODE.SUCCESS.name());
//		rep.setData(data);
//		return rep;
//	}

    public static <T> RestResponse<T> fail() {
        RestResponse rep = new RestResponse();
        rep.setCode(CODE.FAIL.name());
        return rep;
    }

    public static <T> RestResponse<T> fail(T data) {
        RestResponse rep = new RestResponse();
        rep.setCode(CODE.FAIL.name());
        rep.setData(data);
        return rep;
    }

    public static <T> RestResponse<T> fail(String reserve) {
        RestResponse rep = new RestResponse();
        rep.setCode(CODE.FAIL.name());
        rep.setReserve(reserve);
        return rep;
    }

}