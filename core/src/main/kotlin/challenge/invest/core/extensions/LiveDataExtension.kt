package challenge.invest.core.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, func: (T) -> Unit) {
    observe(owner, Observer {
        it?.let {
            func(it)
        }
    })
}