package customer.barcode.barcodewebx;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import customer.barcode.barcodewebx.RoomDatabase.mytable;
import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;


public class Recycleadapter extends RecyclerView.Adapter<Recycleadapter.viewholder> {

  private   Context con;
    private productViewmodel mWordViewModel;




    private final LayoutInflater mInflater;
    private List<mytable> mWords; // Cached copy of words

    Recycleadapter(Context context) {
        this.con=context;
        mInflater = LayoutInflater.from(context);


        mWordViewModel = ViewModelProviders.of((FragmentActivity) context).get(productViewmodel.class);

    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.rowrecycle, parent, false);
        return new viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(final viewholder holder, final int position) {
        if (mWords != null) {
            final mytable current = mWords.get(position);
            holder.namee.setText(current.getPname());
            Glide.with(con)
                    .load(current.getPimg())
                    .into(holder.productimage);

            holder.numberr.setText(current.getPbar());
            holder.pricee.setText(current.getPprice());
            holder.productdetailss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myintent=new Intent(con , Productdetails.class);
                    myintent.putExtra("barnum",current.getPbar());
                    con.startActivity(myintent);

                }
            });


            holder.removeimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.rowrecycle.open();
                }
            });
            holder.rowrecycle.setShowMode(SwipeLayout.ShowMode.PullOut);
           holder.rowrecycle.addSwipeListener(new SwipeLayout.SwipeListener() {
               @Override
               public void onStartOpen(SwipeLayout layout) {

               }

               @Override
               public void onOpen(SwipeLayout layout) {

                   Animation animation=AnimationUtils.loadAnimation(con,R.anim.notify);
                   holder.xremove.startAnimation(animation);
                   holder.deleterowww.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           int i=holder.getAdapterPosition();


                           mWordViewModel.delterow(mWords.get(i));
                           mWords.remove(i);
                           notifyItemRemoved(i);
                           notifyDataSetChanged();




                       }
                   });

               }

               @Override
               public void onStartClose(SwipeLayout layout) {

               }

               @Override
               public void onClose(SwipeLayout layout) {

               }

               @Override
               public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

               }

               @Override
               public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

               }
           });
        } else {
            // Covers the case of data not being ready yet.
            holder.namee.setText("No Word");
        }
    }

    void setWords(List<mytable> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }


    class viewholder extends RecyclerView.ViewHolder {

        TextView namee, numberr, pricee, deleterowww;
        ImageView productimage, removeimg;
        ImageView xremove;
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



        }
    }

   /*
    public List<productmodel> getMylist() {
        return mylist;
    }
    public List<String> getPrices() {
        return prices;
    }


    public void removeItem(int position) {
        mylist.remove(position);
        notifyItemRemoved(position);
    }
    public List<String> getKeys() {
        return keys;
    }
    */



}
