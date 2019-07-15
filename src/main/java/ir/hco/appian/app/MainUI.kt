package ir.hco.appian.app

import android.graphics.Color
import android.view.Gravity.CENTER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.lifecycle.Observer
import ir.hco.appian.app.data.Repository
import ir.hossainco.utils.App
import ir.hossainco.utils.view.appTextView
import org.jetbrains.anko.*

internal class MainUI : AnkoComponent<MainActivity> {
	override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
		verticalLayout {
			frameLayout {
				id = R.id.fragment

				Repository.settingsLiveData.observe(ui.owner, Observer { settings ->
					backgroundColor = if (settings?.darkMode == true)
						Color.parseColor("#FFeecc66")
					else
						Color.parseColor("#FFeeeeee")
				})
			}.lparams(width = MATCH_PARENT, height = 0, weight = 1f)

			frameLayout {
				backgroundColorResource = R.color.primaryDarkColor

				appTextView(textRes = R.string.ads, dark = false)
					.lparams(gravity = CENTER)

				(App.app as MainApp).advertiser.createBanner(this)
					.lparams(width = MATCH_PARENT, height = WRAP_CONTENT, gravity = CENTER)
			}.lparams(width = MATCH_PARENT, height = dip(50))
		}
	}

}
