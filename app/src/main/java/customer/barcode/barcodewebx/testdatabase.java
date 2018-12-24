package customer.barcode.barcodewebx;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;
import customer.barcode.barcodewebx.productdatabasemodels.Product;

public class testdatabase extends AppCompatActivity {

    private productViewmodel mWordViewModel;
    TextView mytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdatabase);
        mytext=findViewById(R.id.testdata);

        mWordViewModel = ViewModelProviders.of(this, new factoryclass(this.getApplication(),62211611)).get(productViewmodel.class);




    mWordViewModel.getrowdetails(62211611).observe(this, new Observer<Product>() {
        @Override
        public void onChanged(@Nullable Product product) {


            Toast.makeText(testdatabase.this,product.getName(),Toast.LENGTH_SHORT).show();
        }



       //   mytext.setText();

    });

    }
}
