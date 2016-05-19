package com.renarosantos.mybaseapplication.sale.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.renarosantos.mybaseapplication.base.entity.AppDatabase;
import com.renarosantos.mybaseapplication.sale.model.SaleParcel;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by renarosantos on 18/05/16.
 */
@Table(database = AppDatabase.class)
public class ParcelEntity extends BaseModel {

    private static final String DEFAULT_TIME_ZONE_STRING = "GMT";

    @Column
    @PrimaryKey
    String id;

    @Column
    String saleId;

    @Column
    Double total;

    @Column
    Long dueDate;

    @Column
    Integer order;

    @Column
    Integer state;

    @Column
    Boolean edited;

    @Column
    Long lastModified;

    public ParcelEntity(final String id, final String saleId, final double total, final long dueDate, final int order,
                        final int state, final long lastModified, final Boolean edited) {
        this.id = id;
        this.saleId = saleId;
        this.total = total;
        this.dueDate = dueDate;
        this.order = order;
        this.state = state;
        this.lastModified = lastModified;
        this.edited = edited;
    }

    public ParcelEntity() {
    }

    public String id() {
        return id;
    }

    public String saleId() {
        return saleId;
    }

    public double total() {
        return total;
    }

    public long dueDate() {
        return dueDate;
    }

    public int order() {
        return order;
    }

    public int state() {
        return state;
    }

    public long lastModified() {
        return lastModified;
    }

    public Boolean edited() {
        return edited;
    }

    public static ParcelEntity from(final SaleParcel parcel) {
        //FIXME CREATE LOGIC TO PARCEL DUE DATE
        return new ParcelEntity(parcel.id(), parcel.saleId(), parcel.total(), getTimeStamp(), parcel.order(),
                parcel.paymentState().key(), getTimeStamp(), false);
    }

    public static Long getTimeStamp() {
        return Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIME_ZONE_STRING)).getTimeInMillis() / 1000;
    }

}
