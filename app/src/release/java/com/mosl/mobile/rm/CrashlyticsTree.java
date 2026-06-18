package com.mosl.mobile.rm;

import android.util.Log;
import androidx.annotation.NonNull;
import com.crashlytics.android.Crashlytics;
import com.mosl.app.core.exception.DataException;
import com.mosl.app.core.exception.ServiceFailureException;
import com.mosl.app.core.exception.WebCallException;
import com.mosl.validator.exception.ValidationException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.security.auth.login.LoginException;
import timber.log.Timber;

/**
 * Created by lalkishore on 5/26/2016.
 */
public class CrashlyticsTree extends Timber.Tree {

  @Override protected boolean isLoggable(String tag, int priority) {
    return priority != Log.VERBOSE
        && priority != Log.DEBUG
        && priority != Log.INFO;//Only log WARN, ERROR, WTF
  }

  @Override protected void log(int priority, String tag, @NonNull String message, Throwable t) {
    if (isLoggable(tag, priority)) {
      switch (priority) {
        case Log.ERROR:
          if (t == null) {
            Crashlytics.log(message);
          } else {
            if (t instanceof UnknownHostException
                || t instanceof ConnectException
                || t instanceof SocketTimeoutException
                || t instanceof ServiceFailureException
                || t.getCause() instanceof ServiceFailureException
                || t instanceof ValidationException
                || t instanceof LoginException
                || t instanceof DataException
                || t instanceof WebCallException) {
              Log.println(priority, tag, message);
              return;
            }
            Crashlytics.log(message);
            Crashlytics.logException(t);
          }
          break;
        case Log.WARN:
          Crashlytics.log(message);
          break;
      }
    }
  }
}
