package com.ng.ngbaselib;

import androidx.lifecycle.Observer;

public abstract class SafeObserver<T> implements Observer<T> {
    @Override
    public final void onChanged(T t) {
        try {
            onSoftChanged(t);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    protected abstract void onSoftChanged(T t);
}
