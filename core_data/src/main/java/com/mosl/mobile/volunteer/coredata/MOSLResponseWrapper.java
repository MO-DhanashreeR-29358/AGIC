package com.mosl.mobile.volunteer.coredata;

import com.google.gson.reflect.TypeToken;
import com.mosl.annotations.ExcludeWrapper;
import com.mosl.app.core.exception.ServiceFailureException;
import com.mosl.data.api.BaseResponseAgic;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by lalkishore on 3/1/2017.
 */

public class MOSLResponseWrapper extends Converter.Factory {

  @Inject MOSLResponseWrapper() {
  }

  protected static Class<?> getRawType(Type type) {
    if (type instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) type;
      Type rawType = parameterizedType.getRawType();
      if (!(rawType instanceof Class)) throw new IllegalArgumentException();
      return (Class<?>) rawType;
    }
    throw new IllegalArgumentException();
  }

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
      Retrofit retrofit) {
    if (isWrapped(type)) {
      return null;
    }
    for (Annotation annotation : annotations) {
      if (annotation.annotationType() == ExcludeWrapper.class) {
        return null;
      }
    }
    Type envelopedType = TypeToken.getParameterized(BaseResponseAgic.class, type).getType();
    final Converter<ResponseBody, BaseResponseAgic<?>> delegate =
        retrofit.nextResponseBodyConverter(this, envelopedType, annotations);
    return (Converter<ResponseBody, Object>) value -> {
      BaseResponseAgic<?> baseResponse = delegate.convert(value);
      String code = "ErrorOccurred";
      String message = "ErrorOccurred";
      if (baseResponse != null) {
        if (baseResponse.isSuccessful()) {
          return baseResponse.data;
        }
        code = baseResponse.getMessage();

      }
      throw new ServiceFailureException(code, baseResponse.getMessage());
    };
  }

  private boolean isWrapped(Type type) {
    if (type instanceof ParameterizedType) {
      Class<?> rawType = getRawType(type);
      return (rawType == BaseResponseAgic.class);
    }
    return false;
  }
}




