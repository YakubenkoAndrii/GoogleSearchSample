package by.search.app.googlesearch.presentation.search.searchfragment

import android.arch.lifecycle.MutableLiveData
import android.support.v7.app.AppCompatActivity
import by.search.app.googlesearch.common.baseclasses.BaseFragment
import by.search.app.googlesearch.common.baseclasses.BaseViewModel
import by.search.app.googlesearch.common.extentions.track
import by.search.app.googlesearch.data.entity.search.SearchItem
import by.search.app.googlesearch.data.entity.search.SearchResponse
import by.search.app.googlesearch.domain.GoogleSearchInteractor
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import okhttp3.ResponseBody
import org.koin.core.inject

class SearchViewModel : BaseViewModel() {

    private val interactor by inject<GoogleSearchInteractor>()
    private var searchDisposable = CompositeDisposable()
    val isProgress = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()
    var searchItems = MutableLiveData<MutableList<SearchItem>>()
    var lastSearchInput = MutableLiveData<String>()

    lateinit var actionSearch: Single<MutableList<SearchItem>>

    fun loadCachedResults() {
        interactor.loadCachedResults()
            .doOnSubscribe { isProgress.postValue(true) }
            .doAfterTerminate { isProgress.postValue(false) }
            .subscribe(
                {
                    lastSearchInput.value = it.inputString
                    searchItems.value = it.items
                },
                {

                }).track(disposables)
    }

    fun search(inputString: String) {
        actionSearch = interactor.search(inputString)
        if (searchDisposable.isDisposed) {
            searchDisposable = CompositeDisposable()
        } else {
            searchDisposable.add(actionSearch
                .doOnSubscribe { isProgress.postValue(true) }
                .doAfterTerminate { isProgress.postValue(false) }
                .subscribe(
                    {
                        searchItems.value = it
                    },
                    {
                        errorMessage.value = it.localizedMessage
                    })
            )
        }
    }

    fun terminateActionSearch() {
        isProgress.postValue(false)
        searchDisposable.dispose()
    }

    override fun removeObservers(T: AppCompatActivity) {

    }

    override fun removeObservers(T: BaseFragment) {
        isProgress.removeObservers(T)
        errorMessage.removeObservers(T)
        searchItems.removeObservers(T)
        lastSearchInput.removeObservers(T)
    }
}