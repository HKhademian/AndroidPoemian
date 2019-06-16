package ir.hco.appian.app.utils.views

import android.view.Gravity.CENTER_VERTICAL
import android.view.Gravity.START
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewManager
import androidx.annotation.StringRes
import androidx.cardview.widget.CardView
import ir.hco.appian.app.R
import ir.hco.appian.app.page.BasePage
import ir.hossainco.utils.ui.TextSize
import org.jetbrains.anko.dip
import org.jetbrains.anko.imageView
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.padding

fun ViewManager.item(
	owner: BasePage,

	title: String? = null,
	@StringRes titleRes: Int? = null,

	icon: Int = R.mipmap.ic_launcher,
	init: (CardView.() -> Unit) = {}
) = myCardView(owner) {
	linearLayout {
		padding = dip(8)

		imageView(icon)
			.lparams(width = dip(64), height = dip(64))

		myTextView(owner, text = title, textRes = titleRes, textSize = TextSize.LargeTextSize) {
			gravity = START or CENTER_VERTICAL
		}.lparams(
			width = MATCH_PARENT,
			height = MATCH_PARENT
		)
	}

	init()
}
