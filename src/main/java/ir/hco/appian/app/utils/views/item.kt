package ir.hco.appian.app.utils.views

import android.view.Gravity.CENTER_VERTICAL
import android.view.Gravity.START
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.cardview.widget.CardView
import ir.hco.appian.app.R
import ir.hco.appian.app.page.BasePage
import ir.hco.appian.app.utils.linearLParams
import ir.hossainco.utils.ui.TextSize
import org.jetbrains.anko.*

fun ViewGroup.item(
	owner: BasePage,
	title: String,
	icon: Int = R.mipmap.ic_launcher,
	init: (CardView.() -> Unit) = {}
) = myCardView(owner) {
	linearLayout {
		padding = dip(8)

		imageView(icon)
			.lparams(width = dip(64), height = dip(64))

		myTextView(owner, text = title, textSize = TextSize.LargeTextSize) {
			gravity = START or CENTER_VERTICAL
		}.lparams(
			width = MATCH_PARENT,
			height = MATCH_PARENT
		)
	}

	init()
}.linearLParams(width = MATCH_PARENT) {
	margin = dip(8)
}
