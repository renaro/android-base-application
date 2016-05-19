package com.renarosantos.mybaseapplication.sale.model;

import android.support.annotation.NonNull;

import com.renarosantos.mybaseapplication.remote.request.sales.RemoteSalesParcel;
import com.renarosantos.mybaseapplication.sale.entity.ParcelEntity;

import java.io.Serializable;

/**
 * Created by renarosantos on 18/05/16.
 */
public class SaleParcel implements Serializable {

    private final String mId;
    private final String mSaleId;
    private final Double mTotal;
    private final String mDueDate;
    private final Integer mOrder;
    private final PaymentSaleState mPaymentState;
    private final Boolean mIsEdited;

    public SaleParcel(final String id, final String saleId, final Double total, final String dueDate,
                      final Integer order, final PaymentSaleState paymentState, @NonNull final Boolean edited) {
        mId = id;
        mSaleId = saleId;
        mTotal = total;
        mDueDate = dueDate;
        mOrder = order;
        mPaymentState = paymentState;
        mIsEdited = edited;
    }

    public static SaleParcel from(@NonNull final ParcelEntity entity) {
        //FIXME CREATE LOGIC TO PARCEL DUE DATE
        return new SaleParcel(entity.id(), entity.saleId(), entity.total(), String.valueOf(entity.dueDate()),
                entity.order(), PaymentSaleState.values()[entity.state()], entity.edited());
    }

    public Double total() {
        return mTotal;
    }

    public PaymentSaleState paymentState() {
        return mPaymentState;
    }

    public static SaleParcel from(final RemoteSalesParcel parcel) {
        final Integer state = parcel.state();
        final PaymentSaleState paymentSaleState = PaymentSaleState.values()[state];
        return new SaleParcel(parcel.id(), parcel.saleId(), parcel.total(), parcel.dueDate(), parcel.order(),
                paymentSaleState, false);
    }

    public String id() {
        return mId;
    }

    public String saleId() {
        return mSaleId;
    }

    public String dueDate() {
        return mDueDate;
    }

    public Integer order() {
        return mOrder;
    }

    public Boolean isEdited() {
        return mIsEdited;
    }
}
