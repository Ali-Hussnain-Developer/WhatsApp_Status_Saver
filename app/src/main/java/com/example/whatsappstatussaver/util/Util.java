package com.example.whatsappstatussaver.util;

import android.os.Environment;

import java.io.File;

public class Util {
    public static File RootDirectoryWhatsapp = new File(Environment.getExternalStorageDirectory() + "/Download/MyStorySaver/Whatsapp");

    public static void createFileFolder() {
        if (!RootDirectoryWhatsapp.exists()) {
            RootDirectoryWhatsapp.mkdirs();
        }
    }
}
