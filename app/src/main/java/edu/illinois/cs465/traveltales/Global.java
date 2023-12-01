package edu.illinois.cs465.traveltales;

import android.app.Application;
import android.net.Uri;

import java.util.ArrayList;

public class Global extends Application {
    public ArrayList<Uri> images;
    public int coverPhotoId = -1;
    public String title;
    public String location;
    public String description;
    public int journal_count = 3;
    // set this variable where I am choosing visibility, read this value at profile fragment
    public int visibility = 1;  // 0 -> public, // 1 -> private
}
