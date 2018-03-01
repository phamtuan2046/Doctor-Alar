package phamtuan.doctoralarm.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import phamtuan.doctoralarm.entity.Doctor;

/**
 * Created by P.Tuan on 11/14/2017.
 */

public class converter extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Doctor doctor = getIntent().getParcelableExtra("aa");
    }
}
