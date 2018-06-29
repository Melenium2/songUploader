package com.uploader.Service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.uploader.Model.SongModel;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
public class FirestoreService
{
    private DocumentReference docRef;

    public FirestoreService() throws IOException
    {
        FileInputStream serviceAccount = new FileInputStream("./musicplayer-b8def-firebase-adminsdk-105rh-28289f061d.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://musicplayer-b8def.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();
        docRef = db.collection("Music").document();
    }

    public boolean addDataToFirestore(SongModel model)
    {
        ApiFuture<WriteResult> result = docRef.set(model);
        try {
            if (result.get().getUpdateTime() != null)
            {
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
