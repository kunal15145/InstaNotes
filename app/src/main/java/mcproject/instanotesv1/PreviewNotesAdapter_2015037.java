package mcproject.instanotesv1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class PreviewNotesAdapter_2015037 extends RecyclerView.Adapter<PreviewNotesAdapter_2015037.itemHolder> {

    private List<Notes_2015037> previewNotesList;
//    private int like_click = 0;
    private Context context;
    private int fav_click = 0;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;
    private String OWN_TAG = "OWN";
    private String DATE_TAG = "DATE";
    private String Course_TAG="Course";
    private String own;
    private String coursename;
    private String date;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    public class itemHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle, txtUser, txtLike, textdislike;
        public ImageView imgLike, imgUser, imgDownload, imgFav,imgDislike;
        public LinearLayout imageLinear;
        public View view;

        public itemHolder(View itemView) {
            super(itemView);
            imageLinear = itemView.findViewById(R.id.imageLinear);
            txtTitle = itemView.findViewById(R.id.textTitle);
            txtUser = itemView.findViewById(R.id.textUser);
            txtLike = itemView.findViewById(R.id.textLike);
            imgLike = itemView.findViewById(R.id.imageLike);
            imgDislike = itemView.findViewById(R.id.imagedislike);
            textdislike = itemView.findViewById(R.id.textdislike);
            imgFav = itemView.findViewById(R.id.imageFav);
            imgDownload = itemView.findViewById(R.id.imageDownload);
            imgUser = itemView.findViewById(R.id.imageUser);
        }
    }

    public PreviewNotesAdapter_2015037(List<Notes_2015037> previewNotesList, PreviewNotes_2015037 previewNotes, String coursenames, String owns, String dates){
        this.previewNotesList = previewNotesList;
        this.context = previewNotes;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        own=owns;
        coursename=coursenames;
        date=dates;
    }

    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.previewnotes,parent,false);
        return new itemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PreviewNotesAdapter_2015037.itemHolder holder, int position) {
        final Notes_2015037 previewNotes = previewNotesList.get(position);
        holder.txtTitle.setText(previewNotes.getTitle());
        holder.txtUser.setText(previewNotes.getUser());
        holder.txtLike.setText(String.valueOf(previewNotes.getLike()));
        if(previewNotes.isFav()){
            holder.imgFav.setImageResource(R.drawable.green_star);
        }
        holder.imgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ArrayList<String> imagestodownload = previewNotes.getList();
                File wallpaperDirectory = new File(Environment.getExternalStorageDirectory()+"/sdcard/Instanotes/");
                if(!wallpaperDirectory.isDirectory()){
                    System.out.println("down1");
                    wallpaperDirectory.mkdirs();
                }
                System.out.println("down");
                String directoryname = coursename+date+"/";
                File subdirectory = new File(Environment.getExternalStorageDirectory()+"/sdcard/Instanotes/"+directoryname+"/");
                if(!subdirectory.isDirectory()){
                    subdirectory.mkdirs();
                }
                System.out.println("down2");
                String pathname = Environment.getExternalStorageDirectory()+"/sdcard/Instanotes/"+directoryname+"/";
                for(String s:imagestodownload) {

                    pathname = pathname+String.valueOf(imagestodownload.indexOf(s))+".jpg";
                    new task().execute(s,pathname);

                }
            }
        });

        if(previewNotes.getisLiked() == TRUE){
            previewNotes.setLike(previewNotes.getLike());
            holder.txtLike.setText(String.valueOf(previewNotes.getLike()));
            holder.imgLike.setImageResource(R.drawable.green_like);
        }
        else{
            previewNotes.setLike(previewNotes.getLike());
            holder.txtLike.setText(String.valueOf(previewNotes.getLike()));
            holder.imgLike.setImageResource(R.drawable.like);
        }
        if(previewNotes.getisDisliked() == TRUE){
            previewNotes.setDislike(previewNotes.getDislike());
            holder.textdislike.setText(String.valueOf(previewNotes.getDislike()));
            holder.imgDislike.setImageResource(R.drawable.green_dislike);
        }
        else{
            previewNotes.setDislike(previewNotes.getDislike());
            holder.textdislike.setText(String.valueOf(previewNotes.getDislike()));
            holder.imgDislike.setImageResource(R.drawable.dislike);
        }
        //change this to boolean and then if liked then show green else show normal and increment as always.
        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sjdhfk", "Like pressed");
                if(previewNotes.getisLiked() == TRUE){
                    firebaseFirestore.collection("uploads")
                            .whereEqualTo(OWN_TAG, own)
                            .whereEqualTo(Course_TAG, coursename)
                            .whereEqualTo(DATE_TAG, date)
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            for(DocumentSnapshot doc: documentSnapshots) {
                                ArrayList<Map<String, Object>> uplo= (ArrayList<Map<String, Object>>) doc.get("User_uploads");
                                for(int i=0;i<uplo.size();i++) {
                                    if(uplo.get(i).containsValue(previewNotes.getUserid())){
                                        previewNotes.setisLiked(FALSE);
                                        previewNotes.setLike(previewNotes.getLike() - 1);
                                        uplo.get(i).put("Likes", String.valueOf(Integer.parseInt((String)uplo.get(i).get("Likes"))-1));
                                        holder.txtLike.setText(String.valueOf(previewNotes.getLike()));
                                        holder.imgLike.setImageResource(R.drawable.like);
                                        firebaseFirestore.collection("uploads").document(doc.getId())
                                                .update("User_uploads", uplo);
                                        break;
                                    }
                                }
                            }
                        }
                    });
                }else {
                    firebaseFirestore.collection("uploads")
                            .whereEqualTo(OWN_TAG, own)
                            .whereEqualTo(Course_TAG, coursename)
                            .whereEqualTo(DATE_TAG, date)
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            for(DocumentSnapshot doc: documentSnapshots) {
                                ArrayList<Map<String, Object>> uplo= (ArrayList<Map<String, Object>>) doc.get("User_uploads");
                                for(int i=0;i<uplo.size();i++) {
                                    if(uplo.get(i).containsValue(previewNotes.getUserid())){
                                        previewNotes.setisLiked(TRUE);
                                        previewNotes.setLike(previewNotes.getLike()+1);
                                        holder.txtLike.setText(String.valueOf(previewNotes.getLike()));
                                        holder.imgLike.setImageResource(R.drawable.green_like);
                                        uplo.get(i).put("Likes", String.valueOf(Integer.parseInt((String)uplo.get(i).get("Likes"))+1));
                                        firebaseFirestore.collection("uploads").document(doc.getId())
                                                .update("User_uploads", uplo);
                                        break;
                                    }
                                }
                            }
                        }
                    });

                }
            }
        });
        holder.imgDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(previewNotes.getisDisliked() == TRUE){
                    firebaseFirestore.collection("uploads")
                            .whereEqualTo(OWN_TAG, own)
                            .whereEqualTo(Course_TAG, coursename)
                            .whereEqualTo(DATE_TAG, date)
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            for(DocumentSnapshot doc: documentSnapshots) {
                                ArrayList<Map<String, Object>> uplo= (ArrayList<Map<String, Object>>) doc.get("User_uploads");
                                for(int i=0;i<uplo.size();i++) {
                                    if(uplo.get(i).containsValue(previewNotes.getUserid())){
                                        previewNotes.setisDisliked(FALSE);
                                        previewNotes.setDislike(previewNotes.getDislike()-1);
                                        holder.textdislike.setText(String.valueOf(previewNotes.getDislike()));
                                        holder.imgDislike.setImageResource(R.drawable.dislike);
                                        uplo.get(i).put("Dislikes", String.valueOf(Integer.parseInt((String)uplo.get(i).get("Dislikes"))-1));
                                        firebaseFirestore.collection("uploads").document(doc.getId())
                                                .update("User_uploads", uplo);
                                        break;
                                    }
                                }
                            }
                        }
                    });

                }else {
                    firebaseFirestore.collection("uploads")
                            .whereEqualTo(OWN_TAG, own)
                            .whereEqualTo(Course_TAG, coursename)
                            .whereEqualTo(DATE_TAG, date)
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            for(DocumentSnapshot doc: documentSnapshots) {
                                ArrayList<Map<String, Object>> uplo= (ArrayList<Map<String, Object>>) doc.get("User_uploads");
                                for(int i=0;i<uplo.size();i++) {
                                    if(uplo.get(i).containsValue(previewNotes.getUserid())){
                                        previewNotes.setisDisliked(TRUE);
                                        previewNotes.setDislike(previewNotes.getDislike()+1);
                                        holder.textdislike.setText(String.valueOf(previewNotes.getDislike()));
                                        holder.imgDislike.setImageResource(R.drawable.green_dislike);
                                        uplo.get(i).put("Dislikes", String.valueOf(Integer.parseInt((String)uplo.get(i).get("Dislikes"))+1));
                                        firebaseFirestore.collection("uploads").document(doc.getId())
                                                .update("User_uploads", uplo);
                                        break;
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });
        //change this to boolean and then if liked then show green else show normal and increment as always.
        holder.imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav_click%2 == 0){
                    firebaseFirestore.collection("uploads")
                            .whereEqualTo(OWN_TAG, own)
                            .whereEqualTo(Course_TAG, coursename)
                            .whereEqualTo(DATE_TAG, date)
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            for(DocumentSnapshot doc: documentSnapshots) {
                                ArrayList<Map<String, Object>> uplo= (ArrayList<Map<String, Object>>) doc.get("User_uploads");
                                for(int i=0;i<uplo.size();i++) {
                                    if(uplo.get(i).containsValue(previewNotes.getUserid())){
                                        previewNotes.setFav(TRUE);
                                        holder.imgFav.setImageResource(R.drawable.green_star);
                                        ArrayList<String> users=(ArrayList<String>)uplo.get(i).get("Favs");
                                        users.add(firebaseUser.getUid());
                                        uplo.get(i).put("Favs", users);
                                        firebaseFirestore.collection("uploads").document(doc.getId())
                                                .update("User_uploads", uplo);
                                        break;
                                    }
                                }
                            }
                        }
                    });
                }else{

                    firebaseFirestore.collection("uploads")
                            .whereEqualTo(OWN_TAG, own)
                            .whereEqualTo(Course_TAG, coursename)
                            .whereEqualTo(DATE_TAG, date)
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            for(DocumentSnapshot doc: documentSnapshots) {
                                ArrayList<Map<String, Object>> uplo= (ArrayList<Map<String, Object>>) doc.get("User_uploads");
                                for(int i=0;i<uplo.size();i++) {
                                    if(uplo.get(i).containsValue(previewNotes.getUserid())){
                                        previewNotes.setFav(FALSE);
                                        holder.imgFav.setImageResource(R.drawable.star);
                                        ArrayList<String> users=(ArrayList<String>)uplo.get(i).get("Favs");
                                        users.remove(firebaseUser.getUid());
                                        uplo.get(i).put("Favs", users);
                                        firebaseFirestore.collection("uploads").document(doc.getId())
                                                .update("User_uploads", uplo);
                                        break;
                                    }
                                }
                            }
                        }
                    });
                }
                fav_click = fav_click + 1;
            }
        });

        Picasso.with(context)
                .load(previewNotes.getPicuri())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        holder.imgUser.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

        float width = holder.imgLike.getContext().getResources().getDimension(R.dimen.image);
        float height = holder.imgLike.getContext().getResources().getDimension(R.dimen.image);
        for(int i = 0; i< previewNotes.getImgCount(); ++i){
            final String s = previewNotes.getList().get(i);
            final ImageView image = new ImageView(holder.imgDownload.getContext());
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams((int) width, (int)height));
            image.setMaxHeight(250);
            image.setMaxWidth(250);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( (int)width, (int)height);
            lp.setMargins(20,0,20,0);
            image.setLayoutParams(lp);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), FullImageViewer_2015037.class);
                    intent.putExtra("resourceInt",s);
                    context.startActivity(intent);

                }
            });
            Picasso.with(context)
                    .load(Uri.parse(s))
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            System.out.println("Hee;lll");
                            image.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {
                            
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
            holder.imageLinear.addView(image);
        }
    }

    @Override
    public int getItemCount() {
        return previewNotesList.size();
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public class task extends AsyncTask<String,String,Bitmap>{

        String pathname = null;

        @Override
        protected Bitmap doInBackground(String... strings) {
            System.out.println(strings[0]);
            pathname = strings[1];
            System.out.println(pathname);
            return getBitmapFromURL(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Toast.makeText(context, "Downloaded", Toast.LENGTH_SHORT).show();
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(pathname);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        }
    }

}