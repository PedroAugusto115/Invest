package challenge.invest.core.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelStoreOwner
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlin.reflect.KClass

class ViewModelProviderFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.newInstance()
}

fun <T : ViewModel> AppCompatActivity.provideSharedViewModel(
    viewModel: KClass<T>
) = lazy {
    this.provideViewModel(viewModel)
}

fun <T : ViewModel> Fragment.provideActivityViewModel(
    viewModel: KClass<T>
) = lazy {
    requireActivity().provideViewModel(viewModel)
}

private fun <T : ViewModel> ViewModelStoreOwner.provideViewModel(viewModel: KClass<T>) =
    ViewModelProvider(this, ViewModelProviderFactory()).get(viewModel.java)