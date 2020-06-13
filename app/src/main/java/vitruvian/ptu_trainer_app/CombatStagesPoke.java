package vitruvian.ptu_trainer_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Vitruvian on 02/03/2018.
 */

public class CombatStagesPoke extends Fragment implements View.OnClickListener {
    public static final String ARG_PAGE = "ARG_PAGE";
    private static final String TAG = "";

    private int uniqueID = 0;
    private TextView Hit_Points_Current_Number, Hit_Points_Max_Number, Overland_Number, Swim_Number, Phy_Eva_Number, Sp_Eva_Number, Speed_Eva_Number, HP_Number, Atk_Number;
    private TextView Def_Number, SpAtk_Number, SpDef_Number, Spd_Number, Atk_CS, Def_CS, SpAtk_CS, SpDef_CS, Speed_CS, Init_Number;
    private int hitPts, phyEva, spEva, speedEva, HP, atk, def, spAtk, spDef, speed, csAtk, csDef, csSpAtk, csSpDef, csSpeed = 0;
    private int hitPtsMax, weightClass, overland, swim;

    public static CombatStagesTrainer newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        CombatStagesTrainer fragment = new CombatStagesTrainer();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_combat_stages_poke, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        SharedPreferences activityID = this.getActivity().getSharedPreferences(getString(R.string.activityID), Context.MODE_PRIVATE);
        uniqueID = activityID.getInt(getString(R.string.unqiueID), 0);

        Button button_Minus_Hit_Pts = (Button) view.findViewById(R.id.Button_Minus_Hit_Points);
        Button button_Plus_Hit_Pts = (Button) view.findViewById(R.id.Button_Plus_Hit_Points);
        Button button_Minus_Atk = (Button) view.findViewById(R.id.Button_Minus_Atk);
        Button button_Plus_Atk = (Button) view.findViewById(R.id.Button_Plus_Atk);
        Button button_Minus_Def = (Button) view.findViewById(R.id.Button_Minus_Def);
        Button button_Plus_Def = (Button) view.findViewById(R.id.Button_Plus_Def);
        Button button_Minus_SpAtk = (Button) view.findViewById(R.id.Button_Minus_SpAtk);
        Button button_Plus_SpAtk = (Button) view.findViewById(R.id.Button_Plus_SpAtk);
        Button button_Minus_SpDef = (Button) view.findViewById(R.id.Button_Minus_SpDef);
        Button button_Plus_SpDef = (Button) view.findViewById(R.id.Button_Plus_SpDef);
        Button button_Minus_Speed = (Button) view.findViewById(R.id.Button_Minus_Speed);
        Button button_Plus_Speed = (Button) view.findViewById(R.id.Button_Plus_Speed);
        Button button_Training = (Button) view.findViewById(R.id.Button_Training);
        Button button_Status_Effects = (Button) view.findViewById(R.id.Button_Status_Effects);

        button_Minus_Hit_Pts.setOnClickListener(this);
        button_Plus_Hit_Pts.setOnClickListener(this);
        button_Minus_Atk.setOnClickListener(this);
        button_Plus_Atk.setOnClickListener(this);
        button_Minus_Def.setOnClickListener(this);
        button_Plus_Def.setOnClickListener(this);
        button_Minus_SpAtk.setOnClickListener(this);
        button_Plus_SpAtk.setOnClickListener(this);
        button_Minus_SpDef.setOnClickListener(this);
        button_Plus_SpDef.setOnClickListener(this);
        button_Minus_Speed.setOnClickListener(this);
        button_Plus_Speed.setOnClickListener(this);
        button_Training.setOnClickListener(this);
        button_Status_Effects.setOnClickListener(this);

