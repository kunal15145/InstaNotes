package mcproject.instanotesv1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DatesTab extends AppCompatActivity{

    String userChoosenTask;
    Spinner spinner;
    ImageView ivImage,downarrow;
    DatePicker datepicker;
    Calendar currentDate;
    int day,month,year;
    TextView choosedate;
    ImageView plusbutton;
    TextView count;
    int currentcount=0;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    int REQUEST_CAMERA=0;
    int SELECT_FILE=1;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<Uri> filePath;
    ArrayList<String> download_filePath;
    private static final String IMAGES_TAG = "Images";
    private static final String User_ID_TAG = "UserID";
    private static final String OWN_TAG = "OWN";
    private static final String DATE_TAG = "DATE";
    private static final String Course_TAG="Course";
    String coursename = null;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dates_tab);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        coursename = (String) getIntent().getExtras().get("CourseName");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(coursename);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


//        ivImage=findViewById(R.id.gallery);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filePath=new ArrayList<Uri>();
                download_filePath=new ArrayList<String>();
                final View view2=getLayoutInflater().inflate(R.layout.dialog_newnotes,null);
                count=view2.findViewById(R.id.count);
                count.setText(String.valueOf(currentcount));
                spinner=view2.findViewById(R.id.spinner1);
                ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(DatesTab.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.privacy_array));
                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setSelection(0);
                spinner.setAdapter(myAdapter);

                currentDate=Calendar.getInstance();
                day=currentDate.get(Calendar.DAY_OF_MONTH);
                month=currentDate.get(Calendar.MONTH);
                year=currentDate.get(Calendar.YEAR);
                plusbutton=view2.findViewById(R.id.addbutton);
                plusbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectImage();
                        count.setText(String.valueOf(currentcount));
                    }
                });





                choosedate=view2.findViewById(R.id.choosedate);
                downarrow=view2.findViewById(R.id.dropdown);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                choosedate.setText(dateFormat.format(date));
                choosedate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog=new DatePickerDialog(view2.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month=month+1;
                                choosedate.setText(dayOfMonth+"/"+month+"/"+year);
                            }
                        },year,month,day);
                        datePickerDialog.show();
                    }
                });

                downarrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog=new DatePickerDialog(view2.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month=month+1;
                                choosedate.setText(dayOfMonth+"/"+month+"/"+year);
                            }
                        },year,month,day);
                        datePickerDialog.show();
                    }
                });


                AlertDialog.Builder builder=new AlertDialog.Builder(DatesTab.this);

                builder.setView(view2)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final Map<String,Object> NewUpload = new HashMap<>();
                                NewUpload.put(User_ID_TAG, firebaseUser.getUid());
                                NewUpload.put(IMAGES_TAG, download_filePath);
                                NewUpload.put("Dislikes", "0");
                                NewUpload.put("Likes", "0");
                                firebaseFirestore.collection("uploads")
                                        .whereEqualTo(DATE_TAG, choosedate.getText())
                                        .whereEqualTo(Course_TAG, getIntent().getExtras().getString("CourseName"))
                                        .whereEqualTo(OWN_TAG, String.valueOf(spinner.getSelectedItemPosition()))
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot documentSnapshots) {
                                                if(documentSnapshots.isEmpty()){
                                                    final Map<String,Object> Newsuperupload = new HashMap<>();
                                                    Newsuperupload.put(DATE_TAG, choosedate.getText());
                                                    Newsuperupload.put(Course_TAG, getIntent().getExtras().getString("CourseName"));
                                                    Newsuperupload.put(OWN_TAG, String.valueOf(spinner.getSelectedItemPosition()));
                                                    ArrayList<Map<String,Object>> uplo= new ArrayList<Map<String, Object>>();
                                                    uplo.add(NewUpload);
                                                    Newsuperupload.put("User_uploads", uplo);
                                                    firebaseFirestore.collection("uploads").document().set(Newsuperupload)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    firebaseFirestore.collection("users")
                                                                            .document(firebaseUser.getUid())
                                                                            .get()
                                                                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                                @Override
                                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                                    String instacoins = (String) documentSnapshot.get("InstaCoins");
                                                                                    int t = Integer.parseInt(instacoins);
                                                                                    firebaseFirestore.collection("users")
                                                                                            .document(firebaseUser.getUid())
                                                                                            .update("InstaCoins",String.valueOf(t+1));
                                                                                }
                                                                            });
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {

                                                        }
                                                    });
                                                }
                                                else {
                                                    for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                                                        ArrayList<Map<String, Object>> uplo = (ArrayList<Map<String, Object>>) documentSnapshot.get("User_uploads");
                                                        uplo.add(NewUpload);
                                                        firebaseFirestore.collection("uploads")
                                                                .document(documentSnapshot.getId())
                                                                .update("User_uploads", uplo)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        firebaseFirestore.collection("users")
                                                                                .document(firebaseUser.getUid())
                                                                                .get()
                                                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                                    @Override
                                                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                                        String instacoins = (String) documentSnapshot.get("InstaCoins");
                                                                                        int t = Integer.parseInt(instacoins);
                                                                                        firebaseFirestore.collection("users")
                                                                                                .document(firebaseUser.getUid())
                                                                                                .update("InstaCoins",String.valueOf(t+1));
                                                                                    }
                                                                                });
                                                                    }
                                                                });

                                                    }
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });

                                uploadImage();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog=builder.create();
                dialog.show();
