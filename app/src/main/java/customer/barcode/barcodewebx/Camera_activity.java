package customer.barcode.barcodewebx;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.List;

import customer.barcode.barcodewebx.RoomDatabase.mytable;
import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;
import customer.barcode.barcodewebx.productmodels.Rootproductdetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Camera_activity extends AppCompatActivity {
    private BarcodeDetector detector;
    private TextView suborder;
    private SurfaceView cameraView;
    private customer.barcode.barcodewebx.CameraSource cameraSource;
    private Button cancel;
    private FrameLayout myframe;
    private MediaPlayer ring;
    private View leaser;
    LinearLayout changelay;
    String detect = null;
    private productViewmodel mWordViewModel;
    private Switch myswitch;
    String conditionn, finalcon;
    private String usertoken;
    private SharedPreferences prefs,perfscamera;
    private Call<Rootproductdetail> mcall;
    private boolean add;
    private ImageView addbtn,removebtn;
    private EditText itemsnum;
    private LinearLayout layoutitems;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_activity);

        prefs = getSharedPreferences("token", Context.MODE_PRIVATE);
        usertoken = prefs.getString("usertoken", "def");
        mWordViewModel = ViewModelProviders.of(this).get(productViewmodel.class);

        mWordViewModel = ViewModelProviders.of(this).get(productViewmodel.class);

        mWordViewModel.getAllWords().observe(this, new Observer<List<mytable>>() {
            @Override
            public void onChanged(@Nullable final List<mytable> words) {
                // Update the cached copy of the words in the adapter.


            }

        });
    
        leaser = findViewById(R.id.leaserline);
        ring = MediaPlayer.create(Camera_activity.this, R.raw.notif);
        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        suborder=findViewById(R.id.submit);
        addbtn=findViewById(R.id.additem);
        removebtn=findViewById(R.id.removeitem);
        itemsnum=findViewById(R.id.numberofitems);
        layoutitems=findViewById(R.id.itemslayout);
        cancel = (Button) findViewById(R.id.backtomain);
        myframe = (FrameLayout) findViewById(R.id.myframecamera);
        changelay = findViewById(R.id.mydetect);
        myswitch = findViewById(R.id.switchtorch);
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {


            myswitch.post(new Runnable() {
                @Override
                public void run() {

                    myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                            // do something, the isChecked will be
                            if (isChecked) {


                                conditionn = "true";
                                SharedPreferences prefs = getSharedPreferences("condition", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("mycon", conditionn);
                                editor.apply();
                                restart();

                            } else {

                                conditionn = "false";
                                SharedPreferences prefs = getSharedPreferences("condition", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("mycon", conditionn);
                                editor.apply();
                                restart();

                            }


                        }
                    });


                }
            });

        } else {
            myswitch.setVisibility(View.GONE);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("condition", Context.MODE_PRIVATE);
        String conde = prefs.getString("mycon", "false");

        if (conde.equals("true")) {
            //myswitch.setOnCheckedChangeListener (null);
            myswitch.setChecked(true);
            instalcamerawithflash();
        } else {
            instalcamerawirhoutflash();
        }


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });


    }


    public void instalcamerawithflash() {

        detector =
                new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(0)
                        .build();
        if (!detector.isOperational()) {
            //  txtView.setText("Could not set up the detector!");
            Toast.makeText(Camera_activity.this, "Could not set up the detector!", Toast.LENGTH_LONG).show();

            return;
        }


        cameraSource = new customer.barcode.barcodewebx.CameraSource
                .Builder(Camera_activity.this, detector)
                .setRequestedPreviewSize(640, 480)
                .setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)
                .setscanmode(Camera.Parameters.SCENE_MODE_BARCODE)
                .setFlashMode(Camera.Parameters.FLASH_MODE_TORCH)
                .build();


        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                try {


                    if (ActivityCompat.checkSelfPermission(Camera_activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    cameraSource.start(cameraView.getHolder());


                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

                cameraSource.stop();
                //  cameraSource.release();
            }
        });

        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();


                if (barcodes.size() != 0) {
                    changelay.post(new Runnable() {
                        @Override
                        public void run() {
                            ring.start();
                            layoutitems.setVisibility(View.VISIBLE);
                          //  loginwithbarcode(barcodes.valueAt(0).displayValue);
                            cameraSource.stop();
                            Handler myhandler = new Handler();
                            myhandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        if (ActivityCompat.checkSelfPermission(Camera_activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                            // TODO: Consider calling
                                            //    ActivityCompat#requestPermissions
                                            // here to request the missing permissions, and then overriding
                                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                            //                                          int[] grantResults)
                                            // to handle the case where the user grants the permission. See the documentation
                                            // for ActivityCompat#requestPermissions for more details.
                                            return;
                                        }
                                        cameraSource.start();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, 3000);


                        }


                        // changelay.setVisibility(View.VISIBLE);
                        // leaser.setVisibility(View.GONE);
                        // cameraView.setBackground(getResources().getDrawable(R.drawable.camerashapedark));

                        // Intent myintent = new Intent();
                        // myintent.putExtra("open", barcodes.valueAt(0).displayValue);

                        // setResult(CommonStatusCodes.SUCCESS, myintent);
                        // finish();


                    });


                }
            }
        });


    }

    public void instalcamerawirhoutflash() {

        detector =
                new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(0)
                        .build();
        if (!detector.isOperational()) {
            //  txtView.setText("Could not set up the detector!");
            Toast.makeText(Camera_activity.this, "Could not set up the detector!", Toast.LENGTH_LONG).show();

            return;
        }


        cameraSource = new customer.barcode.barcodewebx.CameraSource
                .Builder(Camera_activity.this, detector)
                .setRequestedPreviewSize(640, 480)
                .setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)
                .setscanmode(Camera.Parameters.SCENE_MODE_BARCODE)
                .build();


        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                try {


                    if (ActivityCompat.checkSelfPermission(Camera_activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    cameraSource.start(cameraView.getHolder());

                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

                cameraSource.stop();
//             cameraSource.release();
            }
        });


        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();


                if (barcodes.size() != 0) {
                        changelay.post(new Runnable() {
                            @Override
                            public void run() {
                                synchronized (this)
                                {
                                    SharedPreferences sharedPreferences=getSharedPreferences("productbar",Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putString("bar",barcodes.valueAt(0).displayValue);
                                    editor.apply();
                                    layoutitems.setVisibility(View.VISIBLE);
                                    ring.start();
                                    cameraSource.stop();
                                }







                            }
                        });




                    }



                        }


                        // changelay.setVisibility(View.VISIBLE);
                        // leaser.setVisibility(View.GONE);
                        // cameraView.setBackground(getResources().getDrawable(R.drawable.camerashapedark));

                        // Intent myintent = new Intent();
                        // myintent.putExtra("open", barcodes.valueAt(0).displayValue);

                        // setResult(CommonStatusCodes.SUCCESS, myintent);
                        // finish();








        });
        suborder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=itemsnum.getText().toString();
                presssubmitaction(Integer.parseInt(num));
                layoutitems.setVisibility(View.GONE);
                resumecamera();
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int currentnum=Integer.parseInt(itemsnum.getText().toString());
              itemsnum.setText(String.valueOf(currentnum+1));

            }
        });
        removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentnum=  Integer.parseInt(itemsnum.getText().toString());
                itemsnum.setText(String.valueOf(currentnum-1));

            }
        });

    }


    private void loginwithbarcode(final String barcodedata, final int itemsnum) {
        Retrofitclient myretro = Retrofitclient.getInstance();
        Retrofit retrofitt = myretro.getretro();


        final Endpoints myendpoints = retrofitt.create(Endpoints.class);

        mcall = myendpoints.getdetails("Bearer " + usertoken, barcodedata);
        mcall.enqueue(new Callback<Rootproductdetail>() {
            @Override
            public void onResponse(Call<Rootproductdetail> call, Response<Rootproductdetail> response) {

                if (response.isSuccessful()) {


                    if (response.body().getProduct() != null) {

                        String pronam, prodbar, prodimg, broddetail, brodprice, prodcat;
                        pronam = response.body().getProduct().getName();
                        prodbar = response.body().getProduct().getBarcode();
                        prodimg = response.body().getProduct().getImage().getUrl();
                        broddetail = response.body().getProduct().getDescription();
                        brodprice = response.body().getProduct().getPrice();
                        prodcat = response.body().getProduct().getCategory().getName();
                        mytable word = new mytable(pronam, prodbar,itemsnum, prodimg, broddetail, brodprice, prodcat);
                        mWordViewModel.insert(word);



                    } else {
                        Toast.makeText(Camera_activity.this, "not recorded in database", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(Camera_activity.this, "not recorded in database", Toast.LENGTH_LONG).show();


                }


            }

            @Override
            public void onFailure(Call<Rootproductdetail> call, Throwable t) {

                mytable word = new mytable(getResources().getString(R.string.defayltproductname), barcodedata, null, null, null, null,null);
                mWordViewModel.insert(word);


            }
        });



    }

    private void resumecamera()
    {
        Handler myhandler = new Handler();
        myhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ActivityCompat.checkSelfPermission(Camera_activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        },1000);
    }


    public void restart ()
    {
        this.recreate();
    }

    private void presssubmitaction(int number)
    {
        SharedPreferences preferences=getSharedPreferences("productbar",Context.MODE_PRIVATE);
        String barcod=preferences.getString("bar","null");
        loginwithbarcode(barcod,number);
        resumecamera();
        layoutitems.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        cameraSource.stop();
        cameraSource.release();
    }
}














