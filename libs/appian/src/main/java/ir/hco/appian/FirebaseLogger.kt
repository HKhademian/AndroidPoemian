package ir.hco.appian

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import ir.hco.utils.Logger

object FirebaseLogger : Logger {
	private var analytics: FirebaseAnalytics? = null

	override fun init(context: Context) {
		analytics = FirebaseAnalytics.getInstance(context.applicationContext)
		analytics!!.setAnalyticsCollectionEnabled(true)
	}

	override fun event(priority: Int, source: String, event: String, data: Bundle?) {
		val eventName = "$source:$event"
		analytics?.logEvent(eventName, data)
	}
}
