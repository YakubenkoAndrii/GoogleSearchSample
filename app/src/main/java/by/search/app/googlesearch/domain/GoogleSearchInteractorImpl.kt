package by.search.app.googlesearch.domain

import by.search.app.googlesearch.common.utils.Scheduler
import by.search.app.googlesearch.data.entity.search.SearchedResultBundle
import by.search.app.googlesearch.data.entity.search.SearchItem
import by.search.app.googlesearch.data.entity.search.SearchResponse

import by.search.app.googlesearch.data.repositories.search.local.LocalSearchRepository
import by.search.app.googlesearch.data.repositories.search.remote.RemoteSearchRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GoogleSearchInteractorImpl(
    private val scheduler: Scheduler,
    private val remoteRepository: RemoteSearchRepository,
    private val localRepository: LocalSearchRepository) : GoogleSearchInteractor {

    override fun loadCachedResults(): Single<SearchedResultBundle> =
        Single.fromCallable { localRepository.loadCachedResults() }
            .subscribeOn(scheduler.subscribeWorker)
            .observeOn(scheduler.observeWorker)

    override fun search(inputString: String): Single<MutableList<SearchItem>> =
        Single.zip(
            remoteRepository.search(inputString, 1),
            remoteRepository.search(inputString, 11),
            BiFunction<SearchResponse, SearchResponse,
                    MutableList<SearchItem>>
            { t1, t2 ->
                (t1.items + t2.items).toMutableList()
            })
            .map { result ->
                result.map {
                    if (it.description.isNotEmpty() && it.description.length > 30) {
                        it.description = it.description.substring(0, 30)
                    }
                }
                result
            }
            .doOnSuccess {
                localRepository.deleteAll()
                localRepository.insertResults(it, inputString)
            }
            .subscribeOn(scheduler.subscribeWorker)
            .observeOn(scheduler.observeWorker)

}