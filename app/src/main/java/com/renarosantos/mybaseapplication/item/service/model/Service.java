package com.renarosantos.mybaseapplication.item.service.model;

import android.support.annotation.NonNull;

import com.renarosantos.mybaseapplication.item.model.Item;
import com.renarosantos.mybaseapplication.item.service.entity.ServiceEntity;

import java.io.Serializable;

/**
 * Created by renarosantos on 18/05/16.
 */
public class Service extends Item implements Serializable {

    public Service(final String serviceId, final String productName, final Double price, final Double cost,
                   final String image, final boolean isDeleted, final boolean isEdited) {
        super(serviceId, productName, "description", image, price, cost,
                isDeleted, isEdited);
    }

    public static Service from(@NonNull ServiceEntity service) {
        return new Service(service.id(), service.name(), service.price(), service.cost(), service.image(),
                service.isDeleted(), service.isEdited());
    }
    
}
