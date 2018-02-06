package io.datalive.deeplink.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.datalive.deeplink.activity.InstallActivity

class ReferrerReceiver : BroadcastReceiver() {

    companion object {
        private val ACTION_INSTALL_REFERRER = "com.android.vending.INSTALL_REFERRER"
    }

    override fun onReceive(context: Context, intent: Intent) {
        intent.let {
            when (intent.action) {
                ACTION_INSTALL_REFERRER -> {
                    if (intent.data.query == null) return

                    val intent = Intent(context, InstallActivity::class.java)

                    with(intent) {
                        data.queryParameterNames.forEach { putExtra(it, data.getQueryParameter(it)) }

                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                        context.startActivity(this)
                    }
                }
            }
        }
    }
}