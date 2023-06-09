package com.onlineteer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.onlineteer.Api.HomeAPi;
import com.onlineteer.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomeAPi.OnHomeAPiHit {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.HomeRecycler.showShimmerAdapter();
        HomeAPi homeAPi = new HomeAPi(this);
        homeAPi.CallApi();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnHomeApiGivesResult(ArrayList<HomeViewModel> homeViewModels) {
        requireActivity().runOnUiThread( () -> {
            binding.HomeRecycler.setLayoutManager(new GridLayoutManager(requireContext(),2));
            binding.HomeRecycler.setAdapter(new HomeAdaptor(homeViewModels));
        } );
    }

    @Override
    public void OnHomeAPiGivesError(String error) {

    }
}