package ir.hco.appian.app.page.splash

import android.view.Gravity.BOTTOM
import android.view.Gravity.CENTER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import ir.hco.appian.app.BuildConfig
import ir.hco.appian.app.R
import ir.hco.appian.app.views.cardView
import ir.hossainco.utils.ui.TextSize
import ir.hossainco.utils.view.appTextView
import org.jetbrains.anko.*

internal class SplashUI : AnkoComponent<SplashPage> {
	override fun createView(ui: AnkoContext<SplashPage>) = with(ui) {
		frameLayout {
			imageView(R.drawable.splash) {
				scaleType = ImageView.ScaleType.CENTER_CROP
			}.lparams(width = MATCH_PARENT, height = MATCH_PARENT)


			cardView {
				radius = 32f

				verticalLayout {
					gravity = CENTER

					imageView(R.mipmap.ic_launcher)
						.lparams(width = dip(192), height = dip(192))

					appTextView(textRes = R.string.app_title, textSize = TextSize.HeaderTextSize * 1.5f)

					appTextView(textRes = R.string.loading, textSize = TextSize.MediumTextSize)
				}.lparams(width = MATCH_PARENT, height = MATCH_PARENT)
			}.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT) {
				gravity = CENTER
			}

			verticalLayout {
				gravity = CENTER

				appTextView(text = BuildConfig.VERSION_NAME, dark = false) {
					backgroundColorResource = R.color.primaryDarkColor
				}
			}.lparams(width = MATCH_PARENT, height = WRAP_CONTENT) {
				gravity = BOTTOM
			}
		}
	}
}
