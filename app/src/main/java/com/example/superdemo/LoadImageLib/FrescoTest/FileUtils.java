package com.example.superdemo.LoadImageLib.FrescoTest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/9/1.
 */
public class FileUtils {

    public static void mkdirs(File directory) throws CreateDirectoryException {
        if (directory.exists()) {
            // file exists and *is* a directory
            if (directory.isDirectory()) {
                return;
            }

            // file exists, but is not a directory - delete it
            if (!directory.delete()) {
                throw new CreateDirectoryException(
                        directory.getAbsolutePath(),
                        new FileDeleteException(directory.getAbsolutePath()));
            }
        }

        // doesn't exist. Create one
        if (!directory.mkdirs() && !directory.isDirectory()) {
            throw new CreateDirectoryException(directory.getAbsolutePath());
        }


    }


    /**
     * Represents an exception during directory creation
     */
    public static class CreateDirectoryException extends IOException {
        public CreateDirectoryException(String message) {
            super(message);
        }

        public CreateDirectoryException(String message, Throwable innerException) {
            super(message);
            initCause(innerException);
        }
    }

    /**
     * Represents an exception when the target file/directory cannot be deleted
     */
    public static class FileDeleteException extends IOException {
        public FileDeleteException(String message) {
            super(message);
        }
    }


    public static void getTouch(Context context) {
        View view = new View(context);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Looper.prepare();
            }
        },3000);
    }
}
