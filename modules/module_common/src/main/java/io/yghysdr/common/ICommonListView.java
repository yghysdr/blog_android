package io.yghysdr.common;


import com.github.yghysdr.http.HttpException;
import io.blog.modle.BaseResponse;

public interface ICommonListView {
    void onListSuccess(BaseResponse response);

    void onListFailed(HttpException exception);
}
