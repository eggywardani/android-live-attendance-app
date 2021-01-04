package com.eggy.liveattendanceapp.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.eggy.liveattendanceapp.R
import org.jetbrains.anko.AlertDialogBuilder

object MyDialog {
    private var dialogBuilder:AlertDialog? = null
    fun dynamicDialog(context: Context?, title:String, message:String){
        dialogBuilder = context?.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .show()
        }
    }

    fun showProgressDialog(context: Context?){
        val dialogView = LayoutInflater.from(context).inflate(R.layout.layout_progress, null)
        dialogBuilder = context?.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
                .setCancelable(false)
                .create()
        }

        dialogBuilder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogBuilder?.show()

    }

    fun hideDialog(){
        if (dialogBuilder != null){
            dialogBuilder?.dismiss()
        }
    }
}