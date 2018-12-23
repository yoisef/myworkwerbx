package customer.barcode.barcodewebx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;

import java.util.List;

import customer.barcode.barcodewebx.RoomDatabase.mytable;

public class detailsadapter extends RecyclerView.Adapter<detailsadapter.viewholder> {

    public List<mytable> details;
    public Context context;

    public detailsadapter(List<mytable> listdetails,Context con)
    {
        this.details=listdetails;
        this.context=con;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(context).inflate(R.layout.rowrecycledetails,parent,false);
        viewholder viewholder=new viewholder(myview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.namee.setText(details.get(position).getPname());
        holder.pricee.setText(details.get(position).getPprice());
        holder.nuofproductitem.setText(String.valueOf(details.get(position).getPitemn()));

        Glide.with(context)
                .load(details.get(position).getPimg())
                .into(holder.productimage);

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        TextView namee, numberr, pricee, deleterowww,nuofproductitem;
        ImageView productimage, removeimg;
        ImageView  xremove;
        RelativeLayout removerow, productdetailss, backlayout;
        LinearLayout toplayout;
        SwipeLayout rowrecycle;

        public viewholder(View itemView) {
            super(itemView);

            namee = itemView.findViewById(R.id.nameproduct);
            xremove=itemView.findViewById(R.id.xsign);
            rowrecycle=itemView.findViewById(R.id.myrow);
            numberr=itemView.findViewById(R.id.numberproduct);
            pricee=itemView.findViewById(R.id.itempricee);
            productimage=itemView.findViewById(R.id.productimg);
            removeimg=itemView.findViewById(R.id.remove);
            productdetailss=itemView.findViewById(R.id.productdetails);
            deleterowww=itemView.findViewById(R.id.deleterow);
            backlayout=itemView.findViewById(R.id.background);
            toplayout=itemView.findViewById(R.id.foregoroundd);
            nuofproductitem=itemView.findViewById(R.id.numberofitems);
        }
    }
}
