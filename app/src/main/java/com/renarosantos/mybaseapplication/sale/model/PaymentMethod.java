package com.renarosantos.mybaseapplication.sale.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.renarosantos.mybaseapplication.R;

/**
 * Created by renarosantos on 18/05/16.
 */
public enum PaymentMethod {

    A_VISTA(0, R.string.pagamento_at_sight),
    A_PRAZO(1, R.string.pagamento_divided);

    private int mKey;
    private int mStringResource;

    PaymentMethod(final int key, final int stringResource) {
        mKey = key;
        mStringResource = stringResource;
    }

    public static String[] stringValues(@NonNull final Context context) {
        final PaymentMethod[] payments = PaymentMethod.values();
        String[] result = new String[payments.length];
        for (final PaymentMethod paymentMethod : payments) {
            result[paymentMethod.key()] = context.getString(paymentMethod.stringResource());
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