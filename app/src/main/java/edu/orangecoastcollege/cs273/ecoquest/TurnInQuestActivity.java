package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TurnInQuestActivity extends AppCompatActivity {

    private Animation shrinkGrowAnimation;
    private Animation spinAnimation;
    private Animation plusOneAnimation;

    private ImageView dogPooAddButton;
    private ImageView pooPlusOneIcon;

    private ImageView cigButtAddButton;
    private ImageView cigPlusOneIcon;

    private ImageView coffeeCupAddButton;
    private ImageView coffeeCupPlusOneIcon;

    private ImageView plasticBottleAddButton;
    private ImageView plasticBottlePlusOneIcon;

    private ImageView aluminumCanAddButton;
    private ImageView aluminumCanPlusOneIcon;

    private ImageView drinkCupAddButton;
    private ImageView drinkCupPlusOneIcon;

    private ImageView scrapeGumAddButton;
    private ImageView scrapGumPlusOneIcon;

    private ImageView miscAddButton;
    private ImageView miscPlusOneIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_in_quest);

        dogPooAddButton = (ImageView) findViewById(R.id.dogPooAddButton);
        pooPlusOneIcon = (ImageView) findViewById(R.id.pooPlus1Icon);

        cigButtAddButton = (ImageView) findViewById(R.id.cigButtAddButton);
        cigPlusOneIcon = (ImageView) findViewById(R.id.cigPlus1Icon);

        coffeeCupAddButton = (ImageView) findViewById(R.id.coffeeCupButton);
        coffeeCupPlusOneIcon = (ImageView) findViewById(R.id.coffeeCupPlus1Icon);

        plasticBottleAddButton = (ImageView) findViewById(R.id.plasticBottleAddButton);
        plasticBottlePlusOneIcon = (ImageView) findViewById(R.id.plasticBottlePlus1Icon);

        aluminumCanAddButton = (ImageView) findViewById(R.id.aluminumCanAddButton);
        aluminumCanPlusOneIcon = (ImageView) findViewById(R.id.aluminumCanPlus1Icon);

        drinkCupAddButton = (ImageView) findViewById(R.id.drinkCupAddButton);
        drinkCupPlusOneIcon = (ImageView) findViewById(R.id.drinkCupPlus1Icon);

        scrapeGumAddButton = (ImageView) findViewById(R.id.scrapeGumAddButton);
        scrapGumPlusOneIcon = (ImageView) findViewById(R.id.scrapeGumPlus1Icon);

        miscAddButton = (ImageView) findViewById(R.id.miscAddButton);
        miscPlusOneIcon = (ImageView) findViewById(R.id.miscPlus1Icon);
    }


    public void spinAnimation(View view)
    {
        spinAnimation = AnimationUtils.loadAnimation(this, R.anim.spin_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        dogPooAddButton.startAnimation(spinAnimation);
        pooPlusOneIcon.startAnimation(plusOneAnimation);
    }

    public void shrinkGrowAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        cigButtAddButton.startAnimation(shrinkGrowAnimation);
        cigPlusOneIcon.startAnimation(plusOneAnimation);
    }

    public void coffeeCupPlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        coffeeCupAddButton.startAnimation(shrinkGrowAnimation);
        coffeeCupPlusOneIcon.startAnimation(plusOneAnimation);
    }

    public void plasticBottlePlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        plasticBottleAddButton.startAnimation(shrinkGrowAnimation);
        plasticBottlePlusOneIcon.startAnimation(plusOneAnimation);
    }

    public void aluminumCanPlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        aluminumCanAddButton.startAnimation(shrinkGrowAnimation);
        aluminumCanPlusOneIcon.startAnimation(plusOneAnimation);
    }

    public void drinkCupPlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        drinkCupAddButton.startAnimation(shrinkGrowAnimation);
        drinkCupPlusOneIcon.startAnimation(plusOneAnimation);
    }

    public void scrapeGumPlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        scrapeGumAddButton.startAnimation(shrinkGrowAnimation);
        scrapGumPlusOneIcon.startAnimation(plusOneAnimation);
    }

    public void miscPlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        miscAddButton.startAnimation(shrinkGrowAnimation);
        miscPlusOneIcon.startAnimation(plusOneAnimation);
    }
}
