package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.constant.Constant;

public class ChildMainForumFragment extends BaseFragment {
    private static final String PARCEL_DATA = "parcel_data";

    public static ChildMainForumFragment newInstance(int param1) {
        ChildMainForumFragment fragment = new ChildMainForumFragment();
        Bundle args = new Bundle();
        args.putString(PARCEL_DATA, Constant.FORUM_MENU[param1]);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_main_forum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_child_main_forum);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            String type = bundle.getString(PARCEL_DATA);

        }
    }
}