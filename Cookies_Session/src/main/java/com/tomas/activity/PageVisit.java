package com.tomas.activity;

import java.net.InetAddress;

/**
 * Created by Tomas on 18/05/2016.
 */
public class PageVisit
{
    private long enteredTimesTamp;
    private Long leftTimesTamp;
    private String request;
    private InetAddress ipAddress;

    public long getEnteredTimesTamp() {
        return enteredTimesTamp;
    }

    public void setEnteredTimesTamp(long enteredTimesTamp) {
        this.enteredTimesTamp = enteredTimesTamp;
    }

    public Long getLeftTimesTamp() {
        return leftTimesTamp;
    }

    public void setLeftTimesTamp(Long leftTimesTamp) {
        this.leftTimesTamp = leftTimesTamp;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
}
