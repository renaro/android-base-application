package com.renarosantos.mybaseapplication.item.service.entity;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.renarosantos.mybaseapplication.base.entity.AppDatabase;
import com.renarosantos.mybaseapplication.item.service.model.Service;

/**
 * Created by renarosantos on 18/05/16.
 */

@Table(database = AppDatabase.class)
public class ServiceEntity extends BaseModel {


    @Column
    @PrimaryKey
    String id;

    @Column
    String name;

    @Column
    String image;

    @Column
    Double price;

    @Column
    Double cost;

    @Column
    Long lastModified;

    @Column
    Boolean deleted;

    @Column
    Boolean edited;

    ServiceEntity() {
    }

    public ServiceEntity(final String id_, final String pName, final Double price, final Double cost,
                         final String image, final boolean isDeleted, final boolean isEdited) {
        id = id_;
        name = pName;
        this.price = price;
        this.cost = cost;
        this.image = image;
        deleted = isDeleted;
        edited = isEdited;
    }

    public static ServiceEntity from(@NonNull final Service service) {
        return new ServiceEntity(service.id(), service.name(),
                service.price(), service.cost(),
                service.imageURL(), service.isDeleted(), service.isEdited());
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

    public Double price() {
        return price;
    }

    public Double cost() {
        return cost;
    }

    public long lastModified() {
        return lastModified;
    }

    public void setLastModified(final long plastModified) {
        lastModified = plastModified;
    }

    public String image() {
        return image;
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

