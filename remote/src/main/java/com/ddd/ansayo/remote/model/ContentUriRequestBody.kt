package com.ddd.ansayo.remote.model

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source

class ContentUriRequestBody(
    private val contentResolver: ContentResolver,
    private val contentUri: Uri
) : RequestBody() {

    override fun contentType(): MediaType? {
        val contentType = contentResolver.getType(contentUri)
        return contentType?.toMediaTypeOrNull()
    }

    override fun writeTo(sink: BufferedSink) {
        contentResolver.openInputStream(contentUri)?.use {
            it.source().use { source ->
                sink.writeAll(source)
            }
        } ?: throw Exception(contentUri.toString())
    }

    override fun contentLength(): Long {
        return contentResolver.query(contentUri, null, null, null, null)?.use { cursor ->
            val sizeColumnIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            cursor.moveToFirst()
            cursor.getLong(sizeColumnIndex)
        } ?: super.contentLength()
    }
}
