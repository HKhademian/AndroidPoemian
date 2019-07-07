package ir.hco.appian.app

import android.view.View
import android.view.ViewManager
import com.magnetadservices.sdk.MagnetInterstitialAd
import com.magnetadservices.sdk.MagnetMobileBannerAd
import com.magnetadservices.sdk.MagnetSDK
import ir.hco.appian.utils.BazaarPublisher
import org.jetbrains.anko.frameLayout

class MarketApp : MainApp() {
	override val publisher = BazaarPublisher(
		developerId = BuildConfig.DEVELOPER_ID,
		forceMarket = false
	)

	private var interstitialAd: MagnetInterstitialAd? = null
	private val bannerAd by lazy {
		MagnetMobileBannerAd.create(applicationContext)
	}

	override fun initAds() {
		MagnetSDK.initialize(applicationContext)
		MagnetSDK.getSettings().setTestMode(BuildConfig.DEBUG)
		loadInterstitial()
	}

	override fun createBanner(vm: ViewManager, init: View.() -> Unit) = with(vm) {
		frameLayout {
			bannerAd.load(BuildConfig.MAGNET_BANNER_AD_TOKEN, this)
		}
	}

	override fun hasInterstitial(): Boolean {
		return interstitialAd?.isAdReady == true
	}

	override fun showInterstitial(): Boolean {
		val ad = interstitialAd ?: return false
		val res = ad.show()
		interstitialAd = null
		loadInterstitial()
		return res
	}

	private fun loadInterstitial() {
		if (interstitialAd == null || interstitialAd!!.isUsed) {
			interstitialAd = MagnetInterstitialAd.create(applicationContext)
		}
		interstitialAd!!.load(BuildConfig.MAGNET_INTERSTITIAL_AD_TOKEN)
	}
}
