package by.search.app.googlesearch.common.extentions

import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun View.show(show: Boolean?) {
    this.visibility = if (show == true) View.VISIBLE else View.INVISIBLE
}

fun Disposable.track(disposables: CompositeDisposable) {
    disposables.add(this)
}