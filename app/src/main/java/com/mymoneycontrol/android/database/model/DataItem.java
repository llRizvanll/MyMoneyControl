package com.mymoneycontrol.android.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jrizvan on 1/28/17.
 */

public class DataItem extends RealmObject {
    Integer amountType;

    @PrimaryKey
    Integer id;

    Double totalAmount;

    Long updatedTime;

    Integer quantity;

    public Integer getAmountType() {
        return amountType;
    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }
}
