package com.example.miniprojet.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.List.listimplementationsqlite.ShowList;
import com.example.miniprojet.R;
import com.example.miniprojet.Register;
import com.example.miniprojet.databinding.FragmentSlideshowBinding;
import com.example.miniprojet.drawer;

public class SlideshowFragment extends Fragment {

    Button listShow;

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow,
                container, false);
        Button button = (Button) view.findViewById(R.id.listShow);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),ShowList.class);
                getActivity().startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}