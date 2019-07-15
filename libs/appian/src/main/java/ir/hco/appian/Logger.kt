@file:Suppress("unused")

package ir.hco.appian

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics


interface Logger {
	companion object {
		const val VERBOSE = Log.VERBOSE
		const val DEBUG = Log.DEBUG
		const val INFO = Log.INFO
		const val WARN = Log.WARN
		const val ERROR = Log.ERROR
		const val ASSERT = Log.ASSERT

		const val SOURCE_UNKNOWN = "unknown"
		const val SOURCE_GLOBAL = "global"
		const val EVENT_APP_OPEN = "appOpen"
		const val EVENT_PAGE_OPEN = "pageOpen"
		const val EVENT_LOG = "log"

		const val DATA_MESSAGE = "message"
		const val DATA_THROWABLE = "throwable"

		fun Logger.v(tag: String, message: String? = null, throwable: Throwable? = null) =
			log(Log.VERBOSE, tag, message, throwable)

		fun Logger.d(tag: String, message: String? = null, throwable: Throwable? = null) =
			log(Log.DEBUG, tag, message, throwable)

		fun Logger.i(tag: String, message: String? = null, throwable: Throwable? = null) =
			log(Log.INFO, tag, message, throwable)

		fun Logger.w(tag: String, message: String? = null, throwable: Throwable? = null) =
			log(Log.WARN, tag, message, throwable)

		fun Logger.e(tag: String, message: String? = null, throwable: Throwable? = null) =
			log(Log.ERROR, tag, message, throwable)

		fun Logger.a(tag: String, message: String? = null, throwable: Throwable? = null) =
			log(Log.ASSERT, tag, message, throwable)

		fun Logger.wtf(tag: String, message: String? = null, throwable: Throwable? = null) =
			log(Log.ASSERT, tag, message, throwable)
	}

	fun init()


	fun event(source: String, event: String, data: Bundle?)

	fun event(source: String, event: String, data: Map<String, Any?> = emptyMap()) =
		event(source, event, bundleOf(*data.entries.map { it.key to it.value }.toTypedArray()))

	fun event(source: String, event: String, vararg data: Pair<String, Any?>) =
		event(source, event, bundleOf(*data))


	fun appOpen(intent: Intent?) =
		event(SOURCE_GLOBAL, EVENT_APP_OPEN, intent?.extras)

	fun pageOpen(page: String, data: Map<String, String> = emptyMap()) =
		event(SOURCE_GLOBAL, EVENT_PAGE_OPEN, data)

	fun log(
		priority: Int = Log.VERBOSE,
		tag: String = SOURCE_GLOBAL,
		message: String? = null,
		throwable: Throwable? = null
	) {
		Log.println(priority, tag, message)
		event(
			tag, EVENT_LOG,
			DATA_MESSAGE to message,
			DATA_THROWABLE to throwable?.toString()
		)
	}
}

class FirebaseLogger(private val context: Context) : Logger {
	private val analytics by lazy {
		FirebaseAnalytics.getInstance(context)
	}

	override fun init() {
		analytics.setAnalyticsCollectionEnabled(true)
	}

	override fun event(source: String, event: String, data: Bundle?) {
		val eventName = "$source:$event"
		analytics.logEvent(eventName, data)
	}
}
