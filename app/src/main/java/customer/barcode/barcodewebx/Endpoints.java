package customer.barcode.barcodewebx;


import customer.barcode.barcodewebx.modelsauth.Roottoken;

import customer.barcode.barcodewebx.productmodels.Rootproductdetail;
import customer.barcode.barcodewebx.salemodel.Saleroot;
import customer.barcode.barcodewebx.usermodels.Userroot;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Endpoints {



    @POST("barcode")
    Call<Rootproductdetail> getdetails(@Header("Authorization")String token, @Query("barcode") String barcodeproduct);

    @FormUrlEncoded
    @POST("login")
    Call<Roottoken> signuser(@Header("Content-Type") String content, @Field("email")String email, @Field("password") String string);



    @GET("user")
    Call<Userroot> getuserdata(@Header("Authorization") String auth);

    @FormUrlEncoded
    @POST("retailersale")
    Call<Saleroot> getsalecondition(@Field("barcode") String barcode,@Field("quantity")int qua,@Field("retailer_id")String id,@Field("live") Boolean value);




}
