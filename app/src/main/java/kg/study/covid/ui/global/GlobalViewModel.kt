package kg.study.covid.ui.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kg.study.covid.model.CountriesService
import kg.study.covid.model.Country
import kg.study.covid.model_global.GlobalData
import kg.study.covid.model_global.GlobalService

class GlobalViewModel : ViewModel() {

    private val globalService = GlobalService()
    private val disposable = CompositeDisposable()

    val global = MutableLiveData<List<GlobalData>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }
    private fun fetchCountries() {
        loading.value = true
        disposable.add(
            globalService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<GlobalData>() {
                    override fun onSuccess(value: GlobalData) {
                        global.value = listOf(value)
                        countryLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}