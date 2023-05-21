package org.chi.example.helper;

import com.google.gson.Gson;
import org.chi.example.data.Goods;

public class Helper {
    private Helper() {
    }

    public static Goods initFromJsonUser(String data){
        Gson gson = new Gson();
        return gson.fromJson(data, Goods.class);
    }


}
