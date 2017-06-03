package top.tobiaslee.cardemulator;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView c1_front = (ImageView) findViewById(R.id.card1_front);
        ImageView c1_back = (ImageView) findViewById(R.id.card1_back);

        ImageView c2_front = (ImageView) findViewById(R.id.card2_front);
        ImageView c2_back = (ImageView) findViewById(R.id.card2_back);

        ImageView c3_front = (ImageView) findViewById(R.id.card3_front);
        ImageView c3_back = (ImageView) findViewById(R.id.card3_back);

        ImageView c4_front = (ImageView) findViewById(R.id.card4_front);
        ImageView c4_back = (ImageView) findViewById(R.id.card4_back);

        ImageView c5_front = (ImageView) findViewById(R.id.card5_front);
        ImageView c5_back = (ImageView) findViewById(R.id.card5_back);

        c1_front.setImageResource(R.drawable.card1);
        c2_front.setImageResource(R.drawable.card1);
        c3_front.setImageResource(R.drawable.card1);
        c4_front.setImageResource(R.drawable.card1);
        c5_front.setImageResource(R.drawable.card1);


        FlipImageView c1 = new FlipImageView(MainActivity.this,
                c1_front, c1_back);
        c1.readyImage();


        FlipImageView c2 = new FlipImageView(MainActivity.this,
                c2_front, c2_back);
        c2.readyImage();

        FlipImageView c3 = new FlipImageView(MainActivity.this,
                c3_front, c3_back);
        c3.readyImage();

        FlipImageView c4 = new FlipImageView(MainActivity.this,
                c4_front, c4_back);

        c4.readyImage();

        FlipImageView c5 = new FlipImageView(MainActivity.this,
                c5_front, c5_back);
        c5.readyImage();



    }
}
