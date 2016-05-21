package com.renarosantos.mybaseapplication.item.model;

import java.io.Serializable;

/**
 * Created by renarosantos on 18/05/16.
 */
public class Item implements Serializable {

    protected static final int DELETED = 1;

    protected String mId;
    protected String mName;
    protected String mDescription;
    protected String mImage;
    protected Double mPrice;
    protected Double mCost;
    protected Boolean mIsEdited;
    protected Boolean mIsDeleted;

    public String id() {
        return mId;
    }

    public void setId(final String id) {
        mId = id;
    }

    public String name() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public String description() {
        return mDescription;
    }

    public void setDescription(final String description) {
        mDescription = description;
    }

    public String imageURL() {
        return mImage;
    }

    public void setImage(final String image) {
        mImage = image;
    }

    public Double price() {
        return mPrice;
    }

    public void setPrice(final Double price) {
        mPrice = price;
    }

    public Double cost() {
        return mCost;
    }

    public void setCost(final Double cost) {
        mCost = cost;
    }

    public Boolean isEdited() {
        return mIsEdited;
    }

    public void setIsEdited(final Boolean isEdited) {
        mIsEdited = isEdited;
    }

    public Boolean isDeleted() {
        return mIsDeleted;
    }

    public void setIsDeleted(final Boolean isDeleted) {
        mIsDeleted = isDeleted;
    }

    public Item(final String id, final String name, final String description, final String image,
                final Double price, final Double cost, final Boolean isDeleted, Boolean isEdited) {
        mId = id;
        mName = name;
        mDescription = description;
        mImage = image;
        mPrice = price;
        mCost = cost;
        mIsDeleted = isDeleted;
        mIsEdited = isEdited;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Item item = (Item) o;

        if (mId != null ? !mId.equals(item.mId) : item.mId != null) {
            return false;
        }
        if (mName != null ? !mName.equals(item.mName) : item.mName != null) {
            return false;
        }
        if (mPrice != null ? !mPrice.equals(item.mPrice) : item.mPrice != null) {
            return false;
        }
        return !(mCost != null ? !mCost.equals(item.mCost) : item.mCost != null);
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mPrice != null ? mPrice.hashCode() : 0);
        result = 31 * result + (mCost != null ? mCost.hashCode() : 0);
        return result;
    }

}

