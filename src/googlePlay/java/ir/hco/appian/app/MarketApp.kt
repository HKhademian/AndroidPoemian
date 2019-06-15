package ir.hco.appian.app

import android.util.Log
import android.view.View
import android.view.ViewManager
import ir.hossainco.utils.view.factory
import org.jetbrains.anko.toast

class MarketApp : MainApp() {
	override fun initAds() {
	}

	override fun createBanner(vm: ViewManager, init: View.() -> Unit) = with(vm) {
	}
}
