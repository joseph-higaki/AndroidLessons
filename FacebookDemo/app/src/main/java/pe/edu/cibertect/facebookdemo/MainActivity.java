package pe.edu.cibertect.facebookdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActionBarActivity{

    private CallbackManager callbackManager;
    @InjectView(R.id.txvProfile) private  TextView txvProfile;
    @InjectView(R.id.profilePictureView) private ProfilePictureView pictureView;
    String PERMISSION = "publish_actions";

    ShareDialog shareDialog;

   private FacebookCallback<Sharer.Result> sharedCallback = new FacebookCallback<Sharer.Result>() {
        @Override
            public void onSuccess(Sharer.Result result) {
            Log.d("FacebookApp", "sharedCallback:onSuccess");

            if (result.getPostId() != null){
                Toast.makeText(MainActivity.this,"Te postie", Toast.LENGTH_LONG).show();
                //Toast.makeText(MainActivity.this,"Post publicado"+result.getPostId(),Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Log.d("FacebookApp", "sharedCallback:onCancel");
        }

        @Override
        public void onError(FacebookException e) {
            Log.d("FacebookApp", "sharedCallback:onError");
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);


        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("FacebookApp", "onSuccess");
                updateUI();

            }

            @Override
            public void onCancel() {
                Log.d("FacebookApp", "onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.d("FacebookApp", "onError");

            }
        });

        shareDialog= new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, sharedCallback);
        //setContentView(R.layout.activity_main);
        //replaced by @ContentView(R.layout.activity_main)

        //txvProfile = (TextView) findViewById(R.id.txvProfile);
        //replaced by @InjectView(R.id.txvProfile)

        //pictureView  = (ProfilePictureView) findViewById(R.id.profilePictureView);
        //replaced by  @InjectView(R.id.profilePictureView)

        updateUI();

    }

    private void updateUI() {
        Log.d("FacebookApp", "updateUI");
        boolean enableButtons = AccessToken.getCurrentAccessToken()!=null;
        Profile profile = Profile.getCurrentProfile();

        if(enableButtons && profile != null){
            Log.d("FacebookApp", "updateUI");
            txvProfile.setText(profile.getFirstName() + " " + profile.getLastName());
            pictureView.setProfileId(profile.getId());
        }

    }

    public void doPost(View view){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null){
            boolean permissions = accessToken.getPermissions().contains(PERMISSION);
            if (permissions){
                Profile profile = Profile.getCurrentProfile();
                ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                        .setContentTitle("clase de android babrbrbrbr, me quiero tirar al rio")
                        .setContentDescription("description, facebook integration")
                        .setContentUrl(Uri.parse("www.surfline.com"))
                        .build();

                //shareDialog.show(shareLinkContent);
                ShareApi.share(shareLinkContent, sharedCallback);

            }else{
                LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList(PERMISSION));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
