package com.List.listimplementationsqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojet.R;
import com.List.listimplementationsqlite.entity.Personne;

import java.util.ArrayList;
import java.util.List;


public class PersonneRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;
    private boolean mIsLoadingAdded = false;
    private List<Personne> mPersonneList;
    private Context mContext;

    public PersonneRecyclerViewAdapter(Context context) {
        mPersonneList = new ArrayList<>();
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_ITEM:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.personne_list_row, parent, false);
                viewHolder = new PersonneViewHolder(itemView);
                break;
            case TYPE_LOADING:
                View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_row, parent, false);
                viewHolder = new LoadingViewHolder(loadingView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case TYPE_ITEM:
                Personne personne = mPersonneList.get(position);

                ((PersonneViewHolder) holder).nameTV.setText(personne.getmFirstName() + " " + personne.getmLastName());
                ((PersonneViewHolder) holder).emailTV.setText(personne.getmEmail());
                ((PersonneViewHolder) holder).phoneTV.setText(personne.getmPhoneNumber());
                ((PersonneViewHolder) holder).ageTV.setText(personne.getmAge() + " ans");

                break;
            case TYPE_LOADING:
                // Do nothing
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mPersonneList == null ? 0 : mPersonneList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mPersonneList.size() - 1 && mIsLoadingAdded) ? TYPE_LOADING : TYPE_ITEM;
    }

    /**
     * ----------------------------------   Helper --------------------------------------------
     */

    public void add(Personne personne) {
        mPersonneList.add(personne);
        notifyItemInserted(mPersonneList.size() - 1);
    }

    public void addAll(List<Personne> personneList) {
        if(personneList != null && !personneList.isEmpty()) {
            for (Personne personne : personneList) {
                add(personne);
            }
        }
    }

    public void remove(Personne personne) {
        int position = mPersonneList.indexOf(personne);
        if (position > -1) {
            mPersonneList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        mIsLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        mIsLoadingAdded = true;
        add(new Personne());
    }

    public void removeLoadingFooter() {
        mIsLoadingAdded = false;

        int position = mPersonneList.size() - 1;
        Personne item = getItem(position);

        if (item != null) {
            mPersonneList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Personne getItem(int position) {
        return mPersonneList.get(position);
    }

    /**
     * -----------------------------------  ViewHolder ------------------------------------------
     */

    public class PersonneViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV, emailTV, phoneTV, ageTV;

        public PersonneViewHolder(View view) {
            super(view);
            nameTV = view.findViewById(R.id.nameTV);
            emailTV = view.findViewById(R.id.emailTV);
            phoneTV = view.findViewById(R.id.phoneTV);
            ageTV = view.findViewById(R.id.ageTV);
        }

    }

    protected class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

}