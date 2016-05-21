package com.renarosantos.mybaseapplication.sale.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by renarosantos on 18/05/16.
 */
public class Cart implements Serializable {

    private String mCustomerId;
    private String mCustomerName;
    private List<SaleItem> mItems;
    private double mSaleItemsTotal;
    private PaymentMethod mPaymentMethod;
    private PaymentSaleState mPaymentSaleState;
    private double mInterest;
    private double mEntrance;
    private double mDiscount;
    private int mTimes;
    private double mTotalValue;
    private double mPartialValue;
    private boolean isPaid;


    public Cart() {
        mItems = new ArrayList<>();
        mPaymentMethod = PaymentMethod.A_VISTA;
        mPaymentSaleState = PaymentSaleState.NOT_PAID;
        mInterest = 0;
        mEntrance = 0;
        mDiscount = 0;
        mTimes = 1;
        mSaleItemsTotal = 0;
        mCustomerId = "";
        mCustomerName= "";
        isPaid = false;
    }



    public double saleTotalItems() {
        return mSaleItemsTotal;
    }


    public void addItem(final SaleItem saleItem) {
        if (!mItems.contains(saleItem)) {
            mItems.add(saleItem);
        } else {
            final SaleItem currentItem = mItems.get(mItems.indexOf(saleItem));
            currentItem.addAmount(saleItem.amount());
        }
        computeTotalItemsValue();
    }

    private void computeTotalItemsValue() {
        BigDecimal total = new BigDecimal(0);
        for (SaleItem item : mItems) {
            total = total.add(new BigDecimal(item.totalPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        mSaleItemsTotal = total.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void removeItem(final SaleItem item) {
        mItems.remove(item);
        computeTotalItemsValue();
    }

    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        mPaymentMethod = paymentMethod;
    }

    public void setInterest(final String interest) {
        try {
            mInterest = Double.valueOf(interest) / 100;
        } catch (NumberFormatException e) {
            mInterest = 0;
        }
    }

    public void setEntrance(final String entrance) {
        try {
            mEntrance = Double.valueOf(entrance);
        } catch (NumberFormatException e) {
            mEntrance = 0;
        }
    }

    public void setTimes(final String times) {
        try {
            mTimes = Integer.valueOf(times);
        } catch (NumberFormatException e) {
            mTimes = 1;
        }
    }

    public void setDiscount(final String discount) {
        try {
            mDiscount = Double.valueOf(discount);

        } catch (NumberFormatException e) {
            mDiscount = 0;
        }
    }

    public void computePartValue() {
        if (mPaymentMethod == PaymentMethod.A_PRAZO) {
            BigDecimal interestValue;
            BigDecimal discountValue = new BigDecimal(mDiscount);
            BigDecimal totalItemsValue = new BigDecimal(mSaleItemsTotal);
            interestValue = new BigDecimal(mInterest).add(new BigDecimal(1));
            BigDecimal entranceValue = new BigDecimal(mEntrance);
            BigDecimal times = new BigDecimal(mTimes);
            BigDecimal subTotalWithInterest = totalItemsValue.subtract(entranceValue).subtract(discountValue).multiply(
                    interestValue);
            mPartialValue = subTotalWithInterest.divide(times, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            mTotalValue = subTotalWithInterest.add(entranceValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else if(mPaymentMethod == PaymentMethod.A_VISTA) {
            mTotalValue = mSaleItemsTotal;
        }
    }

    public double discount() {
        return mDiscount;
    }

    public PaymentMethod paymentMethod() {
        return mPaymentMethod;
    }

    public double interest() {
        return mInterest;
    }

    public double entrance() {
        return mEntrance;
    }

    public int times() {
        return mTimes;
    }

    public double totalValue() {
        return mTotalValue;
    }

    public double partialValue() {
        return mPartialValue;
    }

    public PaymentSaleState paymentSaleState() {
        return mPaymentSaleState;
    }

    public void setPaymentSaleState(final PaymentSaleState paymentSaleState) {
        mPaymentSaleState = paymentSaleState;
    }

    public void setItem(final SaleItem item) {
        computeTotalItemsValue();
    }

    public SaleItem getSaleItem(final int position) {
        return mItems.get(position);
    }

    public List<SaleItem> getItems() {
        return mItems;
    }

    public String customerId() {
        return mCustomerId;
    }

    public String customerName() {
        return mCustomerName;
    }

    public void setCustomerId(final String customerId) {
        mCustomerId = customerId;
    }

    public void setCustomerName(final String customerName) {
        mCustomerName = customerName;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setIsPaid(final boolean isPaid) {
        this.isPaid = isPaid;
    }

    public void setPartialValue(final double partialValue) {
        mPartialValue = partialValue;
    }
}

