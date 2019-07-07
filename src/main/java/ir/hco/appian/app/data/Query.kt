package ir.hco.appian.app.data

import java.io.Serializable

interface Query : Serializable {
	val title: String?
	val itemIds: List<String>
	val itemCount: Int

	object AllQuery : Query {
		override val title = null

		private val query =
			Repository.poems.map { it.id }

		override val itemIds
			get() = query

		override val itemCount
			get() = query.size
	}

	object BookmarkQuery : Query {
		override val title = "نشان گذاری ها"

		private val query =
			Repository.poems
				.filter { it.bookmark }
				.map { it.id }

		override val itemIds
			get() = query

		override val itemCount
			get() = query.size
	}

	data class CategoryQuery(val catId: String) : Query {
		override val title get() = "دسته ${Repository.cats.firstOrNull { it.id == catId }?.title}"

		private val query =
			Repository.poems
				.filter { it.parentId == catId }
				.map { it.id }

		override val itemIds
			get() = query

		override val itemCount
			get() = query.size
	}
}
