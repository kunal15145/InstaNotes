package mcproject.instanotesv1;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notif_datepicker extends DialogFragment {

    private DatePicker datePicker;

    public Notif_datepicker() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.notif_datepicker,null);
        datePicker = view.findViewById(R.id.date)
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
