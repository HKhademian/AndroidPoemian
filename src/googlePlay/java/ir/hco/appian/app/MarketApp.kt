package ir.hco.appian.app

import ir.hco.appbrain.AppBrainAdvertiser
import ir.hco.util.GooglePlayPublisher

class MarketApp : MainApp() {
	override val publisher = GooglePlayPublisher(
		developerId = BuildConfig.DEVELOPER_ID
	)

	override val advertiser = AppBrainAdvertiser()
}
