package com.app.unsplashapp.presentation.view

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.extensions.showKeyboard
import kotlinx.android.synthetic.main.layout_search_view.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class CustomSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var searchTextListener: ((String) -> Unit)? = null
    var voiceBtnClickListener: (() -> Unit)? = null

    var searchHint: String? = null
        set(value) {
            if (!value.isNullOrEmpty()) {
                searchField.hint = value
            }
            field = value
        }

    var searchText: String? = null
        set(value) {
            if (!value.isNullOrEmpty()) {
                searchField.setText(value)
            }
            field = value
        }

    init {
        inflate(context, R.layout.layout_search_view, this)

        context.obtainStyledAttributes(attrs, R.styleable.CustomSearchView).apply {
            searchText = getString(R.styleable.CustomSearchView_searchText)
            searchHint = getString(R.styleable.CustomSearchView_searchHint)
            recycle()
        }

        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        background = ContextCompat.getDrawable(context, R.drawable.background_search)

        searchField.doAfterTextChanged {
            searchTextListener?.invoke(it.toString())

            if (it.isNullOrEmpty()) {
                setActionVoice()
            } else {
                setActionClose()
            }
        }

        setActionVoice()
    }

    fun startSearch() {
        searchField.apply {
            requestFocus()
            showKeyboard()
        }
    }

    private fun setActionClose() {
        searchActionBtn.apply {
            setImageResource(R.drawable.ic_close)
            setOnClickListener {
                searchField.setText("")
            }
        }
    }

    private fun setActionVoice() {
        searchActionBtn.apply {
            setImageResource(R.drawable.ic_voice)
            setOnClickListener {
                voiceBtnClickListener?.invoke()
            }
        }
    }
}