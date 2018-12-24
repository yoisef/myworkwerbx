package customer.barcode.barcodewebx;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;


public class testdatabase extends AppCompatActivity {

    private productViewmodel mWordViewModel;
    TextView mytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdatabase);
        mytext=findViewById(R.id.testdata);






    }
}
