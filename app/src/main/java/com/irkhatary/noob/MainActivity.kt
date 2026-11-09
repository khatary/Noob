package com.irkhatary.noob

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream

private val Bg = Color(0xFF080611)
private val Panel = Color(0xFF171126)
private val Purple = Color(0xFF9B35FF)
private val White = Color(0xFFF7F1FF)
private const val PIN = "1382000"

data class MediaItem(val name: String, val path: String, val mime: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.rgb(8, 6, 17)
        window.navigationBarColor = android.graphics.Color.rgb(8, 6, 17)
        setContent { NoobApp() }
    }
}

@Composable
private fun NoobApp() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val activity = context as ComponentActivity
    val prefs = remember { context.getSharedPreferences("noob_media", 0) }
    var unlocked by remember { mutableStateOf(false) }
    var pin by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }
    var tab by remember { mutableStateOf(1) }
    var media by remember { mutableStateOf(loadMedia(prefs)) }

    val picker = rememberLauncherForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { uris ->
        val added = media.toMutableList()
        uris.forEach { uri ->
            runCatching {
                val displayName = queryName(context, uri)
                val mime = context.contentResolver.getType(uri) ?: "application/octet-stream"
                val safeName = System.currentTimeMillis().toString() + "_" + displayName.replace(Regex("[^A-Za-z0-9._-]"), "_")
                val target = File(context.filesDir, safeName)
                context.contentResolver.openInputStream(uri)?.use { input ->
                    FileOutputStream(target).use { output -> input.copyTo(output) }
                }
                if (target.exists() && target.length() > 0) added.add(MediaItem(displayName, target.absolutePath, mime))
            }
        }
        media = added
        saveMedia(prefs, media)
    }

    MaterialTheme(colorScheme = darkColorScheme(background = Bg, surface = Panel, primary = Purple, onBackground = White, onSurface = White)) {
        Surface(Modifier.fillMaxSize(), color = Bg) {
            if (!unlocked) {
                Column(Modifier.fillMaxSize().padding(28.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text("NOOB", color = White, fontSize = 42.sp, fontWeight = FontWeight.Black)
                    Text("PRIVATE MEDIA VAULT", color = Purple, fontSize = 11.sp, letterSpacing = 3.sp)
                    Spacer(Modifier.height(38.dp))
                    Icon(Icons.Default.Lock, null, tint = Purple, modifier = Modifier.size(48.dp))
                    Spacer(Modifier.height(18.dp))
                    Text("رمز ورود را وارد کنید", color = White, fontSize = 19.sp)
                    Spacer(Modifier.height(18.dp))
                    OutlinedTextField(value = pin, onValueChange = { if (it.length <= 7 && it.all(Char::isDigit)) pin = it; error = false }, label = { Text("رمز ورود") }, visualTransformation = PasswordVisualTransformation(), singleLine = true)
                    if (error) Text("رمز ورود اشتباه است", color = Color(0xFFFF5577))
                    Spacer(Modifier.height(18.dp))
                    Button(onClick = { if (pin == PIN) unlocked = true else error = true }, colors = ButtonDefaults.buttonColors(containerColor = Purple), modifier = Modifier.fillMaxWidth()) { Text("ورود به Noob") }
                }
            } else {
                Column(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                    Spacer(Modifier.height(18.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Noob", color = White, fontSize = 32.sp, fontWeight = FontWeight.Black)
                        Spacer(Modifier.weight(1f))
                        Text("${media.size} فایل ذخیره‌شده", color = Purple, fontSize = 12.sp)
                    }
                    Spacer(Modifier.height(14.dp))
                    Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(22.dp), modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(18.dp)) {
                            Text(if (tab == 0) "آپلود رسانه" else "اکسپلور", color = White, fontSize = 21.sp, fontWeight = FontWeight.Bold)
                            Text("فایل‌ها در حافظه خصوصی برنامه کپی می‌شوند", color = Color.LightGray, fontSize = 12.sp)
                            if (tab == 0) {
                                Spacer(Modifier.height(14.dp))
                                Button(onClick = { picker.launch(arrayOf("image/*", "video/*", "*/*")) }, colors = ButtonDefaults.buttonColors(containerColor = Purple)) {
                                    Icon(Icons.Default.Add, null); Spacer(Modifier.width(8.dp)); Text("انتخاب عکس و ویدیو")
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(14.dp))
                    if (media.isEmpty()) {
                        Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text("هنوز فایلی اضافه نکرده‌ای\nاز بخش آپلود شروع کن", color = Color.Gray)
                        }
                    } else {
                        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.weight(1f), contentPadding = PaddingValues(bottom = 12.dp), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            items(media) { item ->
                                Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth().clickable {
                                    val file = File(item.path)
                                    if (file.exists()) {
                                        val uri = androidx.core.content.FileProvider.getUriForFile(context, context.packageName + ".provider", file)
                                        val intent = Intent(Intent.ACTION_VIEW).setDataAndType(uri, item.mime).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                        runCatching { activity.startActivity(intent) }
                                    }
                                }) {
                                    Column(Modifier.padding(12.dp)) {
                                        Text(if (item.mime.startsWith("video/")) "▶ VIDEO" else if (item.mime.startsWith("image/")) "▧ IMAGE" else "▤ FILE", color = Purple, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                        Spacer(Modifier.height(8.dp))
                                        Text(item.name, color = White, maxLines = 2, fontSize = 12.sp)
                                        Text(item.mime, color = Color.Gray, fontSize = 10.sp, maxLines = 1)
                                    }
                                }
                            }
                        }
                    }
                    NavigationBar(containerColor = Panel) {
                        NavigationBarItem(selected = tab == 0, onClick = { tab = 0 }, icon = { Icon(Icons.Default.Add, null) }, label = { Text("آپلود") })
                        NavigationBarItem(selected = tab == 1, onClick = { tab = 1 }, icon = { Icon(Icons.Default.Explore, null) }, label = { Text("اکسپلور") })
                    }
                }
            }
        }
    }
}

private fun queryName(context: android.content.Context, uri: Uri): String {
    var name = "media_${System.currentTimeMillis()}"
    context.contentResolver.query(uri, null, null, null, null)?.use { c ->
        val i = c.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
        if (i >= 0 && c.moveToFirst()) name = c.getString(i) ?: name
    }
    return name
}

private fun loadMedia(prefs: android.content.SharedPreferences): List<MediaItem> {
    return runCatching {
        val arr = JSONArray(prefs.getString("items", "[]"))
        (0 until arr.length()).map { i ->
            val o = arr.getJSONObject(i)
            MediaItem(o.getString("name"), o.getString("path"), o.getString("mime"))
        }.filter { File(it.path).exists() }
    }.getOrDefault(emptyList())
}

private fun saveMedia(prefs: android.content.SharedPreferences, items: List<MediaItem>) {
    val arr = JSONArray()
    items.forEach { arr.put(JSONObject().put("name", it.name).put("path", it.path).put("mime", it.mime)) }
    prefs.edit().putString("items", arr.toString()).apply()
}
