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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.RecyclingCompany;
import br.com.marcosouza.justamobile.model.RecyclingCompanyResponse;
import br.com.marcosouza.justamobile.ui.adapter.RecyclingCompanyAdapter;
import br.com.marcosouza.justamobile.ui.viewmodels.RecyclingCompaniesViewModel;

public class RecyclingCompaniesFragment extends Fragment {

    private ArrayList<RecyclingCompany> recyclingCompanies = new ArrayList <> ();
    private RecyclingCompanyAdapter recyclingCompanyAdapter;
    private RecyclerView recyclerView;

    private RecyclingCompaniesViewModel recyclingCompaniesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        recyclingCompaniesViewModel =
                ViewModelProviders.of(this).get(RecyclingCompaniesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recycling_companies, container, false);
        recyclerView = root.findViewById(R.id.rv_companies);
        recyclingCompaniesViewModel =
                ViewModelProviders.of(this).get(RecyclingCompaniesViewModel.class);
        recyclingCompaniesViewModel.init();
        recyclingCompaniesViewModel.getCompaniesRepository().observe(this, new Observer<RecyclingCompanyResponse>() {
            @Override
            public void onChanged(RecyclingCompanyResponse recyclingCompany) {
                List<RecyclingCompany> newsArticles = recyclingCompany.getResults();
                recyclingCompanies.addAll(newsArticles);
                recyclingCompanyAdapter.notifyDataSetChanged();
            }
        });

        loadRecyclerView();
        return root;
    }
    private void loadRecyclerView() {
        if (recyclingCompanyAdapter == null) {
            recyclingCompanyAdapter = new RecyclingCompanyAdapter(getActivity(), recyclingCompanies);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(recyclingCompanyAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            recyclingCompanyAdapter.notifyDataSetChanged();
        }
    }
}