package io.yghysdr.blog.common;


import com.github.yghysdr.http.HttpException;
import io.blog.res.BaseResponse;

public interface ICommonListView {
    void onListSuccess(BaseResponse response);

    void onListFailed(HttpException exception);
}
