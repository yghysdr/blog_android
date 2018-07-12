package io.yghysdr.login;

import io.blog.res.BaseResponse;
import io.blog.res.bean.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginApi {
    @POST("api/auth/login")
    Observable<BaseResponse<User>> login(@Body User user);
}
