package customer.barcode.barcodewebx.Werbx;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import customer.barcode.barcodewebx.R;
import customer.barcode.barcodewebx.RoomDatabase.historytable;
import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;

public class sales_history extends AppCompatActivity {

    private RecyclerView history_recycle;
    private salesAdapter mAdapter;
    private productViewmodel mWordViewModel;
    private TextView orders_coast,remove_history;
    private List<historytable> daytable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_history);

        daytable=new ArrayList<>();

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




               // mAdapter.setHistory(historytables);

                for (int i=0;i<historytables.size();i++)
                {
                   // String dayOfTheWeek = (String) DateFormat.format("EEEE", currDate);
                    Locale locale = new Locale("ar", "KW");
                    SimpleDateFormat sdf = new SimpleDateFormat("E, dd-MMMM-yy");
                    Date currDate = new Date();



                    String formattedDate = sdf.format(currDate);
                    Toast.makeText(sales_history.this,formattedDate,Toast.LENGTH_LONG).show();

                    historytable myhis=historytables.get(i);
                    if (myhis.getOrdata().contains(formattedDate))
                    {
                        daytable.add(myhis);

                    }

                        Double currentamount=Double.parseDouble(myhis.getOramount()) ;
                   allordercoast=allordercoast+currentamount;
                }
                mAdapter.sethistoryfilter(daytable);

         orders_coast.setText(String.valueOf(allordercoast));

            }
        });

        remove_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mWordViewModel.deleteallhist();
            }
        });


    }
}