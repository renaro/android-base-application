package com.renarosantos.mybaseapplication.remote.request;

import android.support.v4.util.ArrayMap;

import com.renarosantos.mybaseapplication.item.product.model.Product;


/**
 * Created by renarosantos on 26/09/15.
 */
public class ProductsRequest {

    ArrayMap<String, String> mParameters;
    private final String NOME = "nome";
    private final String DESCRICAO = "descricao";
    private final String PRECO_VENDA = "preco_venda";
    private final String QUANTIDADE = "quantidade";
    private final String PRECO_COMPRA = "preco_compra";


    public ProductsRequest(Product product) {
        mParameters = new ArrayMap<>();
        mParameters.put(NOME, product.name());
        mParameters.put(DESCRICAO, product.description());
        mParameters.put(PRECO_VENDA, "" + product.price());
        mParameters.put(QUANTIDADE, "" + product.amount());
        mParameters.put(PRECO_COMPRA, "" + product.cost());
    }

    public ArrayMap<String, String> parameters() {
        return mParameters;
    }
}
