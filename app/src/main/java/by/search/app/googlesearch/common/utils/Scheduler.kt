package by.search.app.googlesearch.common.utils

import io.reactivex.Scheduler

class Scheduler(val subscribeWorker: Scheduler, val observeWorker: Scheduler)