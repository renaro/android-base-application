package com.renarosantos.mybaseapplication.contact.entity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.renarosantos.mybaseapplication.base.entity.AppDatabase;
import com.renarosantos.mybaseapplication.contact.model.Contact;

/**
 * Created by renarosantos on 18/05/16.
 */

@Table(database = AppDatabase.class)
public class LocalContactEntity extends BaseModel {

    @Column
    @PrimaryKey
    private String id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String photo;

    @Column
    Long creationTime;

    @Column
    Boolean edited;

    @Column
    Boolean deleted;


    public LocalContactEntity() {
    }

    private LocalContactEntity(@NonNull final String name, @NonNull final String id, @Nullable final String phones,
                               @NonNull final String photo, @NonNull final String email, final
                               long creationTime, boolean edited, boolean deleted) {
        this.name = name;
        this.id = id;
        this.phone = phones;
        this.photo = photo;
        this.email = email;
        this.creationTime = creationTime;
        this.edited = edited;
        this.deleted = deleted;
    }

    public static LocalContactEntity from(@NonNull final Contact contact) {
        return new LocalContactEntity(contact.name(), contact.getId(), contact.phone(), contact.photo(),
                contact.email(), contact.creationTime(), contact.isEdited(), contact.isDeleted());
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public boolean isEdited() {
        return edited;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setEdited(final boolean edited) {
        this.edited = edited;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }
}
