package com.renarosantos.mybaseapplication.sale.model;

import android.support.annotation.NonNull;

import com.renarosantos.mybaseapplication.remote.request.sales.RemoteSale;
import com.renarosantos.mybaseapplication.sale.entity.SaleEntity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by renarosantos on 18/05/16.
 */
public class Sale implements Serializable {

    private String mId;
    private String mCustomerId;
    private String mCustomerName;
    private final int mPaymentMethod;
    private final int mTimes;
    private final double mTotal;
    private final double mItemsTotal;
    private final double mEntrance;
    private final double mInterest;
    private final double mDiscount;
    private final String mDate;
    private final int mState;
    private final Boolean mIsDeleted;
    private final Boolean mIsEdited;
    private ArrayList<SaleParcel> mParcels;

    public Sale(final String id, final String customerId, final String customerName, final int paymentMethod,
                final int times, final double total, final double itemsTotal, final double entrance,
                final double interest, final double discount, final String date, final int state,
                final Boolean isDeleted, final Boolean isEdited) {
        mId = id;
        mCustomerId = customerId;
        mCustomerName = customerName;
        mPaymentMethod = paymentMethod;
        mTimes = times;
        mItemsTotal = itemsTotal;
        mTotal = total;
        mEntrance = entrance;
        mInterest = interest;
        mDiscount = discount;
        mDate = date;
        mState = state;
        mIsDeleted = isDeleted;
        mIsEdited = isEdited;
        mParcels = new ArrayList<>();
    }

    public static Sale from(@NonNull final SaleEntity entity) {
        return new Sale(entity.id(), entity.customerId(), entity.customerName(), entity.paymentMethod(), entity.times(),
                entity.total(), entity.itemsTotal(), entity.entrance(), entity.interest(),
                entity.discount(), entity.date(), entity.state(), entity.deleted(), entity.edited());
    }

    public String id() {
        return mId;
    }

    public int times() {
        return mTimes;
    }

    public double total() {
        return mTotal;
    }

    public double itemsTotal() {
        return mItemsTotal;
    }

    public String date() {
        return mDate;
    }

    public Boolean deleted() {
        return mIsDeleted;
    }

    public Boolean isEdited() {
        return mIsEdited;
    }

    public String customerId() {
        return mCustomerId;
    }

    public String customerName() {
        return mCustomerName;
    }

    public void setId(final String id) {
        mId = id;
    }

    public PaymentMethod paymentMethod() {

        return PaymentMethod.values()[mPaymentMethod];
    }

    public PaymentSaleState state() {
        return PaymentSaleState.values()[mState];
    }

    public void setParcels(final ArrayList<SaleParcel> result) {
        mParcels.clear();
        mParcels.addAll(result);
    }

    public ArrayList<SaleParcel> parcels() {
        return mParcels;
    }

    public double entrance() {
        return mEntrance;
    }

    public double interest() {
        return mInterest;
    }

    public double discount() {
        return mDiscount;
    }

    public static Sale from(final RemoteSale sale) {
        return new Sale(sale.id(), sale.idContact(), sale.contactName(), sale.paymentMethod(), sale.times(),
                sale.total(), sale.totalItems(), sale.entrance(), sale.interest(), sale.discount(), sale.date(),
                sale.paymentState(), sale.deleted() == 1, false);
    }
}
