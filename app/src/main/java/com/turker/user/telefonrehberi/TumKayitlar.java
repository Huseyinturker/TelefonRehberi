package com.turker.user.telefonrehberi;

import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;



import java.sql.SQLException;

public class TumKayitlar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.kisiler);

        TextView tv=(TextView)findViewById(R.id.tvTumKayitlar);

        VeriTabani db=new VeriTabani(TumKayitlar.this);
        try {
            db.baglantiyiAc();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String tumKayitlar=db.tumKayitlar();

        db.baglantiyiKapat();




        tv.setText(tumKayitlar);


    }

}