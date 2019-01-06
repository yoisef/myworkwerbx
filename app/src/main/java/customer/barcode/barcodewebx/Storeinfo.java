package customer.barcode.barcodewebx;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;

import java.io.IOException;
import java.net.URI;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import customer.barcode.barcodewebx.Werbx.MainActivity;

public class Storeinfo extends AppCompatActivity {


   private TextView openT, closT, openD, closD, Dleiverytime,imgadd;
    TextView currencychoose;
    private static final int image = 101;
    private android.app.AlertDialog.Builder builder;
    private android.app.AlertDialog chossewaydialog;
    private Button next;
    CurrencyPicker picker;
    private Spinner distruborspinnerr,  subdistributer;
    private LinearLayout addimg;
    private EditText name,email,pass,retypepass,phonenum,delcharg,descr,addressedit;
    private ImageView storeimage,placepicker;
    private Uri uriprofileimage;
    private Boolean imgcond;
    private final static int PLACE_PICKER_REQUEST = 999;
    private Geocoder geocoder;
    private List<Address> addresses;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);

        imgcond=true;
        geocoder = new Geocoder(this, Locale.getDefault());
        name=findViewById(R.id.storernam);
        email=findViewById(R.id.storeemail);
        pass=findViewById(R.id.storepass);
        retypepass=findViewById(R.id.storpassretype);
        addressedit=findViewById(R.id.addresss);
        storeimage=findViewById(R.id.storeimg);
        imgadd=findViewById(R.id.addphototxt);
        placepicker=findViewById(R.id.openplacepicker);


        placepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openplacepicker();

            }
        });




        addimg = findViewById(R.id.relativeLayout);

     //   distruborspinnerr=findViewById(R.id.storedistrbter);
    //    subdistributer=findViewById(R.id.storesubdis);


       addimg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               imgadd.setError(null);
               chooseimg();

           }
       });





    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgcond=false;
               uriprofileimage = data.getData();


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriprofileimage);

               storeimage.setBackground(null);
               storeimage.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (requestCode == 1 && resultCode == RESULT_OK)
        {

            if (data.getExtras()!=null)
            {
                imgcond=false;
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                storeimage.setBackground(null);
                storeimage.setImageBitmap(photo);
            }

            }

            if (requestCode==PLACE_PICKER_REQUEST&&resultCode==RESULT_OK)
            {

                Place place = PlacePicker.getPlace(this, data);
                String placeName = String.format("Place: %s", place.getName());
                double latitude = place.getLatLng().latitude;
                double longitude = place.getLatLng().longitude;

                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addresses!=null)
                {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();

                    addressedit.setText(address+""+city+""+state+""+country);
                }



            }
        }





    private void openplacepicker()
    {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            // for activty
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            // for fragment
            //startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }    }





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
                Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 1);//zero can be replaced with any action code
                chossewaydialog.cancel();

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 0);//one can be replaced with any action code
                chossewaydialog.cancel();

            }
        });

    }


    private void intialzedatapicker()
    {
        openT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openT.setError(null);

                datapicker(openT);

            }
        });

        openD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openD.setError(null);
                datapicker(openD);
            }
        });

        closD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closD.setError(null);

                datapicker(closD);
            }
        });

        closT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closT.setError(null);

                datapicker(closT);
            }
        });
        Dleiverytime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dleiverytime.setError(null);

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
                closettxt,namtxt,imgcondition;

        Drawable imgstore;



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
        addresstxt=addressedit.getText().toString().trim();
        imgcondition=imgadd.getText().toString();



        if (imgcond)
        {
            imgadd.setError(getResources().getString(R.string.imgV));
            imgadd.requestFocus();
            return;

        }


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

        if (closettxt.isEmpty())
        {
            closT.setError(getResources().getString(R.string.closetV));
            closT.requestFocus();
            return;
        }

        if (opedeltxt.isEmpty())
        {
            openD.setError(getResources().getString(R.string.opendV));
            openD.requestFocus();
            return;
        }

        if (closedelitxt.isEmpty())
        {
            closD.setError(getResources().getString(R.string.closedV));
            closD.requestFocus();
            return;
        }

        if (deliverytimetxt.isEmpty())
        {
            Dleiverytime.setError(getResources().getString(R.string.detimeV));
            Dleiverytime.requestFocus();
        }



        if (addresstxt.isEmpty())
        {
            addressedit.setError(getResources().getString(R.string.addressV));
            addressedit.requestFocus();
            return;
        }



        startActivity(new Intent(Storeinfo.this,Retailer_details.class));








    }
}
