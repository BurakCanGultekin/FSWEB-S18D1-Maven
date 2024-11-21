package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "SELECT * FROM ogrenci AS o\n" +
            "JOIN islem AS i\n" +
            "ON i.ogrno = o.ogrno\n" +
            "JOIN kitap AS k\n" +
            "ON k.kitapno = i.kitapno;";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT * FROM ogrenci AS o\n" +
            "LEFT JOIN islem AS i\n" +
            "ON o.ogrno = i.ogrno\n" +
            "WHERE i.kitapno IS NULL;";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT sinif, COUNT(kitapno) FROM ogrenci AS o\n" +
            "JOIN islem AS i\n" +
            "ON o.ogrno = i.ogrno\n" +
            "WHERE sinif IN ('10A', '10B')\n" +
            "GROUP BY sinif;";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "SELECT COUNT(ogrno) FROM ogrenci;";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT COUNT(DISTINCT ad) FROM ogrenci;";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "SELECT ad, COUNT(ad) FROM ogrenci\n" +
            "GROUP BY ad;";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();

    //Her sınıftaki öğrenci sayısını bulunuz..
    String QUESTION_8 = "SELECT sinif, COUNT(ogrno) FROM ogrenci\n" +
            "GROUP BY sinif;";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    //Her öğrencinin ad soyad karşılığında okuduğu kitap sayısını getiriniz.
    String QUESTION_9 = "SELECT ad, soyad, COUNT(kitapno) FROM ogrenci AS o\n" +
            "LEFT JOIN islem AS i\n" +
            "ON i.ogrno = o.ogrno\n" +
            "GROUP BY o.ad, o.soyad;";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
