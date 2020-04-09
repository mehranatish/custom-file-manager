package com.example.toterandroid.ui.DIR;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.toterandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.example.toterandroid.class_.Globals.directory_string;
import static com.example.toterandroid.class_.Globals.file_path;

public class directory extends Fragment {



    private TextView txt_address;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.directory, container, false);


        txt_address=(TextView)root.findViewById(R.id.txt_address);


        scr_itm_lst=root. findViewById(R.id.scr_itm_lst);
        scr_itm_formats=root. findViewById(R.id.scr_itm_formats);


        path=directory_string;
        listdir( );
        BottomNavigationView bottomNavigationView = (BottomNavigationView) root.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                format="";
                switch (item.getItemId()) {
                    case R.id.action_memory:
                        path="/storage/sdcard0/";
                        listdir( );
                        break;
                    case R.id.action_ram:
                        path="/storage/extSdCard/";
                        listdir( );
                        break;
                    case R.id.action_allfoldewr:
                        path="/";
                        listdir( );
                        break;
                }
                return true;
            }
        });


        return root;
    }
    private String path;
     LinearLayout scr_itm_lst;
    LinearLayout scr_itm_formats;
    private void   listdir(){
        try{
            // setTitle(path);
            // Read all files sorted into the values-array
            List<String> values = new ArrayList<>();
            File dir = new File(path);
            path= dir.getAbsolutePath();
            dir = new File(path);

            txt_address.setText(path);
            if (!dir.canRead()) {

                //  setTitle(getTitle() + " (inaccessible)");
            }
            String[] list = dir.list();

            ArrayList<String> frm_list=new ArrayList<String>();
            if (list != null) {
                values.add("");
                for (String file : list) {
                    if (!file.startsWith(".")) {
                        String[] s=file.split("\\.");

                        if(s.length>1){

                            values.add(file);
                            if(!format.equals("")){
                                frm_list.clear();
                            frm_list.add(format) ;

                            }else {
                             boolean b=true;
                            for (int i=0;i<frm_list.size();i++){
                                if(frm_list.get(i).equals(s[s.length-1])){
                                    b=false;
                                }
                            }

                            if (b){
                                frm_list.add(s[s.length-1]) ;

                            }
                         }

                        }else if(s.length==1){
                            values.add("..."+file);
                        }

                    }
                }

            }

            Collections.sort(values);//مرتب سازی
            Collections.sort(frm_list);//مرتب سازی

            values.add("");
            setlist(values,scr_itm_lst,R.layout.list_dir);

            setformats(frm_list,scr_itm_formats,R.layout.lst_formats);
        }catch (Exception e){}

    }
    @SuppressLint({"InflateParams", "NewApi", "LocalSuppress","SetTextI18n"})
    private void setlist(List<String> list, final LinearLayout scr_itm_lst,int list_dir) {
        scr_itm_lst.removeAllViews();
        try {
            LayoutInflater inflater = (LayoutInflater)  Objects.requireNonNull(this.getContext()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for(String s:list){
                if(s.endsWith("."+format)||s.startsWith("...")||format.equals("")||s.equals("")) {
                    View v = Objects.requireNonNull(inflater).inflate(list_dir, null);
                    TextView itm = v.findViewById(R.id.itm);
                    ImageView imageitm = v.findViewById(R.id.imageitm);
                    itm.setText(s);
                    itm.setTag(s);
                    imageitm.setTag(s);
                    if (s.startsWith("...")) {
                        imageitm.setImageResource(R.drawable.ic_folder_black_24dp);
                    } else if (s.endsWith(".mp3")) {
                        imageitm.setImageResource(R.drawable.ic_queue_music_black_24dp);
                    } else if (s.endsWith(".mp4")) {
                        imageitm.setImageResource(R.drawable.ic_local_movies_black_24dp);
                    } else if (s.endsWith(".3gp")) {
                        imageitm.setImageResource(R.drawable.ic_movie_black_24dp);
                    } else if (s.endsWith(".jpg")) {
                        imageitm.setImageResource(R.drawable.ic_image_black_24dp);
                    } else if (s.endsWith(".png")) {
                        imageitm.setImageResource(R.drawable.ic_broken_image_black_24dp);
                    } else if (s.endsWith(".pdf")) {
                        imageitm.setImageResource(R.drawable.ic_picture_as_pdf_black_24dp);
                    } else if (s.endsWith(".zip")) {
                        imageitm.setImageResource(R.drawable.ic_clear_all_black_24dp);
                    } else if (s.endsWith(".rar")) {
                        imageitm.setImageResource(R.drawable.ic_clear_all_black_);
                    } else if (s.endsWith(".apk")) {
                        imageitm.setImageResource(R.drawable.ic_android_black_24dp);
                    } else {
                        imageitm.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
                    }
                    itm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            itemclick(view);
                        }
                    });
                    imageitm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            itemclick(view);
                        }
                    });
                    scr_itm_lst.addView(v);
                }}

        }catch (Exception e){}

    }
    @SuppressLint({"InflateParams", "NewApi", "LocalSuppress","SetTextI18n"})
    private void setformats(ArrayList<String> list, final LinearLayout scr_itm_lst,int list_dir) {
        scr_itm_lst.removeAllViews();
        try {
            LayoutInflater inflater = (LayoutInflater)  Objects.requireNonNull(this.getContext()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for(final String s:list){
                View v = Objects.requireNonNull(inflater).inflate(list_dir, null);
                TextView itm=v.findViewById(R.id.itm);
                ImageView imageitm=v.findViewById(R.id.imageitm);
                itm.setText(s);
                itm.setTag(s);
                imageitm.setTag(s);
                if(s.equals("mp3")){
                    imageitm.setImageResource(R.drawable.ic_queue_music_black_24dp);
                }else if(s.equals("mp4")){
                    imageitm.setImageResource(R.drawable.ic_local_movies_black_24dp);
                }else if(s.equals("3gp")){
                    imageitm.setImageResource(R.drawable.ic_movie_black_24dp);
                }else if(s.equals("jpg")){
                    imageitm.setImageResource(R.drawable.ic_image_black_24dp);
                }else if(s.equals("png")){
                    imageitm.setImageResource(R.drawable.ic_broken_image_black_24dp );
                }else if(s.equals("pdf")){
                    imageitm.setImageResource(R.drawable.ic_picture_as_pdf_black_24dp );
                }else if(s.equals("zip")){
                    imageitm.setImageResource(R.drawable.ic_clear_all_black_24dp);
                }else if(s.equals("rar")){
                    imageitm.setImageResource(R.drawable.ic_clear_all_black_ );
                }else if(s.equals("apk")){
                    imageitm.setImageResource(R.drawable.ic_android_black_24dp);
                }else {
                    imageitm.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
                }
                itm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         format=s;
                        listdir( );
                    }
                });
                imageitm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         format=s;
                        listdir( );
                    }
                });
                scr_itm_lst.addView(v);
            }

        }catch (Exception e){}

    }
    String format="";
    @SuppressLint("SetTextI18n")
    private void itemclick(View view ) {

        String filename = view.getTag().toString();

        try {

            if(filename.startsWith("..."))
                filename=  filename.substring(3);

            if (path.endsWith(File.separator)) {
                filename = path + filename;
            } else {
                filename = path + File.separator + filename;
            }
            if (new File(filename).isDirectory()) {
                this.path=filename;
                listdir();
                txt_address.setText("..."+filename);

            } else if(filename.endsWith(format)){
                Toast.makeText(this.getContext(), filename, Toast.LENGTH_LONG).show();
                file_path=filename;
                txt_address.setText("..."+filename);

            }else {
                Toast.makeText(this.getContext(), "لطفا یک فایل دیگر را انتخاب کنید", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){}

    }





}
