package ir.hco.appian.app.page.poemList

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import ir.hco.appian.app.R
import ir.hco.appian.app.data.Repository

internal class ArticleItemViewHolder(
	itemView: View,
	itemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {
	private val titleView = itemView.findViewById<TextView>(R.id.title)
	private val indexView = itemView.findViewById<TextView>(R.id.index)
	private val bookmarkView = itemView.findViewById<ImageView>(R.id.bookmark)

	private val bookmarkOnDrawable = DrawableCompat.wrap(
		itemView.context.resources.getDrawable(R.drawable.ic_bookmark_on)
	).mutate()
	private val bookmarkOffDrawable = DrawableCompat.wrap(
		itemView.context.resources.getDrawable(R.drawable.ic_bookmark_off)
	).mutate()

	init {
		itemView.setOnClickListener {
			itemClickListener(layoutPosition)
		}

		val color = Color.parseColor("#FF991111")
		DrawableCompat.setTint(bookmarkOnDrawable, color)
		DrawableCompat.setTint(bookmarkOffDrawable, color)
	}

	@SuppressLint("SetTextI18n")
	fun bind(position: Int, articleId: String) {
		val item = Repository.poems.first { it.id == articleId }
		titleView.text = item.title
		indexView.text = "%03d".format((item.id.split("-").last().toIntOrNull() ?: 0) + 1)
		bookmarkView.setImageDrawable(if (item.bookmark) bookmarkOnDrawable else bookmarkOffDrawable)
	}
}