        Hit_Points_Current_Number = (TextView) view.findViewById(R.id.Hit_Points_Current_Number);
        Hit_Points_Max_Number = (TextView) view.findViewById(R.id.Hit_Points_Max_Number);
        Overland_Number = (TextView) view.findViewById(R.id.Overland_Number);
        Swim_Number = (TextView) view.findViewById(R.id.Swim_Number);
        Phy_Eva_Number = (TextView) view.findViewById(R.id.Phy_Eva_Number);
        Sp_Eva_Number = (TextView) view.findViewById(R.id.Sp_Eva_Number);
        Speed_Eva_Number = (TextView) view.findViewById(R.id.Speed_Eva_Number);
        HP_Number = (TextView) view.findViewById(R.id.HP_Number);
        Atk_Number = (TextView) view.findViewById(R.id.Atk_Number);
        Def_Number = (TextView) view.findViewById(R.id.Def_Number);
        SpAtk_Number = (TextView) view.findViewById(R.id.SpAtk_Number);
        SpDef_Number = (TextView) view.findViewById(R.id.SpDef_Number);
        Spd_Number = (TextView) view.findViewById(R.id.Spd_Number);
        Atk_CS = (TextView) view.findViewById(R.id.Atk_CS);
        Def_CS = (TextView) view.findViewById(R.id.Def_CS);
        SpAtk_CS = (TextView) view.findViewById(R.id.SpAtk_CS);
        SpDef_CS = (TextView) view.findViewById(R.id.SpDef_CS);
        Speed_CS = (TextView) view.findViewById(R.id.Speed_CS);
        Init_Number = (TextView) view.findViewById(R.id.Init_Number);


        String sharedPrefStats = "savedPoke" + uniqueID + "Stats";
        SharedPreferences savedStats = this.getActivity().getSharedPreferences(sharedPrefStats, Context.MODE_PRIVATE);

        int lvl = savedStats.getInt("baseLevel", 0);

        HP = savedStats.getInt(getString(R.string.baseHP), 0);
        hitPtsMax = (10 + lvl + (HP * 3));
        Hit_Points_Current_Number.setText(String.valueOf(hitPts));
        Hit_Points_Max_Number.setText(String.valueOf(hitPtsMax));

        overland = savedStats.getInt("baseOverland", 0);
        swim = savedStats.getInt("baseSwim",0);
        Overland_Number.setText(String.valueOf(overland));
        Swim_Number.setText(String.valueOf(swim));

        def = savedStats.getInt(getString(R.string.baseDef), 0);
        phyEva = def / 5;
        spDef = savedStats.getInt(getString(R.string.baseSpDef), 0);
        spEva = spDef / 5;
        speed = savedStats.getInt(getString(R.string.baseSpeed), 0);
        speedEva = speed / 5;

        atk = savedStats.getInt(getString(R.string.baseAtk), 0);
        spAtk = savedStats.getInt(getString(R.string.baseSpAtk), 0);
        HP_Number.setText(String.valueOf(HP));

        String sharedPrefCS = ("poke" + uniqueID + "CS");
        SharedPreferences saveCS = this.getActivity().getSharedPreferences(sharedPrefCS, Context.MODE_PRIVATE);

            int saveHitPts = saveCS.getInt("HitPtsCurrent", hitPtsMax);
            int saveOverland = saveCS.getInt("Overland", overland);
            int saveSwim = saveCS.getInt("Swim", swim);
            int savePhyEva = saveCS.getInt("PhyEva", phyEva);
            int saveSpEva = saveCS.getInt("SpEva", spEva);
            int saveSpdEva = saveCS.getInt("SpdEva", speedEva);
            int saveAtk = saveCS.getInt("Atk", atk);
            int saveDef = saveCS.getInt("Def", def);
            int saveSpAtk = saveCS.getInt("SpAtk", spAtk);
            int saveSpDef = saveCS.getInt("SpDef", spDef);
            int saveSpeed = saveCS.getInt("Speed", speed);
            int saveCSAtk = saveCS.getInt("CSAtk", 0);
            int saveCSDef = saveCS.getInt("CSDef", 0);
            int saveCSSpAtk = saveCS.getInt("CSSpAtk", 0);
            int saveCSSpDef = saveCS.getInt("CSSpDef", 0);
            int savedCSSPeed = saveCS.getInt("CSSpeed", 0);
            int saveInit = saveCS.getInt("Init", speed);

