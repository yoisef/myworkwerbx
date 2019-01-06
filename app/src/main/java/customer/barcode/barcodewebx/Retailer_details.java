package customer.barcode.barcodewebx;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.util.CachedHashCodeArrayMap;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Retailer_details extends AppCompatActivity {


    private Spinner retailertype;
    private TextView currency;
    private EditText renam,retemail,retapass,retailer_retype_pass,remobile;
    private Button register;
    private SearchView storeid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_details);

        renam=findViewById(R.id.retailernam);
        retemail=findViewById(R.id.retaileremail);
        register=findViewById(R.id.registerr);

        retapass=findViewById(R.id.retailerpass);
        retailer_retype_pass=findViewById(R.id.retailerrepass);
        remobile=findViewById(R.id.retailermobile);

        storeid=findViewById(R.id.storid);

        Intent intent=getIntent();
      int condition=  intent.getIntExtra("havestore",0);

      if (condition==0)
      {
          storeid.setVisibility(View.GONE);
      }






        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validteretailer();

            }
        });
    }



    private void validteretailer()
    {
        String nam,email,pas1,pas2,mob,storeidd;

        nam=renam.getText().toString().trim();
        email=retemail.getText().toString().trim();
        pas1=retapass.getText().toString().trim();
        pas2=retailer_retype_pass.getText().toString().trim();
        mob=remobile.getText().toString().trim();


        if (nam.isEmpty())
        {
            renam.setError(getResources().getString(R.string.namV));
            renam.requestFocus();
            return;
        }
        if (email.isEmpty())
        {
            retemail.setError(getResources().getString(R.string.emailV));
            retemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
           retemail.setError(getResources().getString(R.string.vaildemailV));
           retemail.requestFocus();
            return ;
        }
        if (pas1.isEmpty())
        {
            retapass.setError(getResources().getString(R.string.passV));
            retapass.requestFocus();

            return;
        }
        if (pas1.length()<6)
        {
            retapass.setError(getResources().getString(R.string.minumV));
            retapass.requestFocus();
            return;
        }
        if (pas2.isEmpty())
        {
            retailer_retype_pass.setError(getResources().getString(R.string.passRV));
            retailer_retype_pass.requestFocus();

            return;
        }
        if (!pas2.equals(pas1))
        {
            retailer_retype_pass.setError(getResources().getString(R.string.passRV));
            retailer_retype_pass.requestFocus();
            return;
        }
        if (mob.isEmpty())
        {
            remobile.setError(getResources().getString(R.string.mobileV));
            remobile.requestFocus();
            return;
        }
        if (retailertype.getSelectedItemPosition()==0)
        {
            ((TextView)retailertype.getSelectedView()).setError(getResources().getString(R.string.distuboterV));
            ((TextView)retailertype.getSelectedView()).requestFocus();
            return;
        }
    }


}
