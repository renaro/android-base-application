package com.renarosantos.mybaseapplication.sale.entity;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.renarosantos.mybaseapplication.base.entity.AppDatabase;
import com.renarosantos.mybaseapplication.sale.model.SaleItem;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by renarosantos on 18/05/16.
 */
@Table(database = AppDatabase.class)
public class SaleItemEntity extends BaseModel {

    private static final String DEFAULT_TIME_ZONE_STRING = "GMT";

    @Column
    @PrimaryKey
    String id;

    @Column
    String saleId;

    @Column
    @PrimaryKey
    String itemId;

    @Column
    String name;

    @Column
    Integer type;

    @Column
    String photoUrl;

    @Column
    Double totalPrice;

    @Column
    Double discount;

    @Column
    Integer amount;

    @Column
    Double unitPrice;

    @Column
    Long lastModified;

    @Column
    Boolean edited;

    SaleItemEntity() {
    }

    public SaleItemEntity(final String id, final String saleId, final String itemId, final String name,
                          final Integer type, final String photoUrl, final double totalPrice, final double discount,
                          final int amount, final double unitPrice, final long lastModified, final Boolean edited) {
        this.id = id;
        this.saleId = saleId;
        this.itemId = itemId;
        this.name = name;
        this.type = type;
        this.photoUrl = photoUrl;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.lastModified = lastModified;
        this.edited = edited;
    }

    public String id() {
        return id;
    }

    public String saleId() {
        return saleId;
    }

    public void setSaleId(final String saleId) {
        this.saleId = saleId;
    }

    public Boolean edited() {
        return edited;
    }

    public static SaleItemEntity from(final SaleItem item) {
        return new SaleItemEntity(UUID.randomUUID().toString(), item.saleId(), item.itemId(), item.name(), item.type(),
                item.photoUrl(), item.totalPrice(), item.discount(), item.amount(), item.unityPrice(),
                getTimeStamp(), item.edited());
    }

    public Integer type() {
        return type;
    }

    public String itemId() {
        return itemId;
    }

    public String name() {
        return name;
    }

    public String photoUrl() {
        return photoUrl;
    }

    public double totalPrice() {
        return totalPrice;
    }

    public double discount() {
        return discount;
    }

    public int amount() {
        return amount;
    }

    public double unitPrice() {
        return unitPrice;
    }

    @NonNull
    public static Long getTimeStamp() {
        return Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIME_ZONE_STRING)).getTimeInMillis() / 1000;
    }
}

