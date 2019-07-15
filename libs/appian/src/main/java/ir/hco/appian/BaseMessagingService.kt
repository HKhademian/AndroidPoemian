package ir.hco.appian

import co.ronash.pushe.Pushe
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

abstract class BaseMessagingService : FirebaseMessagingService() {
	@Suppress("LeakingThis")
	val handlers = mutableListOf<MessagingHandler>(
		ParseMessagingHandler(this)
	)

	override fun onMessageReceived(remoteMessage: RemoteMessage?) {
		for (handler in handlers) {
			if (handler.onMessageReceived(remoteMessage)) {
				return
			}
		}

		super.onMessageReceived(remoteMessage)
	}

	override fun onNewToken(tocken: String?) {
		handlers.forEach { it.onNewToken(tocken) }
		super.onNewToken(tocken)
	}

	override fun onMessageSent(s: String?) {
		handlers.forEach { it.onMessageSent(s) }
		super.onMessageSent(s)
	}

	override fun onSendError(s: String?, e: Exception?) {
		handlers.forEach { it.onSendError(s, e) }
		super.onSendError(s, e)
	}

	override fun onDeletedMessages() {
		handlers.forEach { it.onDeletedMessages() }
		super.onDeletedMessages()
	}
}

interface MessagingHandler {
	fun onMessageReceived(remoteMessage: RemoteMessage?) =
		false

	fun onNewToken(s: String?) =
		Unit

	fun onMessageSent(s: String?) =
		Unit

	fun onSendError(s: String?, e: Exception?) =
		Unit

	fun onDeletedMessages() =
		Unit
}

class ParseMessagingHandler(service: BaseMessagingService) : MessagingHandler {
	private val pusheHandler by lazy { Pushe.getFcmHandler(service) }

	override fun onMessageReceived(remoteMessage: RemoteMessage?) =
		pusheHandler.onMessageReceived(remoteMessage)

	override fun onNewToken(s: String?) =
		pusheHandler.onNewToken(s)

	override fun onMessageSent(s: String?) =
		pusheHandler.onMessageSent(s)

	override fun onSendError(s: String?, e: Exception?) =
		pusheHandler.onSendError(s, e)

	override fun onDeletedMessages() =
		pusheHandler.onDeletedMessages()
}
