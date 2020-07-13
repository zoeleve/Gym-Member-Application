package com.example.workout;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.Serializable;

public class User implements Serializable {

    public String username;
    private String email;
    private String password;
    private String phone;
    private int barcode;

    User() {
    }

    User(String email, String username, String password, String phone, int barcode) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.barcode = barcode;
    }

    //getters
    String getPhone() {
        return this.phone;
    }

    String getEmail() {
        return email;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public int getBarcode() {
        return barcode;
    }

    void exportStatistics(String athleteName) {
        for (User athlete : SignUp.userList)
            if (athlete.getUsername().equals(athleteName)) {
                Statistics statistics = new Statistics((Athlete) athlete);
                statistics.addResults();
            }
    }

    //takes an int barcode number and converts it in barcode img

    public Bitmap generateBarcode(int barcode) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bit = multiFormatWriter.encode(String.valueOf(barcode), BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            return barcodeEncoder.createBitmap(bit);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }


}
