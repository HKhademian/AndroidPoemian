package ir.hco.appian.app

import ir.hco.magnet.MagnetAdvertiser
import ir.hco.magnet.MagnetToken
import ir.hco.util.ads.Advertiser
import ir.hco.util.MyketPublisher

class MarketApp : MainApp() {
	override val publisher = MyketPublisher(
		developerId = BuildConfig.DEVELOPER_ID
	)

	override val advertiser = MagnetAdvertiser(
		testMode = BuildConfig.DEBUG,
		fullToken = BuildConfig.MAGNET_FULL_TOKEN,
		bannerTokens = *arrayOf(
			MagnetToken(null, BuildConfig.MAGNET_BANNER_TOKEN, null),
			MagnetToken(
				Advertiser.UNIT_CONTENT,
				BuildConfig.MAGNET_CONTENT_TOKEN,
				MagnetAdvertiser.BANNER_SIZE_MEDIUM_RECTANGLE
			)
		)
	)
}
