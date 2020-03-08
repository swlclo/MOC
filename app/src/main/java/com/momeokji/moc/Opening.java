package com.momeokji.moc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Opening extends AppCompatActivity {

    private static final int BACK_PRESS_TO_CLOSE_APP_DELAY = 1000;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    ImageView mainImage;
    final String[] mainImageUrl = new String[1];
    //FirebaseStorage 인스턴스를 생성
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    private long backKeyPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_layout);

        View start = findViewById(R.id.opening);
        mainImage = findViewById(R.id.mainImage);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opening.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //Storage에 저장된 사진 URL로 불러오는 과정
//        db.collection("category")
//                //.orderBy("priority", Query.Direction.ASCENDING)
//                .whereEqualTo("priority", 5)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                StorageReference storageReference = firebaseStorage.getReference().child(document.getData().get("image").toString());
//                                storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Uri> task) {
//                                        if (task.isSuccessful()) {
//                                            // Glide 이용하여 이미지뷰에 로딩
//                                            Glide.with(getApplicationContext())
//                                                    .load(task.getResult())
//                                                    .into(mainImage);
//                                        } else {
//                                            // URL을 가져오지 못하면 토스트 메세지
//                                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//
//
//                               // mainImageUrl[0] = document.getData().get("image").toString();
//                                Log.e("Firebase확인", document.getId() + " => " + document.getData());
//                                //Log.e("Firebase확인", mainImageUrl[0]);
//                            }
//                        } else {
//                            Log.e("Firebase확인", "Error getting documents.", task.getException());
//                        }
//                    }
//                });

}

    @Override
    public void onBackPressed() {
        if (backKeyPressedTime == 0 || System.currentTimeMillis() > backKeyPressedTime + BACK_PRESS_TO_CLOSE_APP_DELAY) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르면시면 종료됩니다.", Toast.LENGTH_LONG).show();
            return ;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + BACK_PRESS_TO_CLOSE_APP_DELAY); {
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

}
