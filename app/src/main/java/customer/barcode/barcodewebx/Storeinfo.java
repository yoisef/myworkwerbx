package customer.barcode.barcodewebx;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;

import java.io.IOException;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import customer.barcode.barcodewebx.Werbx.MainActivity;

public class Storeinfo extends AppCompatActivity {


    TextView openT, closT, openD, closD, Dleiverytime;
    TextView currencychoose;
    private static final int image = 101;
    private android.app.AlertDialog.Builder builder;
    private android.app.AlertDialog chossewaydialog;
    private Button next;
    CurrencyPicker picker;
    private Spinner distruborspinnerr,  subdistributer;
    private RelativeLayout addimg;
    private EditText name,email,pass,retypepass,phonenum,delcharg,descr,address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);

        name=findViewById(R.id.storernam);
        email=findViewById(R.id.storeemail);
        pass=findViewById(R.id.storepass);
        retypepass=findViewById(R.id.storpassretype);
        phonenum=findViewById(R.id.phonenum);
        delcharg=findViewById(R.id.delchargec);
        address=findViewById(R.id.addresss);




        addimg = findViewById(R.id.relativeLayout);

     //   distruborspinnerr=findViewById(R.id.storedistrbter);
    //    subdistributer=findViewById(R.id.storesubdis);
        intializespinners();

        openT = findViewById(R.id.Optime);
        openD = findViewById(R.id.opdeliv);
        closT = findViewById(R.id.clostim);
        closD = findViewById(R.id.clodeliv);
        currencychoose=findViewById(R.id.choosecurrency);
        next=findViewById(R.id.nextstep);
        Dleiverytime = findViewById(R.id.delverytime);

        intialzedatapicker();

        picker = CurrencyPicker.newInstance("Select Currency");  // dialog title
        picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                currencychoose.setText(name);
                picker.dismiss();
          }
        });

        currencychoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");
            }
        });


       addimg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               chooseimg();

           }
       });

       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               intilazeviewswithvaildate();

           }
       });



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == image && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //   uriprofileimage = data.getData();
            /*
            try {
               // Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriprofileimage);

              //  proilephoto.setBackground(null);
               // proilephoto.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
*/
        }
    }
    public void intializespinners()
    {
        distruborspinnerr=findViewById(R.id.storedistrbter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.distrbuter, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distruborspinnerr.setAdapter(adapter);

        subdistributer=findViewById(R.id.storesubdis);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.subdist, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subdistributer.setAdapter(adapter1);


    }




    private void chooseimg()
    {
        LinearLayout camera,gallery;

        builder = new android.app.AlertDialog.Builder(Storeinfo.this);

        View myview = LayoutInflater.from(Storeinfo.this.getApplicationContext()).inflate(R.layout.chooselayoutfortakepic, null);
        camera=myview.findViewById(R.id.takcam);
        gallery=myview.findViewById(R.id.takgalee);
        builder.setView(myview);
        chossewaydialog = builder.create();
        chossewaydialog.show();

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);//zero can be replaced with any action code

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code

            }
        });

    }


    private void intialzedatapicker()
    {
        openT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datapicker(openT);

            }
        });

        openD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datapicker(openD);
            }
        });

        closD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datapicker(closD);
            }
        });

        closT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datapicker(closT);
            }
        });
        Dleiverytime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datapicker(Dleiverytime);
            }
        });
    }

    public void datapicker(final TextView Mtext)
    {
        final Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

     TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(Storeinfo.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

               String time= getTime(selectedHour,selectedMinute);
                Mtext.setText( time);

            }
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private String getTime(int hr,int min) {
        Time tme = new Time(hr,min,0);//seconds by default set to zero
        Format formatter;
        formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(tme);
    }


    private void intilazeviewswithvaildate()
    {
        String emailtxt,passtxt,passretpetxt,phonenumtxt,delchargetxt,addresstxt,
                ChoseCuretxt,openttxt,clottxt,opedeltxt,closedelitxt,deliverytimetxt,
                closettxt,namtxt;



        namtxt =name.getText().toString().trim();
        emailtxt=email.getText().toString().trim();
        passtxt=pass.getText().toString().trim();
        passretpetxt=retypepass.getText().toString().trim();
        phonenumtxt=phonenum.getText().toString().trim();
        delchargetxt=delcharg.getText().toString().trim();
        ChoseCuretxt=currencychoose.getText().toString().trim();
        openttxt=openT.getText().toString().trim();
        closettxt=closT.getText().toString().trim();
        opedeltxt=openD.getText().toString().trim();
        closedelitxt=closD.getText().toString().trim();
        deliverytimetxt=Dleiverytime.getText().toString().trim();
        addresstxt=address.getText().toString().trim();



        if (namtxt.isEmpty())
        {
            name.setError(getResources().getString(R.string.namV));
            name.requestFocus();

            return ;
        }

        if (emailtxt.isEmpty())
        {
            email.setError(getResources().getString(R.string.emailV));
            email.requestFocus();
            return ;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches())
        {
            email.setError(getResources().getString(R.string.vaildemailV));
            email.requestFocus();
            return ;
        }
        if (passtxt.isEmpty())
        {
            pass.setError(getResources().getString(R.string.passV));
            pass.requestFocus();
            return;
        }
        if (passtxt.length()<6)
        {
            pass.setError(getResources().getString(R.string.minumV));
            pass.requestFocus();
            return;
        }
        if (passretpetxt.isEmpty())
        {
            retypepass.setError(getResources().getString(R.string.passRV));
            retypepass.requestFocus();
            return;
        }
        if (passretpetxt.length()<6) {
            retypepass.setError(getResources().getString(R.string.passRV));
            retypepass.requestFocus();
            return;
        }

        if (!passretpetxt.equals(passtxt))
        {
            retypepass.setError(getResources().getString(R.string.passRV));
            retypepass.requestFocus();
            return;
        }
        if (phonenumtxt.isEmpty())
        {
            phonenum.setError(getResources().getString(R.string.phoneV));
            phonenum.requestFocus();
            return;
        }
        if (delchargetxt.isEmpty())
        {
            delcharg.setError(getResources().getString(R.string.delchargeV));
            delcharg.requestFocus();
            return;
        }
        if (ChoseCuretxt.isEmpty())
        {
             currencychoose.setError(getResources().getString(R.string.currencyV));
             currencychoose.requestFocus();
             return;
        }
        else {
            currencychoose.setError(null);
        }

        if (distruborspinnerr.getSelectedItemPosition()==0)
        {
            ((TextView)distruborspinnerr.getSelectedView()).setError(getResources().getString(R.string.distuboterV));
            ((TextView)distruborspinnerr.getSelectedView()).requestFocus();
            return;

        }
        if (subdistributer.getSelectedItemPosition()==0)
        {
            ((TextView)subdistributer.getSelectedView()).setError(getResources().getString(R.string.subdistV));
            ((TextView)subdistributer.getSelectedView()).requestFocus();
            return;
        }
        if (openttxt.isEmpty())
        {
            openT.setError(getResources().getString(R.string.opentV));
            openT.requestFocus();
            return;
        }
        else {
            openT.setError(null);

        }
        if (closettxt.isEmpty())
        {
            closT.setError(getResources().getString(R.string.closetV));
            closT.requestFocus();
            return;
        }
        else {
            closT.setError(null);
        }
        if (opedeltxt.isEmpty())
        {
            openD.setError(getResources().getString(R.string.opendV));
            openD.requestFocus();
            return;
        }
        else{
            openD.setError(null);
        }
        if (closedelitxt.isEmpty())
        {
            closD.setError(getResources().getString(R.string.closedV));
            closD.requestFocus();
            return;
        }
        else {
            closD.setError(null);
        }
        if (deliverytimetxt.isEmpty())
        {
            Dleiverytime.setError(getResources().getString(R.string.detimeV));
            Dleiverytime.requestFocus();
        }
        else {
            Dleiverytime.setError(null);
        }


        if (addresstxt.isEmpty())
        {
            address.setError(getResources().getString(R.string.addressV));
            address.requestFocus();
            return;
        }
        else {
            address.setError(null);
        }


        startActivity(new Intent(Storeinfo.this,Retailer_details.class));








    }
}
