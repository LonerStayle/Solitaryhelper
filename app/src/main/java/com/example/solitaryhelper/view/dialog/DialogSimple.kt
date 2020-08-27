package com.example.solitaryhelper.view.dialog

import android.app.AlertDialog
import android.content.Context

object DialogSimple {
    fun show(
        context: Context,
        dialogTitle: String,
        dialogMessage: String,
        positiveText: String,
        onPositive: () -> Unit = {},
        negativeText: String,
        onNegative: (() -> Unit) = {}
    ) =  AlertDialog.Builder(context)
            .setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setPositiveButton(positiveText) { _, _ ->
                onPositive()
            }
        .setNegativeButton(negativeText) { _, _ ->

            onNegative()
        }
        .create()
            .show()
    }
