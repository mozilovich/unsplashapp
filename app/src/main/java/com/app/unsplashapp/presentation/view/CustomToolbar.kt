package com.app.unsplashapp.presentation.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.extensions.isVisible
import com.app.unsplashapp.presentation.utils.image.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import javax.inject.Inject

@AndroidEntryPoint
class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var imageLoader: ImageLoader

    var enableNavBtn: Boolean = false
        set(value) {
            toolbarNavBtn.isVisible = value
            field = value
        }

    @DrawableRes
    var avatarIcon: Int = 0
        set(value) {
            if (value != 0) {
                toolbarAvatar.setImageDrawable(ContextCompat.getDrawable(context, value))
                toolbarAvatar.isVisible = true
            } else {
                toolbarAvatar.isVisible = false
            }
            field = value
        }

    var avatarUrl: String? = null
        set(value) {
            if (value != null) {
                imageLoader.loadAvatar(toolbarAvatar, value)
                toolbarAvatar.isVisible = true
            } else {
                toolbarAvatar.isVisible = false
            }
            field = value
        }

    var title: String? = context.getString(R.string.app_name)
        set(value) {
            toolbarTitle.text = value
            field = value
        }

    var subtitle: String? = null
        set(value) {
            if (value != null) {
                toolbarSubtitle.text = value
                toolbarSubtitle.isVisible = true
            } else {
                toolbarSubtitle.isVisible = false
            }
            field = value
        }

    var navBtnListener: (() -> Unit)? = null

    init {
        inflate(context, R.layout.layout_toolbar, this)

        toolbarNavBtn.apply {
            setImageDrawable(ContextCompat.getDrawable(context, NAV_BTN_DEFAULT))
            setOnClickListener {
                navBtnListener?.invoke()
            }
        }

        context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar).apply {
            enableNavBtn = getBoolean(R.styleable.CustomToolbar_enableNavBtn, false)
            avatarIcon = getResourceId(R.styleable.CustomToolbar_avatarSrc, 0)
            title = getString(R.styleable.CustomToolbar_toolbarTitle)
            recycle()
        }
    }

    companion object {
        private const val NAV_BTN_DEFAULT = R.drawable.ic_back
    }
}