package com.shun.blog.base.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.shun.blog.weights.AppDialog;

public class AppDialogFragment extends DialogFragment {


    private Builder mBuilder;


    public void show() {
        show(mBuilder.mContext.getFragmentManager(), "AppDialogFragment");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AppDialog.Builder(getActivity())
                .setTitle(mBuilder.mTitle)
                .setNegativeButton(mBuilder.mNegativeButtonText, mBuilder.mNegativeButtonListener)
                .setPositiveButton(mBuilder.mPositiveButtonText, mBuilder.mPositiveButtonListener);
        return builder.create();
    }

    public static class Builder {
        public Activity mContext;
        public CharSequence mTitle;
        public CharSequence mPositiveButtonText;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mNegativeButtonText;
        public DialogInterface.OnClickListener mNegativeButtonListener;

        public Builder(Activity context){
            this.mContext = context;
        }

        public Builder setTitle(@Nullable CharSequence title) {
            this.mTitle = title;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            this.mNegativeButtonText = text;
            this.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            this.mPositiveButtonText = text;
            this.mPositiveButtonListener = listener;
            return this;
        }

        public AppDialogFragment show() {
            AppDialogFragment appDialogFragment = new AppDialogFragment();
            appDialogFragment.mBuilder = this;
            appDialogFragment.show();
            return appDialogFragment;
        }

    }

}
