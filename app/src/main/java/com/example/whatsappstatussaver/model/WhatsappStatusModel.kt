package com.example.whatsappstatussaver.model

import android.net.Uri

data class WhatsappStatusModel(

    var name: String,
    var uri: Uri,
    var path: String,
    var fileName: String,
)