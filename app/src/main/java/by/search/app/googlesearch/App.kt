package by.search.app.googlesearch

import android.app.Application
import by.search.app.googlesearch.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(
                appModule,
                networkModule,
                viewModelModule,
                repositoryModule,
                interactorModule))
        }

    }

}