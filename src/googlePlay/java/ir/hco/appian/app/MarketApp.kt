package ir.hco.appian.app

import ir.hco.utils.GooglePlayPublisher

class MarketApp : MainApp() {
	override val publisher = GooglePlayPublisher(
		developerId = BuildConfig.DEVELOPER_ID
	)
}
