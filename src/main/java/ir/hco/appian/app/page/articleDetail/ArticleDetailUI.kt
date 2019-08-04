package ir.hco.appian.app.page.articleDetail

import android.graphics.Color
import android.os.Build
import android.text.Layout
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import ir.hco.appian.app.R
import ir.hossainco.utils.ui.TextSize
import ir.hossainco.utils.view.appTextView
import ir.hossainco.utils.view.verticalScrollView
import org.jetbrains.anko.*

internal class ArticleDetailUI : AnkoComponent<ArticleDetailPage> {
	override fun createView(ui: AnkoContext<ArticleDetailPage>) = with(ui) {
		verticalScrollView {
			layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
			padding = dip(16)

			appTextView(
				id = R.id.text,
				notebookLine = true,
				notebookLineColor = Color.DKGRAY,
				textSize = TextSize.LargeTextSize
			) {
				textColorResource = R.color.secondaryTextColor
				gravity = Gravity.START or Gravity.TOP
				setLineSpacing(24f, 1.8f)
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
					justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
				}
			}.lparams(MATCH_PARENT, MATCH_PARENT)

		}
	}
}