        Hit_Points_Current_Number.setText(String.valueOf(saveHitPts));
        Overland_Number.setText(String.valueOf(saveOverland));
        Swim_Number.setText(String.valueOf(saveSwim));
        Phy_Eva_Number.setText(String.valueOf(savePhyEva));
        Sp_Eva_Number.setText(String.valueOf(saveSpEva));
        Speed_Eva_Number.setText(String.valueOf(saveSpdEva));
        Atk_Number.setText(String.valueOf(saveAtk));
        Def_Number.setText(String.valueOf(saveDef));
        SpAtk_Number.setText(String.valueOf(saveSpAtk));
        SpDef_Number.setText(String.valueOf(saveSpDef));
        Spd_Number.setText(String.valueOf(saveSpeed));
        Atk_CS.setText(String.valueOf(saveCSAtk));
        Def_CS.setText(String.valueOf(saveCSDef));
        SpAtk_CS.setText(String.valueOf(saveCSSpAtk));
        SpDef_CS.setText(String.valueOf(saveCSSpDef));
        Speed_CS.setText(String.valueOf(savedCSSPeed));
        Init_Number.setText(String.valueOf(saveInit));

        hitPts = saveHitPts;
        csAtk = saveCSAtk;
        csDef = saveCSDef;
        csSpAtk = saveCSSpAtk;
        csSpDef = saveCSSpDef;
        csSpeed = savedCSSPeed;

        weightClass = savedStats.getInt("baseWeightClass",1);
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.Button_Minus_Hit_Points:
                hitPts--;
                Hit_Points_Current_Number.setText(String.valueOf(hitPts));
                break;
            case R.id.Button_Plus_Hit_Points:
                hitPts++;
                Hit_Points_Current_Number.setText(String.valueOf(hitPts));
                break;
            case R.id.Button_Minus_Atk:
                csAtk = Math.max(csAtk - 1, -6);
                Atk_CS.setText(String.valueOf(csAtk));
                Atk_Number.setText(String.valueOf((int)(atk * CStageMod(csAtk))));
                break;
            case R.id.Button_Plus_Atk:
                csAtk = Math.min(csAtk + 1, 6);
                Atk_CS.setText(String.valueOf(csAtk));
                Atk_Number.setText(String.valueOf((int)(atk * CStageMod(csAtk))));
                break;
            case R.id.Button_Minus_Def:
                csDef = Math.max(csDef - 1, -6);
                Def_CS.setText(String.valueOf(csDef));
                Def_Number.setText(String.valueOf((int)(def * CStageMod(csDef))));
                Phy_Eva_Number.setText(String.valueOf((int)((def * CStageMod(csDef)/5))));
                break;
            case R.id.Button_Plus_Def:
                csDef = Math.min(csDef + 1, 6);
                Def_CS.setText(String.valueOf(csDef));
                Def_Number.setText(String.valueOf((int)(def * CStageMod(csDef))));
                Phy_Eva_Number.setText(String.valueOf((int)((def * CStageMod(csDef)/5))));
                break;
            case R.id.Button_Minus_SpAtk:
                csSpAtk = Math.max(csSpAtk - 1, -6);
                SpAtk_CS.setText(String.valueOf(csSpAtk));
                SpAtk_Number.setText(String.valueOf((int)(spAtk * CStageMod(csSpAtk))));
                break;
            case R.id.Button_Plus_SpAtk:
                csSpAtk = Math.min(csSpAtk + 1, 6);
                SpAtk_CS.setText(String.valueOf(csSpAtk));
                SpAtk_Number.setText(String.valueOf((int)(spAtk * CStageMod(csSpAtk))));
                break;
            case R.id.Button_Minus_SpDef:
                csSpDef = Math.max(csSpDef - 1, -6);
                SpDef_CS.setText(String.valueOf(csSpDef));
                SpDef_Number.setText(String.valueOf((int)(spDef * CStageMod(csSpDef))));
                Sp_Eva_Number.setText(String.valueOf((int)((spDef * CStageMod(csSpDef)/5))));
                break;
            case R.id.Button_Plus_SpDef:
                csSpDef = Math.min(csSpDef + 1, 6);
                SpDef_CS.setText(String.valueOf(csSpDef));
                SpDef_Number.setText(String.valueOf((int)(spDef * CStageMod(csSpDef))));
                Sp_Eva_Number.setText(String.valueOf((int)((spDef * CStageMod(csSpDef)/5))));
                break;
            case R.id.Button_Minus_Speed:
                csSpeed = Math.max(csSpeed - 1, -6);
                Speed_CS.setText(String.valueOf(csSpeed));
                Spd_Number.setText(String.valueOf((int)(speed * CStageMod(csSpeed))));
                Speed_Eva_Number.setText(String.valueOf((int)((speed * CStageMod(csSpeed)/5))));
                int currOverlandMinus = overland + (csSpeed / 2);
                int currSwimMinus = swim + (csSpeed / 2);
                int minOverMinus = 2, minSwimMinus = 2;
                if (overland < 2){
                    minOverMinus = overland;
                }
                if (minSwimMinus < 2){
                    minSwimMinus = swim;
                }
                Overland_Number.setText(String.valueOf(Math.max(currOverlandMinus, minOverMinus)));
                Swim_Number.setText(String.valueOf(Math.max((currSwimMinus), minSwimMinus)));
                break;
            case R.id.Button_Plus_Speed:
                csSpeed = Math.min(csSpeed + 1, 6);
                Speed_CS.setText(String.valueOf(csSpeed));
                Spd_Number.setText(String.valueOf((int)(speed * CStageMod(csSpeed))));
                Speed_Eva_Number.setText(String.valueOf((int)((speed * CStageMod(csSpeed)/5))));
                int currOverlandPlus = overland + (csSpeed / 2);
                int currSwimPlus = swim + (csSpeed / 2);
                int minOverPlus = 2, minSwimPlus = 2;
                if (overland < 2){
                    minOverPlus = overland;
                }
                if (minSwimPlus < 2){
                    minSwimPlus = swim;
                }
                Overland_Number.setText(String.valueOf(Math.max(currOverlandPlus, minOverPlus)));
                Swim_Number.setText(String.valueOf(Math.max((currSwimPlus), minSwimPlus)));
                break;
            case R.id.Button_Training:
                String sharedPref = ("poke" + uniqueID + "CS");
                SharedPreferences saveCS = this.getActivity().getSharedPreferences(sharedPref,Context.MODE_PRIVATE);
                SharedPreferences.Editor editorCS = saveCS.edit();

