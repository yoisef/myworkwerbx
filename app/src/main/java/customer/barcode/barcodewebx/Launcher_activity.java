package customer.barcode.barcodewebx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Launcher_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_activity);


        Handler myhandler=new Handler();
        myhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("token", Context.MODE_PRIVATE);
                String condition= prefs.getString("usertoken","def");


                choosebeginingactivity(condition);
                finish();

            }
        },3000);
    }


    public void choosebeginingactivity(String condition)
    {



       if (condition.equals("def"))
       {
           startActivity(new Intent(Launcher_activity.this,loginactivity.class));
       }
       else
       {
           startActivity(new Intent(Launcher_activity.this,MainActivity.class));
       }

    }
}
