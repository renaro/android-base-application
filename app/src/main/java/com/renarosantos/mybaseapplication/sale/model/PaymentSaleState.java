package com.renarosantos.mybaseapplication.sale.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.renarosantos.mybaseapplication.R;

/**
 * Created by renarosantos on 18/05/16.
 */
public enum PaymentSaleState {

    NOT_PAID(0, R.string.payment_status_not_paid),
    PARTIALLY_PAID(1, R.string.payment_status_partially_paid),
    PAID(2, R.string.payment_status_paid),
    ALL_STATE(3, R.string.payment_status_all);

    private int mKey;
    private int mStringResource;

    PaymentSaleState(final int key, final int stringResource) {
        mKey = key;
        mStringResource = stringResource;
    }

    public static String[] stringValues(@NonNull final Context context) {
        final PaymentSaleState[] states = PaymentSaleState.values();
        String[] result = new String[states.length];
        for (final PaymentSaleState state : states) {
            result[state.key()] = context.getString(state.stringResource());
        }
        return result;
    }

    public int key() {
        return mKey;
    }

    public int stringResource() {
        return mStringResource;
    }

}

