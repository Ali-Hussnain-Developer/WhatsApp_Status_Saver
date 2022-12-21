package com.example.whatsappstatussaver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsappstatussaver.R;
import com.example.whatsappstatussaver.constants.Constants;
import com.example.whatsappstatussaver.databinding.SingleLayotRvBinding;
import com.example.whatsappstatussaver.model.WhatsappStatusModel;
import com.example.whatsappstatussaver.util.Util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WhatsAppStatusAdapter extends RecyclerView.Adapter<WhatsAppStatusAdapter.ViewHolder> {


    private final ArrayList<WhatsappStatusModel> list;
    private final Context context;
    private final String saveFilePathh = Util.RootDirectoryWhatsapp + "/";
    private LayoutInflater inflater;

    public WhatsAppStatusAdapter(ArrayList<WhatsappStatusModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public WhatsAppStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        return new ViewHolder(DataBindingUtil.inflate(inflater, R.layout.single_layot_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppStatusAdapter.ViewHolder holder, int position) {
        WhatsappStatusModel item = list.get(position);
        Glide.with(context).load(item.getPath()).into(holder.binding.imgStatus);
        holder.binding.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.createFileFolder();
                final String path = item.getPath();
                final File file = new File(path);
                final File destinationFilePath = new File(saveFilePathh);
                try {
                    FileUtils.copyFileToDirectory(file, destinationFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(context, Constants.INSTANCE.getSAVED_SUCCESS(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SingleLayotRvBinding binding;

        public ViewHolder(SingleLayotRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
