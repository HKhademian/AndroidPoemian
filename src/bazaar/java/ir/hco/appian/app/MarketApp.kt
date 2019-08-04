package ir.hco.appian.app

import ir.hco.adad.AdadAdvertiser
import ir.hco.util.BazaarPublisher

class MarketApp : MainApp() {
	override val publisher = BazaarPublisher(
		developerId = BuildConfig.DEVELOPER_ID
	)

	override val advertiser = AdadAdvertiser(
		testMode = BuildConfig.DEBUG,
		appToken = BuildConfig.ADAD_APP_TOKEN,
		bannerToken = BuildConfig.ADAD_BANNER_TOKEN,
		fullToken = BuildConfig.ADAD_FULL_TOKEN,
		notificationToken = BuildConfig.ADAD_NOTIFICATION_TOKEN
	)
}
