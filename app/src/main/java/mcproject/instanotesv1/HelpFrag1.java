package mcproject.instanotesv1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class HelpFrag1 extends Fragment{

    private ImageView imageView;
    boolean isImageFitToScreen;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.help_frag1,container,false);
        imageView = view.findViewById(R.id.imageview);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





        return inflater.inflate(R.layout.help_frag1, container, false);

    }

}
