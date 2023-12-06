package com.example.disciplinarycommitteeassistant;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.disciplinarycommitteeassistant.Adapters.FacultyMembersAdapter;
import com.example.disciplinarycommitteeassistant.Adapters.NonFacultyMembersAdapters;
import com.example.disciplinarycommitteeassistant.Interfaces.Api;
import com.example.disciplinarycommitteeassistant.Interfaces.RetrofitClient;
import com.example.disciplinarycommitteeassistant.Models.ReportModel;
import com.example.disciplinarycommitteeassistant.Models.User;
import com.example.disciplinarycommitteeassistant.databinding.ActivityReportFormBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import kotlin.io.FilePathComponents;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityReportForm extends AppCompatActivity {
    File myfile;
    Uri uri;
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_GALLERY = 2;
    Api api= RetrofitClient.getInstance().getMyApi();
    ActivityReportFormBinding binding;
    private FacultyMembersAdapter adapter;
    private NonFacultyMembersAdapters adapter1;
    private Spinner categoryspinner;
    String Reportedby;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AndroidNetworking.initialize(getApplicationContext());
        categoryspinner=findViewById(R.id.spinCategory);
        fetchCategories();
        SearchView searchView = findViewById(R.id.search_members);
        FacultyMembersAdapter.sv=searchView;
        binding.recyclerUsers.setHasFixedSize(true);
        adapter= new FacultyMembersAdapter(new ArrayList<>());
        binding.recyclerUsers.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerUsers.setAdapter(adapter);

        adapter1= new NonFacultyMembersAdapters(new ArrayList<>());
        binding.recyclerUsers.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerUsers.setAdapter(adapter1);
//        binding.btnsubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                ReportModel reportModel = new ReportModel(0, rbId, stId, description, violationDate, category, imageuri);
//
//                // Toast.makeText(getApplicationContext(),"Uploaded Successfully",Toast.LENGTH_SHORT).show();
//                Api api1 = RetrofitClient.getInstance().getMyApi();
//                RequestBody rb_id = api.createPartFromString(ReportModel.getRb_id());
//                RequestBody st_id = api.createPartFromString(ReportModel.getSt_id());
//                RequestBody entereddescription = api.createPartFromString(ReportModel.getDescription());
//                RequestBody viol_date = api.createPartFromString(ReportModel.getViol_date());
//                RequestBody scategory = api.createPartFromString(ReportModel.getCategory());
//                MultipartBody.Part imagePart = null;
//                try {
//                    imagePart = api.prepareFilePart("image", ReportModel.getImage(), getApplicationContext());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                Call<ReportModel> call = api1.uploadCase(rb_id, st_id, entereddescription, viol_date, scategory, imagePart);
//                call.enqueue(new Callback<ReportModel>() {
//                    @Override
//                    public void onResponse(Call<ReportModel> call, Response<ReportModel> response) {
//                        if (response.isSuccessful()) {
//                            Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ReportModel> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(), "Not Uploaded Successfully", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        binding.radioGroupMembers.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.radioButtonFaculty) {
                binding.recyclerUsers.setVisibility(View.VISIBLE);
                Call<List<User>> call = api.getFacultyMembers();
                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccessful()) {
                            List<User> allFacultyMembers = response.body();
                            adapter = new FacultyMembersAdapter(allFacultyMembers);
                            binding.recyclerUsers.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(ActivityReportForm.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(checkedId == R.id.radioButtonNonFaculty){
                binding.recyclerUsers.setVisibility(View.VISIBLE);
                Call<List<User>> call = api.getNonFacultyMembers();
                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccessful()) {
                            List<User> allnonFacultyMembers = response.body();
                            adapter1=new NonFacultyMembersAdapters(allnonFacultyMembers);
                            binding.recyclerUsers.setAdapter(adapter1);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(ActivityReportForm.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(checkedId == R.id.radioButtonstudent){
                binding.recyclerUsers.setVisibility(View.GONE);
            }
        });
        binding.btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextInputEditText violationDateEditText = findViewById(R.id.violation_date);
        violationDateEditText.setOnClickListener(v -> showDatePickerDialog());
        findViewById(R.id.SelectImage).setOnClickListener(v -> showImagePickerDialog());
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Reportedby=query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the RecyclerView data based on newText
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        binding.btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_LONG).show();
                String rbId = FacultyMembersAdapter.ViewHolder.class.getName();
                String stId = binding.txtreportedstudent.getText().toString();
                String description = binding.txtdescription.getText().toString();
                String violationDate = binding.violationDate.getText().toString();
                String category = categoryspinner.getSelectedItem().toString();
                Api api1=RetrofitClient.getInstance().getMyApi();
                //Call<ReportModel>call=api1.uploadCase(rbId,stId,description,violationDate,category,myfile);
            }
        });
    }
    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Handle the selected date
                String selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                TextInputEditText violationDateEditText = findViewById(R.id.violation_date);
                violationDateEditText.setText(selectedDate);
            }
        };

        // Get the current date for initial date in the dialog
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                year,
                month,
                day
        );

        // Show the date picker dialog
        datePickerDialog.show();
    }


    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image")
                .setItems(new CharSequence[]{"Take Photo", "Choose from Gallery"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                checkCameraPermissionAndOpenCamera();
                                break;
                            case 1:
                                openGallery();
                                break;
                        }
                    }
                });
        builder.show();
    }

    private void checkCameraPermissionAndOpenCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        } else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQUEST_CAMERA);
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                assert data != null;

                Bundle bundle = data.getExtras();
                Bitmap bmpImage = (Bitmap) bundle.get("data");
                binding.Showimage.setImageBitmap(bmpImage);
                ByteArrayOutputStream outputsStream = new ByteArrayOutputStream();
                bmpImage.compress(Bitmap.CompressFormat.PNG, 100, outputsStream);
                binding.Showimage.setVisibility(View.VISIBLE);
                myfile = new File(getCacheDir(), "image.jpg");
                try {
                    FileOutputStream fos = new FileOutputStream(myfile);
                    bmpImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQUEST_GALLERY) {
            if (resultCode == RESULT_OK) {
                try {
                    assert data != null;

                    uri = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    binding.Showimage.setImageURI(uri);
                    binding.Showimage.setVisibility(View.VISIBLE);
                    myfile = new File(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

//
//        if (resultCode == RESULT_OK) {
//            if (requestCode == REQUEST_CAMERA) {
//                // Handle camera image captured
//                if (data != null && data.getExtras() != null) {
//                    Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
//                    ImageView imageView = findViewById(R.id.Showimage);
//                    imageView.setImageBitmap(imageBitmap);
//                }
//            } else if (requestCode == REQUEST_GALLERY) {
//                // Handle gallery image picked
//                Uri imageUri = data.getData();
//                ImageView imageView = findViewById(R.id.Showimage);
//                imageView.setImageURI(imageUri);
//            }
//        }
//    }
//public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//    super.onActivityResult(requestCode, resultCode, data);
//
//    if (resultCode == RESULT_OK) {
//        // compare the resultCode with the
//        // SELECT_PICTURE constant
//        if (requestCode == REQUEST_CAMERA) {
//            // Get the url of the image from data
//            Uri selectedImageUri = data.getData();
//            if (null != selectedImageUri) {
//                // update the preview image in the layout
//                Picasso.get().load(myfile).into(binding.Showimage);
//                String imagepath = getRealPathFromURI(selectedImageUri, getApplicationContext());
//                myfile = new File(imagepath);
//            }
//        }
//    }
}
    public String getRealPathFromURI(Uri contentURI, Context context) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentURI, projection, null, null, null);

        if (cursor == null)
            return null;

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        if (cursor.moveToFirst()) {
            String s = cursor.getString(column_index);
            cursor.close();
            return s;
        }

        cursor.close();
        return null;
    }

    private void fetchCategories() {
        Call<List<String>> call = api.getcategory();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    List<String> categories = response.body();

                    // Populate spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ActivityReportForm.this,
                            android.R.layout.simple_spinner_item, categories);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categoryspinner.setAdapter(adapter);
                } else {
                    // Handle error response
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                // Handle network failure
            }
        });
}

}