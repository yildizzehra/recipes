package com.example.lezzetansiklopedisi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class KaydolActivity extends AppCompatActivity {

    private EditText edtKullaniciAdi, edtAd, edtEmail, edtSifre;
    private Button btnKaydol;
    private TextView txtGirisSayfasinaGit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);

        // Firebase Authentication instance'ını al
        mAuth = FirebaseAuth.getInstance();

        // UI bileşenlerini tanımla
        edtKullaniciAdi = findViewById(R.id.edt_kullaniciAdi);
        edtAd = findViewById(R.id.edt_Ad);
        edtEmail = findViewById(R.id.edt_Email);
        edtSifre = findViewById(R.id.edt_Sifre);
        btnKaydol = findViewById(R.id.btn_Kaydol_activity);
        txtGirisSayfasinaGit = findViewById(R.id.txt_GirisSayfasina_git);

        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtSifre.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(KaydolActivity.this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kullanıcıyı Firebase Authentication ile kaydet
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(KaydolActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Kayıt başarılı
                                Toast.makeText(KaydolActivity.this, "Kayıt başarılı!", Toast.LENGTH_SHORT).show();
                                // Giriş sayfasına dönebilirsiniz veya uygulamanın ana sayfasına yönlendirebilirsiniz
                                finish(); // Bu activity'yi kapatır
                            } else {
                                // Kayıt başarısız
                                handleFirebaseAuthException(task.getException());
                            }
                        });
            }
        });

        txtGirisSayfasinaGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Giriş sayfasına yönlendirme
                Intent intent = new Intent(KaydolActivity.this, PersonFragment.class);
                startActivity(intent);
            }
        });
    }

    private void handleFirebaseAuthException(Exception exception) {
        if (exception instanceof FirebaseAuthUserCollisionException) {
            Toast.makeText(KaydolActivity.this, "Bu e-posta adresi zaten kullanılıyor.", Toast.LENGTH_SHORT).show();
        } else if (exception instanceof FirebaseAuthWeakPasswordException) {
            Toast.makeText(KaydolActivity.this, "Şifre çok zayıf. Daha güçlü bir şifre kullanın.", Toast.LENGTH_SHORT).show();
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(KaydolActivity.this, "Geçersiz e-posta adresi.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(KaydolActivity.this, "Kayıt sırasında bir hata oluştu: " + exception.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
