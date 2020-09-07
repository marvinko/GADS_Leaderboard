package com.andela.gads.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SubmitClient {
    @POST("1FAIpQLSdbmFa2dc-PJjA2arJva5Q9g0Egc7qf3rRIQA0S9NobOUszUA/formResponse")
    @FormUrlEncoded
    Call<ResponseBody> sendProject(
            @Field("entry.2054702199") String email,
            @Field("entry.943723235") String name,
            @Field("entry.504602641") String lastname,
            @Field("entry.116761455") String linkproject
    );
}


