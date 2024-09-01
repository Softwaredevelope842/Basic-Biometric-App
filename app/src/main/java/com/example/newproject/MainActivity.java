package com.example.newproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
@RequiresApi(api = Build.VERSION_CODES.P)
public class MainActivity extends AppCompatActivity {

    BiomatricCallBack bioMatricCallBacks;
    BiometricPrompt biometricPrompt;
    CancellationSignal cancellationSignal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });





        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.P) {
            bioMatricCallBacks = new BiomatricCallBack(this);
        }
        if (checkSelfPermission(Manifest.permission.USE_BIOMETRIC)== PackageManager.PERMISSION_GRANTED){
            FingerPrint();
        }
        else {
            requestPermissions(new String[]{Manifest.permission.USE_BIOMETRIC}, 134);
        }

    }

    private void FingerPrint() {

        biometricPrompt= new BiometricPrompt.Builder(this)
                .setTitle("Verify Fingerprint")
                .setSubtitle("Login with fingerprint")
                .setDescription("Login with your fingerprint to continue")
                .setNegativeButton("Cancel",getMainExecutor(),( dialog, which) -> {
                    Toast.makeText(this, "Cancel by User", Toast.LENGTH_SHORT).show();

                })
                .build();

        biometricPrompt.authenticate(getCancelSignal(),getMainExecutor(),bioMatricCallBacks);
    }

    private CancellationSignal getCancelSignal() {

        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(() -> {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        });
        return cancellationSignal;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==134){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                FingerPrint();
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                requestPermissions(new String[]{Manifest.permission.USE_BIOMETRIC}, 134);



            }
        }
        else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.USE_BIOMETRIC}, 134);



        }

    }
}

