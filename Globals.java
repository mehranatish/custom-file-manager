package com.example.toterandroid.class_;

import android.app.Application;

import androidx.fragment.app.FragmentManager;

import java.io.File;


public class Globals extends Application {

public static
String file_path="";

    public static
    FragmentManager fragment_Manager;

    static File root = android.os.Environment.getExternalStorageDirectory();
    public static
    String directory_string = root.getAbsolutePath() ;

    public static
    String Url="http://192.168.0.6:8080/";


    public static
     String mail="mehranatashfaraze@gmail",
            phon="989372647407",
            name="مهران",
            famil="آتشفراز";

}
