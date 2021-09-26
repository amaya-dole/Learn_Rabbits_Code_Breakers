package com.example.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FactorAdapter extends RecyclerView.Adapter<FactorAdapter.ModelviewHolder> {
    Context context;
    ArrayList<FactorsModel>modelArrayList = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    //generate constructor
    public FactorAdapter(Context context, int factorsdata, ArrayList<FactorsModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }



    @NonNull
    @Override
    public FactorAdapter.ModelviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.factorsdata, null);
        return new ModelviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FactorAdapter.ModelviewHolder holder, @SuppressLint("RecyclerView") int position) {
        final FactorsModel factorsModel = modelArrayList.get(position);
        holder.txtnum1.setText(factorsModel.getNum1());
        holder.txtnum2.setText(factorsModel.getNum2());
        holder.txtnum3.setText(factorsModel.getNum3());

        //go to add factors
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", factorsModel.getId());
                bundle.putString("num1", factorsModel.getNum1());
                bundle.putString("num2", factorsModel.getNum2());
                bundle.putString("num3", factorsModel.getNum3());
                Intent intent = new Intent(context, addFactors.class);
                intent.putExtra("factorsdata",bundle);
                context.startActivity(intent);
            }
        });

        //delete row
        holder.delete.setOnClickListener(new View.OnClickListener() {
            DatabaseMain databaseMain = new DatabaseMain(context);
            @Override
            public void onClick(View view) {
                sqLiteDatabase= databaseMain.getReadableDatabase();
                long delete = sqLiteDatabase.delete("factors", "id="+factorsModel.getId(),null);
                if (delete!=-1){
                    Toast.makeText(context,"Deleted Successfully", Toast.LENGTH_SHORT).show();
                    modelArrayList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ModelviewHolder extends RecyclerView.ViewHolder {
        TextView txtnum1,txtnum2,txtnum3;
        Button edit,delete;

        public ModelviewHolder(@NonNull View itemView) {
            super(itemView);
            txtnum1=itemView.findViewById(R.id.txtnum1);
            txtnum2=itemView.findViewById(R.id.txtnum2);
            txtnum3=itemView.findViewById(R.id.txtnum3);
            edit=itemView.findViewById(R.id.btn_edit);
            delete=itemView.findViewById(R.id.btn_delete);
        }
    }
}
