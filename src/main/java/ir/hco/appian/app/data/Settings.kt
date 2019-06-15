package ir.hco.appian.app.data

import java.io.Serializable

data class Settings(
	val fontSizeMultiplier: Float = 1.0f,
	val darkMode: Boolean = false
) : Serializable {
	fun copy(
		fontSizeMultiplier: Float? = null,
		darkMode: Boolean? = null
	) = Settings(
		fontSizeMultiplier = fontSizeMultiplier ?: this.fontSizeMultiplier,
		darkMode = darkMode ?: this.darkMode
	)
}
