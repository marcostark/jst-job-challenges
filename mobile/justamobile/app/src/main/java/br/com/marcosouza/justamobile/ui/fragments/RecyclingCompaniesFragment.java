package br.com.marcosouza.justamobile.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.ClickRecyclerView;
import br.com.marcosouza.justamobile.model.RecyclingCompany;
import br.com.marcosouza.justamobile.model.RecyclingCompanyResponse;
import br.com.marcosouza.justamobile.ui.activity.CompanyDetailActivity;
import br.com.marcosouza.justamobile.ui.adapter.RecyclingCompanyAdapter;
import br.com.marcosouza.justamobile.ui.viewmodels.RecyclingCompaniesViewModel;
import br.com.marcosouza.justamobile.util.Utils;

public class RecyclingCompaniesFragment extends Fragment implements ClickRecyclerView {

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


        recyclingCompaniesViewModel.init();
        recyclingCompaniesViewModel.getCompaniesRepository().observe(this, new Observer<RecyclingCompanyResponse>() {
            @Override
            public void onChanged(RecyclingCompanyResponse recyclingCompany) {
                if(recyclingCompany == null){
                    return;
                }
                if(recyclingCompany.getError() == null) {
                    List<RecyclingCompany> newsArticles = recyclingCompany.getResults();
                    recyclingCompanies.addAll(newsArticles);
                    recyclingCompanyAdapter.notifyDataSetChanged();
                } else {
                    Throwable e = recyclingCompany.getError();
                    Utils.messageConnectFailed(getActivity(), e);
                }
            }
        });

        loadRecyclerView();
        return root;
    }
    private void loadRecyclerView() {
        if (recyclingCompanyAdapter == null) {
            recyclingCompanyAdapter = new RecyclingCompanyAdapter(getActivity(), recyclingCompanies,this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(recyclingCompanyAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            recyclingCompanyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCustomClick(View view, int position, boolean isLongClick) {
        recyclingCompanies.get(position);
        Intent intent = new Intent(getActivity(), CompanyDetailActivity.class);
        intent.putExtra("company", recyclingCompanies.get(position));
        startActivity(intent);
    }
}