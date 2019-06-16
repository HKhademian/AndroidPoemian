package ir.hco.appian.utils.views

import android.view.ViewManager
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.cardview.widget.CardView
import ir.hco.appian.page.BasePage
import ir.hossainco.utils.ui.TextSize
import ir.hossainco.utils.view.appTextView
import ir.hossainco.utils.view.factory

fun ViewManager.myCardView(
	owner: BasePage,
	init: CardView.() -> Unit
) = factory(::CardView) {
	//	Repository.observeSettings(owner) {
//		backgroundColor = if (it.darkMode) Color.DKGRAY else Color.WHITE
//	}
	init()
}

fun ViewManager.myTextView(
	owner: BasePage,
	@IdRes id: Int? = null,

	@StringRes textRes: Int? = null,
	text: String? = null,

	dark: Boolean = true,

	@ColorInt notebookLineColor: Int? = null,
	@ColorRes notebookLineColorRes: Int? = null,

	textSize: TextSize = TextSize.DefaultTextSize,
	init: TextView.() -> Unit = {}
) = appTextView(
	id = id,
	textRes = textRes,
	text = text,
	dark = dark,
	notebookLineColor = notebookLineColor,
	notebookLineColorRes = notebookLineColorRes,
	textSize = textSize
) {
	//	Repository.observeSettings(owner) {
//		textColor = (if (dark xor it.darkMode) 0x06 else 0xf0).gray.opaque
//		this.textSize = textSize.value * it.fontSizeMultiplier
//	}
	init()
}
