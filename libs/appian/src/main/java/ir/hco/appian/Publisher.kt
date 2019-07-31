package ir.hco.appian

import android.content.Context
import android.content.Intent
import android.net.Uri

interface Publisher {
	fun init(context: Context) = Unit

	fun createDeveloperPageIntent(): Intent

	fun createAppRatePageIntent(context: Context): Intent
}

open class BazaarPublisher(
	private val developerId: String,
	private val forceMarket: Boolean = false
) : Publisher {
	override fun createDeveloperPageIntent() =
		Intent(Intent.ACTION_VIEW).apply {
			data = Uri.parse("https://cafebazaar.ir/developer/$developerId")
			if (forceMarket) `package` = MARKET_PACKAGE
		}

	override fun createAppRatePageIntent(context: Context) =
		Intent(Intent.ACTION_VIEW).apply {
			data = Uri.parse("https://cafebazaar.ir/app/${context.packageName}")
			if (forceMarket) `package` = MARKET_PACKAGE
		}

	companion object {
		private const val MARKET_PACKAGE = "com.farsitel.bazaar"
	}
}

open class GooglePlayPublisher(
	private val developerId: String,
	private val forceMarket: Boolean = false
) : Publisher {
	override fun createDeveloperPageIntent() =
		Intent(Intent.ACTION_VIEW).apply {
			data = Uri.parse("https://play.google.com/store/apps/developer?id=$developerId")
			if (forceMarket) `package` = MARKET_PACKAGE
		}

	override fun createAppRatePageIntent(context: Context) =
		Intent(Intent.ACTION_VIEW).apply {
			data = Uri.parse("https://play.google.com/store/apps/details?id=${context.packageName}")
			if (forceMarket) `package` = MARKET_PACKAGE
		}

	companion object {
		private const val MARKET_PACKAGE = "com.android.vending"
	}
}
