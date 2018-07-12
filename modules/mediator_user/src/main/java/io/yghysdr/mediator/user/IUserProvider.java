package io.yghysdr.mediator.user;

import com.alibaba.android.arouter.facade.template.IProvider;

import io.blog.res.bean.User;

public interface IUserProvider extends IProvider {
    User getUser();

    void updateUser(User user);

    void clearUser();
}
