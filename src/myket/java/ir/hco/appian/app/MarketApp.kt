package ir.hco.appian.app

import ir.hco.utils.MyketPublisher

class MarketApp : MainApp() {
	override val publisher = MyketPublisher(
		developerId = BuildConfig.DEVELOPER_ID
	)
}
