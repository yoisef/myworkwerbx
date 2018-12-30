package customer.barcode.barcodewebx;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import customer.barcode.barcodewebx.RoomDatabase.mytable;
import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;

public class mytextwatcher implements TextWatcher {

    private productViewmodel mWordViewModel;


    EditText myedirtext;
    Context context;

    public   mytextwatcher(EditText editText, Context con)
    {
        this.myedirtext=editText;
        this.context=con;
        mWordViewModel = ViewModelProviders.of((FragmentActivity) con).get(productViewmodel.class);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        mytable pos= (mytable) myedirtext.getTag();
        mWordViewModel.updateproduct(Long.parseLong(myedirtext.getText().toString()),Long.parseLong(pos.getPbar()));


    }
}