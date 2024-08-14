package com.example.lezzetansiklopedisi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MantarFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private int likeCount = 0;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mantar, container, false);

        TextView likesCount = view.findViewById(R.id.like_count);
        ImageButton likeButton = view.findViewById(R.id.like_button);
        Button commentButton = view.findViewById(R.id.post_comment_button);
        EditText commentInput = view.findViewById(R.id.comment_edittext);
        RecyclerView commentsRecyclerView = view.findViewById(R.id.comments_recyclerview);
        ImageButton takePhotoButton = view.findViewById(R.id.add_image_button);


        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeCount++;
                likesCount.setText("Beğeni: " + likeCount);
            }
        });

        commentButton.setOnClickListener(v -> {
            String commentText = commentInput.getText().toString();
            if (!commentText.isEmpty()) {
                Comment commentObject = new Comment("Me", commentText, null); // Eğer resim eklenmeyecekse null
                addComment(commentObject);
                commentInput.setText("");
            }
        });

        takePhotoButton.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

        commentList = new ArrayList<>();
        commentList.add(new Comment("User1", "Bu yemek gerçekten çok lezzetli!", null));
        commentList.add(new Comment("User2", "Tarif için teşekkürler.", null));

        commentAdapter = new CommentAdapter(commentList);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentsRecyclerView.setAdapter(commentAdapter);

        return view;
    }

    private void addComment(Comment comment) {
        commentList.add(comment);
        commentAdapter.notifyItemInserted(commentList.size() - 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (imageBitmap != null) {
                // Bitmap'i byte[]'a dönüştür
                byte[] imageByteArray = bitmapToByteArray(imageBitmap);
                // Byte[]'ı Base64 ile String'e dönüştür
                String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

                // Resim içeren yeni bir yorum oluştur
                Comment comment = new Comment("Me", null, encodedImage);
                addComment(comment);
            }
        }
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }
}