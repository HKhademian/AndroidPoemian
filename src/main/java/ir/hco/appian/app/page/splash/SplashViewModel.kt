package ir.hco.appian.app.page.splash

import android.app.Application
import androidx.lifecycle.viewModelScope
import ir.hco.appian.app.MainApp
import ir.hco.util.page.BaseViewModel
import ir.hossainco.utils.event.Event
import ir.hossainco.utils.event.MutableLiveEvent
import ir.hossainco.utils.event.postEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.max

internal class SplashViewModel(app: Application) : BaseViewModel(app) {
	val loadCompleteEvent = MutableLiveEvent(Event(false))

	fun start() {
		viewModelScope.launch {
			load()

			loadCompleteEvent.postEvent(true)
		}
	}

	private suspend fun load() = withContext(Dispatchers.IO) {
		val start = System.currentTimeMillis()
		(app as MainApp).load()
		val duration = System.currentTimeMillis() - start
		val wait = max(100, 5000 - duration)
		delay(wait)
	}

}
