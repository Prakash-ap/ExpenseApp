package com.eron.android.expenseapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.CopySpinnerCatAdapter1;
import com.eron.android.expenseapp.Adapter.ExportAdapter;
import com.eron.android.expenseapp.Adapter.TransAdapter;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Fragments.SpendingFragment;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.TransModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportCatAcc extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int MULTIPLE_PERMISSIONS = 100;
    private Spinner spinner;
    private Button export_btn;
    CatItemData catItemData;
    private ArrayList<CatItemData>catItemDataArrayList;
    DataBaseHandler db;
    CopySpinnerCatAdapter1 copySpinnerCatAdapter1;
    public static RecyclerView recyclerView;
    public static ExportAdapter exportAdapter;
    ArrayList<TransModel> transModelArrayList;
    ArrayList<TransModel> transModelArrayList1;
    TransModel transModel;
    String ocatname;
    String[] permissions;

    private int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_cat_acc);
        spinner=findViewById(R.id.spinnerexport);
        export_btn=findViewById(R.id.btnexport);
        recyclerView=findViewById(R.id.exportlist);
        permissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

checkPermissions();

        db=new DataBaseHandler(this);
        spinner.setOnItemSelectedListener(this);

        loadCatg();
        transModel=new TransModel();
        transModelArrayList1=new ArrayList<>();

        export_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

                    new ExportDatabaseCSVTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                } else {

                    new ExportDatabaseCSVTask().execute();
                }
            }
        });


        /*transModelArrayList=db.getAllNewIncome();
        for(int i=0;i<transModelArrayList.size();i++){

            transModel=transModelArrayList.get(i);
            String dbcat=transModel.getCategory_name();
            if(dbcat.equals(ocatname)){

                //transModelArrayList.clear();
                transModelArrayList1=new ArrayList<>();
                transModelArrayList1=db.getCurrentcatList(ocatname);
                exportAdapter = new ExportAdapter(this, transModelArrayList1);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(exportAdapter);


            }else{
                Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();


            }*/


    //    }

        /*exportAdapter = new ExportAdapter(this, transModelArrayList1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(exportAdapter);*/


    }

    private void loadCatg() {

        catItemData = new CatItemData();
        catItemDataArrayList = new ArrayList<>();
        catItemDataArrayList = db.getAllCatg();
        copySpinnerCatAdapter1 = new CopySpinnerCatAdapter1(catItemDataArrayList, getApplicationContext());
        spinner.setAdapter(copySpinnerCatAdapter1);
    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(ExportCatAcc.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
           ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

// all permissions are granted.
                } else {

//permissions missing

                }
                return;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, "Loadede"+position, Toast.LENGTH_SHORT).show();
        catItemDataArrayList=db.getAllCatg();

        ocatname=catItemDataArrayList.get(position).getText();

        transModelArrayList1=db.getCurrentcatList(ocatname);
        if(transModelArrayList1.size()!=0) {
            exportAdapter = new ExportAdapter(this, transModelArrayList1);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(exportAdapter);
        }else{
            transModelArrayList1=new ArrayList<>();

            transModelArrayList1.clear();
            exportAdapter = new ExportAdapter(this, transModelArrayList1);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(exportAdapter);
            Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();


        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @SuppressLint("StaticFieldLeak")
    public class ExportDatabaseCSVTask extends AsyncTask<String, Void, Boolean> {

        private final ProgressDialog dialog = new ProgressDialog(ExportCatAcc.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();

        }

        protected Boolean doInBackground(final String... args) {

            File exportDir = new File(Environment.getExternalStorageDirectory(), "/codesss/");
            if (!exportDir.exists()) { exportDir.mkdirs(); }

            File file = new File(exportDir, "person.csv");
            try {
                file.createNewFile();
                CsvWriter csvWrite = new CsvWriter(new FileWriter(file));
                Cursor curCSV = db.raw();
                csvWrite.writeNext(curCSV.getColumnNames());
                while(curCSV.moveToNext()) {
                    String arrStr[]=null;
                    String[] mySecondStringArray = new String[curCSV.getColumnNames().length];
                    for(int i=0;i<curCSV.getColumnNames().length;i++)
                    {
                        mySecondStringArray[i] =curCSV.getString(i);
                    }
                    csvWrite.writeNext(mySecondStringArray);
                }
                csvWrite.close();
                curCSV.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        protected void onPostExecute(final Boolean success) {
            if (this.dialog.isShowing()) { this.dialog.dismiss(); }
            if (success) {
                Toast.makeText(ExportCatAcc.this, "Export successful!", Toast.LENGTH_SHORT).show();
              //  ShareFile();
            } else {
                Toast.makeText(ExportCatAcc.this, "Export failed", Toast.LENGTH_SHORT).show();
            }
        }

        private void ShareFile() {
            File exportDir = new File(Environment.getExternalStorageDirectory(), "/codesss/");
            String fileName = "myfile.csv";
            File sharingGifFile = new File(exportDir, fileName);
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("application/csv");
            Uri uri = Uri.fromFile(sharingGifFile);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(shareIntent, "Share CSV"));
        }
    }
}
