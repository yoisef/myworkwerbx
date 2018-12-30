package customer.barcode.barcodewebx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Storeinfo extends AppCompatActivity {


    EditText openT,closT,openD,closD,Dleiverytime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);

        openT=findViewById(R.id.Optime);
        openD=findViewById(R.id.opdeliv);
        closT=findViewById(R.id.clostim);
        closD=findViewById(R.id.clodeliv);
        Dleiverytime=findViewById(R.id.delverytime);
    }
}
