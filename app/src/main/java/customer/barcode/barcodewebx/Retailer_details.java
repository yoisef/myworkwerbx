package customer.barcode.barcodewebx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.security.PrivateKey;

public class Retailer_details extends AppCompatActivity {


    private Spinner retailertype;
    private TextView currency;
    private EditText storeid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_details);

        storeid=findViewById(R.id.storid);

        Intent intent=getIntent();
      int condition=  intent.getIntExtra("havestore",0);

      if (condition==0)
      {
          storeid.setVisibility(View.GONE);
      }


        retailertype=findViewById(R.id.retailerspinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.levels, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        retailertype.setAdapter(adapter);
    }

    private void registeruser() {

    /*
        String email=emailedit.getText().toString();
        String passwordd=passwordedit.getText().toString().trim();
        String retpingpass=Retypingpass.getText().toString().trim();


        if (email.isEmpty())
        {
            emailedit.setError("email is required");
            emailedit.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailedit.setError("please Enter a valid email");
            emailedit.requestFocus();
            return;
        }
        if (passwordd.isEmpty())
        {
            passwordedit.setError("Password is required");
            passwordedit.requestFocus();
            return;
        }
        if (passwordd.length()<6)
        {
            passwordedit.setError("Minimum Length of password should be 6");
            passwordedit.requestFocus();
            return;
        }
        if (retpingpass.isEmpty())
        {
            Retypingpass.setError("Password is required");
            Retypingpass.requestFocus();
            return;
        }
        if (retpingpass.length()<6) {
            Retypingpass.setError("Minimum Length of password should be 6");
            Retypingpass.requestFocus();
            return;
        }

        if (!retpingpass.equals(passwordd))

        */

    }
}
