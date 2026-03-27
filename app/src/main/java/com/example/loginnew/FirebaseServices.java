package com.example.loginnew;   // تعريف الباكيج اللي موجود فيها الكلاس
import com.google.firebase.auth.FirebaseAuth;          // استيراد FirebaseAuth للتعامل مع تسجيل الدخول والتسجيل
import com.google.firebase.firestore.FirebaseFirestore; // استيراد Firestore للتعامل مع قاعدة البيانات
import com.google.firebase.storage.FirebaseStorage;     // استيراد Firebase Storage لتخزين الملفات

public class FirebaseServices {

    private static FirebaseServices instance;   // متغير static لتطبيق Singleton (كائن واحد فقط)
    private FirebaseAuth auth;                  // كائن المصادقة (تسجيل دخول/تسجيل)
    private FirebaseFirestore fire;             // كائن قاعدة البيانات Firestore
    private FirebaseStorage storage;            // كائن التخزين السحابي Storage

    // Getter لإرجاع كائن FirebaseAuth
    public FirebaseAuth getAuth() {
        return auth;
    }

    // Getter لإرجاع كائن Firestore
    public FirebaseFirestore getFire() {
        return fire;
    }

    // Getter لإرجاع كائن Storage
    public FirebaseStorage getStorage() {
        return storage;
    }

    // الكونستركتور — يتم استدعاؤه مرة واحدة فقط عند إنشاء الـ instance
    public FirebaseServices() {
        auth = FirebaseAuth.getInstance();              // تهيئة FirebaseAuth
        fire = FirebaseFirestore.getInstance();         // تهيئة Firestore
        storage = FirebaseStorage.getInstance();        // تهيئة Storage
    }

    // دالة Singleton لإرجاع نفس الـ instance كل مرة
    public static FirebaseServices getInstance() {
        if (instance == null) {                         // إذا ما كان في instance منشأ
            instance = new FirebaseServices();          // أنشئ instance جديد
        }
        return instance;                                // رجّع نفس الـ instance
    }
}
