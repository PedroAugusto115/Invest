package challenge.invest.core.extensions

import android.app.Activity
import android.app.Dialog
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <V : View> View.bindView(id: Int):
        ReadOnlyProperty<View, V> = required(id, viewFinder)
fun <V : View> Activity.bindView(id: Int):
        ReadOnlyProperty<Activity, V> = required(id, viewFinder)
fun <V : View> Dialog.bindView(id: Int):
        ReadOnlyProperty<Dialog, V> = required(id, viewFinder)
fun <V : View> DialogFragment.bindView(id: Int):
        ReadOnlyProperty<DialogFragment, V> = required(id, viewFinder)
fun <V : View> Fragment.bindView(id: Int):
        ReadOnlyProperty<Fragment, V> = required(id, viewFinder)
fun <V : View> RecyclerView.ViewHolder.bindView(id: Int):
        ReadOnlyProperty<RecyclerView.ViewHolder, V> = required(id, viewFinder)
fun <T : Any> Activity.bindBundle(key: String):
        ReadOnlyProperty<Activity, T> = required(key, argumentsFinder)
fun <T : Any> Fragment.bindBundle(key: String):
        ReadOnlyProperty<Fragment, T> = required(key, argumentsFinder)

fun <V : View> View.bindOptionalView(id: Int):
        ReadOnlyProperty<View, V?> = optional(id, viewFinder)
fun <V : View> Activity.bindOptionalView(id: Int):
        ReadOnlyProperty<Activity, V?> = optional(id, viewFinder)
fun <V : View> Dialog.bindOptionalView(id: Int):
        ReadOnlyProperty<Dialog, V?> = optional(id, viewFinder)
fun <V : View> DialogFragment.bindOptionalView(id: Int):
        ReadOnlyProperty<DialogFragment, V?> = optional(id, viewFinder)
fun <V : View> Fragment.bindOptionalView(id: Int):
        ReadOnlyProperty<Fragment, V?> = optional(id, viewFinder)
fun <V : View> RecyclerView.ViewHolder.bindOptionalView(id: Int):
        ReadOnlyProperty<RecyclerView.ViewHolder, V?> = optional(id, viewFinder)
fun <T> Activity.bindOptionalBundle(key: String):
        ReadOnlyProperty<Activity, T?> = optional(key, argumentsFinder)
fun <T> Fragment.bindOptionalBundle(key: String):
        ReadOnlyProperty<Fragment, T?> = optional(key, argumentsFinder)

fun <V : View> View.bindViews(vararg ids: Int):
        ReadOnlyProperty<View, List<V>> = required(ids, viewFinder)
fun <V : View> Activity.bindViews(vararg ids: Int):
        ReadOnlyProperty<Activity, List<V>> = required(ids, viewFinder)
fun <V : View> Dialog.bindViews(vararg ids: Int):
        ReadOnlyProperty<Dialog, List<V>> = required(ids, viewFinder)
fun <V : View> DialogFragment.bindViews(vararg ids: Int):
        ReadOnlyProperty<DialogFragment, List<V>> = required(ids, viewFinder)
fun <V : View> Fragment.bindViews(vararg ids: Int):
        ReadOnlyProperty<Fragment, List<V>> = required(ids, viewFinder)
fun <V : View> RecyclerView.ViewHolder.bindViews(vararg ids: Int):
        ReadOnlyProperty<RecyclerView.ViewHolder, List<V>> = required(ids, viewFinder)

fun <V : View> View.bindOptionalViews(vararg ids: Int):
        ReadOnlyProperty<View, List<V>> = optional(ids, viewFinder)
fun <V : View> Activity.bindOptionalViews(vararg ids: Int):
        ReadOnlyProperty<Activity, List<V>> = optional(ids, viewFinder)
fun <V : View> Dialog.bindOptionalViews(vararg ids: Int):
        ReadOnlyProperty<Dialog, List<V>> = optional(ids, viewFinder)
