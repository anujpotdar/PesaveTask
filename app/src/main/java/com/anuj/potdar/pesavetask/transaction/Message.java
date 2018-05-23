
package com.anuj.potdar.pesavetask.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("transactionName")
    @Expose
    private String transactionName;
    @SerializedName("transactionAmount")
    @Expose
    private String transactionAmount;
    @SerializedName("chillrAmount")
    @Expose
    private String chillrAmount;
    @SerializedName("transactionTime")
    @Expose
    private String transactionTime;

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getChillrAmount() {
        return chillrAmount;
    }

    public void setChillrAmount(String chillrAmount) {
        this.chillrAmount = chillrAmount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

}
