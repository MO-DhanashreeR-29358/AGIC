package com.mosl.mobile.volunteer.helper

import androidx.appcompat.app.AppCompatActivity
import com.mosl.annotations.ActivityScoped
import com.mosl.app.core.prefs.StringPreference
import com.mosl.data.api.AGICApi
import com.mosl.mobile.volunteer.annotations.ClientCode
import com.mosl.navigator.Navigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

interface CustomNavigator {
  fun handle(entry: CustomNavigationEntry)
}

@ActivityScoped
class CustomNavigatorImpl @Inject constructor(
    protected val activity: AppCompatActivity,
    protected val navigator: Navigator,
    @param:ClientCode protected val clientCode: StringPreference,
    protected val moslApi: AGICApi
   // protected val screenNavigator: Lazy<ScreenNavigator>
) : CustomNavigator, Disposable {
  private val disposable: CompositeDisposable = CompositeDisposable()

  override fun handle(entry: CustomNavigationEntry) {

  }

  override fun isDisposed(): Boolean {
    return disposable.isDisposed
  }

  override fun dispose() {
    disposable.dispose()
  }

  fun Navigator.toggleProgressBar(inProgress: Boolean) {
    if (inProgress) {
      showProgress()
    } else {
      hideProgress()
    }
  }

}