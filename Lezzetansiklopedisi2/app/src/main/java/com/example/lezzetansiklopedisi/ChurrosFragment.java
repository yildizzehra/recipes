package com.example.lezzetansiklopedisi;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.lezzetansiklopedisi.BesamelliTavukFragment.REQUEST_IMAGE_CAPTURE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChurrosFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private int likeCount = 0;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList;
    private String filePath;
    private List<String> imageList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_churros, container, false);

        TextView likesCount = view.findViewById(R.id.like_count);
        EditText commentInput = view.findViewById(R.id.comment_edittext);
        ImageButton likeButton = view.findViewById(R.id.like_button);
        Button commentButton = view.findViewById(R.id.post_comment_button);
        RecyclerView commentsRecyclerView = view.findViewById(R.id.comments_recyclerview);
        ImageButton takePhotoButton = view.findViewById(R.id.add_image_button);

        likeButton.setOnClickListener(v -> {
            likeCount++;
            likesCount.setText("Beğeni: " + likeCount);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                File file = new File(filePath);
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.fromFile(file));

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 25, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encoded = encoded.replace("\n", "").trim();
                imageList.add(encoded);

                // Yorum ekleme
                Comment newComment = new Comment("Kullanıcı Adı", null, encoded);
                addComment(newComment);

                Toast.makeText(getActivity(), imageList.size() + ". fotoğraf eklendi.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), "Fotoğraf çekmeden çıktınız.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "HATA: Fotoğraf çekilemedi.", Toast.LENGTH_SHORT).show();
        }
    }

    private void dosyalariSil() {
        File directory = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
}