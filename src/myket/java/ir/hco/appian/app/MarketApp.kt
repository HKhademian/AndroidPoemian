package ir.hco.appian.app

import ir.hco.magnet.MagnetAdvertiser
import ir.hco.util.Advertiser
import ir.hco.util.MyketPublisher

class MarketApp : MainApp() {
	override val publisher = MyketPublisher(
		developerId = BuildConfig.DEVELOPER_ID
	)

	override val advertiser = MagnetAdvertiser(
		testMode = BuildConfig.DEBUG,
		fullAdUnitId = BuildConfig.MAGNET_FULL_TOKEN,
		bannerAdUnitIds = *arrayOf(
			Triple(null, BuildConfig.MAGNET_BANNER_TOKEN, null),
			Triple(
				Advertiser.UNIT_CONTENT,
				BuildConfig.MAGNET_CONTENT_TOKEN,
				MagnetAdvertiser.BANNER_SIZE_MEDIUM_RECTANGLE
			)
		)
	)
}
