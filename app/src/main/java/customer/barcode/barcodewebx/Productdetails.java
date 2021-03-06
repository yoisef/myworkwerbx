package customer.barcode.barcodewebx;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import customer.barcode.barcodewebx.productmodels.Rootproductdetail;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Productdetails extends AppCompatActivity {

    TextView productdescription,productionname;
    ImageView productimagee;


    Call<Rootproductdetail> mycall;
    ProgressBar myprogressbar;
    private SharedPreferences prefs;
    private String usertoken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);


        prefs = getSharedPreferences("token", Context.MODE_PRIVATE);
        usertoken=prefs.getString("usertoken","def");
        // Write a message to the database
        productimagee=findViewById(R.id.imgp);
        productdescription=findViewById(R.id.desp);
        productionname=findViewById(R.id.namp);
        myprogressbar=findViewById(R.id.waitprogressbar);

        Intent recieveintent=getIntent();
        String mynumbar=recieveintent.getStringExtra("barnum");

        OkHttpClient.Builder builderr = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builderr.addInterceptor(loggingInterceptor);


        Retrofit retrofitt = new Retrofit.Builder()
                .baseUrl("https://www.werpx.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builderr.build())
                .build();


        productdescription.setText(null);
        productionname.setText(null);
        final Endpoints myendpoints = retrofitt.create(Endpoints.class);
        mycall = myendpoints.getdetails("Bearer "+usertoken,mynumbar);
        mycall.enqueue(new Callback<Rootproductdetail>() {
            @Override
            public void onResponse(Call<Rootproductdetail> call, Response<Rootproductdetail> response) {


                if (response.isSuccessful()) {
                    myprogressbar.setVisibility(View.GONE);

                    String urlimg = response.body().getProduct().getImage().getUrl();
                    String descrip = response.body().getProduct().getDescription();
                    String namee = response.body().getProduct().getName();
                    if (response.body().getProduct() != null) {
                        productdescription.setText(descrip);
                        productionname.setText(namee);
                    }


                    if (urlimg != null) {
                        Glide.with(Productdetails.this)
                                .load(urlimg)
                                .into(productimagee);
                    } else {
                        Glide.with(Productdetails.this)
                                .load(getResources().getDrawable(R.drawable.lipton))
                                .into(productimagee);
                    }
                }
                else
                {
                    Toast.makeText(Productdetails.this, "Not record in database", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Rootproductdetail> call, Throwable t) {
                Toast.makeText(Productdetails.this, "Connection Failed", Toast.LENGTH_LONG).show();


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.uptocenter, R.anim.centertodown);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}