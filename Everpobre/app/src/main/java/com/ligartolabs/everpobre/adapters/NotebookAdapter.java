package com.ligartolabs.everpobre.adapters;

import com.ligartolabs.everpobre.R;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ligartolabs.everpobre.model.Notebook;
import com.ligartolabs.everpobre.model.Notebooks;
import com.ligartolabs.everpobre.views.NotebookRowViewHolder;

public class NotebookAdapter extends RecyclerView.Adapter<NotebookRowViewHolder> {

    private LayoutInflater layoutInflater;
    private Notebooks notebooks;

    public NotebookAdapter(Notebooks notebooks, Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.notebooks = notebooks;
    }

    @Override
    public NotebookRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_notebook, parent, false);

        return new NotebookRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotebookRowViewHolder holder, int position) {
        Notebook notebook = notebooks.get(position);

        holder.setNotebookName(notebook.getName());
    }

    @Override
    public int getItemCount() {
        return notebooks.size();
    }
}
