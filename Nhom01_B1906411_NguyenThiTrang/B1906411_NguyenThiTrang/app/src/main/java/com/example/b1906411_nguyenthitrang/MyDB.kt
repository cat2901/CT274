package com.example.b1906411_nguyenthitrang

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDB(context: Context):SQLiteOpenHelper(context,"user",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table Lophoc(id integer primary key autoincrement not null, Tenlop text)")
        p0?.execSQL("insert into Lophoc(id, Tenlop) values('1','CT27401')")
        p0?.execSQL("insert into Lophoc(id, Tenlop) values('2','CT27402')")
        p0?.execSQL("insert into Lophoc(id, Tenlop) values('3','CT27403')")

        p0?.execSQL("create table sinhvien(id integer primary key autoincrement not null, Hoten text, Email text, GioiTinh text, Malop integer)")
        p0?.execSQL("insert into sinhvien(id, Hoten, Email, GioiTinh, Malop) values('1','Nguyễn Văn A', 'nva@gmail.com','Nam','1')")
        p0?.execSQL("insert into sinhvien(id, Hoten, Email, GioiTinh, Malop) values('2','Trần Văn B', 'tvb@gmail.com','Nữ','2')")
        p0?.execSQL("insert into sinhvien(id, Hoten, Email, GioiTinh, Malop) values('3','Cao Văn C', 'cvc@gmail.com','Nam','3')")

        p0?.execSQL("create table Monhoc(id integer primary key autoincrement not null, Mamonhoc text, Tenmh text)")
        p0?.execSQL("insert into Monhoc(id,Mamonhoc, Tenmonhoc) values('1','CT173','Kiến trúc máy tính')")
        p0?.execSQL("insert into Monhoc(id,Mamonhoc, Tenmonhoc) values('2','CT274','Lập trình cho các thiết bị di động')")

        p0?.execSQL("create table Diemso(id integer primary key autoincrement not null, Masv integer, Mamonhoc integer, Diemso integer)")
        p0?.execSQL("insert into Diemso(id,Masv, Mamonhoc,Diemso) values('1','1','1','9')")

        p0?.execSQL("insert into Diemso(id,Masv, Mamonhoc,Diemso) values('3','2','1','1')")
        p0?.execSQL("insert into Diemso(id,Masv, Mamonhoc,Diemso) values('4','2','2','6')")
        p0?.execSQL("insert into Diemso(id,Masv, Mamonhoc,Diemso) values('5','3','1','7')")
        p0?.execSQL("insert into Diemso(id,Masv, Mamonhoc,Diemso) values('6','3','2','8')")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}