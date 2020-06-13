package vitruvian.ptu_trainer_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;

public class DialogTraining extends AppCompatActivity implements View.OnClickListener {

    private int resultCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_training);

        SharedPreferences switchState = this.getSharedPreferences("switchState", Context.MODE_PRIVATE);

        SwitchCompat switchAgi = (SwitchCompat) findViewById(R.id.Agi_Training);
        boolean agiState = switchState.getBoolean("Agi", false);
        if (agiState){
            switchAgi.setChecked(true);
        } else {
            switchAgi.setChecked(false);
        }
        switchAgi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences("switchState", MODE_PRIVATE).edit();
                editor.putBoolean("Agi", isChecked);
                editor.commit();
                if (isChecked) {
                    resultCode = 1;
                } else {
                    resultCode = 2;
                }
            }
        });

        SwitchCompat switchInsp = (SwitchCompat) findViewById(R.id.Insp_Training);
        boolean inspState = switchState.getBoolean("Insp", false);
        if (inspState){
            switchInsp.setChecked(true);
        } else {
            switchInsp.setChecked(false);
        }
        switchInsp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences("switchState", MODE_PRIVATE).edit();
                editor.putBoolean("Insp", isChecked);
                editor.commit();
                if (isChecked) {
                    resultCode = 3;
                } else {
                    resultCode = 4;
                }
            }
        });

        Button buttonAgi = (Button) findViewById(R.id.Button_Agility);
        Button buttonBrutal = (Button) findViewById(R.id.Button_Brutal);
        Button buttonFoc = (Button) findViewById(R.id.Button_Focused);
        Button buttonInsp = (Button) findViewById(R.id.Button_Inspired);
        Button buttonDone = (Button) findViewById(R.id.Button_Training_Done);

        buttonAgi.setOnClickListener(this);
        buttonBrutal.setOnClickListener(this);
        buttonFoc.setOnClickListener(this);
        buttonInsp.setOnClickListener(this);
        buttonDone.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Button_Agility:
                break;
            case R.id.Button_Brutal:
                break;
            case R.id.Button_Focused:
                break;
            case R.id.Button_Inspired:
                break;
            case R.id.Button_Training_Done:
                Intent returnIntent = getIntent();
                returnIntent.putExtra("result", resultCode);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
        }

    }
}