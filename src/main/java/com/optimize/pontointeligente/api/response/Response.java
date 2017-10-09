package com.optimize.pontointeligente.api.response;

import java.util.LinkedList;
import java.util.List;

public class Response<T> {

	private T data;
	private List<String> errors = new LinkedList<String>();

	public Response() {
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public void addError(String mensagem){
		this.getErrors().add(mensagem);
	}

}
