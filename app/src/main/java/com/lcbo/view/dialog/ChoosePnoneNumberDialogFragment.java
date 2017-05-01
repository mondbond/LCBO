package com.lcbo.view.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import com.lcbo.R;
import com.lcbo.view.activity.StoreDetailActivity;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChoosePnoneNumberDialogFragment extends DialogFragment {

    private static final String PHONE_NUMBERS_DIALOG = "phoneNumbers";

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(getTargetFragment() != null){

                ((StoreDetailActivity)getActivity()).makeCall(((TextView) view).getText().toString());
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_choose_pnone_number_dialog, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bundle bundle = getArguments();
        ArrayList<String> phoneNumbers = bundle.getParcelable(PHONE_NUMBERS_DIALOG);

        TextView firstNumber = (TextView) v.findViewById(R.id.phone_dialog_first_number);
        firstNumber.setOnClickListener(mOnClickListener);
        TextView secondNumber = (TextView) v.findViewById(R.id.phone_dialog_second_number);
        secondNumber.setOnClickListener(mOnClickListener);

        firstNumber.setText(phoneNumbers.get(0));
        secondNumber.setText(phoneNumbers.get(1));

        return v;
    }

}
