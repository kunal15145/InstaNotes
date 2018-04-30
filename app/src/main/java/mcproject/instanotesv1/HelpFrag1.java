package mcproject.instanotesv1;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class HelpFrag1 extends Fragment{

    private ImageView imageView;
    boolean isImageFitToScreen;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.help_frag1,container,false);
        imageView = view.findViewById(R.id.frag1img);
        Log.d("aabc","abc");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BitmapDrawable bitmapDrawable = ((BitmapDrawable) R.id.imageview);
//                Bitmap bitmap = bitmapDrawable .getBitmap();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] byteArray = stream.toByteArray();
                Log.d("aabc","ab");
                Intent intent = new Intent(v.getContext(), FullImageViewer.class);
                intent.putExtra("resourseInt", R.drawable.uploadnotes);
                startActivity(intent);
            }
        });


        return view;

    }

}
