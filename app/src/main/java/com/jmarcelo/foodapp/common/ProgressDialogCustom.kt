package com.jmarcelo.foodapp.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import com.jmarcelo.foodapp.R

class ProgressDialogCustom( context: Context) {

    private val progressDialog = Dialog(context)

    fun showLoadingDialog():Dialog{
        progressDialog.let {
            it.show()
            it.window?.setBackgroundDrawable(Color.WHITE.toDrawable())
            it.setContentView(R.layout.custom_dialog)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }

    fun hideLoadingDialog(){
        progressDialog.let{
            if (it.isShowing) it.cancel()
        }
    }
}