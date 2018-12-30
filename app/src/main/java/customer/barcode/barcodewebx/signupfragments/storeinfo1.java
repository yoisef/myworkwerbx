package customer.barcode.barcodewebx.signupfragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;

import customer.barcode.barcodewebx.R;
import customer.barcode.barcodewebx.connection;

public class storeinfo1 extends android.support.v4.app.Fragment {

    Button button;
    connection mconnection;
    CurrencyPicker picker;
    Context context;
    Spinner distruborspinner,subdistributer;
    TextView choose_curency;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.store1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subdistributer=view.findViewById(R.id.storesubdis);
        choose_curency=view.findViewById(R.id.choosecurrency);



        intializespinners(view);


        mconnection= (connection) getActivity();
        context=getActivity();

        picker = CurrencyPicker.newInstance("Select Currency");  // dialog title
        picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {

                picker.dismiss();

                choose_curency.setText(name);
            }
        });

        choose_curency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.show(getFragmentManager(), "CURRENCY_PICKER");
            }
        });



        button=view.findViewById(R.id.nextstep);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mconnection.changevisibltyfrag2();
            }
        });
    }

    public void intializespinners(View view)
    {
        distruborspinner=view.findViewById(R.id.storedistrbter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.distrbuter, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distruborspinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.subdist, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subdistributer.setAdapter(adapter1);


    }
}
