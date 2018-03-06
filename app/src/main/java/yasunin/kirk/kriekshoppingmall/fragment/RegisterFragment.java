package yasunin.kirk.kriekshoppingmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import yasunin.kirk.kriekshoppingmall.MainActivity;
import yasunin.kirk.kriekshoppingmall.R;

/**
 * Created by User on 6/3/2561.
 */

public class RegisterFragment extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //creat toolbar
        creatToolbar();

    }

    private void creatToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.ToolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.message_have_space1));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fraingment_main,container,false);
        return  view;
    }
}
