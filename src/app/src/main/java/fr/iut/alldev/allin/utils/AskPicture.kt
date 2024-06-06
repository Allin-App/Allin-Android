package fr.iut.alldev.allin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.content.FileProvider
import fr.iut.alldev.allin.R
import java.io.File

class AskPicture : ActivityResultContract<AskPictureParams, AskPictureResult?>() {
    private var uri: Uri? = null

    override fun createIntent(context: Context, input: AskPictureParams): Intent {
        val pickerIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            .also { intent ->
                input.cameraFile.also { file ->
                    val photoUri: Uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.fileprovider",
                        file
                    ).also { uri = it }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }

        return Intent.createChooser(cameraIntent, context.getText(R.string.profile_pick_profile_picture))
            .also { intent ->
                intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickerIntent))
            }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): AskPictureResult? {
        return if (resultCode != Activity.RESULT_OK) {
            null
        } else {
            (intent?.data ?: uri)?.let {
                AskPictureResult.PictureResult(it)
            }
        }
    }

}

data class AskPictureParams(val cameraFile: File)

sealed class AskPictureResult {
    data class PictureResult(val pickedFile: Uri?) : AskPictureResult()
}

fun createImageFile(context: Context): File {
    val directory = File(context.cacheDir, ".").apply {
        mkdirs()
    }
    return File.createTempFile("picture_", ".jpg", directory)
}