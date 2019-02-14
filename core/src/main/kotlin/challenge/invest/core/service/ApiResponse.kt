package challenge.invest.core.service

import challenge.invest.core.livedata.SingleLiveEvent

class ApiResponse<T> {
    val errorMessage = SingleLiveEvent<String>()
    val data = SingleLiveEvent<T>()
}
