package customer.barcode.barcodewebx.Werbx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import customer.barcode.barcodewebx.R;
import customer.barcode.barcodewebx.Storeinfo;

public class Choose_Store extends AppCompatActivity {


    TextView addstore,havesto;
   LinearLayout enterstoreid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);


        havesto=findViewById(R.id.havestore);
        addstore=findViewById(R.id.newstoree);
        enterstoreid=findViewById(R.id.storenum);

        havesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enterstoreid.setVisibility(View.VISIBLE);

            }
        });

        addstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Choose_Store.this,Storeinfo.class));
            }
        });
    }
}
