package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.service.autofill.DateValueSanitizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.project.DBmain.TABLENAME;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ModelViewHolder> {
    Context context;
    ArrayList<Model>modelArrayList = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;
    //generate constructor

    public MyAdapter(Context context, int singledata, ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public MyAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledata, null);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ModelViewHolder holder, int position) {
        final Model model = modelArrayList.get(position);
        holder.eName.setText(model.geteName());
        holder.eDescription.setText(model.geteDescription());
        holder.eLocation.setText(model.geteLocation());
        holder.sTIme.setText(model.getsTime());
        holder.eTime.setText(model.geteTime());

        //click on button to go back to the main activity
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", model.getId());
                bundle.putString("eName", model.geteName());
                bundle.putString("eDescription", model.geteDescription());
                bundle.putString("eLocation", model.geteLocation());
                bundle.putString("sTIme", model.getsTime());
                bundle.putString("eTime", model.geteTime());
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("eventdata", bundle);
                context.startActivity(intent);
            }
        });

        //delete event
        holder.delete.setOnClickListener(new View.OnClickListener() {
            DBmain dBmain = new DBmain(context);
            @Override
            public void onClick(View v) {
                sqLiteDatabase = dBmain.getReadableDatabase();
                long delete = sqLiteDatabase.delete(TABLENAME, "id="+model.getId(), null);
                if(delete != -1){
                    Toast.makeText(context, "Event Deleted Successfully!", Toast.LENGTH_SHORT).show();
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

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView eName, eDescription, eLocation, sTIme, eTime;
        Button edit, delete;
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            eName = (TextView)itemView.findViewById(R.id.txteName);
            eDescription = (TextView)itemView.findViewById(R.id.txteDescription);
            eLocation = (TextView)itemView.findViewById(R.id.txteLocation);
            sTIme = (TextView)itemView.findViewById(R.id.txtsTime);
            eTime = (TextView)itemView.findViewById(R.id.txteTime);
            edit = (Button)itemView.findViewById(R.id.txt_btn_update);
            delete = (Button)itemView.findViewById(R.id.txt_btn_delete);
        }
    }
}
