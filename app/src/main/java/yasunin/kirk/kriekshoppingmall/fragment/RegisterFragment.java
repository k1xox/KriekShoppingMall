package yasunin.kirk.kriekshoppingmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


import yasunin.kirk.kriekshoppingmall.MainActivity;
import yasunin.kirk.kriekshoppingmall.R;
import yasunin.kirk.kriekshoppingmall.utility.AddNewUserToServer;
import yasunin.kirk.kriekshoppingmall.utility.MyAlert;
import yasunin.kirk.kriekshoppingmall.utility.MyConstant;

/**
 * Created by User on 6/3/2561.
 */

public class RegisterFragment extends Fragment {

    //Explicit
    private String nameString, userString , passwordString, modeString;
    private boolean aBoolean = true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //creat toolbar
        creatToolbar();

        //Radio Controller
        radioController();


    }//Main Method

    private void radioController() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragMode);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                aBoolean = false;
                switch (i) {
                    case R.id.radOwnerShop:
                        modeString = "OwnerShop";
                        break;
                    case R.id.radCustomer:
                        modeString = "Customer";
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemUpload) {
            uploadToServer();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void uploadToServer() {

        //get Value From EditText
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText userEditText = getView().findViewById(R.id.edtUser);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //check Space
        if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()){
            //Have Space
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.myDialog(getString(R.string.title_have_space), getString(R.string.message_have_space1));
        } else if (aBoolean) {
           // Non Choose Mode
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.myDialog("Non choose Mode" ,
                    "Please Choose Mode");
        } else {
            //choose Mode OK

        try {

            MyConstant myConstant = new MyConstant();
            AddNewUserToServer addNewUserToServer = new AddNewUserToServer(getActivity());
            addNewUserToServer.execute(nameString,userString,
                    passwordString,modeString,myConstant.getUrlAddUserString());
            String result = addNewUserToServer.get();
            if (Boolean.parseBoolean(result)) {
                getActivity().getSupportFragmentManager().popBackStack();
            }else {
                Toast.makeText(getActivity(),"Press Try Again Cannot Add User",Toast.LENGTH_LONG).show();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        } //if


    }//uploadToServer

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_register, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void creatToolbar() {

        setHasOptionsMenu(true);

        Toolbar toolbar = getView().findViewById(R.id.ToolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.message_have_space1));

        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fraingment_main, container, false);
        return view;
    }
}
