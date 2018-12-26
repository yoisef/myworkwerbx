package customer.barcode.barcodewebx;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
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
        View itemView = mInflater.inflate(R.layout.rowcashier, parent, false);
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

            SharedPreferences rowandnum=con.getSharedPreferences("we",Context.MODE_PRIVATE);
           int qua= rowandnum.getInt("is",87);

           holder.showitems_number.setText(String.valueOf(current.getPitemn()));

           Double coast=current.getPitemn()*Double.parseDouble(current.getPprice());

           holder.total_itemscoast.setText(String.valueOf(coast));

           holder.unitprice.setText(current.getPprice());

           holder.barcodeee.setText(current.getPbar());
           holder.add_items.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   int num=Integer.parseInt(holder.showitems_number.getText().toString());
                   int currentnum=num+1;
                   holder.showitems_number.setText(String.valueOf(currentnum));
                   Double Uprice=Double.parseDouble(current.getPprice());

                   Double totalpriceP=currentnum*Uprice;
                   holder.total_itemscoast.setText(String.valueOf(totalpriceP));
                   mWordViewModel.updateproduct(Long.parseLong(holder.showitems_number.getText().toString()),Long.parseLong(current.getPbar()));
               }
           });

           holder.remove_item.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int num=Integer.parseInt(holder.showitems_number.getText().toString());
                   if (num <= 1) {
                       holder.showitems_number.setText(String.valueOf(1));

                   } else {
                       int cunum=num-1;
                       holder.showitems_number.setText(String.valueOf(cunum));
                       Double Uprice=Double.parseDouble(current.getPprice());
                      Double totalp=Uprice*cunum;
                       holder.total_itemscoast.setText(String.valueOf(totalp));
                       mWordViewModel.updateproduct(Long.parseLong(holder.showitems_number.getText().toString()),Long.parseLong(current.getPbar()));

                   }
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
                   holder.delete_product.setOnClickListener(new View.OnClickListener() {
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

    void deleterow(int i)
    {
        if (mWords.size()!=0){
            mWordViewModel.delterow(mWords.get(i));
            mWords.remove(i);
            notifyItemRemoved(i);
            notifyDataSetChanged();
        }

    }

    int getadapterposition()
    {
      int i = getadapterposition();
      return i;

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

       ImageView productimage,add_items,remove_item,xremove;
        SwipeLayout rowrecycle;
        TextView namee,unitprice,barcodeee,total_itemscoast,delete_product;
     EditText showitems_number;

        public viewholder(View itemView) {
            super(itemView);


            namee = itemView.findViewById(R.id.nameproduct);
            rowrecycle=itemView.findViewById(R.id.rowswipe);
            barcodeee=itemView.findViewById(R.id.numberproduct);
            total_itemscoast=itemView.findViewById(R.id.totalitemsc);
            productimage=itemView.findViewById(R.id.productimg);
            add_items=itemView.findViewById(R.id.additemsc);
            remove_item=itemView.findViewById(R.id.removeitemsc);
            showitems_number=itemView.findViewById(R.id.itemsnumberc);
            unitprice=itemView.findViewById(R.id.unitpricec);
            delete_product=itemView.findViewById(R.id.deleterow);
            xremove=itemView.findViewById(R.id.xsign);




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
