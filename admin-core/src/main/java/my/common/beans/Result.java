package my.common.beans;

public class Result<T> {
	
	public static final Result SUCCESS = new Result();
	
	public String result = "true";
	public String message = "操作成功";
	public T data = null;
	
	public Result() {}
	
	public Result(T data) {
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public Result setResult(String result) {
		this.result = result;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	public Result setData(T data) {
		this.data = data;
		return this;
	}
	
	public Result setError(String message) {
		this.result = "false";
		this.message = message;
		return this;
	}
	
	public static Result error(String message) {
		Result result = new Result();
		return result.setError(message);
	}
	
	public static Result success(String message) {
		Result result = new Result();
		return result.setMessage(message);
	}
	
	public static Result success(String message, Object data) {
		Result result = new Result();
		return result.setMessage(message).setData(data);
	}
	
	public boolean isError() {
		return "false".equals(this.result);
	}
}
