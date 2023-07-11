package com.example.main_library_published

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.Keep

@Keep
class AndroidLibrarySample(
    private val context: Context? = null,
    private val useAsLibrary: Boolean = true,
    private val appId: String,
) {

    private constructor(builder: Builder) : this(
        context = builder.context,
        useAsLibrary = builder.useAsLibrary,
        appId = builder.appId,
    )

    private val REQUESTCODE = 10

    @Keep
    class Builder {

        var context: Context? = null
            private set

        var useAsLibrary: Boolean = false
            private set

        var appId: String = ""
            private set

        fun setContext(context: Context) = apply { this.context = context }

        fun useAsLibrary(useAsLibrary: Boolean = true) = apply { this.useAsLibrary = useAsLibrary }

        fun build() = AndroidLibrarySample(this)
    }

    fun start() {
        Intent(context, LibraryActivity::class.java).apply {
            (context as Activity).startActivityForResult(this, REQUESTCODE)
        }
    }

}