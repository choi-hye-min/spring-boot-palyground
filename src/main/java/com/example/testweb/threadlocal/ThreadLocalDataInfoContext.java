package com.example.testweb.threadlocal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalDataInfoContext implements AutoCloseable  {
    private final ThreadLocal<ReqResData> userContext = new ThreadLocal<>();

    public void setData(ReqResData reqResData) {
        userContext.set(reqResData);
    }

    public ReqResData getUserInfo() {
        return userContext.get();
    }

    public void clear() {
        userContext.remove();
    }

    @Override
    public void close() throws Exception {
        clear();
    }
}
