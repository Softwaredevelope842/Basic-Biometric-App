//import android.content.Context;
//import android.content.Intent;
//import android.hardware.biometrics.BiometricPrompt;
//import android.os.Build;
//import android.widget.Toast;
//
//import androidx.annotation.RequiresApi;
//
//import com.example.newproject.MainActivity;
//
//@RequiresApi(api = Build.VERSION_CODES.P)
//public class BiomatricCallBack extends BiometricPrompt.AuthenticationCallback {
//Context context;
//
//    public BiomatricCallBack(Context context){
//        this.context=context;
//
//    }
//    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result){
//        super.onAuthenticationSucceeded(result);
//        Toast.makeText(context,"successfully Authenticated",Toast.LENGTH_SHORT).show();
//        Intent intent=new Intent(context.getApplicationContext(), MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//
//    }
//    public void onAuthenticationFailed() {
//        super.onAuthenticationFailed();
//        Toast.makeText(context,"Authentication Failed",Toast.LENGTH_SHORT);
//    }
//    public void onAuthenticationError(int errorCode,CharSequence errString){
//        super.onAuthenticationError(errorCode,errString);
//        Toast.makeText(context,"Authentication Error"+errorCode,Toast.LENGTH_SHORT).show();
//    }
//}
//
//
//