                int saveOverland = Integer.parseInt(Overland_Number.getText().toString());
                int saveSwim = Integer.parseInt(Swim_Number.getText().toString());
                int savePhyEva = Integer.parseInt(Phy_Eva_Number.getText().toString());
                int saveSpEva = Integer.parseInt(Sp_Eva_Number.getText().toString());
                int saveSpdEva = Integer.parseInt(Speed_Eva_Number.getText().toString());
                int saveInit = Integer.parseInt(Init_Number.getText().toString());

                editorCS.putInt("Overland", saveOverland);
                editorCS.putInt("Swim", saveSwim);
                editorCS.putInt("PhyEva", savePhyEva);
                editorCS.putInt("SpEva", saveSpEva);
                editorCS.putInt("SpdEva", saveSpdEva);
                editorCS.putInt("Init", saveInit);
                editorCS.apply();

                Intent intent = new Intent(getContext(), DialogTraining.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.Button_Status_Effects:
                break;
        }
    }

    private float CStageMod(int stage){
        float CSModifier = 1;
        if (stage < 0){
            CSModifier += (float) stage /10;
        };
        if (stage > 0){
            CSModifier += (float) stage /5;
        }
        return CSModifier;
    }

    @Override
    public void onActivityResult(int requestCode, int result, Intent data){
        if (requestCode == 1){
            if (result == Activity.RESULT_OK){
                int resultCode = data.getIntExtra("result", 0);
                if (resultCode == 1) {
                    String sharedPrefCS = ("poke" + uniqueID + "CS");
                    SharedPreferences saveCS = this.getActivity().getSharedPreferences(sharedPrefCS, Context.MODE_PRIVATE);

                    int saveSwim = saveCS.getInt("Swim", swim);
                    int saveOver = saveCS.getInt("Overland", overland);
                    int saveInit = saveCS.getInt("Init", speed);

                    saveSwim++;
                    saveOver++;
                    saveInit += 4;

                    Overland_Number.setText(String.valueOf(saveOver));
                    Swim_Number.setText(String.valueOf(saveSwim));
                    Init_Number.setText(String.valueOf(saveInit));
                } else if (resultCode == 2) {
                    String sharedPrefCS = ("poke" + uniqueID + "CS");
                    SharedPreferences saveCS = this.getActivity().getSharedPreferences(sharedPrefCS, Context.MODE_PRIVATE);

                    int saveSwim = saveCS.getInt("Swim", swim);
                    int saveOver = saveCS.getInt("Overland", overland);
                    int saveInit = saveCS.getInt("Init", speed);

                    saveSwim--;
                    saveOver--;
                    saveInit -= 4;

                    Overland_Number.setText(String.valueOf(saveOver));
                    Swim_Number.setText(String.valueOf(saveSwim));
                    Init_Number.setText(String.valueOf(saveInit));
                } else if (resultCode == 3) {
                    String sharedPrefCS = ("poke" + uniqueID + "CS");
                    SharedPreferences saveCS = this.getActivity().getSharedPreferences(sharedPrefCS, Context.MODE_PRIVATE);

                    int savePhyEva = saveCS.getInt("PhyEva", phyEva);
                    int saveSpEva = saveCS.getInt("SpEva", spEva);
                    int saveSpdEva = saveCS.getInt("SpdEva", speedEva);

                    savePhyEva++;
                    saveSpEva++;
                    saveSpdEva++;

                    Phy_Eva_Number.setText(String.valueOf(savePhyEva));
                    Sp_Eva_Number.setText(String.valueOf(saveSpEva));
                    Speed_Eva_Number.setText(String.valueOf(saveSpdEva));
                } else if (resultCode == 4) {
                    String sharedPrefCS = ("poke" + uniqueID + "CS");
                    SharedPreferences saveCS = this.getActivity().getSharedPreferences(sharedPrefCS, Context.MODE_PRIVATE);

                    int savePhyEva = saveCS.getInt("PhyEva", phyEva);
                    int saveSpEva = saveCS.getInt("SpEva", spEva);
                    int saveSpdEva = saveCS.getInt("SpdEva", speedEva);

                    savePhyEva--;
                    saveSpEva--;
                    saveSpdEva--;

                    Phy_Eva_Number.setText(String.valueOf(savePhyEva));
                    Sp_Eva_Number.setText(String.valueOf(saveSpEva));
                    Speed_Eva_Number.setText(String.valueOf(saveSpdEva));
                } else if (resultCode == 0){

                }
            }
            if (result == Activity.RESULT_CANCELED){
            }
        }
    }

    @Subscribe
    public void onBaseStatsSaved(SendBaseStatsPoke event){
        Hit_Points_Current_Number.setText(String.valueOf(hitPtsMax));
        Overland_Number.setText(String.valueOf(overland));
        Swim_Number.setText(String.valueOf(swim));
        Phy_Eva_Number.setText(String.valueOf(phyEva));
        Sp_Eva_Number.setText(String.valueOf(spEva));
        Speed_Eva_Number.setText(String.valueOf(speedEva));
        Atk_Number.setText(String.valueOf(atk));
        Def_Number.setText(String.valueOf(def));
        SpAtk_Number.setText(String.valueOf(spAtk));
        SpDef_Number.setText(String.valueOf(spDef));
        Spd_Number.setText(String.valueOf(speed));
    }

    @Subscribe
    public void onTrainingReceived(SendTraining event){
        String key = SendTraining.getKey();
        int[] mods = SendTraining.getMods();

        String sharedPrefStats = "savedPoke" + uniqueID + "Stats";
        SharedPreferences savedStats = this.getActivity().getSharedPreferences(sharedPrefStats, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = savedStats.edit();

        int saveOverland = Integer.parseInt(Overland_Number.getText().toString());
        int saveSwim = Integer.parseInt(Swim_Number.getText().toString());
        int savePhyEva = Integer.parseInt(Phy_Eva_Number.getText().toString());
        int saveSpEva = Integer.parseInt(Sp_Eva_Number.getText().toString());
        int saveSpdEva = Integer.parseInt(Speed_Eva_Number.getText().toString());
        int savedInit = Integer.parseInt(Init_Number.getText().toString());

        switch(key) {
            case "a":
                saveOverland += mods[0];
                saveSwim += mods [0];
                savedInit += mods [1];

                editor.putInt("Overland", saveOverland);
                editor.putInt("Swim", saveSwim);
                editor.putInt("Init", savedInit);
                editor.apply();
                Log.d(TAG, "onTrainingReceived: hjvghv");
                break;
            case "i":
                savePhyEva += mods[0];
                saveSpEva += mods [0];
                saveSpdEva += mods[0];

                editor.putInt("PhyEva", savePhyEva);
                editor.putInt("SpEva", saveSpEva);
                editor.putInt("SpdEva", saveSpdEva);
                editor.apply();
                break;
        }
    }

    @Override
    public void onStop(){
        String sharedPref = ("poke" + uniqueID + "CS");
        SharedPreferences saveCS = this.getActivity().getSharedPreferences(sharedPref,Context.MODE_PRIVATE);
        SharedPreferences.Editor editorCS = saveCS.edit();

        int saveHitPts = Integer.parseInt(Hit_Points_Current_Number.getText().toString());
        int saveOverland = Integer.parseInt(Overland_Number.getText().toString());
        int saveSwim = Integer.parseInt(Swim_Number.getText().toString());
        int savePhyEva = Integer.parseInt(Phy_Eva_Number.getText().toString());
        int saveSpEva = Integer.parseInt(Sp_Eva_Number.getText().toString());
        int saveSpdEva = Integer.parseInt(Speed_Eva_Number.getText().toString());
        int saveAtk = Integer.parseInt(Atk_Number.getText().toString());
        int saveDef = Integer.parseInt(Def_Number.getText().toString());
        int saveSpAtk = Integer.parseInt(SpAtk_Number.getText().toString());
        int saveSpDef = Integer.parseInt(SpDef_Number.getText().toString());
        int saveSpeed = Integer.parseInt(Spd_Number.getText().toString());
        int saveCSAtk = Integer.parseInt(Atk_CS.getText().toString());
        int saveCSDef = Integer.parseInt(Def_CS.getText().toString());
        int saveCSSpAtk = Integer.parseInt(SpAtk_CS.getText().toString());
        int saveCSSpDef = Integer.parseInt(SpDef_CS.getText().toString());
        int savedCSSPeed = Integer.parseInt(Speed_CS.getText().toString());

        editorCS.putInt("HitPtsCurrent", saveHitPts);
        editorCS.putInt("Overland", saveOverland);
        editorCS.putInt("Swim", saveSwim);
        editorCS.putInt("PhyEva", savePhyEva);
        editorCS.putInt("SpEva", saveSpEva);
        editorCS.putInt("SpdEva", saveSpdEva);
        editorCS.putInt("Atk", saveAtk);
        editorCS.putInt("Def", saveDef);
        editorCS.putInt("SpAtk", saveSpAtk);
        editorCS.putInt("SpDef", saveSpDef);
        editorCS.putInt("Speed", saveSpeed);
        editorCS.putInt("CSAtk", saveCSAtk);
        editorCS.putInt("CSDef", saveCSDef);
        editorCS.putInt("CSSpAtk", saveCSSpAtk);
        editorCS.putInt("CSSpDef", saveCSSpDef);
        editorCS.putInt("CSSpeed", savedCSSPeed);
        editorCS.apply();

        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
}
