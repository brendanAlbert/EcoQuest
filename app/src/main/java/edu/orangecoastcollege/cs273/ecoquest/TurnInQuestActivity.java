package edu.orangecoastcollege.cs273.ecoquest;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

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

    private int poosCollected;
    private int buttsCollected;
    private int coffeeCupsCollected;
    private int plasticBottlesCollected;

    // TODO: Add functionality for the demo to demonstrate what earning a badge will look like.
    // TODO: When the user taps a button enough times, trigger the badge earned splash
    // TODO: Use the manning.com add a book as a reference, white text, part of the text zooms in from the right, and a 2-3 second duration timer
    private Animation badgeEarnedAnimation;
    private Animation textZoomLeftAnimation;
    private Animation badgeFadeInZoomLeftAnimation;

    private ImageView badgeEarnedImageView;
    private ScrollView questTurnInScrollView;
    private TextView badgeEarnedTextView;
    private TextView badgeNameTextView;
    private ImageView whichBadgeUserEarnedImageView;

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

        badgeEarnedImageView = (ImageView) findViewById(R.id.badgeEarnedImageView);
        questTurnInScrollView = (ScrollView) findViewById(R.id.questTurnInScrollView);
        badgeEarnedTextView = (TextView) findViewById(R.id.badgeEarnedTextView);
        badgeNameTextView = (TextView) findViewById(R.id.badgeNameTextView);
        dogPooAddButton = (ImageView) findViewById(R.id.dogPooAddButton);
        pooPlusOneIcon = (ImageView) findViewById(R.id.pooPlus1Icon);
        whichBadgeUserEarnedImageView = (ImageView) findViewById(R.id.whichBadgeUserEarnedImageView);

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
        poosCollected++;
        spinAnimation = AnimationUtils.loadAnimation(this, R.anim.spin_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);

        badgeEarnedAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.badge_earned_anim);
        textZoomLeftAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.text_zoom_left_anim);
        badgeFadeInZoomLeftAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.badge_fade_in_zoom_left_anim);

        badgeEarnedImageView.getLayoutParams().width = questTurnInScrollView.getWidth();
        badgeEarnedImageView.getLayoutParams().height = questTurnInScrollView.getHeight();
        badgeEarnedImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        AssetManager am = this.getAssets();
        InputStream stream;
        Drawable image;
        try {

            switch (poosCollected) {
                case 1:
                    stream = am.open("meadow_muffin_knight.png");
                    image = Drawable.createFromStream(stream, "meadow_muffin_knight.png");
                    badgeNameTextView.setText("meadow muffin knight");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 5:
                    // earned turd templar
                    stream = am.open("turd_templar.png");
                    image = Drawable.createFromStream(stream, "turd_templar.png");
                    badgeNameTextView.setText("turd templar");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 10:
                    // earned poopoo paladin templar
                    stream = am.open("poopoo_paladin.png");
                    image = Drawable.createFromStream(stream, "poopoo_paladin.png");
                    badgeNameTextView.setText("poopoo paladin");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 25:
                    // earned chevalier du caca
                    stream = am.open("chevalier_du_caca.png");
                    image = Drawable.createFromStream(stream, "chevalier_du_caca.png");
                    badgeNameTextView.setText("chevalier du caca");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                default:
                    dogPooAddButton.startAnimation(spinAnimation);
                    pooPlusOneIcon.startAnimation(plusOneAnimation);
                    break;
            }
        } catch (IOException e) { e.printStackTrace(); }
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
        buttsCollected++;
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);

        badgeEarnedAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.badge_earned_anim);
        textZoomLeftAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.text_zoom_left_anim);
        badgeFadeInZoomLeftAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.badge_fade_in_zoom_left_anim);

        badgeEarnedImageView.getLayoutParams().width = questTurnInScrollView.getWidth();
        badgeEarnedImageView.getLayoutParams().height = questTurnInScrollView.getHeight();
        badgeEarnedImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        AssetManager am = this.getAssets();
        InputStream stream;
        Drawable image;
        try {

            switch (buttsCollected) {
                case 5:
                    stream = am.open("journeyman_buttcollector.png");
                    image = Drawable.createFromStream(stream, "journeyman_buttcollector.png");
                    badgeNameTextView.setText("noob butt collector");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 10:
                    stream = am.open("squire_buttcollector.png");
                    image = Drawable.createFromStream(stream, "squire_buttcollector.png");
                    badgeNameTextView.setText("squire butt collector");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 25:
                    stream = am.open("adept_buttcollector.png");
                    image = Drawable.createFromStream(stream, "adept_buttcollector.png");
                    badgeNameTextView.setText("adept butt collector");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 50:
                    stream = am.open("instructor_buttcollector.png");
                    image = Drawable.createFromStream(stream, "instructor_buttcollector.png");
                    badgeNameTextView.setText("instructor butt collector");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 100:
                    stream = am.open("master_buttcollector.png");
                    image = Drawable.createFromStream(stream, "master_buttcollector.png");
                    badgeNameTextView.setText("master butt collector");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                default:
                    cigButtAddButton.startAnimation(shrinkGrowAnimation);
                    cigPlusOneIcon.startAnimation(plusOneAnimation);
                    break;
            }
        } catch (IOException e) { e.printStackTrace(); }

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
        coffeeCupsCollected++;
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);

        badgeEarnedAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.badge_earned_anim);
        textZoomLeftAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.text_zoom_left_anim);
        badgeFadeInZoomLeftAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.badge_fade_in_zoom_left_anim);

        badgeEarnedImageView.getLayoutParams().width = questTurnInScrollView.getWidth();
        badgeEarnedImageView.getLayoutParams().height = questTurnInScrollView.getHeight();
        badgeEarnedImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        AssetManager am = this.getAssets();
        InputStream stream;
        Drawable image;
        try {

            switch (coffeeCupsCollected) {
                case 1:
                    stream = am.open("lvl1_cup_collector.png");
                    image = Drawable.createFromStream(stream, "lvl1_cup_collector.png");
                    badgeNameTextView.setText("level 1\ncup collector");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 5:
                    stream = am.open("lvl2_cup_collector.png");
                    image = Drawable.createFromStream(stream, "lvl2_cup_collector.png");
                    badgeNameTextView.setText("level 2\ncup collector");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 10:
                    stream = am.open("lvl3_cup_collector.png");
                    image = Drawable.createFromStream(stream, "lvl3_cup_collector.png");
                    badgeNameTextView.setText("level 3\ncup collector");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 20:
                    stream = am.open("lvl4_cup_collector.png");
                    image = Drawable.createFromStream(stream, "lvl4_cup_collector.png");
                    badgeNameTextView.setText("level 4\ncup collector");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                default:
                    coffeeCupAddButton.startAnimation(shrinkGrowAnimation);
                    coffeeCupPlusOneIcon.startAnimation(plusOneAnimation);
                    break;
            }
        } catch (IOException e) { e.printStackTrace(); }
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
        plasticBottlesCollected++;
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);

        badgeEarnedAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.badge_earned_anim);
        textZoomLeftAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.text_zoom_left_anim);
        badgeFadeInZoomLeftAnimation = AnimationUtils.loadAnimation(TurnInQuestActivity.this, R.anim.badge_fade_in_zoom_left_anim);

        badgeEarnedImageView.getLayoutParams().width = questTurnInScrollView.getWidth();
        badgeEarnedImageView.getLayoutParams().height = questTurnInScrollView.getHeight();
        badgeEarnedImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        AssetManager am = this.getAssets();
        InputStream stream;
        Drawable image;
        try {

            switch (plasticBottlesCollected) {
                case 5:
                    stream = am.open("level1_waterbottle_recycler.png");
                    image = Drawable.createFromStream(stream, "level1_waterbottle_recycler.png");
                    badgeNameTextView.setText("level 1\nwater bottle recycler");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 15:
                    stream = am.open("level2_waterbottle_recycler.png");
                    image = Drawable.createFromStream(stream, "level2_waterbottle_recycler.png");
                    badgeNameTextView.setText("level 2\nwater bottle recycler");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                case 30:
                    stream = am.open("level3_waterbottle_recycler.png");
                    image = Drawable.createFromStream(stream, "level3_waterbottle_recycler.png");
                    badgeNameTextView.setText("level 3\nwater bottle recycler");
                    whichBadgeUserEarnedImageView.setImageDrawable(image);
                    badgeEarnedImageView.startAnimation(badgeEarnedAnimation);
                    badgeEarnedTextView.startAnimation(badgeEarnedAnimation);
                    badgeNameTextView.startAnimation(textZoomLeftAnimation);
                    whichBadgeUserEarnedImageView.startAnimation(badgeFadeInZoomLeftAnimation);
                    break;
                default:
                    plasticBottleAddButton.startAnimation(shrinkGrowAnimation);
                    plasticBottlePlusOneIcon.startAnimation(plusOneAnimation);
                    break;
            }
        } catch (IOException e) { e.printStackTrace(); }

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
