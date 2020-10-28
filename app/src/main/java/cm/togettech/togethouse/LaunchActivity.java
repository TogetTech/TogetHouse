package cm.togettech.togethouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Animation ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        Animation stb = AnimationUtils.loadAnimation(this, R.anim.stb);

        Animation btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        Animation btt2 = AnimationUtils.loadAnimation(this, R.anim.btt2);
        Animation btt3 = AnimationUtils.loadAnimation(this, R.anim.btt3);

        Animation btt_next = AnimationUtils.loadAnimation(this, R.anim.btn__next);

        TextView headertitle = findViewById(R.id.headertitle);
        TextView subtitle = findViewById(R.id.subtitle);

        ImageView ic_cards = findViewById(R.id.ic_cards);

        LinearLayout resultOne = findViewById(R.id.resultOne);
        LinearLayout resultTwo = findViewById(R.id.resultTwo);
        LinearLayout resultThree = findViewById(R.id.resultThree);

        Button btn_next = findViewById(R.id.btn_next);

        //Set the animation
        headertitle.startAnimation(ttb);
        subtitle.startAnimation(ttb);

        ic_cards.startAnimation(stb);

        resultOne.startAnimation(btt);
        resultTwo.startAnimation(btt2);
        resultThree.startAnimation(btt3);

        btn_next.startAnimation(btt3);

    }
}