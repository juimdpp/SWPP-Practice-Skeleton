package com.example.cloudcomputingtutorial;

import com.google.gson.annotations.SerializedName;

public class CodeMessageIdResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("userId")
    private String userId;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getUserId() {return userId;}
}
