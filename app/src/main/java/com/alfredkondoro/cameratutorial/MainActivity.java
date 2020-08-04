package com.alfredkondoro.cameratutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonCamera;
    private ImageView imageCapture;
    private static final int Image_Capture_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        buttonCamera = (Button) findViewById (R.id.buttonCamera);
        imageCapture = (ImageView) findViewById (R.id.imageCamera);

        buttonCamera.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent CameraIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult (CameraIntent, Image_Capture_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras ().get ("data");
                imageCapture.setImageBitmap (bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText (this, "Cancelled", Toast.LENGTH_LONG).show ();
            }
        }
    }
}
