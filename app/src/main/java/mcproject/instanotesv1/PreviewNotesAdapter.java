package mcproject.instanotesv1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class PreviewNotesAdapter extends RecyclerView.Adapter<PreviewNotesAdapter.itemHolder> {

    private List<Notes> previewNotesList;
//    private int like_click = 0;
    private Context context;
    private int fav_click = 0;

    public class itemHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle, txtUser, txtLike;
        public ImageView imgLike, imgUser, imgComment, imgDownload, imgFav;
        public LinearLayout imageLinear;
        public View view;

        public itemHolder(View itemView) {
            super(itemView);
            imageLinear = itemView.findViewById(R.id.imageLinear);
            txtTitle = itemView.findViewById(R.id.textTitle);
            txtUser = itemView.findViewById(R.id.textUser);
            txtLike = itemView.findViewById(R.id.textLike);
            imgLike = itemView.findViewById(R.id.imageLike);
            imgComment = itemView.findViewById(R.id.imageComment);
            imgFav = itemView.findViewById(R.id.imageFav);
            imgDownload = itemView.findViewById(R.id.imageDownload);
            imgUser = itemView.findViewById(R.id.imageUser);
        }
    }

    public PreviewNotesAdapter(List<Notes> previewNotesList, PreviewNotes previewNotes){
        this.previewNotesList = previewNotesList;
        this.context = previewNotes;
    }

    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.previewnotes,parent,false);
        return new itemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PreviewNotesAdapter.itemHolder holder, int position) {
        final Notes previewNotes = previewNotesList.get(position);
        holder.txtTitle.setText(previewNotes.getTitle());
        holder.txtUser.setText(previewNotes.getUser());
        holder.txtLike.setText(String.valueOf(previewNotes.getLike()));
        if(previewNotes.isFav()){
            holder.imgFav.setImageResource(R.drawable.green_star);
        }
        holder.imgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), previewNotes.getLink(), Toast.LENGTH_SHORT).show();
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
        //change this to boolean and then if liked then show green else show normal and increment as always.
        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(previewNotes.getisLiked() == TRUE){
                    previewNotes.setisLiked(FALSE);
                    previewNotes.setLike(previewNotes.getLike()-1);
                    holder.txtLike.setText(String.valueOf(previewNotes.getLike()));
                    holder.imgLike.setImageResource(R.drawable.like);
                }else {
                    previewNotes.setisLiked(TRUE);
                    previewNotes.setLike(previewNotes.getLike()+1);
                    holder.txtLike.setText(String.valueOf(previewNotes.getLike()));
                    holder.imgLike.setImageResource(R.drawable.green_like);
                }
            }
        });
        //change this to boolean and then if liked then show green else show normal and increment as always.
        holder.imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav_click%2 == 0){
                    previewNotes.setFav(TRUE);
                    holder.imgFav.setImageResource(R.drawable.green_star);
                }else{
                    previewNotes.setFav(FALSE);
                    holder.imgFav.setImageResource(R.drawable.star);
                }
                fav_click = fav_click + 1;
            }
        });

        float width = holder.imgComment.getContext().getResources().getDimension(R.dimen.image);
        float height = holder.imgComment.getContext().getResources().getDimension(R.dimen.image);
        for(int i = 0; i< previewNotes.getImgCount(); ++i){
            String s = previewNotes.getList().get(i);
            final ImageView image = new ImageView(holder.imgDownload.getContext());
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams((int) width, (int)height));
            image.setMaxHeight(250);
            image.setMaxWidth(250);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( (int)width, (int)height);
            lp.setMargins(20,0,20,0);
            image.setLayoutParams(lp);
            Picasso.with(context)
                    .load(Uri.parse(s))
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
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

}