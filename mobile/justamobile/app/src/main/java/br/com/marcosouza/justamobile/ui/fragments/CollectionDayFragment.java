package br.com.marcosouza.justamobile.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.ui.viewmodels.CollectionDayViewModel;

public class CollectionDayFragment extends Fragment {

    private CollectionDayViewModel collectionDayViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        collectionDayViewModel =
                ViewModelProviders.of(this).get(CollectionDayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_collection_day, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        collectionDayViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}