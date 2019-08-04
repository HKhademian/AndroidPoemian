package ir.hco.appian.app.page.poemList

import android.view.Gravity.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import ir.hco.appian.app.R
import ir.hossainco.utils.ui.TextSize
import ir.hossainco.utils.view.appTextView
import ir.hossainco.utils.view.spliter
import org.jetbrains.anko.*

internal class ArticleItemUI : AnkoComponent<ArticleAdapter> {
	override fun createView(ui: AnkoContext<ArticleAdapter>) = with(ui) {
		linearLayout {
			layoutParams = RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
			gravity = CENTER

			appTextView(id = R.id.index) {
				gravity = CENTER
				padding = dip(16)
				minWidth = dip(64)
			}.lparams(width = WRAP_CONTENT, height = MATCH_PARENT)

			spliter()
				.lparams(width = dip(1), height = MATCH_PARENT)

			appTextView(id = R.id.title, textSize = TextSize.LargeTextSize) {
				padding = dip(16)
				gravity = START or CENTER_VERTICAL
			}.lparams(width = 0, height = WRAP_CONTENT, weight = 1f)

			spliter()
				.lparams(width = dip(1), height = MATCH_PARENT)

			imageView {
				id = R.id.bookmark
				padding = dip(12)
			}.lparams(width = dip(48), height = dip(48))
		}
	}
}
