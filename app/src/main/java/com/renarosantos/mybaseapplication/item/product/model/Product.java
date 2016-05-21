package com.renarosantos.mybaseapplication.item.product.model;

import android.support.annotation.NonNull;

import com.renarosantos.mybaseapplication.item.model.Item;
import com.renarosantos.mybaseapplication.item.product.entity.ProductEntity;

import java.io.Serializable;

/**
 * Created by renarosantos on 18/05/16.
 */
public class Product extends Item implements Serializable {

    private Integer mProductAmount;

    public Product(final String id, final String name, final Double price, final Integer amount,
                   final Double cost, final String image, final Boolean isDeleted, final Boolean isEdited) {
        super(id, name, "", image, price, cost, isDeleted, isEdited);
        mProductAmount = amount;
    }


    public static Product from(@NonNull ProductEntity product) {
        return new Product(
                product.id(),
                product.name(),
                product.price(),
                product.amount(),
                product.buyingPrice(),
                product.image(),
                product.isDeleted(), product.isEdited());
    }


    public Integer amount() {
        return mProductAmount;
    }

    public void addToStock(final Integer amount) {
        mProductAmount += amount;
    }

    public void removeFromStock(final Integer amount) {
        mProductAmount -= amount;
        mProductAmount = mProductAmount > 0 ? mProductAmount : 0;
    }

}
