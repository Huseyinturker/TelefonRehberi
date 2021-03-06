package com.turker.user.telefonrehberi;

        import java.sql.SQLException;
        import android.app.Activity;
        import android.content.Intent;
        import android.widget.*;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.app.Dialog;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.Menu;


public class MainActivity extends Activity implements View.OnClickListener {
    EditText eIsim,eTelefon,eGetir;
    Button bKisiGetir,bEkle,bSil,bGetir,bGuncelle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eIsim= (EditText) findViewById(R.id.eIsim);
        eTelefon= (EditText) findViewById(R.id.eTelefon);
        eGetir= (EditText) findViewById(R.id.eGetir);

        bEkle= (Button) findViewById(R.id.bEkle);
        bGuncelle= (Button) findViewById(R.id.bGuncelle);
        bSil= (Button) findViewById(R.id.bSil);
        bGetir= (Button) findViewById(R.id.bGetir);
        bKisiGetir= (Button) findViewById(R.id.bKisiGetir);
        bEkle.setOnClickListener(this);
        bSil.setOnClickListener(this);
        bGuncelle.setOnClickListener(this);
        bGetir.setOnClickListener(this);
        bKisiGetir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bEkle:
                String ad = eIsim.getText().toString();
                String telefon = eTelefon.getText().toString();

                try {
                    VeriTabani db = new VeriTabani(MainActivity.this);
                    db.baglantiyiAc();
                    db.isimYasBilgisiniKaydet(ad, telefon);
                    db.baglantiyiKapat();
                } catch (Exception e) {

                    Dialog hata = new Dialog(this);
                    hata.setTitle("Ekleme İşlemi");
                    TextView tvHata = new TextView(this);
                    tvHata.setText(e.toString());
                    hata.setContentView(tvHata);
                    hata.show();

                } finally {
                    Dialog dialog = new Dialog(this);
                    dialog.setTitle("Ekleme İşlemi");
                    TextView tvSonuc = new TextView(this);
                    tvSonuc.setText("BAŞARILI");
                    dialog.setContentView(tvSonuc);
                    dialog.show();
                }

                break;
            case R.id.bGetir:

                Intent i = new Intent("android.umiitkose.androiddb.TUMKAYITLAR");
                startActivity(i);

                break;
            case R.id.bGuncelle:
                String adGuncelle=eIsim.getText().toString();
                String yasGuncelle=eTelefon.getText().toString();
                String idGuncelle=eGetir.getText().toString();

                long guncellenecekID=Long.parseLong(idGuncelle);

                VeriTabani dbGuncelle=new VeriTabani(MainActivity.this);
                try {
                    dbGuncelle.baglantiyiAc();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dbGuncelle.kaydiGuncelle(guncellenecekID, adGuncelle, yasGuncelle);
                dbGuncelle.baglantiyiKapat();



                break;

            case R.id.bSil:

                String silinecekKayit=eGetir.getText().toString();
                long silinecekID=Long.parseLong(silinecekKayit);

                VeriTabani dbSil=new VeriTabani(MainActivity.this);
                try {
                    dbSil.baglantiyiAc();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                dbSil.kaydiSil(silinecekID);

                dbSil.baglantiyiKapat();


                break;
            case R.id.bKisiGetir:
                String id=eGetir.getText().toString();
                long aranacakID=Long.parseLong(id);
                VeriTabani db=new VeriTabani(MainActivity.this);
                try {
                    db.baglantiyiAc();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String eldeEdilenIsim=db.getName(aranacakID);
                String eldeEdilenYas = db.getYas(aranacakID);

                db.baglantiyiKapat();

                eIsim.setText(eldeEdilenIsim);
                eTelefon.setText(eldeEdilenYas);

                break;

        }
    }
}