package com.zilong.dubao;

import android.content.Context;
import android.graphics.*;
import android.os.Environment;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtil {

    /**
     * 生成带Logo的彩色二维码（返回Bitmap）
     * @param content 二维码内容
     * @param size 二维码宽高（px）
     * @param logo Logo位图
     * @param logoSizeRatio Logo占二维码比例（建议 0.15~0.25）
     * @param foregroundColor 前景色
     * @param backgroundColor 背景色
     * @return 生成的二维码Bitmap
     */
    public static Bitmap createQRCodeWithLogo(String content, int size, Bitmap logo,
                                              float logoSizeRatio, int foregroundColor, int backgroundColor) {
        try {
            // 1. 配置参数
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // 高容错（适合加Logo）
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1); // 边距

            // 2. 生成原始二维码矩阵
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, size, size, hints);

            // 3. 转Bitmap（自定义颜色）
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap qrBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    qrBitmap.setPixel(x, y, bitMatrix.get(x, y) ? foregroundColor : backgroundColor);
                }
            }

            // 4. 叠加Logo（圆形）
            if (logo != null) {
                int logoSize = (int) (size * logoSizeRatio);
                Bitmap scaledLogo = Bitmap.createScaledBitmap(logo, logoSize, logoSize, true);
                Bitmap circleLogo = getCircleBitmap(scaledLogo); // 圆形裁剪

                Bitmap resultBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(resultBitmap);
                canvas.drawBitmap(qrBitmap, 0, 0, null);
                // 绘制Logo到中心
                int left = (size - logoSize) / 2;
                int top = (size - logoSize) / 2;
                canvas.drawBitmap(circleLogo, left, top, null);
                canvas.save();
                canvas.restore();

                // 回收
                qrBitmap.recycle();
                scaledLogo.recycle();
                return resultBitmap;
            }

            return qrBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把Bitmap裁成圆形
     */
    private static Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawCircle(bitmap.getWidth() / 2f, bitmap.getHeight() / 2f, bitmap.getWidth() / 2f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }

    /**
     * 保存Bitmap到本地（DCIM/QRCode）
     */
    public static File saveBitmapToFile(Context context, Bitmap bitmap, String fileName) {
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "QRCode");
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, fileName + ".png");
        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}