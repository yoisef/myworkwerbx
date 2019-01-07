package customer.barcode.barcodewebx;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.Observable;

import customer.barcode.barcodewebx.Werbx.MainActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Storeinfo extends AppCompatActivity {


   private TextView openT, closT, openD, closD, Dleiverytime,imgadd;
    TextView currencychoose;
    private static final int image = 101;
    private android.app.AlertDialog.Builder builder;
    private android.app.AlertDialog chossewaydialog;
    private Button register_store;
    CurrencyPicker picker;
    private Spinner distruborspinnerr,  subdistributer;
    private LinearLayout addimg;

    private ImageView storeimage,placepicker;
    private Uri uriprofileimage;
    private Boolean imgcond;
    private final static int PLACE_PICKER_REQUEST = 999;
    private Geocoder geocoder;
    private List<Address> addresses;
    private EditText Store_Name,Store_Admin,Store_pass,Store_passconfirm,Store_phone,Store_desc,Store_deletecharge,Store_address;
    private Call<ResponseBody> uploadcall;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);

        imgcond=true;
        geocoder = new Geocoder(this, Locale.getDefault());
        Store_Name=findViewById(R.id.storernam);
        Store_phone=findViewById(R.id.storephone);
        Store_pass=findViewById(R.id.storepass);
        Store_passconfirm=findViewById(R.id.storpassconfirm);
        register_store=findViewById(R.id.storesubsc);
        Store_address=findViewById(R.id.storeaddress);
        Store_desc=findViewById(R.id.storedescription);
        Store_Admin=findViewById(R.id.storeadmin);
        storeimage=findViewById(R.id.storeimg);
        imgadd=findViewById(R.id.addphototxt);
        placepicker=findViewById(R.id.openplacepicker);



        register_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intilazeviewswithvaildate();

            }
        });


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
               File myfile=new File(uriprofileimage.getPath());

            String filePath = getRealPathFromURIPath(uriprofileimage, Storeinfo.this);
            File file = new File(filePath);

            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
            RequestBody type = RequestBody.create(MediaType.parse("text/plain"), "3");


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriprofileimage);


                File f = new File(this.getCacheDir(), "file");
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

//Convert bitmap to byte array



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

                uploadcall=myendpoints.uploadimg(fileToUpload,type);
                uploadcall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful())
                        {
                            Toast.makeText(Storeinfo.this,"uploaded",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(Storeinfo.this,"not uploaded",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
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

                    Store_address.setText(address+""+city+""+state+""+country);
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

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
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


   /*

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
    */


    private void intilazeviewswithvaildate()
    {
        String namestore,addressstore,adminstore,phonestore,passstore,confipassstore,descstore,deletstore;



        namestore =Store_Name.getText().toString().trim();
        addressstore=Store_address.getText().toString().trim();
        adminstore=Store_Admin.getText().toString().trim();
        phonestore=Store_phone.getText().toString().trim();
        passstore=Store_pass.getText().toString().trim();
        confipassstore=Store_passconfirm.getText().toString().trim();
        descstore=Store_desc.getText().toString().trim();






        if (imgcond)
        {
            imgadd.setError(getResources().getString(R.string.imgV));
            imgadd.requestFocus();
            return;
        }

        if (namestore.isEmpty())
        {
            Store_Name.setError(getResources().getString(R.string.enterstorenum));
            Store_Name.requestFocus();
            return;
        }


        if (addressstore.isEmpty())
        {
            Store_address.setError(getResources().getString(R.string.emailV));
            Store_address.requestFocus();
            return ;
        }

        if (adminstore.isEmpty())
        {
            Store_Admin.setError(getResources().getString(R.string.passV));
            Store_Admin.requestFocus();
            return;
        }

        if (phonestore.isEmpty())
        {
            Store_phone.setError(getResources().getString(R.string.phoneV));
            Store_phone.requestFocus();
            return;
        }
        if (passstore.length()<6)
        {
            Store_pass.setError(getResources().getString(R.string.minumV));
           Store_pass.requestFocus();
            return;
        }
        if (confipassstore.isEmpty())
        {
            Store_passconfirm.setError(getResources().getString(R.string.passRV));
           Store_passconfirm.requestFocus();
            return;
        }


        if (!confipassstore.equals(passstore))
        {
            Store_passconfirm.setError(getResources().getString(R.string.passRV));
            Store_passconfirm.requestFocus();
            return;
        }




        startActivity(new Intent(Storeinfo.this,MainActivity.class));








    }
}
