package com.example.user.firebaseblog;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class PostClass extends ArrayAdapter<String> {

    private final ArrayList<String> useremail;
    private final ArrayList<String> userImage;
    private final ArrayList<String> userComment;
    private final Activity context;


    public PostClass(ArrayList<String> useremail, ArrayList<String> userImage, ArrayList<String> userComment, Activity context) {
        super(context,R.layout.custom__view,useremail);
        this.useremail = useremail;
        this.userImage = userImage;
        this.userComment = userComment;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        final View customView = layoutInflater.inflate(R.layout.custom__view, null, true);

        TextView userEmailText = customView.findViewById(R.id.postUserNameText);
        TextView commentText = customView.findViewById((R.id.postCommentText));
        ImageView imageView = customView.findViewById((R.id.imageView));

        ImageButton btn = customView.findViewById(R.id.button5);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ImageButton btn = customView.findViewById(R.id.button5);

            btn.setBackgroundResource(R.drawable.like1);


            }
        });


        userEmailText.setText(useremail.get(position));
        commentText.setText(userComment.get(position));
        Picasso.get().load(userImage.get(position)).into(imageView);


        return customView;
    }
}
