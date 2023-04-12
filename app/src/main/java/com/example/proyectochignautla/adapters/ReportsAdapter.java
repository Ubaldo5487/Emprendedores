package com.example.proyectochignautla.adapters;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectochignautla.Class.Reports;
import com.example.proyectochignautla.R;
import com.example.proyectochignautla.ShowActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ViewHolder> {
    List<Reports> ListaReportes;
    List<Reports> originalItems;

    public ReportsAdapter(List<Reports> listaReportes) {
        ListaReportes = listaReportes;
        originalItems = new ArrayList<>();
        originalItems.addAll(listaReportes);
    }

    @NonNull
    @Override
    public ReportsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsAdapter.ViewHolder holder, int position) {
        final Reports item = ListaReportes.get(position);
        holder.earea.setText(ListaReportes.get(position).getArea().toString());
        holder.eproblem.setText(ListaReportes.get(position).getProblem().toString());
        holder.estatus.setText(ListaReportes.get(position).getStatus().toString());
        holder.ecreated_at.setText(ListaReportes.get(position).getCreated_at().toString());

        holder.eshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowActivity.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ListaReportes.size();
    }

    public void filter( String strSearch) {
        int longitud = strSearch.length();
        if (longitud == 0) {
            ListaReportes.clear();
            ListaReportes.addAll(originalItems);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                List<Reports> Colecion = ListaReportes.stream().filter(i -> i.getArea().toLowerCase()
                        .contains(strSearch.toLowerCase())).collect(Collectors.toList());

                ListaReportes.clear();
                ListaReportes.addAll(Colecion);
            }else{
                for (Reports C : originalItems) {
                    if (C.getArea().toLowerCase().contains(strSearch.toLowerCase())) {
                        ListaReportes.add(C);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView earea, eproblem, ecreated_at, estatus;
        Button eshow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            earea = (TextView) itemView.findViewById(R.id.areaRC);
            eproblem = (TextView) itemView.findViewById(R.id.problemaRC);
            ecreated_at = (TextView) itemView.findViewById(R.id.created_atRC);
            estatus = (TextView) itemView.findViewById(R.id.statusRC);
            eshow = (Button) itemView.findViewById(R.id.btnShow);
        }
    }
}
