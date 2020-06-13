package vitruvian.ptu_trainer_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class CombatStagesTrainer extends Fragment implements View.OnClickListener{
    public static final String ARG_PAGE = "ARG_PAGE";

    private TextView Hit_Points_Current_Number;
    private TextView Overland_Number;
    private TextView Swim_Number;
    private TextView Phy_Eva_Number;
    private TextView Sp_Eva_Number;
    private TextView Speed_Eva_Number;
    private TextView Atk_Number;
    private TextView Def_Number, SpAtk_Number, SpDef_Number, Spd_Number, Atk_CS, Def_CS, SpAtk_CS, SpDef_CS, Speed_CS;
    private int hitPts;
    private int overland;
    private int swim;
    private int phyEva;
    private int spEva;
    private int speedEva;
    private int atk;
    private int def;
    private int spAtk;
    private int spDef;
    private int speed;
    private int csAtk;
    private int csDef;
    private int csSpAtk;
    private int csSpDef;
    private int csSpeed;
    private int hitPtsMax, weightClass, power, throwingRange, longJump, highJump;

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
        View view = inflater.inflate(R.layout.fragment_combat_stages_trainer, container, false);
        return view;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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
        Button button_Phys_Att = (Button) view.findViewById(R.id.Button_Phys_Att);
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
        button_Phys_Att.setOnClickListener(this);
        button_Status_Effects.setOnClickListener(this);

        Hit_Points_Current_Number = (TextView) view.findViewById(R.id.Hit_Points_Current_Number);
        TextView hit_Points_Max_Number = (TextView) view.findViewById(R.id.Hit_Points_Max_Number);
        Overland_Number = (TextView) view.findViewById(R.id.Overland_Number);
        Swim_Number = (TextView) view.findViewById(R.id.Swim_Number);
        Phy_Eva_Number = (TextView) view.findViewById(R.id.Phy_Eva_Number);
        Sp_Eva_Number = (TextView) view.findViewById(R.id.Sp_Eva_Number);
        Speed_Eva_Number = (TextView) view.findViewById(R.id.Speed_Eva_Number);
        TextView HP_Number = (TextView) view.findViewById(R.id.HP_Number);
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

        SharedPreferences savedTrainerStats = this.getActivity().getSharedPreferences("savedTrainerStats", Context.MODE_PRIVATE);

        int lvl = savedTrainerStats.getInt("baseLevel", 0);

        int HP = savedTrainerStats.getInt(getString(R.string.baseHP), 0);
        hitPtsMax = (10 + (lvl * 2) + (HP * 3));
        hit_Points_Max_Number.setText(String.valueOf(hitPtsMax));

        int ath = savedTrainerStats.getInt(getString(R.string.baseAth), 0);
        int acro = savedTrainerStats.getInt(getString(R.string.baseAcro), 0);

        overland = 3 + ((ath + acro) / 2);
        swim = overland / 2;

        def = savedTrainerStats.getInt(getString(R.string.baseDef), 0);
        phyEva = def / 5;
        spDef = savedTrainerStats.getInt(getString(R.string.baseSpDef), 0);
        spEva = spDef / 5;
        speed = savedTrainerStats.getInt(getString(R.string.baseSpeed), 0);
        speedEva = speed / 5;

        atk = savedTrainerStats.getInt(getString(R.string.baseAtk), 0);
        spAtk = savedTrainerStats.getInt(getString(R.string.baseSpAtk), 0);
        HP_Number.setText(String.valueOf(HP));

        SharedPreferences saveCS = this.getActivity().getSharedPreferences(getString(R.string.trainerCS), Context.MODE_PRIVATE);
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

        Hit_Points_Current_Number.setText(String.valueOf(saveHitPts));
        Overland_Number.setText(String.valueOf(saveOverland));
        Swim_Number.setText(String.valueOf(saveSwim));
        Phy_Eva_Number.setText(String.valueOf(savePhyEva));
        Sp_Eva_Number.setText(String.valueOf(saveSpEva));
        Speed_Eva_Number.setText(String.valueOf(saveSpdEva));
        Atk_Number.setText(String.valueOf((int)saveAtk));
        Def_Number.setText(String.valueOf(saveDef));
        SpAtk_Number.setText(String.valueOf(saveSpAtk));
        SpDef_Number.setText(String.valueOf(saveSpDef));
        Spd_Number.setText(String.valueOf(saveSpeed));
        Atk_CS.setText(String.valueOf(saveCSAtk));
        Def_CS.setText(String.valueOf(saveCSDef));
        SpAtk_CS.setText(String.valueOf(saveCSSpAtk));
        SpDef_CS.setText(String.valueOf(saveCSSpDef));
        Speed_CS.setText(String.valueOf(savedCSSPeed));

        hitPts = saveHitPts;
        csAtk = saveCSAtk;
        csDef = saveCSDef;
        csSpAtk = saveCSSpAtk;
        csSpDef = saveCSSpDef;
        csSpeed = savedCSSPeed;

        int weight = savedTrainerStats.getInt(getString(R.string.baseWeight),0);
        weightClass = 1;
        if (weight > 11 && weight < 26){
            weightClass = 2;
        }
        else if (weight > 25 && weight < 51){
            weightClass = 3;
        }
        else if (weight > 50 && weight < 101){
            weightClass = 4;
        }
        else if (weight > 100 && weight < 201){
            weightClass = 5;
        }
        else if (weight > 200){
            weightClass = 6;
        }

        int comb = savedTrainerStats.getInt(getString(R.string.baseComb),0);
        int basePower = 4;
        int modPower = 0;
        if (ath > 2){modPower += 1;}
        if (comb > 3){modPower += 1;}
        power = basePower + modPower;

        throwingRange = 4 + ath;
        longJump = acro / 2;

        int baseHighJump = 0;
        int modHighJump = 0;
        if (acro > 3){modHighJump += 1;}
        if (acro > 5){modHighJump += 1;}
        highJump = baseHighJump + modHighJump;
    }

    @Override
    public void
    onClick(View v){

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
                int minOverMinus = 2, minSwimMinus = 2;
                if (overland < 2){
                    minOverMinus = overland;
                }
                if (minSwimMinus < 2){
                    minSwimMinus = swim;
                }
                Overland_Number.setText(String.valueOf(Math.max(currOverlandMinus, minOverMinus)));
                Swim_Number.setText(String.valueOf(Math.max((currOverlandMinus / 2), minSwimMinus)));
                break;
            case R.id.Button_Plus_Speed:
                csSpeed = Math.min(csSpeed + 1, 6);
                Speed_CS.setText(String.valueOf(csSpeed));
                Spd_Number.setText(String.valueOf((int)(speed * CStageMod(csSpeed))));
                Speed_Eva_Number.setText(String.valueOf((int)((speed * CStageMod(csSpeed)/5))));
                int currOverlandPlus = overland + (csSpeed / 2);
                int minOverPlus = 2, minSwimPlus = 2;
                if (overland < 2){
                    minOverPlus = overland;
                }
                if (minSwimPlus < 2){
                    minSwimPlus = swim;
                }
                Overland_Number.setText(String.valueOf(Math.max(currOverlandPlus, minOverPlus)));
                Swim_Number.setText(String.valueOf(Math.max((currOverlandPlus / 2), minSwimPlus)));
                break;
            case R.id.Button_Phys_Att:
                Context context = getContext();
                LayoutInflater inflater = LayoutInflater.from(getActivity());

                View customToastRoot = inflater.inflate(R.layout.toast_phys_att_combat,null);
                Toast customToast = new Toast(context);

                TextView Weight_Class_Number = (TextView) customToastRoot.findViewById(R.id.Weight_Class_Number);
                TextView Power_Number = (TextView) customToastRoot.findViewById(R.id.Power_Number);
                TextView Throwing_Range_Number = (TextView) customToastRoot.findViewById(R.id.Throwing_Range_Number);
                TextView Jump_L_Number = (TextView) customToastRoot.findViewById(R.id.Jump_L_Number);
                TextView Jump_H_Number = (TextView) customToastRoot.findViewById(R.id.Jump_H_Number);

                Weight_Class_Number.setText(String.valueOf(weightClass));
                Power_Number.setText(String.valueOf(power));
                Throwing_Range_Number.setText(String.valueOf(throwingRange));
                Jump_L_Number.setText(String.valueOf(longJump));
                Jump_H_Number.setText(String.valueOf(highJump));

                customToast.setView(customToastRoot);
                customToast.setGravity(Gravity.CENTER,0,0);
                customToast.setDuration(Toast.LENGTH_LONG);
                customToast.show();
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

    @Subscribe
    public void onBaseStatsSaved(SendBaseStatsTrainer event){
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

    @Override
    public void onStop(){
        SharedPreferences saveCS = this.getActivity().getSharedPreferences(getString(R.string.trainerCS),Context.MODE_PRIVATE);
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
