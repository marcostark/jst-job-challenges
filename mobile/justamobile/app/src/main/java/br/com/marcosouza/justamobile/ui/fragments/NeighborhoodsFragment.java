package br.com.marcosouza.justamobile.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.Neighborhoods;
import br.com.marcosouza.justamobile.model.NeighborhoodsResponse;
import br.com.marcosouza.justamobile.ui.adapter.NeighbordhoodsAdapter;
import br.com.marcosouza.justamobile.ui.viewmodels.NeighborhoodsViewModel;

public class NeighborhoodsFragment extends Fragment {

    private NeighborhoodsViewModel neighborhoodsViewModel;
    private ArrayList<Neighborhoods> neighborhoods = new ArrayList <> ();
    private NeighbordhoodsAdapter neighbordhoodsAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        neighborhoodsViewModel =
                ViewModelProviders.of(this).get(NeighborhoodsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_neighborhoods, container, false);
        recyclerView = root.findViewById(R.id.rv_collection);
        neighborhoodsViewModel.init();

        neighborhoodsViewModel.getNeighbordhoodsRepository().observe(this, new Observer<NeighborhoodsResponse>() {
            @Override
            public void onChanged(NeighborhoodsResponse neighborhoodsResponse) {
                if(neighborhoodsResponse == null){
                    return;
                }
                if(neighborhoodsResponse.getError() == null){
                    List<Neighborhoods> newsArticles = neighborhoodsResponse.getResults();
                    neighborhoods.addAll(newsArticles);
                    neighbordhoodsAdapter.notifyDataSetChanged();
                } else {
                    Throwable e = neighborhoodsResponse.getError();
                    Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        loadRecyclerView();
        return root;
    }
    private void loadRecyclerView() {
        if (neighbordhoodsAdapter == null) {
            neighbordhoodsAdapter = new NeighbordhoodsAdapter(getActivity(), neighborhoods);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(neighbordhoodsAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            neighbordhoodsAdapter.notifyDataSetChanged();
        }
    }
}