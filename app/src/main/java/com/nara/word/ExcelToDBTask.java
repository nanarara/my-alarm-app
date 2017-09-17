package com.nara.word;

import android.content.Context;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by nara.yoon on 2017-08-22.
 */

public class ExcelToDBTask extends AsyncTask{

    private NoteDbAdapter dbAdapter;
    private Context context;

    public ExcelToDBTask(Context context){
        this.context = context;
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        dbAdapter = new NoteDbAdapter(context);
        copyExcelDataToDatabase();
        return null;
    }

    private void copyExcelDataToDatabase() {
        Log.w("ExcelToDatabase", "copyExcelDataToDatabase()");

        Workbook workbook = null;
        Sheet sheet = null;

        try {
            InputStream is = context.getResources().getAssets().open("eng1.xls");
            workbook = Workbook.getWorkbook(is);

            if (workbook != null) {
                sheet = workbook.getSheet(0);

                if (sheet != null) {

                    int nMaxColumn = 2;
                    int nRowStartIndex = 0;
                    int nRowEndIndex = sheet.getColumn(nMaxColumn - 1).length - 1;
                    int nColumnStartIndex = 0;
                    int nColumnEndIndex = sheet.getRow(2).length - 1;

                    dbAdapter.open();
                    for (int nRow = nRowStartIndex; nRow <= nRowEndIndex; nRow++) {
                        String eng = sheet.getCell(nColumnStartIndex, nRow).getContents();
                        String kor = sheet.getCell(nColumnStartIndex + 1, nRow).getContents();
                        dbAdapter.createNote(eng, kor);
                    }
                    dbAdapter.close();
                } else {
                    System.out.println("Sheet is null!!");
                }
            } else {
                System.out.println("WorkBook is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }

            Log.d("NARA", "db loading complete..");
        }
    }

}
