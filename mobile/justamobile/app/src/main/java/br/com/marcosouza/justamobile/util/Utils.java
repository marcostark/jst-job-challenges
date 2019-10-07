package br.com.marcosouza.justamobile.util;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void messageConnectFailed(Context context, Throwable e){
        Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
