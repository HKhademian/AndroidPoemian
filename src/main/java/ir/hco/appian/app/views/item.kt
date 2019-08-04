 package ir.hco.appian.app.views

 import android.view.Gravity.CENTER_VERTICAL
 import android.view.Gravity.START
 import android.view.ViewGroup.LayoutParams.MATCH_PARENT
 import android.view.ViewManager
 import androidx.annotation.DrawableRes
 import androidx.annotation.StringRes
 import androidx.cardview.widget.CardView
 import ir.hossainco.utils.ui.TextSize
 import ir.hossainco.utils.view.appTextView
 import org.jetbrains.anko.dip
 import org.jetbrains.anko.imageView
 import org.jetbrains.anko.linearLayout
 import org.jetbrains.anko.padding

 fun ViewManager.item(
 	title: String? = null,
 	@StringRes titleRes: Int? = null,

 	@DrawableRes iconRes: Int? = null,

 	init: (CardView.() -> Unit) = {}
 ) =
 	cardView {
 		linearLayout {
 			padding = dip(8)

 			if (iconRes != null)
 				imageView(iconRes)
 					.lparams(width = dip(64), height = dip(64))

 			appTextView(text = title, textRes = titleRes, textSize = TextSize.LargeTextSize) {
 				gravity = START or CENTER_VERTICAL
 			}.lparams(
 				width = MATCH_PARENT,
 				height = MATCH_PARENT
 			)
 		}

 		init()
 	}
