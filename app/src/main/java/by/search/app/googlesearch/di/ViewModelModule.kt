package by.search.app.googlesearch.di

import by.search.app.googlesearch.presentation.search.searchfragment.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {

    viewModel { SearchViewModel() }

}
