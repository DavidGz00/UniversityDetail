package com.pmdm.university;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pmdm.university.entidad.UniversityDetail;
import com.pmdm.university.databinding.FragmentItemDetailBinding;
import com.pmdm.university.repositorio.implementacion.UniversityDetailsSQLHelper;


public class ItemDetailFragment extends Fragment {


    public static final String NAME = "name";
    public static final String URL = "url";

    private UniversityDetail detail;

    private UniversityDetailsSQLHelper db;

    private FragmentItemDetailBinding binding;



    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*db = new UniversityDetailsSQLHelper(getContext());*/

        updateContent();

        String name = getArguments().getString(NAME);
        if (name != null) {
            if(detail == null)
                detail = new UniversityDetail();

            detail.setName(name);
            detail.setUrl(getArguments().getString(URL));

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentItemDetailBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        updateContent();

        binding.fab.setOnClickListener(v -> {
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.NAME, detail.getName());
            Navigation.findNavController(rootView)
                    .navigate(R.id.formFragment, arguments);
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateContent() {

        if(detail != null){
            binding.universityName.setText(detail.getName());
            binding.universityUrl.setText("See more info at: " + detail.getUrl());
            binding.universityImage.setBackgroundResource(R.drawable.defaultuniversity);

            if(detail.getDescription() != null)
                binding.universityDescription.setText(detail.getDescription());

            if(detail.getImageUrl() !=null && detail.getImageUrl().length() > 0) {
                ImageView itemImage = binding.universityImage;
                Glide.with(this)
                        .load(detail.getImageUrl())
                        .into(itemImage);
            }

        }
    }
}