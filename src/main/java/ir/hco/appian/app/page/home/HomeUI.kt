package ir.hco.appian.app.page.home

import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import ir.hco.appian.app.R
import ir.hco.appian.utils.views.myCardView
import ir.hco.appian.utils.views.myTextView
import ir.hossainco.utils.ui.TextSize
import ir.hossainco.utils.view.spliter
import ir.hossainco.utils.view.verticalScrollView
import org.jetbrains.anko.*

internal class HomeUI : AnkoComponent<HomePage> {
	override fun createView(ui: AnkoContext<HomePage>) = with(ui) {
		val context = ui.ctx

		verticalScrollView {

			myCardView(ui.owner) {
				verticalLayout {
					imageView(R.mipmap.ic_launcher) {
						padding = dip(16)
						adjustViewBounds = true
						maxHeight = dip(196)
					}
					myTextView(ui.owner, textRes = R.string.app_name, textSize = TextSize.HeaderTextSize) {
						gravity = Gravity.CENTER
					}
				}
			}.lparams(width = MATCH_PARENT) {
				margin = dip(8)
			}

			spliter()

		}
	}
}
