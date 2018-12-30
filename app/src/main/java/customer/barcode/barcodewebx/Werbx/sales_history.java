package customer.barcode.barcodewebx.Werbx;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import java.util.List;

import customer.barcode.barcodewebx.R;
import customer.barcode.barcodewebx.RoomDatabase.historytable;
import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;

public class sales_history extends AppCompatActivity {

    private RecyclerView history_recycle;
    private salesAdapter mAdapter;
    private productViewmodel mWordViewModel;
    private TextView orders_coast,remove_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_history);

        orders_coast=findViewById(R.id.totalsalescost);
        remove_history=findViewById(R.id.removehistory);

        history_recycle = findViewById(R.id.saleshistoryrecucle);
        history_recycle.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        history_recycle.setLayoutManager(linearLayoutManager);
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(this, R.anim.layoutanimation);
        history_recycle.setLayoutAnimation(controller);
        mAdapter = new salesAdapter(this);
        history_recycle.setAdapter(mAdapter);
        history_recycle.scheduleLayoutAnimation();


        mWordViewModel = ViewModelProviders.of(this).get(productViewmodel.class);

        mWordViewModel.getAllhistory().observe(this, new Observer<List<historytable>>() {
            @Override
            public void onChanged(@Nullable final List<historytable> historytables) {

                Double allordercoast=0.0;

                mAdapter.setHistory(historytables);

                for (int i=0;i<historytables.size();i++)
                {
                    historytable myhis=historytables.get(i);
                   Double currentamount=Double.parseDouble(myhis.getOramount()) ;
                   allordercoast=allordercoast+currentamount;
                }

         orders_coast.setText(String.valueOf(allordercoast));
                remove_history.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mWordViewModel.deleteallhist();
                    }
                });
            }
        });


    }
}