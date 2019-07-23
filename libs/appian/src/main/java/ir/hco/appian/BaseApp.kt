@file:Suppress("unused")

package ir.hco.appian

import android.app.Application
import android.graphics.Typeface
import ir.hossainco.utils.App
import ir.hossainco.utils.packages.setLocale
import ir.hossainco.utils.tryOrDefault
import ir.hossainco.utils.ui.setDefaultTypefaces
import java.util.*

abstract class BaseApp : Application() {
	companion object {
		private val LOCALE = Locale("fa")
	}

	@Suppress("LeakingThis")
	open val logger: Logger = FirebaseLogger(this)
	open val advertiser: Advertiser = BaseAdvertiser()
	abstract val publisher: Publisher

	override fun onCreate() {
		super.onCreate()
		App.app = this

		initLocale()
		initLogger()
		initPublisher()
		initAdvertiser()
		initRepository()
		initMessaging()
		initFonts()
	}

	protected open fun initLocale() {
		setLocale(this, LOCALE)
	}

	protected open fun initLogger() {
		logger.init()
	}

	protected open fun initPublisher() {
		publisher.init()
	}

	protected open fun initAdvertiser() {
		advertiser.init(applicationContext)
	}

	protected open fun initRepository() {
	}

	protected open fun initMessaging() {
	}

	protected open fun initFonts() {
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
	}

}
