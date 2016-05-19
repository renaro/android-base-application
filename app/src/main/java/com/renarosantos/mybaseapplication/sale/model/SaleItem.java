package com.renarosantos.mybaseapplication.sale.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.renarosantos.mybaseapplication.item.model.Item;
import com.renarosantos.mybaseapplication.item.product.model.Product;
import com.renarosantos.mybaseapplication.sale.entity.SaleItemEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by renarosantos on 18/05/16.
 */
public class SaleItem implements Serializable {

    public static final Integer TYPE_PRODUCT = 0;
    public static final Integer TYPE_SERVICE = 1;
    private final String mSaleId;
    private String mId;
    private String mItemId;
    private String mName;
    private String mPhotoUrl;
    private double mTotalPrice;
    private double mDiscount;
    private int mAmount;
    private double mUnityPrice;
    private final Integer mAmountAvailable;
    private final Boolean mEdited;
    private final Integer mType;

    public String photoUrl() {
        return mPhotoUrl;
    }

    public double unityPrice() {
        return mUnityPrice;
    }

    public void setUnityPrice(final float unityPrice) {
        mUnityPrice = unityPrice;
    }

    public String itemId() {
        return mItemId;
    }

    public Integer type() {
        return mType;
    }

    public void setDiscount(@Nullable final Double discount) {
        if (discount == null) {
            mDiscount = 0;
        } else if (discount > 100) {
            mDiscount = 100;
        } else {
            mDiscount = discount;
        }
    }

    public void setTotalPrice(final double totalPrice) {
        mTotalPrice = totalPrice;
    }

    public int amount() {
        return mAmount;
    }

    public double discount() {
        return mDiscount;
    }

    public void setAmount(@Nullable final Integer amount) {
        if (amount != null) {
            mAmount = amount;
        } else {
            mAmount = 1;
        }
    }

    @Nullable //returns null case Item instanceof Service
    public Integer amountAvailable() {
        return mAmountAvailable;
    }

    public Boolean edited() {
        return mEdited;
    }

    private SaleItem(final String id, final String saleId, final String itemId, final String name,
                     final double totalPrice, final double unityPrice, final String photoUrl,
                     @Nullable final Integer amountAvailable, @Nullable final Integer itemType,
                     @NonNull final Boolean edited) {
        mId = id;
        mSaleId = saleId;
        mItemId = itemId;
        mName = name;
        mTotalPrice = totalPrice;
        mUnityPrice = unityPrice;
        mPhotoUrl = photoUrl;
        mAmount = 1;
        mDiscount = 0;
        mType = itemType;
        mAmountAvailable = amountAvailable;
        mEdited = edited;
    }

    public static SaleItem from(@NonNull final Item item) {
        Integer amountAvailable = null;
        final Integer type;
        if (item instanceof Product) {
            final Product product = (Product) item;
            amountAvailable = product.amount();
            type = TYPE_PRODUCT;
        } else {
            type = TYPE_SERVICE;
        }
        return new SaleItem(null, null, item.id(), item.name(), item.price(), item.price(), item.imageURL(),
                amountAvailable, type, true);
    }

    public static SaleItem from(@NonNull final SaleItemEntity item) {
        return new SaleItem(item.id(), item.saleId(), item.itemId(), item.name(), item.totalPrice(), item.unitPrice(),
                item.photoUrl(), item.amount(), item.type(), item.edited());
    }

    public void computeSubTotal() {
        BigDecimal subTotal = new BigDecimal(mUnityPrice).multiply(new BigDecimal(mAmount));
        BigDecimal discount = new BigDecimal(mDiscount);

        //getting the discount
        final BigDecimal subtract = subTotal.subtract(subTotal.multiply(discount.divide(new BigDecimal(100))));
        String value = subtract.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        Double result = getDouble(value);
        if (result != null) {
            mTotalPrice = result;
        } else {
            mTotalPrice = mUnityPrice;
        }
    }

    public double totalPrice() {
        return mTotalPrice;
    }

    public String name() {
        return mName;
    }

    public String id() {
        return mId;
    }

    public String saleId() {
        return mSaleId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final SaleItem saleItem = (SaleItem) o;
        return !(mItemId != null ? !mItemId.equals(saleItem.mItemId) : saleItem.mItemId != null);
    }

    @Override
    public int hashCode() {
        return mItemId != null ? mItemId.hashCode() : 0;
    }

    public void addAmount(final int amount) {
        mAmount += amount;
        computeSubTotal();
    }

    @Nullable
    public static Double getDouble(String string) {
        Double result = null;
        try {
            result = Double.valueOf(string);
        } catch (NumberFormatException nfe) {
            // do nothing
        }
        return result;
    }

}

