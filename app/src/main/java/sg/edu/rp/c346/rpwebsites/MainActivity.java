package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btnGo;
    Spinner spinnerCategory, spinnerSub;
    ArrayList<String> alCategory;
    ArrayAdapter<String> aaCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = findViewById(R.id.buttonGo);
        spinnerCategory = findViewById(R.id.spinner);
        spinnerSub = findViewById(R.id.spinner2);

        alCategory = new ArrayList<>();

        aaCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alCategory);

        spinnerSub.setAdapter(aaCategory);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        alCategory.clear();
                        String[] strCategory = getResources().getStringArray(R.array.sub_category_RP);
                        alCategory.addAll(Arrays.asList(strCategory));
                        break;

                    case 1:
                        alCategory.clear();
                        String[] strCategory1 = getResources().getStringArray(R.array.sub_category_SOI);
                        alCategory.addAll(Arrays.asList(strCategory1));
                        break;
                }
                aaCategory.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posCat = spinnerCategory.getSelectedItemPosition();
                int posSub = spinnerSub.getSelectedItemPosition();

                String url = "";
                if (posCat == 0 ) {
                    if (posSub == 0) {
                        url = "https://www.rp.edu.sg/";
                    }
                    else{
                        url = "https://www.rp.edu.sg/student-life";
                    }
                }

                else {
                    if (posSub == 0) {
                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
                    }
                    else {
                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
                    }
                }

                Intent intent = new Intent(getBaseContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);


            }
        });

    }
}
