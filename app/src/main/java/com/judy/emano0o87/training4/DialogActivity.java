package com.judy.emano0o87.training4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalTime;

import java.io.File;

public class DialogActivity extends AppCompatActivity {
    Button TakeImage, TakeVideo;
    File ImageFile;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        AndroidThreeTen.init(this);

        TakeImage =(Button) findViewById(R.id.takephoto);
        TakeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picTake();
            }
        });
    }


    void picTake()
    {
        //1st : create intent with action image capture
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //2nd : specify the file place that camera will save the photo
        ImageFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"imageName"+ LocalTime.now());
       //convert the image file to Uri
        imageUri = Uri.fromFile(ImageFile);

        //pass uri to camera to save photo in that place
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

        //there's an camera application handle the intent
        if(takePhotoIntent.resolveActivity(getPackageManager())!= null)
        {
            startActivityForResult(takePhotoIntent,0);
        }
    }

    //override the method onActivityResult that will pass the image to chat activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //camera take photo successfully
        if (resultCode == Activity.RESULT_OK && requestCode == 0)
        {
            //already there's an image saved
            if(ImageFile.exists())
            {
                //create intent back to chat activity with imageFile path
                Intent backTochatActivity = new Intent(this,ChatActivity.class);
                backTochatActivity.putExtra("imageUrl",imageUri.toString());
                startActivity(backTochatActivity);

            }
        }



    }
}






   /** SDK version >=24
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    Toast.makeText(this, "photo take successfully", Toast.LENGTH_SHORT).show();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(this, "you cancel the process of take a photo", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }


    }
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(String.valueOf(pathFile));
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName, // prefix
                ".jpg",        // suffix
                    storageDir     // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    void picTake()
    {
        Intent takeImageIntent = new Intent();
        takeImageIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takeImageIntent.resolveActivity(getPackageManager())!=null)
            pathFile = null;
        try  {
            pathFile = createImageFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if(pathFile!=null) {
            Uri imageUri = FileProvider.getUriForFile(this,
                    "com.judy.emano0o87.training4.fileprovider",
                    pathFile);

            takeImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(takeImageIntent,1);
        }

    }
*/

