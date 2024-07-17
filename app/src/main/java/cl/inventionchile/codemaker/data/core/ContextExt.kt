package cl.inventionchile.codemaker.data.core

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Build
import android.os.PersistableBundle
import android.widget.Toast

fun Context.copyToClipboard(text:String) {
    val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("", text)

    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
        Toast.makeText(this, "Clave copiada", Toast.LENGTH_SHORT).show()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        clipData.apply {
            description.extras = PersistableBundle().apply {
                putBoolean(ClipDescription.EXTRA_IS_SENSITIVE, true)
            }
        }
    }else{
        clipData.apply {
            description.extras = PersistableBundle().apply {
                putBoolean("android.content.extra.IS_SENSITIVE", true)
            }
        }
    }

    clipboardManager.setPrimaryClip(clipData)
}