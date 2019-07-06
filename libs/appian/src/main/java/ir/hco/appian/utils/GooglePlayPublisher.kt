package ir.hco.appian.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

abstract class GooglePlayPublisher : Publisher {
	protected abstract val developerId: String
	protected open val forceMarket: Boolean = false

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
