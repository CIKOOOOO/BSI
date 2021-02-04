package com.bca.bsi.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DecodeBitmap {


    public static Bitmap compressBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
        return bitmap;
    }

    public static Bitmap decodeSampleBitmapFromUri(Uri uri, int w, int h, Context context) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        byte[] bytes = null;
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            try {
                assert inputStream != null;
                bytes = getBytes(inputStream);
                BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
                options.inSampleSize = calculateInSampleSize(options, w, h);
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inJustDecodeBounds = false;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert bytes != null;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    private static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int w, int h) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > h || width > w) {
            final int hHeight = height / 2;
            final int hWidth = width / 2;

            while ((hHeight / inSampleSize >= h) && (hWidth / inSampleSize) >= w) {
                inSampleSize += 2;
            }
        }
        return inSampleSize;
    }
}
