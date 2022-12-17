package com.tutor.TutorSystem.POJOObjects;

public class RequestUpdater {
    int requestId;
    String status;

    public RequestUpdater() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RequestUpdater(int requestId, String status) {
        this.requestId = requestId;
        this.status = status;
    }
}
