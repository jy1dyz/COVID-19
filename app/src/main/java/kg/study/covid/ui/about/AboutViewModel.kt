package kg.study.covid.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kg.study.covid.model.CountriesService
import kg.study.covid.model.Country
import kg.study.covid.model_path.PathService

class AboutViewModel : ViewModel() {

    private val countriesService = CountriesService()
    private val disposable = CompositeDisposable()
    private val pathService = PathService()

    val countries = MutableLiveData<List<Country>>()
    val countryPath = MutableLiveData<Country>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountry()
    }

    fun fetchCountry() {
        loading.value = true
        disposable.add(
            countriesService.getCountry()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Country>() {
                    override fun onSuccess(value: Country) {
                        countryPath.value = value
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