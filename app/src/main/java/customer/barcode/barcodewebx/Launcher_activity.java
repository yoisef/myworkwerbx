package customer.barcode.barcodewebx;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;
import customer.barcode.barcodewebx.productdatabasemodels.Product;
import customer.barcode.barcodewebx.productdatabasemodels.Productroot;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Launcher_activity extends AppCompatActivity {

    private Call<Productroot> mcall;
    private productViewmodel mWordViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_activity);

       // mWordViewModel = ViewModelProviders.of(this, new factoryclass(this.getApplication(),62211611)).get(productViewmodel.class);
        mWordViewModel = ViewModelProviders.of(this).get(productViewmodel.class);




        Handler myhandler=new Handler();
        myhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("token", Context.MODE_PRIVATE);
                String condition= prefs.getString("usertoken","def");

                getallproducts();


                choosebeginingactivity(condition);
                finish();

            }
        },5000);
    }

    public void getallproducts()
    {

        String usertoken = getSharedPreferences("token", Context.MODE_PRIVATE).getString("usertoken","def");


        Retrofitclient myretro=Retrofitclient.getInstance();
        Retrofit retrofittok=  myretro.getretro();
        final Endpoints myendpoints = retrofittok.create(Endpoints.class);

        mcall = myendpoints.getallproducts("Bearer "+usertoken);
        mcall.enqueue(new Callback<Productroot>() {
            @Override
            public void onResponse(Call<Productroot> call, Response<Productroot> response) {

                if (response.isSuccessful())
                {
                    if (response.body().getProducts()!=null)
                    {
                        List<Product> allproducts= response.body().getProducts();
                        for (int i=0;i<allproducts.size();i++)
                        {
                            Product myproduct=allproducts.get(i);
                            mWordViewModel.insertrowinproductlist(myproduct);
                        }
                      //  mWordViewModel.insertallproducts(allproducts);
                    }

                }



            }

            @Override
            public void onFailure(Call<Productroot> call, Throwable t) {

            }
        });
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
