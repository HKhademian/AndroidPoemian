package ir.hco.appian.app.page.home

import android.graphics.Color
import android.view.Gravity.CENTER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewManager
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import ir.hco.appian.app.R
import ir.hco.appian.app.data.Category
import ir.hco.appian.app.data.Repository
import ir.hco.appian.views.cardView
import ir.hco.appian.views.item
import ir.hco.appian.views.linearLParams
import ir.hossainco.utils.App.context
import ir.hossainco.utils.ui.TextSize
import ir.hossainco.utils.view.appTextView
import ir.hossainco.utils.view.spliter
import ir.hossainco.utils.view.verticalScrollView
import org.jetbrains.anko.*

internal class HomeUI : AnkoComponent<HomePage> {
	override fun createView(ui: AnkoContext<HomePage>) = with(ui) {
		val context = ui.ctx

		verticalScrollView {
			id = R.id.list
			padding = dip(8)

			cardView {
				verticalLayout {
					radius = 16f
					gravity = CENTER

					imageView(R.drawable.foroog) {
						backgroundColor = Color.BLACK
						scaleType = ImageView.ScaleType.CENTER_CROP
					}.lparams(width = MATCH_PARENT, height = dip(256))

					appTextView(textRes = R.string.app_title, textSize = TextSize.ExtraLargeTextSize) {
						gravity = CENTER
					}.lparams(width = MATCH_PARENT)

					appTextView(textRes = R.string.app_subtitle, textSize = TextSize.LargeTextSize) {
						gravity = CENTER
					}.lparams(width = MATCH_PARENT)
				}
			}.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
				margin = context.dip(4)
			}

			item(ui.owner, titleRes = R.string.item_bookmarks, iconRes = R.drawable.ic_bookmark) {
				Repository.bookmarksLiveData.observe(ui.owner, Observer {
					isVisible = !it.isNullOrEmpty()
				})
			}.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
				margin = context.dip(4)
			}.setOnClickListener {
				ui.owner.onClickBookmarks(it)
			}

			spliter()

			Repository.cats.filter { it.parentId == "0" }.forEach {
				category(ui, it, 0)
			}

			spliter()

//			item(ui.owner, titleRes = R.string.item_settings, iconRes = R.mipmap.ic_launcher_round)
//				.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
//					margin = context.dip(4)
//				}.setOnClickListener {
//					ui.owner.onClickSettings(it)
//				}

			item(ui.owner, titleRes = R.string.item_references, iconRes = R.drawable.ic_references)
				.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
					margin = context.dip(4)
				}.setOnClickListener {
					ui.owner.onClickReferences(it)
				}

//			item(ui.owner, titleRes = R.string.item_about_us, iconRes = R.mipmap.ic_launcher_round)
//				.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
//					margin = context.dip(4)
//				}.setOnClickListener {
//					ui.owner.onClickAbout(it)
//				}

			item(ui.owner, titleRes = R.string.item_apps, iconRes = R.drawable.ic_other_apps)
				.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
					margin = context.dip(4)
				}.setOnClickListener {
					ui.owner.onClickOtherApps(it)
				}

			item(ui.owner, titleRes = R.string.item_rate, iconRes = R.drawable.ic_vote)
				.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
					margin = context.dip(4)
				}.setOnClickListener {
					ui.owner.onClickRate(it)
				}
		}
	}

	fun ViewManager.category(ui: AnkoContext<HomePage>, category: Category, level: Int): CardView {
		val context = context
		val subCategories = Repository.cats.filter { it.parentId == category.id }

		val view = item(ui.owner, title = category.title, iconRes = R.mipmap.ic_launcher)
			.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
				margin = context.dip(4)
			}

		if (subCategories.isEmpty()) {
			view.setOnClickListener {
				ui.owner.onClickCategory(category)
			}
		} else {
			val subView = verticalLayout {
				isVisible = category.id in ui.owner.expandedItemIds
				topPadding = 0
				bottomPadding = context.dip(16)
				leftPadding = context.dip(16)
				rightPadding = context.dip(16)

				subCategories.forEach {
					category(ui, it, level + 1)
				}
			}

			view.setOnClickListener {
				val isVisible = subView.isVisible
				subView.isVisible = !isVisible
				ui.owner.onItemExpand(view, category, !isVisible)
			}
		}

		return view
	}
}
