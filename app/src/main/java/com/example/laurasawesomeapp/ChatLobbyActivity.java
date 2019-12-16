package com.example.laurasawesomeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.laurasawesomeapp.data.ChatLobbyAdapter;
import com.example.laurasawesomeapp.data.model.ChatLobby;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatLobbyActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ArrayList<ChatLobby> Lobby;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_lobby);
        Lobby = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rcvChatLobby);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // specify an adapter (see also next example)
        mAdapter = new ChatLobbyAdapter(this.Lobby);
        recyclerView.setAdapter(mAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        loadChatRooms();
        //newChatRoom();
    }
    public void loadChatRooms()
    {
        FirebaseDatabase.getInstance().getReference("ChatLobby").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Fetches all the existing items
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
                for (DataSnapshot aSnapshotIterable : snapshotIterable) {
                    ChatLobby clob = aSnapshotIterable.getValue(ChatLobby.class);
                    Lobby.add(clob);
                    //messages.add(msg);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void newChatRoom()
    {
        ChatLobby test = new ChatLobby(0,"MyFirstLobby");
        String key = mDatabase.child("ChatLobby").push().getKey();

        Map<String, Object> postValues = test.toMap();

        //mDatabase.child(key).setValue(postValues);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("ChatLobby/" + key, postValues);

        mDatabase.updateChildren(childUpdates);

    }
}
