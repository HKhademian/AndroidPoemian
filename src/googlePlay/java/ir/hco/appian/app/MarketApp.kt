package ir.hco.appian.app

import com.appbrain.AppBrainAdvertiser
import ir.hco.appian.GooglePlayPublisher

class MarketApp : MainApp() {
	override val publisher = GooglePlayPublisher(
		developerId = BuildConfig.DEVELOPER_ID,
		forceMarket = false
	)
	override val advertiser = AppBrainAdvertiser()
}
