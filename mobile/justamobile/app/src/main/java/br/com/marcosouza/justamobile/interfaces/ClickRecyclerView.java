package br.com.marcosouza.justamobile.interfaces;

import android.view.View;

public interface ClickRecyclerView {
    void onCustomClick(View view, int position, boolean isLongClick);
}
