package by.search.app.googlesearch.di

import by.search.app.googlesearch.data.repositories.search.local.LocalSearchRepository
import by.search.app.googlesearch.data.repositories.search.local.LocalSearchRepositoryImpl
import by.search.app.googlesearch.data.repositories.search.remote.RemoteSearchRepository
import by.search.app.googlesearch.data.repositories.search.remote.RemoteSearchRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    factory<RemoteSearchRepository> {
        RemoteSearchRepositoryImpl(
            searchService = get()
        )
    }

    factory<LocalSearchRepository> {
        LocalSearchRepositoryImpl(
            db = get()
        )
    }
}