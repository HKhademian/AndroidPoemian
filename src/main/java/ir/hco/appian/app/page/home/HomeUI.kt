package ir.hco.appian.app.page.home

import android.view.Gravity.CENTER
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewManager
import androidx.cardview.widget.CardView
import ir.hco.appian.app.R
import ir.hco.appian.app.data.Category
import ir.hco.appian.app.data.Repository
import ir.hco.appian.utils.linearLParams
import ir.hco.appian.utils.views.item
import ir.hco.appian.utils.views.myCardView
import ir.hco.appian.utils.views.myTextView
import ir.hossainco.utils.App.context
import ir.hossainco.utils.ui.TextSize
import ir.hossainco.utils.view.spliter
import ir.hossainco.utils.view.verticalScrollView
import org.jetbrains.anko.*

internal class HomeUI : AnkoComponent<HomePage> {
	override fun createView(ui: AnkoContext<HomePage>) = with(ui) {
		val context = ui.ctx

		verticalScrollView {
			padding = dip(8)

			myCardView(owner) {
				verticalLayout {
					padding = dip(8)
					gravity = CENTER

					imageView(R.mipmap.ic_launcher_round)
						.lparams(width = dip(96), height = dip(96))

					myTextView(owner, textRes = R.string.app_title, textSize = TextSize.ExtraLargeTextSize) {
						gravity = CENTER
					}.lparams(width = MATCH_PARENT)

					myTextView(owner, textRes = R.string.app_subtitle, textSize = TextSize.LargeTextSize) {
						gravity = CENTER
					}.lparams(width = MATCH_PARENT)
				}
			}.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
				margin = context.dip(4)
			}

			spliter()
			spliter()
			spliter()

			Repository.cats.filter { it.parentId == "0" }.forEach {
				category(ui, it, 0)
			}

			spliter()
			spliter()
			spliter()

			item(ui.owner, titleRes = R.string.item_bookmarks, iconRes = R.mipmap.ic_launcher_round)
				.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
					margin = context.dip(4)
				}

			item(ui.owner, titleRes = R.string.item_settings, iconRes = R.mipmap.ic_launcher_round)
				.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
					margin = context.dip(4)
				}

			item(ui.owner, titleRes = R.string.item_about_us, iconRes = R.mipmap.ic_launcher_round)
				.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
					margin = context.dip(4)
				}
		}
	}

	fun ViewManager.category(ui: AnkoContext<HomePage>, category: Category, level: Int): CardView {
		val context = context
		val subCategories = Repository.cats.filter { it.parentId == category.id }

		val view = item(ui.owner, title = category.title, iconRes = R.mipmap.ic_launcher_round)
			.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
				margin = context.dip(4)
			}

		if (subCategories.isEmpty()) {
			view.setOnClickListener {
				ui.owner.onClickCategory(category)
			}
		} else {
			val subView = verticalLayout {
				visibility = GONE
				topPadding = 0
				bottomPadding = context.dip(16)
				leftPadding = context.dip(16)
				rightPadding = context.dip(16)

				subCategories.forEach {
					category(ui, it, level + 1)
				}
			}

			view.setOnClickListener {
				subView.visibility = if (subView.visibility == VISIBLE) GONE else VISIBLE
			}
		}

		return view
	}
}
