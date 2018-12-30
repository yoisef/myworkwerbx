package customer.barcode.barcodewebx.signupfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import customer.barcode.barcodewebx.R;
import customer.barcode.barcodewebx.connection;

public class retailerinfo1 extends android.support.v4.app.Fragment {
    private Spinner levelspinner;

    connection mconnection;
    Button next;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.retainfo1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mconnection= (connection) getActivity();

        levelspinner=view.findViewById(R.id.retailerspinner);
        next=view.findViewById(R.id.register);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.levels, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelspinner.setAdapter(adapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mconnection.changevisibiltyfrag1();
            }
        });

    }
}
