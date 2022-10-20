package com.example.cashflow.APiCall;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiset {

  @FormUrlEncoded
    @POST("Descrption.php")
         Call<ResponseModel> getRegister(
                 @Field("Reciepts") String name,
                 @Field("transactions") String transactions,
                 @Field("Savings") String savings,
                 @Field("Expenditure") String expenditure

         );
}