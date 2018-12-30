package customer.barcode.barcodewebx;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import customer.barcode.barcodewebx.signupfragments.retailerinfo1;
import customer.barcode.barcodewebx.signupfragments.storeinfo1;
import customer.barcode.barcodewebx.signupfragments.storeinfo2;

public class FragmentsActivity extends AppCompatActivity implements connection {

  FragmentManager manager;
    FragmentTransaction transaction;
    retailerinfo1 firstfrag;
    storeinfo1 secondfrag;
    storeinfo2 thirdfrag;
    TextView step1,step2,step3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        firstfrag=new retailerinfo1();
        secondfrag=new storeinfo1();
        thirdfrag=new storeinfo2();

        step1=findViewById(R.id.step_1);
        step2=findViewById(R.id.step_2);
        step3=findViewById(R.id.step_3);

        step1.setTextColor(Color.WHITE);
        step1.setBackgroundColor(Color.GRAY);

        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();

        if(firstfrag.isAdded()&&secondfrag.isAdded()&&thirdfrag.isAdded())
        {
            return; //or return false/true, based on where you are calling from
        }
        else
        {
            transaction.add(R.id.myfrags,firstfrag);
            transaction.add(R.id.myfrags,secondfrag);
            transaction.add(R.id.myfrags,thirdfrag);
            transaction.commit();
        }




    }

    @Override
    public void changevisibiltyfrag1() {

        firstfrag.getView().setVisibility(View.GONE);
        step2.setTextColor(Color.WHITE);
        step2.setBackgroundColor(Color.GRAY);
        step1.setTextColor(Color.BLACK);
        step1.setBackgroundColor(Color.WHITE);


    }

    @Override
    public void changevisibltyfrag2() {

        firstfrag.getView().setVisibility(View.GONE);
        secondfrag.getView().setVisibility(View.GONE);
        step3.setTextColor(Color.WHITE);
        step3.setBackgroundColor(Color.GRAY);


    }

    @Override
    public void changevisibiltyfrag3() {

    }

    @Override
    public void changevisibiltyfrag4() {

    }
}
