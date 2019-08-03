package ir.hco.appian.app

import android.view.Gravity.CENTER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.Toolbar
import ir.hossainco.utils.App
import ir.hossainco.utils.view.appTextView
import ir.hossainco.utils.view.factory
import org.jetbrains.anko.*

internal class MainUI : AnkoComponent<MainActivity> {
	override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
		verticalLayout {
			backgroundColorResource = R.color.background

			factory(::Toolbar, theme = R.style.ThemeOverlay_AppCompat_ActionBar) {
				id = R.id.toolbar
				backgroundColorResource = R.color.primaryColor
				setTitleTextColor(context.resources.getColor(R.color.primaryTextColor))
				setSubtitleTextColor(context.resources.getColor(R.color.primaryTextColor))
				setTitle(R.string.app_title)
				setSubtitle(R.string.app_subtitle)
			}

			frameLayout {
				id = R.id.fragment
			}.lparams(width = MATCH_PARENT, height = 0, weight = 1f)

			frameLayout {
				backgroundColorResource = R.color.secondaryLightColor

				appTextView(textRes = R.string.ads, dark = false)
					.lparams(gravity = CENTER)

				(App.app as MainApp).advertiser.createBanner(this)
					?.lparams(width = MATCH_PARENT, height = WRAP_CONTENT, gravity = CENTER)
			}.lparams(width = MATCH_PARENT, height = dip(50))
		}
	}
}
