package ir.hco.appian.app

import ir.adad.AdadAdvertiser
import ir.hco.appian.BazaarPublisher

class MarketApp : MainApp() {
	override val publisher = BazaarPublisher(
		developerId = BuildConfig.DEVELOPER_ID,
		forceMarket = false
	)
	override val advertiser = AdadAdvertiser(
		testMode = BuildConfig.DEBUG,
		appToken = "a1b66a0e-1db8-4d3a-9c00-5b834954a6bc",
		bannerToken = "b7d2984d-7c9c-4dbf-bf96-b750d320f69b",
		interstitialToken = "45cde708-534e-4142-b0be-ad8b16a68308"
	)
}
