package com.toread.core.interceptor.exception;

import java.util.List;

/**
 * @author 探路者
 * @since 2014年-08月-22日 17:50
 */
public class ResultNorm {
    private boolean success;
    private List<String> errorMsg;
    private Object result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(List<String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
