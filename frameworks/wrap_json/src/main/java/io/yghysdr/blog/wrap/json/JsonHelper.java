package io.yghysdr.blog.wrap.json;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class JsonHelper {

    private static final ThreadLocal<Gson> reuseGson = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            return new Gson();
        }
    };

    /**
     * 获取Gson对象
     *
     * @return
     */
    private static Gson getGson() {
        return reuseGson.get();
    }

    /**
     * object to json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return getGson().toJson(object);
    }

    /**
     * json字符串转Object
     *
     * @param string
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String string, Class<T> clazz) {
        return getGson().fromJson(string, clazz);
    }

    /**
     * json字符串转Object
     * @param jsonList
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJsonList(String jsonList, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        return new Gson().fromJson(jsonList, type);
    }

    static class ParameterizedTypeImpl implements ParameterizedType {

        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
