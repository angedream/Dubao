package com.zilong.dubao;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        showqrQRCode();
        show2();

    }
    private void showqrQRCode(){
        ImageView qrCodeImageView = findViewById(R.id.qrCode);
        uuid u=new uuid();
        String textToEncode =u.getuuid(this);
        try {
            // 使用 BarcodeEncoder 生成二维码 Bitmap
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(textToEncode, BarcodeFormat.QR_CODE, 400, 400);
            // 显示二维码
            qrCodeImageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            // 生成失败时的处理
        }

    }

    private void show2(){
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        // 2. 生成美化二维码
        uuid u=new uuid();
        String content = u.getuuid(this);
        int qrSize = 600; // 二维码大小
        int foreground = Color.parseColor("#2196F3"); // 前景蓝
        int background = Color.WHITE; // 背景白
        Bitmap qrBitmap = QRCodeUtil.createQRCodeWithLogo(
                content, qrSize, logoBitmap, 0.2f, foreground, background);
        ImageView qrCodeImageView = findViewById(R.id.qrCode);
        qrCodeImageView.setImageBitmap(qrBitmap);

        // 3. 显示到ImageView
        if (qrBitmap != null) {
//            ivQr.setImageBitmap(qrBitmap);
            // 保存到相册
//            QRCodeUtil.saveBitmapToFile(this, qrBitmap, "custom_qr");
        }
    }

}