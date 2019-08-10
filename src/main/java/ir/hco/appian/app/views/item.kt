package ir.hco.appian.app.views

import android.view.Gravity.CENTER_VERTICAL
import android.view.Gravity.START
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewManager
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import ir.hco.util.DrawableSource
import ir.hco.util.StringSource
import ir.hossainco.utils.ui.TextSize
import ir.hossainco.utils.view.appTextView
import org.jetbrains.anko.dip
import org.jetbrains.anko.imageView
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.padding

fun ViewManager.item(
	title: StringSource = StringSource.NULL,
	icon: DrawableSource = DrawableSource.NULL,
	radius: Float = 12f,
	mirror: Boolean = false,
	theme: Int = 0,
	init: (CardView.(titleView: TextView, iconView: ImageView) -> Unit) = { _, _ -> }
) =
	cardView(theme = theme) {
		val context = context!!
		this.radius = radius

		if (mirror) {
			linearLayout {
				val titleView =
					appTextView(textSize = TextSize.LargeTextSize) {
						padding = dip(8)
						gravity = START or CENTER_VERTICAL

						isVisible = title.hasString(context)
						title.setText(this)
					}.lparams(
						width = 0,
						height = MATCH_PARENT,
						weight = 1f
					)

				val iconView =
					imageView {
						padding = dip(8)

						isVisible = icon.hasDrawable(context)
						icon.setImage(this)
					}.lparams(width = dip(80), height = dip(80))

				this@cardView.init(titleView, iconView)
			}
		} else {
			linearLayout {

				val iconView =
					imageView {
						padding = dip(8)

						isVisible = icon.hasDrawable(context)
						icon.setImage(this)
					}.lparams(width = dip(80), height = dip(80))

				val titleView =
					appTextView(textSize = TextSize.LargeTextSize) {
						padding = dip(8)
						gravity = START or CENTER_VERTICAL

						isVisible = title.hasString(context)
						title.setText(this)
					}.lparams(
						width = MATCH_PARENT,
						height = MATCH_PARENT
					)

				this@cardView.init(titleView, iconView)
			}
		}
	}
