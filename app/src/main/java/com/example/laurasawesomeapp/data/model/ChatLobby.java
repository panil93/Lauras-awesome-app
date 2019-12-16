package com.example.laurasawesomeapp.data.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ChatLobby {
    public ChatLobby()
    {

    }
    public ChatLobby(int id , String Name)
    {
        this.id = id;
        this.Name = Name;

    }
    public String Name;
    public int id;

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Name", Name);
        result.put("id", id);

        return result;
    }

}
