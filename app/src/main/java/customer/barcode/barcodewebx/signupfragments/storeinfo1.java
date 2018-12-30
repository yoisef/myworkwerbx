package customer.barcode.barcodewebx.signupfragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;

import customer.barcode.barcodewebx.R;
import customer.barcode.barcodewebx.connection;

public class storeinfo1 extends android.support.v4.app.Fragment {

    Button button;
    connection mconnection;
    CurrencyPicker picker;
    Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.store1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mconnection= (connection) getActivity();
        context=getActivity();

        picker = CurrencyPicker.newInstance("Select Currency");  // dialog title
        picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                // Implement your code here
            }
        });
        picker.show(getFragmentManager(), "CURRENCY_PICKER");

        button=view.findViewById(R.id.btn2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mconnection.changevisibltyfrag2();
            }
        });
    }
}
