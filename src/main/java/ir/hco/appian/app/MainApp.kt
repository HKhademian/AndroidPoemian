package ir.hco.appian.app

import android.app.Application
import android.graphics.Typeface
import android.view.View
import android.view.ViewManager
import ir.hco.appian.app.data.Repository
import ir.hco.appian.utils.Publisher
import ir.hossainco.utils.App
import ir.hossainco.utils.packages.setLocale
import ir.hossainco.utils.tryOrDefault
import ir.hossainco.utils.ui.setDefaultTypefaces
import org.jetbrains.anko.view
import java.util.*

abstract class MainApp : Application() {
	private var lastAdShown = 0L
	abstract val publisher: Publisher

	companion object {
		private const val MIN_AD_GAP = 2 * 60 * 1000
		private val LOCALE = Locale("fa-US")
	}

	override fun onCreate() {
		super.onCreate()
		App.app = this

		setLocale(this, LOCALE)

		val (sans, serif) = arrayOf(
			tryOrDefault(Typeface.SANS_SERIF) { Typeface.createFromAsset(assets, "fonts/iransans_light.ttf") },
			tryOrDefault(Typeface.SERIF) { Typeface.createFromAsset(assets, "fonts/byekan.ttf") }
		)
		setDefaultTypefaces(
			default = sans,
			sansSerif = sans,
			serif = serif,
			monospace = serif
		)

		Repository.init()

		initAds()
	}

	protected open fun initAds() =
		Unit

	open fun createBanner(vm: ViewManager, init: View.() -> Unit = {}): View =
		vm.view()

	open fun hasInterstitial() =
		false

	open fun showInterstitial(): Boolean =
		false

	open fun mayShowInterstitial(): Boolean {
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
