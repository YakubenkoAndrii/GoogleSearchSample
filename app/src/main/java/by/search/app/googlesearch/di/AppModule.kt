package by.search.app.googlesearch.di

import android.arch.persistence.room.Room
import io.reactivex.android.schedulers.AndroidSchedulers
import by.search.app.googlesearch.common.utils.Scheduler
import by.search.app.googlesearch.data.db.DB_NAME
import by.search.app.googlesearch.data.db.SearchDb
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    single {
        Schedulers.io()
    }

    single {
        Scheduler(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    single {
        Room.databaseBuilder(androidApplication(), SearchDb::class.java, DB_NAME).build()
    }

}