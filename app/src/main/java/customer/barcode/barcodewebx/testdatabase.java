package customer.barcode.barcodewebx;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import customer.barcode.barcodewebx.RoomDatabase.Productltable;
import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;


public class testdatabase extends AppCompatActivity {

    private productViewmodel mWordViewModel;
    TextView insertdata,getdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdatabase);
        insertdata=findViewById(R.id.insert);
        getdata=findViewById(R.id.show);

        mWordViewModel = ViewModelProviders.of(this).get(productViewmodel.class);


        insertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mWordViewModel.findProduct("62211611");


            }
        });



        mWordViewModel.getSearchResults().observe(testdatabase.this, new Observer<List<Productltable>>() {
            @Override
            public void onChanged(@Nullable List<Productltable> productltables) {

                getdata.setText(productltables.get(0).getName());
            }
        });

    }
}
