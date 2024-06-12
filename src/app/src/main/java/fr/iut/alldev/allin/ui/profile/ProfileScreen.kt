package fr.iut.alldev.allin.ui.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityOptionsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.iut.alldev.allin.ui.profile.components.ProfileScreenContent
import fr.iut.alldev.allin.utils.AskPicture
import fr.iut.alldev.allin.utils.AskPictureParams
import fr.iut.alldev.allin.utils.AskPictureResult
import fr.iut.alldev.allin.utils.createImageFile
import java.io.ByteArrayOutputStream
import java.io.File

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = AskPicture()) { result ->
        when (result) {
            is AskPictureResult.PictureResult -> {
                val resolver = context.contentResolver
                val inputStream = resolver.openInputStream(result.pickedFile!!)
                val file = File.createTempFile("image", null, context.cacheDir)
                file.outputStream().use { outputStream ->
                    inputStream?.copyTo(outputStream)
                }

                val baos = ByteArrayOutputStream()
                BitmapFactory.decodeFile(file.path).compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val encodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
                viewModel.selectNewProfilePicture(encodedImage)
            }

            null -> Unit
        }
    }

    Column {
        when (val s = state) {
            is ProfileViewModel.State.Loaded -> {
                ProfileScreenContent(
                    image = s.user.image,
                    username = s.user.username,
                    totalBets = s.user.nbBets,
                    bestWin = s.user.bestWin,
                    friends = s.user.nbFriends,
                    selectNewProfilePicture = {
                        val file = createImageFile(context = context)
                        launcher.launch(AskPictureParams(file), ActivityOptionsCompat.makeBasic())
                    }
                )
            }

            ProfileViewModel.State.Loading -> {

            }
        }
    }
}