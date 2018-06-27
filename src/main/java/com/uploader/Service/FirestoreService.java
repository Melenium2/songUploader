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
        docRef = db.collection("sample").document();
    }

    public void addDataToFirestore(Map<String, Object> model)
    {
        ApiFuture<WriteResult> result = docRef.set(model);
        try {
            System.out.println(result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void addDataToCloud()
    {

    }

    /**
     * Выцепляем картинку из методанных в js далее аплоадим картинку и аудио в storage
     * (желательно показывать прогресс) Далее передаем управление на сервер
     * где мы уже знаем все данные которые необходимо и записываем их в облако
     *
     * https://www.npmjs.com/package/jsmediatags
     * https://stackoverflow.com/questions/29881237/how-can-i-get-the-cover-of-an-mp3-file
     */

    /**
     * Данные все получилось достать осталось только объединить и отправить в ДБt
     */

    /**
     * на сайте выбираем файл с музыкой
     * 1) Показываем название
     * 2) Показываем картинку если есть и если я обнаружу как это длетьа иначе забить!
     * 3) Показываем путь к файлу
     * далее передаем на сервер
     * 1) В любом случае нужно выцепить методанные как то
     * 2) Сохранить песню в фаерстор
     * 3) Сохранить картинку в фаер стор
     * 4) Отображать прогресс загрузки пользователю
     * 5) Когда получим все данные сохранить в базу данных
     */
}
