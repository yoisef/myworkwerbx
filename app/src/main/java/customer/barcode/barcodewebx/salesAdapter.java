package customer.barcode.barcodewebx;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import customer.barcode.barcodewebx.RoomDatabase.historytable;
import customer.barcode.barcodewebx.RoomDatabase.mytable;
import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;

public class salesAdapter extends RecyclerView.Adapter<salesAdapter.viewholder> {

    private   Context con;
    private productViewmodel mHistoryViewModel;




    private final LayoutInflater mInflater;
    private List<historytable> mHistory; // Cached copy of words

    salesAdapter(Context context) {
        this.con=context;
        mInflater = LayoutInflater.from(context);

        mHistory=new ArrayList<>();


        mHistoryViewModel = ViewModelProviders.of((FragmentActivity) context).get(productViewmodel.class);

    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.rowhistory, parent, false);
        return new salesAdapter.viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        if (mHistory!=null)
        {

            historytable mytable=mHistory.get(position);
            holder.orderid.setText(String.valueOf(position+1));

            holder.oredramount.setText(mytable.getOramount());
            holder.orderunits.setText(mytable.getOrunits());


            holder.orderdata.setText(mytable.getOrdata());
        }



    }

    void setHistory(List<historytable> words) {
        mHistory = words;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mHistory.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        TextView orderdata,orderid,oredramount,orderunits;


        public viewholder(View itemView) {
            super(itemView);

            orderdata=itemView.findViewById(R.id.txt_orderDateTime);
            orderid=itemView.findViewById(R.id.id_order);
            oredramount=itemView.findViewById(R.id.totalordercoast);
            orderunits=itemView.findViewById(R.id.txt_orderQuantity);
        }
    }
}
