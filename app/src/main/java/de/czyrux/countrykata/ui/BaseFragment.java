package de.czyrux.countrykata.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getViewLayoutResourceId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public final void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    protected abstract int getViewLayoutResourceId();
}
