package br.com.marcosouza.justamobile.ui.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.RecyclingCompany;

public class CompanyDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textViewName;
    private TextView textViewAddress;
    private TextView textViewPhone;
    private TextView textViewDescription;
    private RecyclingCompany mRecyclingCompany;
    protected ActionBar action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        this.action = getSupportActionBar();
        this.action.setTitle("Recycle Plus");

        // TODO não volta para o fragmento anterior
        this.action.setDisplayHomeAsUpEnabled(true);


        mRecyclingCompany = (RecyclingCompany) getIntent().getSerializableExtra("company");

        loadComponents();
    }

    @Override
    public void onBackPressed() {
        FragmentManager mgr = getSupportFragmentManager();
        if (mgr.getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            mgr.popBackStack();
        }
    }

    public void loadComponents(){
        imageView = findViewById(R.id.imageView_detail_thumb);
        textViewName = findViewById(R.id.textView_detail_name);
        textViewAddress = findViewById(R.id.textView_detail_address);
        textViewPhone = findViewById(R.id.textView_detail_phone);
        textViewDescription = findViewById(R.id.textView_detail_description);

        textViewName.setText(mRecyclingCompany.getName());
        textViewAddress.setText(mRecyclingCompany.getAddress());
        textViewPhone.setText(mRecyclingCompany.getPhone());
        textViewDescription.setText(mRecyclingCompany.getDescription());
        Picasso.get().load(mRecyclingCompany.getThumb()).into(imageView);


    }

    public void openDialer(View view) {
        // TODO Verificar permissões de ligação
        Uri uri = Uri.parse("tel:" + textViewPhone.getText().toString());
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        try
        {
            startActivity(i);
        }
        catch (SecurityException s)
        {
            Toast.makeText(this, s.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }
}
