package com.lee.mymvvmsample.common

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.common.utils.SafeClickListener
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


fun FragmentActivity.addFragment(fragment: Fragment, tag: String? = null, addBackStack: Boolean = false) {
    supportFragmentManager.beginTransaction().apply {
        setCustomAnimations(R.anim.slide_in_right, R.anim.slide_none)
        add(R.id.fragment_container, fragment, tag ?: fragment::class.java.simpleName)
        if (addBackStack) addToBackStack(null)
        commit()
    }
}

fun FragmentActivity.replaceFragment(fragment: Fragment, tag: String? = null, addBackStack: Boolean = false) {
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragment_container, fragment, tag ?: fragment::class.java.simpleName)
        if (addBackStack) addToBackStack(null)
        commit()
    }
}

fun FragmentActivity.getCurrentFragment(): Fragment {
    return supportFragmentManager.findFragmentById(R.id.fragment_container)!!
}

fun FragmentActivity.removeFragment() {
//    supportFragmentManager.beginTransaction()?.remove(getCurrentFragment()).commit()
    supportFragmentManager.beginTransaction().apply {
        setCustomAnimations(R.anim.slide_none, R.anim.slide_out_right)
        remove(getCurrentFragment())
        commit()
    }
}


fun Int.convertDpToPixel(context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return this * (metrics.densityDpi / 160f)
}

fun Float.toPixel(context: Context?): Int {
    return context?.let { (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, it.resources.displayMetrics).toInt()) }
            ?: 0
}

fun Float.toSpPixel(context: Context?): Int {
    return context?.let { (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, it.resources.displayMetrics).toInt()) }
            ?: 0
}

fun Int.formatString(): String {
    try {
        return DecimalFormat("#,###").format(this.toDouble())
    } catch (e: Exception) {
    }
    return this.toString()
}

fun Long.formatString(): String {
    try {
        return DecimalFormat("#,###").format(this.toDouble())
    } catch (e: Exception) {
    }
    return this.toString()
}

fun Long.formatDateString(): String {
    var date = Date(this)

    var transFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")

    return transFormat.format(date)
}

/**
 * 짧은시간 버튼 연타를 막기위한 처리.
 * 버튼 클릭 이벤트에는 이 함수를 적극적으로 이용해야 함.
 */
fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    setOnClickListener(SafeClickListener {
        onSafeClick(it)
    })
}

fun EditText.editorActionListener(actionId: Int, callback: (String) -> Unit) {
    setOnEditorActionListener { _, _actionId, _ ->
        if (actionId == _actionId) {
            callback.invoke(text.toString())
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener true
    }
}

fun EditText.textChangedListener(callback: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            callback.invoke(s?.toString() ?: "")
        }
    })
}