package com.example.cashflow.APiCall;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {


    private static final String url="https://www.mycompany.com/hub/api/rest/ ";
    private static apicontroller clienObj;
    private static Retrofit retrofit;

    apicontroller(){
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized  apicontroller getInstance()
    {
        if(clienObj==null)
            clienObj=new apicontroller();
        return clienObj;
    }
    apiset getApi()
    {
        return   retrofit.create(apiset.class);

    }


}
