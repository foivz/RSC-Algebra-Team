package com.rsc.idonor.gamefication;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.rsc.idonor.R;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class FacebookManager implements Session.StatusCallback {

    private Activity mActivity;

    private GraphUser mUser;
    private boolean canPresentShareDialog;
    private boolean canPresentShareDialogWithPhotos;

    public void logoutFacebook() {
        Session session = Session.getActiveSession();

        if (session != null) {
            if (!session.isClosed()) {
                session.closeAndClearTokenInformation();
            }
        }
    }

    public FacebookManager(Activity activity) {
        this.mActivity = activity;


        // Can we present the share dialog for regular links?
        canPresentShareDialog = FacebookDialog.canPresentShareDialog(mActivity,
                FacebookDialog.ShareDialogFeature.SHARE_DIALOG);
        // Can we present the share dialog for photos?
        canPresentShareDialogWithPhotos = FacebookDialog.canPresentShareDialog(mActivity,
                FacebookDialog.ShareDialogFeature.PHOTOS);
    }

    public static FacebookManager getInstance(Activity activity) {

        return new FacebookManager(activity);
    }

    private static final String PERMISSION = "publish_actions";
    public static final String PENDING_ACTION_BUNDLE_KEY = "com.rsc.idonor.gamefication.FacebookManager:PendingAction";

    @Override
    public void call(Session session, SessionState state, Exception exception) {
        onSessionStateChange(session, state, exception);
    }

    public enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }

    private UiLifecycleHelper uiHelper;

    public UiLifecycleHelper getUiHelper() {
        return uiHelper;
    }

    public void setUiHelper(UiLifecycleHelper uiHelper) {
        this.uiHelper = uiHelper;
    }

    public PendingAction getPendingAction() {
        return pendingAction;
    }

    public void setPendingAction(String pendingAction) {
        this.pendingAction = PendingAction.valueOf(pendingAction);
    }

    private PendingAction pendingAction = PendingAction.NONE;

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (pendingAction != PendingAction.NONE &&
                (exception instanceof FacebookOperationCanceledException ||
                        exception instanceof FacebookAuthorizationException)) {
            new AlertDialog.Builder(mActivity)
                    .setTitle(R.string.cancelled)
                    .setMessage(R.string.permission_not_granted)
                    .setPositiveButton(R.string.ok, null)
                    .show();
            pendingAction = PendingAction.NONE;
        } else if (state == SessionState.OPENED_TOKEN_UPDATED) {
            handlePendingAction();
        }
//        updateUI();
    }

    private void handlePendingAction() {
        PendingAction previouslyPendingAction = pendingAction;
        // These actions may re-set pendingAction if they are still pending, but we assume they
        // will succeed.
        pendingAction = PendingAction.NONE;

        switch (previouslyPendingAction) {
            case POST_PHOTO:
                postPhoto();
                break;
            case POST_STATUS_UPDATE:
                postStatusUpdate();
                break;
        }
    }

    private void postPhoto() {
        Bitmap image = BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.facebook_icon);
        if (canPresentShareDialogWithPhotos) {
            FacebookDialog shareDialog = createShareDialogBuilderForPhoto(image).build();
            uiHelper.trackPendingDialogCall(shareDialog.present());
        } else if (hasPublishPermission()) {
            Request request = Request.newUploadPhotoRequest(Session.getActiveSession(), image, new Request.Callback() {
                @Override
                public void onCompleted(Response response) {
                    showPublishResult(mActivity.getString(R.string.photo_post), response.getGraphObject(), response.getError());
                }
            });
            request.executeAsync();
        } else {
            pendingAction = PendingAction.POST_PHOTO;
        }
    }

    private void showPublishResult(String message, GraphObject result, FacebookRequestError error) {
        String title;
        String alertMessage;
        if (error == null) {
            title = mActivity.getString(R.string.success);
            String id = result.cast(GraphObjectWithId.class).getId();
            alertMessage = mActivity.getString(R.string.successfully_posted_post, message, id);
        } else {
            title = mActivity.getString(R.string.error);
            alertMessage = error.getErrorMessage();
        }

        new AlertDialog.Builder(mActivity)
                .setTitle(title)
                .setMessage(alertMessage)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    private FacebookDialog.PhotoShareDialogBuilder createShareDialogBuilderForPhoto(Bitmap... photos) {
        return new FacebookDialog.PhotoShareDialogBuilder(mActivity)
                .addPhotos(Arrays.asList(photos));
    }


    private boolean hasPublishPermission() {
        Session session = Session.getActiveSession();
        return session != null && session.getPermissions().contains("publish_actions");
    }

    private FacebookDialog.ShareDialogBuilder createShareDialogBuilderForLink() {
        return new FacebookDialog.ShareDialogBuilder(mActivity)
                .setName("Hello Facebook")
                .setDescription("The 'Hello Facebook' sample application showcases simple Facebook integration")
                .setLink("http://developers.facebook.com/android");
    }

    private interface GraphObjectWithId extends GraphObject {
        String getId();
    }

    private void postStatusUpdate() {
        if (canPresentShareDialog) {
            FacebookDialog shareDialog = createShareDialogBuilderForLink().build();
            uiHelper.trackPendingDialogCall(shareDialog.present());
        } else if (mUser != null && hasPublishPermission()) {
            final String message = mActivity.getString(R.string.status_update, mUser.getFirstName(), (new Date().toString()));
            Request request = Request
                    .newStatusUpdateRequest(Session.getActiveSession(), message, null, null, new Request.Callback() {
                        @Override
                        public void onCompleted(Response response) {
                            showPublishResult(message, response.getGraphObject(), response.getError());
                        }
                    });
            request.executeAsync();
        } else {
            pendingAction = PendingAction.POST_STATUS_UPDATE;
        }
    }

    private void performPublish(PendingAction action, boolean allowNoSession) {
        Session session = Session.getActiveSession();
        if (session != null) {
            pendingAction = action;
            if (hasPublishPermission()) {
                // We can do the action right away.
                handlePendingAction();
                return;
            } else if (session.isOpened()) {
                // We need to get new permissions, then complete the action when we get called back.
                session.requestNewPublishPermissions(new Session.NewPermissionsRequest(mActivity, PERMISSION));
                return;
            }
        }

        if (allowNoSession) {
            pendingAction = action;
            handlePendingAction();
        }
    }

}
