package customer.barcode.barcodewebx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import customer.barcode.barcodewebx.modelsauth.Roottoken;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class loginactivity extends AppCompatActivity {

    private TextView signintext;
    private Call<Roottoken> mcall;
    private EditText emailuser,userpass;
    private ProgressBar signprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        emailuser=findViewById(R.id.useremaill);
        userpass=findViewById(R.id.userpasss);
        signintext=findViewById(R.id.signinbutton);
        signprogress=findViewById(R.id.loginprogressbar);



        signintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signprogress.setVisibility(View.VISIBLE);
                String currentemail=emailuser.getText().toString();
                String currpass=userpass.getText().toString();
                signin(currentemail,currpass);
            }
        });
    }

    public void signin(String email ,String password)
    {

        OkHttpClient.Builder builderr = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builderr.addInterceptor(loggingInterceptor);


        Retrofit retrofitt = new Retrofit.Builder()
                .baseUrl("https://www.werpx.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builderr.build())
                .build();

        final Endpoints myendpoints = retrofitt.create(Endpoints.class);

        mcall = myendpoints.signuser("application/x-www-form-urlencoded",email,password);
        mcall.enqueue(new Callback<Roottoken>() {
            @Override
            public void onResponse(Call<Roottoken> call, Response<Roottoken> response) {
                if (response.isSuccessful())
                {
                    signprogress.setVisibility(View.GONE);
                   String thetoken= response.body().getData().getToken();
                    SharedPreferences prefs = getSharedPreferences("token", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=prefs.edit();
                    editor.putString("usertoken",thetoken);
                    editor.apply();
                    Toast.makeText(loginactivity.this,"Successful login",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(loginactivity.this,MainActivity.class));
                    finish();
                }
                else
                {
                    signprogress.setVisibility(View.GONE);
                    Toast.makeText(loginactivity.this,"Wrong email or password",Toast.LENGTH_LONG).show();



                }
            }

            @Override
            public void onFailure(Call<Roottoken> call, Throwable t) {
                signprogress.setVisibility(View.GONE);
                Toast.makeText(loginactivity.this,"Connection Failed",Toast.LENGTH_LONG).show();


            }
        });


    }
}
