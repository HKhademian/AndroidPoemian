package ir.hco.appian

import android.content.Context
import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.frameLayout

interface Advertiser {
	companion object {
		private const val MIN_AD_GAP = 2 * 60 * 1000L
		const val UNIT_DEFAULT = "default"
		const val UNIT_BANNER = "banner"
		const val UNIT_CONTENT = "content"
		val DEFAULT = object : Advertiser {
			override var lastInterstitialAdShown = System.currentTimeMillis()
		}

		protected fun mayShowInterstitial(
			lastAdShown: Long = 0,
			minAdGap: Long = MIN_AD_GAP,
			hasInterstitial: () -> Boolean,
			showInterstitial: () -> Boolean
		): Long? {
			val now = System.currentTimeMillis()
			if (now - lastAdShown > minAdGap && (Math.random() * 100) < 30) {
				if (hasInterstitial()) {
					val res = showInterstitial()
					if (res) {
						return System.currentTimeMillis()
					}
					return null
				}
			}
			return null
		}
	}

	val minAdGap: Long get() = MIN_AD_GAP
	var lastInterstitialAdShown: Long


	fun init(context: Context) =
		Unit


	fun hasBanner(unitName: String? = null): Boolean =
		false

	fun createBanner(vm: ViewManager, unitName: String? = null, init: View.() -> Unit = {}): View? =
		with(vm) {
			frameLayout()
		}

	fun recycleBanner(view: View) =
		Unit


	fun hasInterstitial(): Boolean =
		false

	fun showInterstitial(): Boolean =
		false

	fun mayShowInterstitial(): Boolean {
		val res = mayShowInterstitial(lastInterstitialAdShown, minAdGap, ::hasInterstitial, ::showInterstitial)
			?: return false
		lastInterstitialAdShown = res
		return true
	}
}
