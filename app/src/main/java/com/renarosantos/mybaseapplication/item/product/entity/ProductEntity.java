package com.renarosantos.mybaseapplication.item.product.entity;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.renarosantos.mybaseapplication.base.entity.AppDatabase;
import com.renarosantos.mybaseapplication.item.product.model.Product;

/**
 * Created by renarosantos on 18/05/16.
 */
@Table(database = AppDatabase.class)
public class ProductEntity extends BaseModel {

    @Column
    @PrimaryKey
    String id;

    @Column
    String name;

    @Column
    String imageName;

    @Column
    Integer quantity;

    @Column
    Double sellingPrice;

    @Column
    Double buyingPrice;

    @Column
    Long lastModified;

    @Column
    Boolean deleted;

    @Column
    Boolean edited;

    ProductEntity() {
    }

    public ProductEntity(final String id_, final String pName, final Integer pQuantity, final double pSellingPrice,
                         final double pBuyingPrice, final String image, final boolean isDeleted, final boolean isEdited) {
        id = id_;
        name = pName;
        quantity = pQuantity;
        sellingPrice = pSellingPrice;
        buyingPrice = pBuyingPrice;
        imageName = image;
        deleted = isDeleted;
        edited = isEdited;
    }

    public static ProductEntity from(@NonNull final Product product) {
        return new ProductEntity(product.id(), product.name(),
                product.amount(), product.price(), product.cost(), product.imageURL(),
                product.isDeleted(), product.isEdited());
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Integer amount() {
        return quantity;
    }

    public Double price() {
        return sellingPrice;
    }

    public Double buyingPrice() {
        return buyingPrice;
    }

    public long lastModified() {
        return lastModified;
    }

    public void setLastModified(final long plastModified) {
        lastModified = plastModified;
    }

    public String image() {
        return imageName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

    public void setEdited(final boolean edited) {
        this.edited = edited;
    }
}

