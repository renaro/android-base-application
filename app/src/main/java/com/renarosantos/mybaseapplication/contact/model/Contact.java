package com.renarosantos.mybaseapplication.contact.model;

import android.support.annotation.NonNull;

import com.renarosantos.mybaseapplication.contact.entity.LocalContactEntity;
import com.renarosantos.mybaseapplication.remote.request.contacts.RemoteContact;

import java.io.Serializable;

/**
 * Created by renarosantos on 18/05/16.
 */
public class Contact implements Serializable, Comparable<Contact>{

    private static final int DELETED = 1;
    private String id;

    private String name;

    private String email;

    private String phone;

    private String photo;

    private boolean isEdited;

    private boolean isDeleted;

    private long creationTime;

    public Contact(final String id, final String name, final String email, final String phone, final String photo,
                   final long creationTime, final boolean isEdited, final boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
        this.creationTime = creationTime;
        this.isEdited = isEdited;
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String phone() {
        return phone;
    }

    public String photo() {
        return photo;
    }

    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public long creationTime() {
        return creationTime;
    }

    public static Contact from(@NonNull final LocalContactEntity entity) {
        return new Contact(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getPhoto(), 0,
                entity.isEdited(), entity.isDeleted());
    }

    //todo CREATION DATE is REALLY a date, yyyy-mm-dd
    public static Contact from(@NonNull final RemoteContact contact) {
        return new Contact(contact.id(), contact.name(), contact.email(), contact.phone(), contact.image(),
                0, false, contact.deleted() == DELETED);
    }

    public static Contact emptyContact() {
        return new Contact(null,"", "","", "",0,false,false);
    }

    @Override
    public int compareTo(final Contact another) {
        if(another == null){
            return 1;
        } else {
            return name.toUpperCase().compareTo(another.name().toUpperCase());
        }
    }
}
