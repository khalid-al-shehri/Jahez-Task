package com.kh09909.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class Queue extends Activity {

    static ArrayList<String> queue = new ArrayList<>();
    
    public static int getQueueSize(){
        return queue.size();
    }

    public static void addCustomer(String CustomerName){
        queue.add(CustomerName);
    }

    public static void moveCustomer(int index){
        queue.remove(index);
    }

}
