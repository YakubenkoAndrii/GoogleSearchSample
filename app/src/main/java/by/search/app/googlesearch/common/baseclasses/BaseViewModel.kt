package by.search.app.googlesearch.common.baseclasses

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val disposables = CompositeDisposable()

    abstract fun removeObservers(T: AppCompatActivity)
    abstract fun removeObservers(T: BaseFragment)

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}