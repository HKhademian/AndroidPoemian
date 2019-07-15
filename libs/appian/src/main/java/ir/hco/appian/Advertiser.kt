package ir.hco.appian

import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.frameLayout

interface Advertiser {
	fun init()


	fun hasBanner(): Boolean

	fun createBanner(vm: ViewManager, init: View.() -> Unit = {}): View


	fun hasInterstitial(): Boolean

	fun showInterstitial(): Boolean

	fun mayShowInterstitial(): Boolean
}

open class BaseAdvertiser : Advertiser {
	companion object {
		private const val MIN_AD_GAP = 2 * 60 * 1000
	}

	private var lastAdShown = System.currentTimeMillis();

	override fun init() =
		Unit

	override fun hasBanner() =
		false

	override fun createBanner(vm: ViewManager, init: View.() -> Unit): View =
		vm.frameLayout()

	override fun hasInterstitial() =
		false

	override fun showInterstitial(): Boolean =
		false

	override fun mayShowInterstitial(): Boolean {
		val now = System.currentTimeMillis()
		if (now - lastAdShown > MIN_AD_GAP && (Math.random() * 100) < 30) {
			if (hasInterstitial()) {
				val res = showInterstitial()
				if (res) {
					lastAdShown = System.currentTimeMillis()
				}
				return res
			}
		}
		return false
	}
}
