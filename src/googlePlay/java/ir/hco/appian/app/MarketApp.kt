package ir.hco.appian.app

import android.view.View
import android.view.ViewManager
import com.appbrain.AdId
import com.appbrain.AppBrain
import com.appbrain.AppBrainBanner
import com.appbrain.InterstitialBuilder
import ir.hco.appian.utils.GooglePlayPublisher
import ir.hossainco.utils.view.factory

class MarketApp : MainApp() {
	override val publisher = GooglePlayPublisher(
		developerId = BuildConfig.DEVELOPER_ID,
		forceMarket = false
	)
	private val interstitialBuilder = InterstitialBuilder.create()!!

	override fun initAds() {
		val context = applicationContext!!

		AppBrain.init(context)
		if (BuildConfig.DEBUG) {
			AppBrain.addTestDevice("cc39a26c3c7c045a")
		}

		interstitialBuilder
			.setAdId(AdId.LEADERBOARDS)
			.setOnDoneCallback {
				interstitialBuilder.preload(context)
			}
			.preload(context)
	}

	override fun createBanner(vm: ViewManager, init: View.() -> Unit) = with(vm) {
		factory(::AppBrainBanner) {
			setSize(AppBrainBanner.BannerSize.STANDARD)
			setAdId(AdId.HOME_SCREEN)
			init()
		}
	}

	override fun hasInterstitial(): Boolean {
		return true
	}

	override fun showInterstitial(): Boolean {
		return interstitialBuilder.show(applicationContext)
	}
}