//                selectImage();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });

    }


    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(DatesTab.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(DatesTab.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public static class Utility {
        public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public static boolean checkPermission(final Context context)
        {
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
            {
                if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        alertBuilder.setCancelable(true);
                        alertBuilder.setTitle("Permission necessary");
                        alertBuilder.setMessage("External storage permission is necessary");
                        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                            }
                        });
                        AlertDialog alert = alertBuilder.create();
                        alert.show();
                    } else {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                try {
                    onSelectFromGalleryResult(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) throws IOException {
        filePath=null;
        if (data!=null){
            ClipData clipData = data.getClipData();
            if (clipData!=null) {
                ArrayList<Uri> uris = new ArrayList<>();
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    Uri uri = item.getUri();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    uris.add(uri);
                }
                filePath=uris;
            }
        }
        currentcount++;
    }

    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ivImage.setImageBitmap(thumbnail);
        filePath.add(Uri.fromFile(destination));
        currentcount++;
    }



    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent()
    {
        Intent intent = new Intent("android.intent.action.MULTIPLE_PICK");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, SELECT_FILE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dates_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorites) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    Tab1_ALL tab1_all=new Tab1_ALL();
                    return tab1_all;

                case 1:
                    Tab2_PRIVATE tab2_private=new Tab2_PRIVATE();
                    return tab2_private;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void uploadImage() {
        firebaseFirestore.collection("uploads")
                .whereEqualTo(Course_TAG, getIntent().getExtras().getString("CourseName"))
                .whereEqualTo(DATE_TAG, choosedate.getText())
                .whereEqualTo(OWN_TAG, String.valueOf(spinner.getSelectedItemPosition()))
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        for (final DocumentSnapshot doc:documentSnapshots) {
                            ArrayList<Map<String,Object>> uplo= (ArrayList<Map<String, Object>>) doc.get("User_uploads");
                            for(int i=0;i<uplo.size();i++){
                                if(uplo.get(i).containsValue(firebaseUser.getUid())){
                                    Log.d("chiiz", uplo.get(i).toString());
                                    download_filePath= (ArrayList<String>) uplo.get(i).get(IMAGES_TAG);
                                    break;
                                }
                            }

                        }
                    }
                });
        if(filePath!=null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading "+String.valueOf(filePath.size())+" images");
            progressDialog.show();
            for(int i=0;i<filePath.size();i++) {
                final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
                ref.putFile(filePath.get(i))
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot){
                                System.out.println(ref.getDownloadUrl().toString());
                                Log.d("sadsa", String.valueOf(taskSnapshot.getDownloadUrl()));
                                firebaseFirestore.collection("uploads")
                                        .whereEqualTo(Course_TAG, getIntent().getExtras().getString("CourseName"))
                                        .whereEqualTo(DATE_TAG, choosedate.getText())
                                        .whereEqualTo(OWN_TAG, String.valueOf(spinner.getSelectedItemPosition()))
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot documentSnapshots) {
                                                for (final DocumentSnapshot doc:documentSnapshots) {
                                                    ArrayList<Map<String,Object>> uplo= (ArrayList<Map<String, Object>>) doc.get("User_uploads");
                                                    for(int i=0;i<uplo.size();i++){
                                                        if(uplo.get(i).containsValue(firebaseUser.getUid())){
                                                            download_filePath.add(taskSnapshot.getDownloadUrl().toString());
                                                            Log.d("oieurojksdfh", String.valueOf(download_filePath.size()));
                                                            uplo.get(i).put(IMAGES_TAG, download_filePath);
                                                            firebaseFirestore.collection("uploads").document(doc.getId())
                                                                    .update("User_uploads", uplo);
                                                            break;
                                                        }
                                                    }

                                                }
                                            }
                                        });

                            }

                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded " + (int) progress + "%");
                            }
                        });


            }

            progressDialog.dismiss();

        }
    }

    public String getCoursename(){
        return coursename;
    }

}
