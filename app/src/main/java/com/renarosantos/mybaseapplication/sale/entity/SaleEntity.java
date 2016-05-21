package com.renarosantos.mybaseapplication.sale.entity;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.renarosantos.mybaseapplication.base.entity.AppDatabase;
import com.renarosantos.mybaseapplication.mechanism.DateHandler;
import com.renarosantos.mybaseapplication.sale.model.Cart;
import com.renarosantos.mybaseapplication.sale.model.Sale;

import java.util.UUID;

/**
 * Created by renarosantos on 18/05/16.
 */
@Table(database = AppDatabase.class)
public class SaleEntity extends BaseModel {

    private static final Boolean NOT_DELETED = false;
    private static final Boolean EDITED = true;

    @Column
    @PrimaryKey
    String id;

    @Column
    String customerId;

    @Column
    String customerName;

    @Column
    Double itemsTotal;

    @Column
    Integer paymentMethod;

    @Column
    Double interest;

    @Column
    Double entrance;

    @Column
    Double discount;

    @Column
    Integer times;

    @Column
    Double total;

    @Column
    String date;

    @Column
    Integer paymentState;

    @Column
    Boolean deleted;

    @Column
    Boolean edited;

    @Column
    Long lastModified;

    public SaleEntity() {
    }

    public SaleEntity(final String id, final String customerId, final String customerName, final double itemsTotal,
                      final int paymentMethod, final double interest, final double entrance, final double discount,
                      final int times, final double total, final String date, final int paymentState,
                      final Boolean deleted, final Boolean edited, final long lastModified) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.itemsTotal = itemsTotal;
        this.paymentMethod = paymentMethod;
        this.interest = interest;
        this.entrance = entrance;
        this.discount = discount;
        this.times = times;
        this.total = total;
        this.date = date;
        this.paymentState = paymentState;
        this.deleted = deleted;
        this.edited = edited;
        this.lastModified = lastModified;
    }

    public static SaleEntity from(@NonNull final Cart cart) {
        String id = UUID.randomUUID().toString();
        return new SaleEntity(id, cart.customerId(), cart.customerName(), cart.saleTotalItems(),
                cart.paymentMethod().key(), cart.interest(), cart.entrance(), cart.discount(), cart.times(),
                cart.totalValue(), DateHandler.date(), cart.paymentSaleState().key(), NOT_DELETED, EDITED,
                DateHandler.getTimeStamp());
    }

    public String id() {
        return id;
    }

    public int paymentMethod() {
        return paymentMethod;
    }

    public int times() {
        return times;
    }

    public double itemsTotal() {
        return itemsTotal;
    }

    public double interest() {
        return interest;
    }

    public double entrance() {
        return entrance;
    }

    public double discount() {
        return discount;
    }

    public double total() {
        return total;
    }

    public String date() {
        return date;
    }

    public int state() {
        return paymentState;
    }

    public Boolean deleted() {
        return deleted;
    }

    public long lastModified() {
        return lastModified;
    }

    public String customerId() {
        return customerId;
    }

    public String customerName() {
        return customerName;
    }

    public Boolean edited() {
        return edited;
    }

    public static SaleEntity from(final Sale sale) {
        //TODO WE DON'T HAVE ITEMS TOTAL HERE, SO WE ARE PASSING 'total' INSTEAD. Get this value later!
        return new SaleEntity(sale.id(), sale.customerId(), sale.customerName(), sale.total(),
                sale.paymentMethod().key(), sale.interest(), sale.entrance(), sale.discount(), sale.times(),
                sale.total(), sale.date(), sale.state().key(), sale.deleted(), false, 0);
    }


}