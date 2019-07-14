package by.search.app.googlesearch.di

import by.search.app.googlesearch.domain.GoogleSearchInteractor
import by.search.app.googlesearch.domain.GoogleSearchInteractorImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val interactorModule: Module = module {

    factory<GoogleSearchInteractor> {
        GoogleSearchInteractorImpl(
            scheduler = get(),
            remoteRepository = get(),
            localRepository = get()
        )
    }

}