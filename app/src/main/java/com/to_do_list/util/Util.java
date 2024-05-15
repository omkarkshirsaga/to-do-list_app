package com.to_do_list.util;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Util {
    public static void openAlertDialogue(Context context, String title, String message, OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    listener.onClick();
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    public interface OnClickListener {
        public void onClick();
    }
}
