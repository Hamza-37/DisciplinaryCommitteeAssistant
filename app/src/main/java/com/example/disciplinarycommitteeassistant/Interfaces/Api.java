package com.example.disciplinarycommitteeassistant.Interfaces;

import android.content.Context;
import android.net.Uri;

import com.example.disciplinarycommitteeassistant.FileUtil;
import com.example.disciplinarycommitteeassistant.Models.AddNewCategoryModel;
import com.example.disciplinarycommitteeassistant.Models.AssignCommitteeModel;
import com.example.disciplinarycommitteeassistant.Models.FacultyMembersModel;
import com.example.disciplinarycommitteeassistant.Models.ReportData;
import com.example.disciplinarycommitteeassistant.Models.ReportModel;
import com.example.disciplinarycommitteeassistant.Models.ThresHoldModel;
import com.example.disciplinarycommitteeassistant.Models.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {
    public static String BASE_URL = "http://192.168.43.97/DCA-FYP-API/api/";

    @POST("User/AuthenticateUser")
    Call<ArrayList<User>> Login(@Query("username") String Uname,@Query("password") String pass);
    @GET("Server/GetMembers")
    Call<ArrayList<FacultyMembersModel>>getmembers();
    @POST("Server/SetCategory")
    Call<String>addcategory(@Query("title") String title);
    @GET("Server/GetCategory")
    Call<List<String>>getcategory();
    @GET("Server/getfacultymembers")
    Call<List<User>> getFacultyMembers();
    @GET("Server/GetNonFacultyMembers")
    Call<List<User>> getNonFacultyMembers();
    @Multipart
    @POST("Server/InsertCase") // Replace with your API endpoint
    Call<String> uploadCase(
            @Part("rb_id") RequestBody rb_id,
            @Part("st_id") RequestBody st_id,
            @Part("description") RequestBody description,
            @Part("viol_date") RequestBody viol_date,
            @Part("Category") RequestBody Category,
            @Part MultipartBody.Part file
    );
    @POST("User/SetThresHold")
    Call<String>addthreshold(@Query("val") String val);
    @GET("Server/GetRegister10")
    Call<String>getregister10(@Query("st_id")String st_id,@Query("cat")String cat);
    public default MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context) throws IOException {
        File file = FileUtil.from(context, fileUri);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(fileUri)),
                        file
                );
        return MultipartBody.Part.createFormData(partName,
                file.getName(),
                requestFile);
    }

    public default RequestBody createPartFromString(String descriptionString){
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);
        return  description;

    }
}
