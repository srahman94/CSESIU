package com.example.sujon.csesiu.activityes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.adapter.GridViewAdapter;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridViewAdapter;

    private ArrayList<String> listImageURLs = new ArrayList<>();
    Toolbar tl;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        linearLayout = findViewById(R.id.g_ll);
        tl= (Toolbar) findViewById(R.id.gallery_tl);
        setSupportActionBar(tl);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if(haveNetworkConnection()==false){
            //AlertMessage.showMessage(con,"Alert","No Internet Connection","OK",R.drawable.alertpic);
            Snackbar.make(linearLayout,"No Internet Connection",Snackbar.LENGTH_LONG)
                    .show();
        }

        addImageURLs();

        gridView = findViewById(R.id.grid_view);
        gridViewAdapter = new GridViewAdapter(this, listImageURLs);
        gridView.setAdapter(gridViewAdapter);

        setGridViewItemClickListener();
    }

    private void setGridViewItemClickListener(){
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putStringArrayList("imageURLs", listImageURLs);
            Intent intent = new Intent(this, ImageActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void addImageURLs(){
        listImageURLs.add("http://siu.edu.bd/wp-content/gallery/marcia-bernicat-the-united-states-ambassador-to-bangladesh-visit-in-siu/DSC_0475.jpg");
        listImageURLs.add("http://siu.edu.bd/wp-content/gallery/marcia-bernicat-the-united-states-ambassador-to-bangladesh-visit-in-siu/DSC_0488.jpg");
        listImageURLs.add("http://siu.edu.bd/wp-content/gallery/marcia-bernicat-the-united-states-ambassador-to-bangladesh-visit-in-siu/DSC_0542.jpg");
        listImageURLs.add("http://siu.edu.bd/wp-content/gallery/pohela-boishakh1423/DSC08068.JPG");
        listImageURLs.add("http://siu.edu.bd/wp-content/gallery/pohela-boishakh1423/DSC08089.JPG");
        listImageURLs.add("http://siu.edu.bd/wp-content/gallery/pohela-boishakh1423/DSC08090.JPG");
        listImageURLs.add("http://siu.edu.bd/wp-content/gallery/17th-march2016/DSC07593.JPG");
        listImageURLs.add("http://siu.edu.bd/wp-content/gallery/17th-march2016/DSC07616.JPG");
        listImageURLs.add("http://siu.edu.bd/wp-content/gallery/26-march2016/DSC07732.JPG");
        listImageURLs.add("https://scontent.fdac6-1.fna.fbcdn.net/v/t1.15752-9/31958240_966081576892191_6075607187269353472_n.jpg?_nc_cat=0&_nc_eui2=v1%3AAeHBS-N9HcXst1JR03voLLwrl5sV96WX45XGiOh4oGJPkFZNCc10tiiaZuqoOY2w0-pN2qzntNZQYT6YrCnUMPq6cFHcgeegB696KrSCGA7yfg&oh=3ab746e5a646fe379f9d092867192243&oe=5B4F73A5");
        listImageURLs.add("https://scontent.fdac6-1.fna.fbcdn.net/v/t1.15752-9/31945461_966082206892128_5312823830178168832_n.jpg?_nc_cat=0&_nc_eui2=v1%3AAeGbT65s7J7RlcGu_p87-O-Q5st-6g9Pj0o15yKTyNMi0_ZwXhmeDEwJzzKnFPTqHalKkq3dLWF1qRPOdr23GzJuSzl2QoUl12J_r_cDcEF2pg&oh=52dcf7da99fea962aece63b221dbd045&oe=5B88F082");
        listImageURLs.add("https://scontent.fdac6-1.fna.fbcdn.net/v/t1.15752-9/31956281_966074873559528_4833679554969075712_n.jpg?_nc_cat=0&_nc_eui2=v1%3AAeGAyzDsnFK_a5WYtI1vZ7yPXHf-l4pV0EMJijF3GyFO2oIhgs-uGTCoG0j3e8pkEK91-sw8R5DyS3yg4hkeF2Qf4dxUXJafb8iukcrWt3J31w&oh=ce1abe627d50f1d937cc2ea2c4ebd3d7&oe=5B580348");
        listImageURLs.add("https://scontent.fdac6-1.fna.fbcdn.net/v/t1.15752-9/31958374_966076130226069_1283497010145525760_n.jpg?_nc_cat=0&_nc_eui2=v1%3AAeFD3uvrynFWUYONV-TgAOIo9Rrwvv02dArTmoFLTx9pPfLbbfy8iS5eYKcrQnryt1N0hlG8SAC8ic5JA-7r_ukEcdW9q_HyZBfANKc_9GExAQ&oh=9f6fddd2d0f810fe480872f428799f4f&oe=5B560DE3");
        listImageURLs.add("https://scontent.fdac6-1.fna.fbcdn.net/v/t1.15752-9/31949199_966076620226020_6059630183806140416_n.jpg?_nc_cat=0&_nc_eui2=v1%3AAeGQldQ0DWOUZ_qhOfUu_32Qhx9nk6VMdYmuvOZa-crBa6OC53jFn9rpZ-poDQJTdRUBr6x_iKLEoGp_psDELNK_vjwUFGQl58yipI5jfG_tkA&oh=2b2d74d22f4a677e5ada8790b636d900&oe=5B8A170A");
        listImageURLs.add("https://scontent.fdac6-1.fna.fbcdn.net/v/t1.15752-9/31949335_966077026892646_3950017031981498368_n.jpg?_nc_cat=0&_nc_eui2=v1%3AAeE0r5WBZmFpd8DwD9CqxMv3akAfBIedck04EJrzB-oQtUJlJdemrNl8PRffj7LmDTESJnRIn_BYlXfW4vZ9LKmXrttSeOkdVjsCE2fRsHhtcA&oh=806a7cc5f1c8b43a9db78e84e5b6e916&oe=5B58356B");
        listImageURLs.addAll(listImageURLs);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
