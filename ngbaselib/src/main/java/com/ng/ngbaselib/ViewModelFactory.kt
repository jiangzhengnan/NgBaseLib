package com.ng.ngbaselib

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       /* val type = modelClass.constructors[0].parameterTypes
        if (type.isNotEmpty()) {
            val tClass = type[0]
            if (HomeRepository::class.java.isAssignableFrom(tClass)) {
                return modelClass.getConstructor(tClass).newInstance(Injection.HomeRepository())
            } else if (XXXRepository::class.java.isAssignableFrom(tClass)) {
                return modelClass.getConstructor(tClass).newInstance(Injection.XXXRepository())
            }
        }*/
        return modelClass.newInstance()
    }
}