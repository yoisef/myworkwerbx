package customer.barcode.barcodewebx.Werbx;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import customer.barcode.barcodewebx.R;
import customer.barcode.barcodewebx.RoomDatabase.historytable;
import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;
import customer.barcode.barcodewebx.spinneradapter;

public class sales_history extends AppCompatActivity {

    private RecyclerView history_recycle;
    private salesAdapter mAdapter;
    private productViewmodel mWordViewModel;
    private TextView orders_coast,remove_history;
    private List<historytable> daytable,weektable,monthtable;
    private Spinner sales_spinner;
    private android.support.v7.widget.Toolbar salestool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_history);

        daytable=new ArrayList<>();
        weektable=new ArrayList<>();
        monthtable=new ArrayList<>();

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
        intlaze_toolbar();

        mWordViewModel = ViewModelProviders.of(this).get(productViewmodel.class);

        mWordViewModel.getAllhistory().observe(this, new Observer<List<historytable>>() {
            @Override
            public void onChanged(@Nullable List<historytable> historytables) {

                mAdapter.setHistory(historytables);
            }
        });

        remove_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mWordViewModel.deleteallhist();
            }
        });







            }


    private void intlaze_toolbar()
    {
        salestool = findViewById(R.id.salestoolbar);
        setSupportActionBar(salestool);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setCustomView(R.layout.salesbar);
        View view = getSupportActionBar().getCustomView();



      sales_spinner = view.findViewById(R.id.salesviewway);
        List<CharSequence> list=new ArrayList<>();
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.salesway)));

        spinneradapter madapter=new spinneradapter(this,android.R.layout.simple_spinner_item,list);
        madapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        sales_spinner.setAdapter(madapter);

        sales_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 1:{
                        weektable.clear();
                        choosefilter(1);
                        break;
                    }
                    case 2:{
                        daytable.clear();
                        choosefilter(2);
                        break;
                    }
                    case 3:{
                        choosefilter(3);

                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    private void choosefilter(final int i)
    {



        mWordViewModel = ViewModelProviders.of(this).get(productViewmodel.class);

        mWordViewModel.getAllhistory().observe(this, new Observer<List<historytable>>() {
            @Override
            public void onChanged(@Nullable final List<historytable> historytables) {



                Double allordercoast=0.0;
                SimpleDateFormat sdf = new SimpleDateFormat("E, dd-MMMM-yy");

              //  String dataformat = sdf.format(dateBefore7Days);



                for (int i=0;i<historytables.size();i++)
                {
                    Log.e("historyrow","for");


                    historytable myhis=historytables.get(i);
                    Locale locale = new Locale("ar", "KW");

                    Date currDate = new Date();
                    String formattedDate = sdf.format(currDate);

                    if (myhis.getOrdata().trim().equals(formattedDate.trim()))
                    {
                        Log.e("historyrow","daycond");
                        daytable.add(myhis);
                    }

                    String rowhis=myhis.getOrdata();
                    Date data = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(data);
                    cal.add(Calendar.DATE, -7);
                    Date dateBefore7Days = cal.getTime();


                    SimpleDateFormat df = new SimpleDateFormat("E, dd-MMMM-yy");
                    try {
                        Date hisdata=df.parse(rowhis);


                        if (hisdata.after(dateBefore7Days))
                        {

                            weektable.add(myhis);
                        }
                    } catch (ParseException e) {
                        Log.e("exceptionyabne",e.getMessage()+"weekcon");
                    }



                    Calendar calm = Calendar.getInstance();
                    calm.setTime(data);
                    calm.add(Calendar.DATE, -30);
                    Date dateBefore30Days = calm.getTime();


                    SimpleDateFormat dfm = new SimpleDateFormat("E, dd-MMMM-yy");
                    try {
                        Date datamonth=dfm.parse(rowhis);
                        Log.e("trymonth",datamonth.toString());


                        if (datamonth.after(dateBefore30Days))
                        {
                            Log.e("addmonth",dateBefore30Days.toString());

                            monthtable.add(myhis);
                        }
                    } catch (ParseException e) {
                        Log.e("exceptionyabne",e.getMessage()+"weekcon");
                    }








                    /*
                    // String dayOfTheWeek = (String) DateFormat.format("EEEE", currDate);
                    Locale locale = new Locale("ar", "KW");
                    SimpleDateFormat sdf = new SimpleDateFormat("E, dd-MMMM-yy");
                    Date currDate = new Date();
                    String formattedDate = sdf.format(currDate);
                    Toast.makeText(sales_history.this,formattedDate,Toast.LENGTH_LONG).show();
*/



                    Double currentamount=Double.parseDouble(myhis.getOramount()) ;
                    allordercoast=allordercoast+currentamount;

                }

                switch (i)
                {
                    case 1:{
                        weektable.clear();
                        monthtable.clear();
                        mAdapter.sethistoryfilter(daytable);
                        break;
                    }

                    case 2:{
                        daytable.clear();
                        monthtable.clear();
                        mAdapter.sethistoryfilter(weektable);
                        break;
                    }
                    case 3:{
                        weektable.clear();
                        daytable.clear();
                        mAdapter.sethistoryfilter(monthtable);
                        break;
                    }

                    default:mAdapter.setHistory(historytables);
                }


                orders_coast.setText(String.valueOf(allordercoast));

            }
        });




    }
}