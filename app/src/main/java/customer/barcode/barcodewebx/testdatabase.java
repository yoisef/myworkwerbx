package customer.barcode.barcodewebx;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import customer.barcode.barcodewebx.RoomDatabase.Productltable;
import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;


public class testdatabase extends AppCompatActivity {

    private productViewmodel mWordViewModel;
    TextView insertdata,getdata;
    EditText entersearchnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdatabase);
        insertdata=findViewById(R.id.insert);
        getdata=findViewById(R.id.show);
        entersearchnumber=findViewById(R.id.searchnumber);

        mWordViewModel = ViewModelProviders.of(this).get(productViewmodel.class);


        insertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String serch=entersearchnumber.getText().toString();

                mWordViewModel.findProduct(serch);


            }
        });



        mWordViewModel.getSearchResults().observe(testdatabase.this, new Observer<Productltable>() {
            @Override
            public void onChanged(@Nullable Productltable productltables) {

                getdata.setText(productltables.getName());
            }
        });

    }
}
