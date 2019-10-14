package com.kh09909.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView queueView;
    Button btnNext, btnNewCustomer;
    EditText ETCustomerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queueView = (ListView)findViewById(R.id.queueView);

        ETCustomerName = (EditText)findViewById(R.id.ETCustomerName);

        btnNext = (Button)findViewById(R.id.btnNext);
        btnNewCustomer = (Button)findViewById(R.id.btnNewCustomer);

        btnNext.setOnClickListener(this);
        btnNewCustomer.setOnClickListener(this);

    }

    void NewCustomer(){
        if(Queue.getQueueSize() >= 5){
            PopUp.setPopMessage(getString(R.string.Bank_busy));
            Intent popUpIntent = new Intent(getApplicationContext(), PopUp.class);
            startActivity(popUpIntent);
        }
        else{
            if(ETCustomerName.length() < 1){
                Toast.makeText(getApplicationContext(),getString(R.string.Write_customer_name), Toast.LENGTH_LONG).show();
            }
            else{
                Queue.addCustomer(ETCustomerName.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row_list, Queue.queue);
                queueView.setEnabled(false);
                queueView.setAdapter(adapter);
                ETCustomerName.setText("");
            }
        }
    }

    void NextCustomer(){
        if(Queue.getQueueSize() < 1){
            // Enjoy your coffee
            PopUp.setPopMessage(getString(R.string.Enjoy));
            Intent popUpIntent = new Intent(getApplicationContext(), PopUp.class);
            startActivity(popUpIntent);
        }
        else{
            Queue.moveCustomer(0);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row_list, Queue.queue);
            queueView.setEnabled(false);
            queueView.setAdapter(adapter);
        }
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnNewCustomer:
                NewCustomer();
                break;

            case R.id.btnNext:
                NextCustomer();
                break;

            default:
                break;

        }

    }

}
