package com.example.laurasawesomeapp.data;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.laurasawesomeapp.ChatWindowActivity;
import com.example.laurasawesomeapp.LoginActivity;
import com.example.laurasawesomeapp.R;
import com.example.laurasawesomeapp.StartActivity;
import com.example.laurasawesomeapp.data.model.ChatLobby;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatLobbyAdapter extends RecyclerView.Adapter<ChatLobbyAdapter.MyViewHolder> {
    private static ArrayList<ChatLobby> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            textView = v.findViewById(R.id.textView2);
        }
        @Override
        public void onClick(View view) {
            int clickIndex = getLayoutPosition();
            ChatLobby c = mDataset.get(clickIndex);
            Intent notAssignedIntent = new Intent(view.getContext(), ChatWindowActivity.class);
            view.getContext().startActivity(notAssignedIntent);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatLobbyAdapter(ArrayList<ChatLobby> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChatLobbyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rclobby_item, parent, false);

        MyViewHolder vh = new MyViewHolder(listItem);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position).Name);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}