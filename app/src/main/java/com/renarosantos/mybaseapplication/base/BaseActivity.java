package com.renarosantos.mybaseapplication.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.renarosantos.mybaseapplication.R;

/**
 * Created by renarosantos on 19/09/15.
 */

public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity {

    protected Presenter presenter;
    protected ActionBar mToolbar;

    @NonNull
    protected abstract Presenter createPresenter(@NonNull final Activity activity);

    @Override
    public void setContentView(@LayoutRes final int layoutResID) {
        super.setContentView(layoutResID);
        setUpToolbar();
    }

    @Override
    public void setContentView(final View view) {
        super.setContentView(view);
        setUpToolbar();
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter(this);
    }


    private void setUpToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        mToolbar = getSupportActionBar();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
     public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}