package com.bw.jiguoshuai202003020;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.decoding.DecodeFormatManager;

import java.util.Hashtable;
import java.util.Vector;

public class Fragment_One extends Fragment {

    private Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.one, null);
        final ImageView iv = view.findViewById(R.id.iv);
//        3、	完成首页生成二维码并展示
        String texts="二维码的信息是";

        bitmap = CodeUtils.createImage(texts, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        iv.setImageBitmap(bitmap);
        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                4、	长按二维码识别二维码信息并Toast提示
                Bitmap bitmap = ((BitmapDrawable) (iv.getDrawable())).getBitmap();
                String s = deCode(bitmap);
                Toast.makeText(getActivity(), ""+s, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return view;
    }
    public String deCode(Bitmap stBitmap){
        Hashtable<DecodeHintType,Object> hins=new Hashtable<>();
        Vector<BarcodeFormat> vector = new Vector<>();
        if (vector.isEmpty()){
            vector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
        }
        hins.put(DecodeHintType.POSSIBLE_FORMATS,vector);
        hins.put(DecodeHintType.CHARACTER_SET,"UTF-8");
        Result result=null;
        int width = stBitmap.getWidth();
        int height = stBitmap.getHeight();
        int[] pirex=new int[width*height];
        stBitmap.setPixels(pirex,0,width,0,0,width,height);
        RGBLuminanceSource source = new RGBLuminanceSource(width, height, pirex);
        BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
        QRCodeMultiReader reader = new QRCodeMultiReader();
        try {
            result=reader.decode(bitmap,hins);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result!=null){
            return result.getText();
        }
        return null;
    }
}
