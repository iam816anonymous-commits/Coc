package com.cocstrategist

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint as NativePaint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PlannerScreen()
                }
            }
        }
    }
}

@Composable
private fun PlannerScreen() {
    val context = LocalContext.current
    var screenshot by remember { mutableStateOf<Bitmap?>(null) }
    var selectedRole by remember { mutableStateOf(MarkerRole.MAIN_ARMY) }
    var townHall by remember { mutableStateOf("") }
    var armyNotes by remember { mutableStateOf("") }
    val markers = remember { mutableStateListOf<PlanMarker>() }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            runCatching {
                context.contentResolver.openInputStream(uri)?.use { input -> BitmapFactory.decodeStream(input) }
            }.getOrNull()?.let {
                screenshot = it
                markers.clear()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("CoC Strategist", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Screenshot planner prototype · manual annotations only",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
            Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Scope notice", style = MaterialTheme.typography.titleSmall)
                Text(
                    "This build imports a screenshot and lets you mark a separate planning image. " +
                        "It does not inspect the live game, detect buildings automatically, generate AI tactics, " +
                        "draw over Clash of Clans, or control the game.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Button(onClick = { imagePicker.launch("image/*") }, modifier = Modifier.fillMaxWidth()) {
            Text(if (screenshot == null) "Import base screenshot" else "Choose another screenshot")
        }

        if (screenshot == null) {
            Box(
                modifier = Modifier.fillMaxWidth().height(230.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Your imported screenshot will appear here")
            }
        } else {
            Text("Planning canvas", style = MaterialTheme.typography.titleMedium)
            Text("Select a marker type, then tap the imported image to place a numbered note.")
            PlanningCanvas(
                bitmap = screenshot!!,
                markers = markers,
                selectedRole = selectedRole,
                onAddMarker = { x, y ->
                    markers.add(PlanMarkerFactory.create(
                        id = (markers.maxOfOrNull { marker -> marker.id } ?: 0L) + 1L,
                        role = selectedRole,
                        x = x,
                        y = y
                    ))
                }
            )
            Text(
                "Markers are normalized to the imported image, so they remain aligned when the preview resizes.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text("Marker type", style = MaterialTheme.typography.titleMedium)
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            MarkerRole.entries.chunked(3).forEach { rowRoles ->
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    rowRoles.forEach { role ->
                        FilterChip(
                            selected = selectedRole == role,
                            onClick = { selectedRole = role },
                            label = { Text(role.displayName, fontSize = 12.sp) }
                        )
                    }
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { if (markers.isNotEmpty()) markers.removeAt(markers.lastIndex) }, enabled = markers.isNotEmpty()) {
                Text("Undo marker")
            }
            OutlinedButton(onClick = { markers.clear() }, enabled = markers.isNotEmpty()) {
                Text("Clear markers")
            }
        }

        Text("Army context", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = townHall,
            onValueChange = { townHall = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Town Hall level") },
            singleLine = true
        )
        OutlinedTextField(
            value = armyNotes,
            onValueChange = { armyNotes = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Available troops, levels, spells and heroes") },
            minLines = 3
        )

        Card {
            Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Current prototype status", style = MaterialTheme.typography.titleSmall)
                Text("Imported image: ${if (screenshot == null) "not selected" else "ready"}")
                Text("Planning markers: ${markers.size}")
                Text("Town Hall input: ${townHall.ifBlank { "not entered" }}")
                Text("Army notes: ${if (armyNotes.isBlank()) "not entered" else "entered"}")
                Spacer(Modifier.height(2.dp))
                Text(
                    "Automatic building recognition and attack recommendations are not implemented yet. " +
                        "They require a labeled screenshot dataset, a measured detector benchmark, and validated game-specific strategy rules.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun PlanningCanvas(
    bitmap: Bitmap,
    markers: List<PlanMarker>,
    selectedRole: MarkerRole,
    onAddMarker: (Float, Float) -> Unit
) {
    Canvas(
        modifier = Modifier.fillMaxWidth().height(360.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF101820))
            .pointerInput(bitmap, selectedRole) {
                detectTapGestures { tap ->
                    val scale = min(size.width.toFloat() / bitmap.width, size.height.toFloat() / bitmap.height)
                    val imageWidth = bitmap.width * scale
                    val imageHeight = bitmap.height * scale
                    val left = (size.width - imageWidth) / 2f
                    val top = (size.height - imageHeight) / 2f
                    val inside = tap.x >= left && tap.x <= left + imageWidth &&
                        tap.y >= top && tap.y <= top + imageHeight
                    if (inside && imageWidth > 0f && imageHeight > 0f) {
                        onAddMarker(
                            ((tap.x - left) / imageWidth).coerceIn(0f, 1f),
                            ((tap.y - top) / imageHeight).coerceIn(0f, 1f)
                        )
                    }
                }
            }
    ) {
        val scale = min(size.width / bitmap.width, size.height / bitmap.height)
        val imageWidth = (bitmap.width * scale).toInt()
        val imageHeight = (bitmap.height * scale).toInt()
        val left = (size.width.toInt() - imageWidth) / 2
        val top = (size.height.toInt() - imageHeight) / 2
        drawImage(
            image = bitmap.asImageBitmap(),
            dstOffset = IntOffset(left, top),
            dstSize = IntSize(imageWidth, imageHeight)
        )

        markers.forEach { marker ->
            val center = Offset(left + marker.x * imageWidth, top + marker.y * imageHeight)
            drawCircle(color = Color(0xFF101820), radius = 17.dp.toPx(), center = center)
            drawCircle(color = Color(0xFFFFC857), radius = 14.dp.toPx(), center = center)
            drawIntoCanvas { canvas ->
                val paint = NativePaint().apply {
                    color = android.graphics.Color.BLACK
                    textAlign = NativePaint.Align.CENTER
                    textSize = 13.sp.toPx()
                    isFakeBoldText = true
                    isAntiAlias = true
                }
                canvas.nativeCanvas.drawText(marker.id.toString(), center.x, center.y + paint.textSize * 0.35f, paint)
            }
        }
    }
}
