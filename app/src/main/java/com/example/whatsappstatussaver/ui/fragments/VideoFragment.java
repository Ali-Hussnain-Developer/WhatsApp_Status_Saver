package com.example.whatsappstatussaver.ui.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.whatsappstatussaver.R;
import com.example.whatsappstatussaver.adapter.WhatsAppStatusAdapter;
import com.example.whatsappstatussaver.constants.Constants;
import com.example.whatsappstatussaver.databinding.FragmentVideoBinding;
import com.example.whatsappstatussaver.model.WhatsappStatusModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class VideoFragment extends Fragment {
    FragmentVideoBinding binding;
    ArrayList<WhatsappStatusModel> list;
    WhatsAppStatusAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        list = new ArrayList<>();
        getData();
        return binding.getRoot();
    }

    private void getData() {
        WhatsappStatusModel model;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constants.INSTANCE.getSTATUS_PATH();
        File targetDirectory = new File(targetPath);
        File[] allFiles = targetDirectory.listFiles();
        Arrays.sort(allFiles, ((o1, o2) -> {
            if (o1.lastModified() > o2.lastModified()) return -1;
            else if (o1.lastModified() < o2.lastModified()) return +1;
            else return 0;
        }
        ));
        for (int i = 0; i < allFiles.length; i++) {
            File file = allFiles[i];
            if (Uri.fromFile(file).toString().endsWith(Constants.INSTANCE.getVIDEO_EXTENSION())
            ) {
                model = new WhatsappStatusModel(Constants.INSTANCE.getWHAT_EXTENSION() + i,
                        Uri.fromFile(file),
                        allFiles[i].getAbsolutePath(),
                        file.getName()
                );
                list.add(model);
            }
        }
        adapter = new WhatsAppStatusAdapter(list, requireContext());
        binding.rvVideos.setAdapter(adapter);
    }
}