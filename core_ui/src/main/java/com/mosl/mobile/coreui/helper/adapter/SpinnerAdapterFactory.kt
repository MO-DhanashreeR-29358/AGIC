package com.mosl.mobile.coreui.helper.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.mosl.mobile.coreui.R

object SpinnerAdapterFactory {

    @JvmStatic
    @JvmOverloads
    fun <T> getSpinnerAdapter(
        context: Context,
        data: List<T> = mutableListOf()
    ): ArrayAdapter<T> {
        val adapter = ArrayAdapter<T>(context, R.layout.row_spinner_simple, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

}