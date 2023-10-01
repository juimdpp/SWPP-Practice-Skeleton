package com.example.cloudcomputingtutorial;

import com.google.gson.annotations.SerializedName;

public class NameData {
    @SerializedName("userName")
    String userName;

    public NameData(String userName){
        this.userName = userName;
    }
}
