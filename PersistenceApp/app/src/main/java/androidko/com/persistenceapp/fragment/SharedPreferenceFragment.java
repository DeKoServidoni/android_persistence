package androidko.com.persistenceapp.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidko.com.persistenceapp.R;

/**
 * Blog post:
 * https://androidko.wordpress.com/2016/06/01/persistencia-de-dados-p1-sharedpreferences
 */
public class SharedPreferenceFragment extends Fragment {

    private TextView tvLabel;
    private EditText edInput;

    private static final String TEST_KEY = "testKey";

    private SharedPreferences sharedPreferences;

    /**
     * Best practice to create a new fragment
     *
     * @return new instance of this fragment
     */
    public static SharedPreferenceFragment newFragment() {
        return new SharedPreferenceFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the default shared preferences of the app
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shared_preference, container, false);

        tvLabel = (TextView) view.findViewById(R.id.fragment_sp_label);
        edInput = (EditText) view.findViewById(R.id.fragment_sp_input);

        Button btnLoad = (Button) view.findViewById(R.id.fragment_sp_button_load);
        Button btnSave = (Button) view.findViewById(R.id.fragment_sp_button_save);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLabel.setText(sharedPreferences.getString(TEST_KEY,"Empty <key, value>"));
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(TEST_KEY, edInput.getText().toString());

                // When save the preferences in an asynchronous way, we use the apply
                // but when save in a synchronous way, use the commit.
                editor.apply();
                //editor.commit();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tvLabel.setText(sharedPreferences.getString(TEST_KEY,"Empty <key, value>"));
    }
}
