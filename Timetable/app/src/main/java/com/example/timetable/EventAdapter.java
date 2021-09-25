package com.example.timetable;


    import android.app.Activity;
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


    import org.jetbrains.annotations.NotNull;

    import java.util.ArrayList;

    import static com.example.timetable.DatabaseHelper.TABLE2_NAME;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ModelViewHolder> {
        Context context;
        ArrayList<EventModel>modelArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase;

        // generate constructor
        public EventAdapter(Context context, int single_event, ArrayList<EventModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
            this.context = context;
            this.modelArrayList = modelArrayList;
            this.sqLiteDatabase = sqLiteDatabase;
        }

        
        @NonNull
        @Override
        public EventAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.single_event,null);
            return new ModelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull EventAdapter.ModelViewHolder holder, int position) {
            final EventModel model = modelArrayList.get(position);
            holder.eName.setText(model.getEventName());
            holder.eDesc.setText(model.getStartTime());
            holder.eLocation.setText(model.getCEventDescription());
            holder.sTime.setText(model.getEventLocation());



            //go back to edit window
            holder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", model.getEventID());
                    bundle.putString("eName", model.getEventName());
                    bundle.putString("eDescription", model.getCEventDescription());
                    bundle.putString("eLocation", model.getEventLocation());
                    bundle.putString("sTime", model.getStartTime());
                    bundle.putString("eTime", model.getFinishTime());
                    Intent intent = new Intent(context, CreateEvent.class);
                    intent.putExtra("eventdata", bundle);
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return modelArrayList.size();
        }

        public class ModelViewHolder extends RecyclerView.ViewHolder {
            TextView eName, eLocation, eDesc, sTime, eTime;
            Button update, delete;
            public ModelViewHolder(@NonNull View itemView) {
                super(itemView);
                eName=(TextView)itemView.findViewById(R.id.eName);
                eLocation=(TextView)itemView.findViewById(R.id.eLocation);
                eDesc=(TextView)itemView.findViewById(R.id.eDesc);
                sTime=(TextView)itemView.findViewById(R.id.sTime);
                eTime=(TextView)itemView.findViewById(R.id.etime);
                update = (Button)itemView.findViewById(R.id.btn_update);
                delete = (Button)itemView.findViewById(R.id.btn_delete);
            }
        }
    }
