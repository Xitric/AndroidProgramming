package com.xitric.fragmentexercise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ColorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            int color = args.getInt("color");
            String colorString = args.getString("colorString");

            View view = inflater.inflate(R.layout.color_fragment, container, false);
            view.setBackgroundColor(color);
            ((TextView) view.findViewById(R.id.colorLabel)).setText(colorString);
            return view;
        }

        return null;
    }
}