fun <V : View> DialogFragment.bindOptionalViews(vararg ids: Int):
        ReadOnlyProperty<DialogFragment, List<V>> = optional(ids, viewFinder)
fun <V : View> Fragment.bindOptionalViews(vararg ids: Int):
        ReadOnlyProperty<Fragment, List<V>> = optional(ids, viewFinder)
fun <V : View> RecyclerView.ViewHolder.bindOptionalViews(vararg ids: Int):
        ReadOnlyProperty<RecyclerView.ViewHolder, List<V>> = optional(ids, viewFinder)

private val View.viewFinder: View.(Int) -> View?
    get() = { findViewById(it) }
private val Activity.viewFinder: Activity.(Int) -> View?
    get() = { findViewById(it) }
private val Dialog.viewFinder: Dialog.(Int) -> View?
    get() = { findViewById(it) }
private val DialogFragment.viewFinder: DialogFragment.(Int) -> View?
    get() = { dialog.findViewById(it) }
private val Fragment.viewFinder: Fragment.(Int) -> View?
    get() = { view!!.findViewById(it) }
private val RecyclerView.ViewHolder.viewFinder: RecyclerView.ViewHolder.(Int) -> View?
    get() = { itemView.findViewById(it) }
private val Activity.argumentsFinder: Activity.(String) -> Any?
    get() = { intent.extras?.get(it) }
private val Fragment.argumentsFinder: Fragment.(String) -> Any?
    get() = { arguments?.get(it) }

private fun viewNotFound(id: Int, desc: KProperty<*>): Nothing =
    throw IllegalStateException("View ID $id for '${desc.name}' not found.")

private fun bundleNotFound(key: String, desc: KProperty<*>): Nothing =
    throw IllegalStateException("Bundle KEY $key for '${desc.name}' not found.")

@Suppress("UNCHECKED_CAST")
private fun <T, B : Any?> required(key: String, finder: T.(String) -> Any?) = Lazy { t: T, desc ->
    t.finder(key) as B ?: bundleNotFound(key, desc)
}

@Suppress("UNCHECKED_CAST")
private fun <T, B : Any?> optional(key: String, finder: T.(String) -> Any?) = Lazy { t: T, _ -> t.finder(key) as B? }

@Suppress("UNCHECKED_CAST")
private fun <T, V : View> required(id: Int, finder: T.(Int) -> View?) = Lazy { t: T, desc ->
    t.finder(id) as V? ?: viewNotFound(id, desc)
}

@Suppress("UNCHECKED_CAST")
private fun <T, V : View> optional(id: Int, finder: T.(Int) -> View?) = Lazy { t: T, _ -> t.finder(id) as V? }

@Suppress("UNCHECKED_CAST")
private fun <T, V : View> required(ids: IntArray, finder: T.(Int) -> View?) = Lazy { t: T, desc ->
    ids.map {
        t.finder(it) as V? ?: viewNotFound(it, desc)
    }
}

@Suppress("UNCHECKED_CAST")
private fun <T, V : View> optional(ids: IntArray, finder: T.(Int) -> View?) = Lazy { t: T, _ ->
    ids.map {
        t.finder(it) as V? }.filterNotNull()
}

private class Lazy<T, V>(private val initializer: (T, KProperty<*>) -> V) :
    ReadOnlyProperty<T, V>, LifecycleObserver {
    private object EMPTY
    private var value: Any? = EMPTY
    private var attachedToLifecycleOwner = false

    override fun getValue(thisRef: T, property: KProperty<*>): V {
        checkAddToLifecycleOwner(thisRef)
        if (value == EMPTY) {
            value = initializer(thisRef, property)
        }
        @Suppress("UNCHECKED_CAST")
        return value as V
    }

    private fun checkAddToLifecycleOwner(thisRef: T) {
        if (!attachedToLifecycleOwner && thisRef is LifecycleOwner) {
            thisRef.lifecycle.addObserver(this)
            attachedToLifecycleOwner = true
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun destroy() {
        value = EMPTY
    }
}