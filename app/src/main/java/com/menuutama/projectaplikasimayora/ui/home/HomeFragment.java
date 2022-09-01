package com.menuutama.projectaplikasimayora.ui.home;

import static android.view.View.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import com.menuutama.projectaplikasimayora.databinding.FragmentHomeBinding;
import com.menuutama.projectaplikasimayora.R;

public class HomeFragment extends Fragment {
    ImageButton imgbtn_scancode;

    private @NonNull FragmentHomeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

//        imgbtn_scancode.setOnClickListener(v->
//        {
//            scanCode();
//        });

        View root = binding.getRoot();
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //--------------------Click for Scan QRCode----------------------------
//
//    private void scanCode() {
//        ScanOptions options = new ScanOptions();
//        options.setPrompt("Volume up to flash on");
//        options.setBeepEnabled(true);
//        options.setOrientationLocked(true);
//        options.setCaptureActivity(CaptureAct.class);
//        barLaucher.launch(options);
//    }
//
//
//    private void onActivityResult(ScanIntentResult result) {
//        if (result.getContents() != null) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle("Result");
//            builder.setMessage(result.getContents());
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            }).show();
//        }
//    }
//
//    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(),result ->
//    {
//        if(result.getContents() != null)
//        {
//            AlertDialog.Builder builder = new AlertDialog.Builder(HomeFragment.this);
//            builder.setTitle("Result");
//            builder.setMessage(result.getContents());
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            }).show();
//        }
//    });



}

