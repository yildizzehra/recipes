package com.example.lezzetansiklopedisi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.squareup.picasso.Picasso;

public class PersonFragment extends Fragment {

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        ImageView profileImage = view.findViewById(R.id.profile_image);
        TextView profileName = view.findViewById(R.id.profile_name);
        TextView profileEmail = view.findViewById(R.id.profile_email);
        EditText emailInput = view.findViewById(R.id.email_giris);
        EditText passwordInput = view.findViewById(R.id.edt_Sifre_giris);
        Button loginButton = view.findViewById(R.id.btn_Giris_activity);
        Button logoutButton = view.findViewById(R.id.logout_button);
        View guestView = view.findViewById(R.id.guest_view);
        View userView = view.findViewById(R.id.user_view);
        TextView registerLink = view.findViewById(R.id.txt_kayitSayfasina_git);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // Kullanıcı giriş yapmış
            guestView.setVisibility(View.GONE);
            userView.setVisibility(View.VISIBLE);

            profileName.setText(user.getDisplayName() != null ? user.getDisplayName() : "Kullanıcı Adı");
            profileEmail.setText(user.getEmail());

            if (user.getPhotoUrl() != null) {
                Picasso.get().load(user.getPhotoUrl()).into(profileImage);
            } else {
                profileImage.setImageResource(R.drawable.user);
            }

            logoutButton.setOnClickListener(v -> {
                mAuth.signOut();
                // Çıkış yapıldıktan sonra görünümü güncelle
                guestView.setVisibility(View.VISIBLE);
                userView.setVisibility(View.GONE);
            });

        } else {
            // Kullanıcı giriş yapmamış
            guestView.setVisibility(View.VISIBLE);
            userView.setVisibility(View.GONE);

            loginButton.setOnClickListener(v -> {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), task -> {
                            if (task.isSuccessful()) {
                                // Giriş başarılı, sayfayı güncelle
                                guestView.setVisibility(View.GONE);
                                userView.setVisibility(View.VISIBLE);
                            } else {
                                // Giriş başarısız, hata mesajı göster
                                String errorMessage = "Giriş başarısız";
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    errorMessage = "Şifreniz yanlış";
                                } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                                    errorMessage = "Geçerli bir mail girin";
                                }
                                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        });
            });

            registerLink.setOnClickListener(v -> {
                // Kayıt sayfasına yönlendirme
                Intent intent = new Intent(getActivity(), KaydolActivity.class);
                startActivity(intent);
            });

        }
    }
}
