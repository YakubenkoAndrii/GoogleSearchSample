package by.search.app.googlesearch.presentation.search.searchfragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import by.search.app.googlesearch.R
import by.search.app.googlesearch.common.IS_PORTRAIT_ORIENTATION
import by.search.app.googlesearch.common.IS_TABLET
import by.search.app.googlesearch.common.baseclasses.BaseFragment
import by.search.app.googlesearch.common.extentions.show
import by.search.app.googlesearch.common.extentions.track
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fr_search_page.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment() {

    override val layout = R.layout.fr_search_page
    private val router by lazy { activity as SearchRouter }
    private val viewModel: SearchViewModel by viewModel()
    private val searchResultAdapter = SearchResultAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadCachedResults()
        viewModel.lastSearchInput.observe(this, Observer {
            tvSearch.setText(it)
            tvSearch.setSelection(tvSearch.text.length)
        })

        RxTextView.textChanges(tvSearch)
            .skipInitialValue()
            .skip(1)
            .debounce(1000, TimeUnit.MILLISECONDS)
            .map { charSequence -> charSequence.toString() }
            .distinctUntilChanged()
            .filter { source -> source.isNotBlank() && source.length > 1 }
            .subscribe { viewModel.search(it) }
            .track(CompositeDisposable())

        initProgressContainer()
        initErrorInitializer()

        arguments?.let {
            initRecyclerList(it.getBoolean(IS_TABLET), it.getBoolean(IS_PORTRAIT_ORIENTATION))
        }

    }

    private fun initRecyclerList(isTablet: Boolean, isPortraitOrientation: Boolean) {

        val spanCount = if (isTablet && isPortraitOrientation) {
            3
        } else if (isTablet && !isPortraitOrientation) {
            4
        } else if (!isTablet && isPortraitOrientation) {
            1
        } else {
            2
        }

        rvSearchResult.apply {
            layoutManager = GridLayoutManager(context,spanCount)
            adapter = searchResultAdapter
        }

        viewModel.searchItems.observe(this, Observer {
            it?.let {response ->
                searchResultAdapter.setItems(response)
            }
        })

        searchResultAdapter.toBrowser = { router.toBrowser(it) }

    }

    private fun initErrorInitializer() {
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initProgressContainer() {
        viewModel.isProgress.observe(this, Observer {
            rlProgressContainer.show(it)
        })

        ivTerminateSearch.setOnClickListener {
            viewModel.terminateActionSearch()
        }
    }

    companion object {
        fun newInstance(isTablet: Boolean,
                        isPortraitOrientation: Boolean) = SearchFragment().apply {
            arguments = Bundle().apply {
                putBoolean(IS_TABLET, isTablet)
                putBoolean(IS_PORTRAIT_ORIENTATION, isPortraitOrientation)
            }
        }
    }

}