package com.upanda.updp.common.dto.response;

import java.io.Serializable;

import com.upanda.updp.common.enums.ResponseStatusEnum;

/**
 * 响应统一DTO
 * @author Front Ng
 * @date 2017年2月23日
 * @param <T> 返回的具体业务数据 
 */
public class ResponseDTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**   响应状态码   */
	private String status;
	
	/**   响应消息    */
	private String msg;
	
	/**  具体业务数据  */
	private T data;

	
	public ResponseDTO() {
		super();
		this.status = ResponseStatusEnum.OK.getValue();
	}
	
	/**
	 * ynbbc Server响应统一DTO 构造函数
	 * 正常返回业务数据情况下构造DTO
	 * @param data
	 */
	public ResponseDTO(T data){
		super();
		this.status = ResponseStatusEnum.OK.getValue();
		this.setData(data);
	}

	/**
	 * ynbbc Server响应统一DTO 构造函数
	 * <br>无响应返回业务数据情况下构造DTO
	 * @param status
	 * @param msg
	 */
	public ResponseDTO(String status, String msg){
		super();
		this.status = status;
		this.msg = msg;
		this.setData(null);
	}
	
	/**
	 * ynbbc Server响应统一DTO 构造函数
	 * @param status
	 * @param msg
	 * @param data
	 */
	public ResponseDTO(String status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.setData(data);
	}
	
	/**
	 * 创建仅含数据的ResponseDTO实例
	 * @param data 业务数据
	 * @return 仅含业务数据的ResponseDTO实例
	 * @author neos
	 */
	public static <T> ResponseDTO<T> newInstance(T data){
		return new ResponseDTO<T>(data);
	}
	
	/**
	 * 创建含状态码、信息、数据的ResponseDTO实例
	 * @param status 状态码
	 * @param msg 信息
	 * @param data 业务数据
	 * @return 含状态码、信息、数据的ResponseDTO实例
	 * @author neos
	 */
	public static <T> ResponseDTO<T> newInstance(String status, String msg, T data){
		return new ResponseDTO<T>(status, msg, data);
	}
	
	/**
	 * 获取响应状态码 
	 * @return 状态码 e.g. 200, 404, 500 ...
	 * @author ZhiFeng Wu
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 获取响应信息
	 * @return 信息 e.g. 参数异常
	 * @author ZhiFeng Wu
	 */
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	/**
	 * 获取具体业务数据
	 * @return 具体业务数据 e.g. List<Object>, Integer, Map ...
	 * @author ZhiFeng Wu
	 */
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	/**
	 * 返回该次调用是否正常
	 * 
	 * @return 
	 * @author neos
	 */
	public Boolean succ(){
		return ResponseStatusEnum.OK.getValue().equals(this.status);
	}

}
