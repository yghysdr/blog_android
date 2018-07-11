package io.yghysdr.login;

import io.blog.modle.BaseResponse;
import io.blog.modle.bean.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginApi {
    @POST("api/auth/login")
    Observable<BaseResponse<User>> login(@Body User user);
}
