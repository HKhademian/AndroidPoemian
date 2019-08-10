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
import ir.hco.appian.app.views.cardView
import ir.hco.appian.app.views.item
import ir.hco.util.BaseApp.Companion.context
import ir.hco.util.DrawableSource
import ir.hco.util.StringSource
import ir.hco.util.views.linearLParams
import ir.hossainco.utils.ui.TextSize
import ir.hossainco.utils.view.appTextView
import ir.hossainco.utils.view.splitter
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

					appTextView(textRes = R.string.app_title, textSize = TextSize.HeaderTextSize) {
						gravity = CENTER
					}.lparams(width = MATCH_PARENT)

					appTextView(textRes = R.string.app_subtitle, textSize = TextSize.LargeTextSize) {
						gravity = CENTER
					}.lparams(width = MATCH_PARENT)
				}
			}.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
				margin = dip(4)
			}

			linearLayout {
				item(title = StringSource.of(R.string.item_apps), icon = DrawableSource.of(R.drawable.ic_action_apps))
					.lparams(0, WRAP_CONTENT, 1f) {
						margin = context.dip(4)
					}.setOnClickListener {
						ui.owner.onClickOtherApps(it)
					}

				item(title = StringSource.of(R.string.item_rate), icon = DrawableSource.of(R.drawable.ic_action_vote))
					.lparams(0, WRAP_CONTENT, 1f) {
						margin = dip(4)
					}.setOnClickListener {
						ui.owner.onClickRate(it)
					}
			}

			item(
				title = StringSource.of(R.string.item_bookmarks),
				icon = DrawableSource.of(R.drawable.ic_action_bookmarks)
			) { _, _ ->
				Repository.bookmarksLiveData.observe(ui.owner, Observer {
					isVisible = !it.isNullOrEmpty()
				})
			}.lparams(MATCH_PARENT, WRAP_CONTENT) {
				margin = dip(4)
			}.setOnClickListener {
				ui.owner.onClickBookmarks(it)
			}

			splitter()

			Repository.cats.filter { it.parentId == "0" }.forEach {
				category(ui, it, 0)
			}

			splitter()

			item(title = StringSource.of(R.string.item_settings), icon = DrawableSource.of(R.drawable.ic_action_settings))
				.lparams(MATCH_PARENT, WRAP_CONTENT) {
					margin = dip(4)
				}.setOnClickListener {
					ui.owner.onClickSettings(it)
				}

			linearLayout {

				item(title = StringSource.of(R.string.item_about_us), icon = DrawableSource.of(R.mipmap.ic_launcher))
					.lparams(0, WRAP_CONTENT, 1f) {
						margin = dip(4)
					}.setOnClickListener {
						ui.owner.onClickAbout(it)
					}

				item(
					title = StringSource.of(R.string.item_references),
					icon = DrawableSource.of(R.drawable.ic_action_references)
				)
					.lparams(0, WRAP_CONTENT, 1f) {
						margin = dip(4)
					}.setOnClickListener {
						ui.owner.onClickReferences(it)
					}
			}
		}
	}

	fun ViewManager.category(ui: AnkoContext<HomePage>, category: Category, level: Int): CardView {
		val subCategories = Repository.cats.filter { it.parentId == category.id }

		val view = item(
			title = StringSource.of(category.title)
		) { _, iconView: ImageView ->
			iconView.isVisible = true
			iconView.scaleType = ImageView.ScaleType.CENTER_CROP
			iconView.padding = 0
			iconView.setImageResource(
				when (category.id.subSequence(0, 1)) {
					"1" -> R.drawable.splash
					"2" -> R.drawable.foroog2
					"3" -> R.drawable.foroog3
					"4" -> R.drawable.foroog4
					"5" -> R.drawable.foroog
					else -> R.mipmap.ic_launcher
				}
			)
		}.linearLParams(MATCH_PARENT, WRAP_CONTENT) {
			margin = context.dip(4)
		}

		if (subCategories.isEmpty()) {
			view.setOnClickListener {
				ui.owner.onClickCategory(category)
			}
		} else {
			val subView = verticalLayout {
				padding = dip(16)
				topPadding = 0
				backgroundColor = Color.LTGRAY.withAlpha(0x33)

				subCategories.forEach {
					category(ui, it, level + 1)
				}
			}

			ui.owner.expandedItemIds.observe(ui.owner.viewLifecycleOwner, Observer {
				subView.isVisible = it != null && category.id in it
			})

			view.setOnClickListener {
				ui.owner.onItemExpand(view, category, !subView.isVisible)
			}
		}

		return view
	}
}
