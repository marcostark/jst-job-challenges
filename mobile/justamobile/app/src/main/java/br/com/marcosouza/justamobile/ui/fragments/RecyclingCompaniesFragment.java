package br.com.marcosouza.justamobile.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.ui.viewmodels.RecyclingCompaniesViewModel;

public class RecyclingCompaniesFragment extends Fragment {

    private RecyclingCompaniesViewModel recyclingCompaniesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recyclingCompaniesViewModel =
                ViewModelProviders.of(this).get(RecyclingCompaniesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recycling_companies, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        recyclingCompaniesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}