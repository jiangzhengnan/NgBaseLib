package com.ng.ngbaselib.utils

import android.content.Context
import android.widget.Toast

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/22
 */
object ToastUtils {

    fun showShort(context: Context?,str: String?)
    {
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
    }
}