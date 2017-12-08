package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * TurnInQuestActivity is the controller for the Activity where users can add
 * various trash or recycle items.
 *
 * The member variables are three types of Animations:
 *  a shrinkGrow animation which makes it look like the buttons are pressed,
 *  a spinAnimation for the dog poo button, simply for variety,
 *  and finally the plusOne animation which provides a neat visual alert
 *  to the user that they incremented that particular trash or recycle item.
 */
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

    /**
     * onCreate()'s only task in this Activity is setting the content view
     * and wiring up all the ImageViews.
     * @param savedInstanceState
     */
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


    /**
     * spinAnimation is called when the user taps the poo plus button.
     *
     * the animations must be individually loaded in each method because we are using
     * 'this' context.  I tested extracting the load animations into onCreate but
     * then the plus one animation fires on any other buttons that were recently
     * pressed.  This was definitely not the desired effect.
     *
     * the spin and plus one animations are loaded from their respective xml files.
     * the spin animation is started on the plus button,
     * and nearly simultaneously, the plus one icon animation starts on the +1 icon.
     * @param view
     */
    public void spinAnimation(View view)
    {
        spinAnimation = AnimationUtils.loadAnimation(this, R.anim.spin_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        dogPooAddButton.startAnimation(spinAnimation);
        pooPlusOneIcon.startAnimation(plusOneAnimation);
    }

    /**
     * shrinkGrowAnimation is called when the user taps the cigarette butt plus button
     *
     * the shrinkGrow and plus one animations are loaded from their respective xml files.
     * the shrinkGrow animation is started on the plus button,
     * and nearly simultaneously, the plus one icon animation starts on the +1 icon.
     * @param view
     */
    public void shrinkGrowAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        cigButtAddButton.startAnimation(shrinkGrowAnimation);
        cigPlusOneIcon.startAnimation(plusOneAnimation);
    }

    /**
     * coffeeCupPlusOneAnimation is called when the user taps the coffee cup plus button
     *
     * the shrinkGrow and plus one animations are loaded from their respective xml files.
     * the shrinkGrow animation is started on the plus button,
     * and nearly simultaneously, the plus one icon animation starts on the +1 icon.
     * @param view
     */
    public void coffeeCupPlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        coffeeCupAddButton.startAnimation(shrinkGrowAnimation);
        coffeeCupPlusOneIcon.startAnimation(plusOneAnimation);
    }

    /**
     * plasticBottlePlusOneAnimation is called when the user taps the plastic bottle plus button
     *
     * * the shrinkGrow and plus one animations are loaded from their respective xml files.
     * the shrinkGrow animation is started on the plus button,
     * and nearly simultaneously, the plus one icon animation starts on the +1 icon.
     * @param view
     */
    public void plasticBottlePlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        plasticBottleAddButton.startAnimation(shrinkGrowAnimation);
        plasticBottlePlusOneIcon.startAnimation(plusOneAnimation);
    }

    /**
     * aluminumCanPlusOneAnimation is called when the user taps the aluminum can plus button
     *
     * the shrinkGrow and plus one animations are loaded from their respective xml files.
     * the shrinkGrow animation is started on the plus button,
     * and nearly simultaneously, the plus one icon animation starts on the +1 icon.
     * @param view
     */
    public void aluminumCanPlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        aluminumCanAddButton.startAnimation(shrinkGrowAnimation);
        aluminumCanPlusOneIcon.startAnimation(plusOneAnimation);
    }

    /**
     * drinkCupPlusOneAnimation is called when the user taps the drink cup plus button
     *
     * the shrinkGrow and plus one animations are loaded from their respective xml files.
     * the shrinkGrow animation is started on the plus button,
     * and nearly simultaneously, the plus one icon animation starts on the +1 icon.
     * @param view
     */
    public void drinkCupPlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        drinkCupAddButton.startAnimation(shrinkGrowAnimation);
        drinkCupPlusOneIcon.startAnimation(plusOneAnimation);
    }

    /**
     * scrapeGumPlusOneAnimation is called when the user taps the scrape gum plus button
     *
     * the shrinkGrow and plus one animations are loaded from their respective xml files.
     * the shrinkGrow animation is started on the plus button,
     * and nearly simultaneously, the plus one icon animation starts on the +1 icon.
     * @param view
     */
    public void scrapeGumPlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        scrapeGumAddButton.startAnimation(shrinkGrowAnimation);
        scrapGumPlusOneIcon.startAnimation(plusOneAnimation);
    }

    /**
     * miscPlusOneAnimation is called when the user taps the miscellaneous plus button
     *
     * the shrinkGrow and plus one animations are loaded from their respective xml files.
     * the shrinkGrow animation is started on the plus button,
     * and nearly simultaneously, the plus one icon animation starts on the +1 icon.
     * @param view
     */
    public void miscPlusOneAnimation(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
        miscAddButton.startAnimation(shrinkGrowAnimation);
        miscPlusOneIcon.startAnimation(plusOneAnimation);
    }
}
