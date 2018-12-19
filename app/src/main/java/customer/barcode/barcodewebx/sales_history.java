package customer.barcode.barcodewebx;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import customer.barcode.barcodewebx.RoomDatabase.historytable;
import customer.barcode.barcodewebx.RoomDatabase.mytable;
import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;

public class sales_history extends AppCompatActivity {

    private RecyclerView history_recycle;
    private salesAdapter mAdapter;
    private productViewmodel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_history);

        history_recycle = findViewById(R.id.saleshistoryrecucle);
        history_recycle.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        history_recycle.setLayoutManager(linearLayoutManager);
        history_recycle.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new salesAdapter(this);
        history_recycle.setAdapter(mAdapter);

        mWordViewModel = ViewModelProviders.of(this).get(productViewmodel.class);

        mWordViewModel.getAllhistory().observe(this, new Observer<List<historytable>>() {
            @Override
            public void onChanged(@Nullable List<historytable> historytables) {

                mAdapter.setHistory(historytables);


            }
        });


    }
}