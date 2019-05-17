package tech.dsckiet.ideasharing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class IdeasAdapter extends RecyclerView.Adapter<IdeasAdapter.viewHolder> {
    Context context;
    private List<IdeasRecyclerView> ideasList;

    public IdeasAdapter(Context applicationContext, List<IdeasRecyclerView> ideasList) {
        this.context = applicationContext;
        this.ideasList = ideasList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.idea_card, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final IdeasAdapter.viewHolder holder, final int position) {

        IdeasRecyclerView ideasRecyclerView = ideasList.get(position);
        holder.titletv.setText(ideasRecyclerView.getIdeaTitle());
        holder.desctv.setText(ideasRecyclerView.getIdeaDesc());

//        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                sharingIntent.setType("text/plain");
//
//                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hey, I have thought of Idea!");
//                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Title: " + titleList.get(position) + "\n\nDescription: " + desclList.get(position) + "\n\nTechnologies: " + techList.get(position));
//
//                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return ideasList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        public TextView titletv, desctv;
//        CardView editBtn, deleteBtn, shareBtn;

        public viewHolder(final View itemView) {
            super(itemView);

            titletv = itemView.findViewById(R.id.titletv);
            desctv = itemView.findViewById(R.id.desctv);
//            editBtn = itemView.findViewById(R.id.editBtn);
//            deleteBtn = itemView.findViewById(R.id.deleteBtn);
//            shareBtn = itemView.findViewById(R.id.shareBtn);
//        techtv = itemView.findViewById(R.id.techtv);
        }
    }
}
