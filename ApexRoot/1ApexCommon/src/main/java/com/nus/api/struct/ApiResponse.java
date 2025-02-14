package com.nus.api.struct;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Jan-2025<br>
 * @Time: 9:42:01 pm<br>
 * @Objective: <br>
 */

public class ApiResponse {	
	private ApiHead head;
	private Object body;// Can hold ApiErrorView or  ApiResponseData object	
	private ApiRequest meta;// Will hold meta data of api.
	/**
	 * @return the head
	 */
	public ApiHead getHead() {
		return head;
	}
	/**
	 * @param head the head to set
	 */
	public void setHead(ApiHead head) {
		this.head = head;
	}
	/**
	 * @return the body
	 */
	public Object getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(Object body) {
		this.body = body;
	}
	/**
	 * @return the meta
	 */
	public ApiRequest getMeta() {
		return meta;
	}
	/**
	 * @param meta the meta to set
	 */
	public void setMeta(ApiRequest meta) {
		this.meta = meta;
	}

	
	
	
}// End of ApiResponse
