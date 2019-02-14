package challenge.invest.core.service

import android.arch.lifecycle.MutableLiveData

class ApiResponse<T> {
    val errorMessage = MutableLiveData<String>()
    val data = MutableLiveData<T>()
}
