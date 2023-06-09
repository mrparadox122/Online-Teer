package com.onlineteer.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onlineteer.R;

import java.util.ArrayList;

public class HomeAdaptor extends RecyclerView.Adapter<HomeAdaptor.ViewHolder> {
    private ArrayList<HomeViewModel> homeViewModelList;

    public HomeAdaptor(ArrayList<HomeViewModel> homeViewModelList) {
        this.homeViewModelList = homeViewModelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeViewModel homeViewModel = homeViewModelList.get(position);

        holder.roundTextView.setText(homeViewModel.getRoundText());
        holder.timingTextView.setText(homeViewModel.getTimingStart());
        holder.timingTextView.append("-"+homeViewModel.getTimingEnd());
        holder.resultTimingTextView.setText("RESULT TIME - "+homeViewModel.getResulttiming());
    }


    @Override
    public int getItemCount() {
        return homeViewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView roundTextView;
        public TextView timingTextView;
        public TextView resultTimingTextView;
        public Button playNowButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundTextView = itemView.findViewById(R.id.RoundText);
            timingTextView = itemView.findViewById(R.id.timing);
            resultTimingTextView = itemView.findViewById(R.id.Resulttiming);
            playNowButton = itemView.findViewById(R.id.Playnow);
        }
    }

}
