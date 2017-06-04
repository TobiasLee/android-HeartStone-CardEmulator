package top.tobiaslee.cardemulator;

import android.app.ProgressDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL= "http://192.168.1.193:9999";
    private static final String TAG= "MainActivity";

    private ArrayList<FlipImageView> cards = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loadCard = (Button) findViewById(R.id.load_cards);
        loadCard.setOnClickListener(this);
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

        cards = new ArrayList<>();

        FlipImageView c1 = new FlipImageView(MainActivity.this,
                c1_front, c1_back);
        cards.add(c1);
        c1.readyImage();

        FlipImageView c2 = new FlipImageView(MainActivity.this,
                c2_front, c2_back);
        cards.add(c2);
        c2.readyImage();

        FlipImageView c3 = new FlipImageView(MainActivity.this,
                c3_front, c3_back);
        cards.add(c3);

        c3.readyImage();
        FlipImageView c4 = new FlipImageView(MainActivity.this,
                c4_front, c4_back);
        cards.add(c4);
        c4.readyImage();

        FlipImageView c5 = new FlipImageView(MainActivity.this,
                c5_front, c5_back);
        cards.add(c5);
        c5.readyImage();

    }


    private void getCard() {
//        if(reset) {
//            for (FlipImageView card:
//                 cards) {
//                card.reset();
//            }
//        }
//        final ProgressDialog loading = new ProgressDialog(MainActivity.this);
//        loading.setMessage("加载中");
//        loading.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetCard getCard = retrofit.create(GetCard.class);
        Call<GetCardsResponse> call = getCard.getCard();

        call.enqueue(new Callback<GetCardsResponse>() {
            @Override
            public void onResponse(Call<GetCardsResponse> call, Response<GetCardsResponse> response) {
                if(response.isSuccessful()) {
                    GetCardsResponse cardsRes = response.body();
                    List<GetCardsResponse.CardsBean> cardList = cardsRes.getCards();
                    for (int i = 0; i < cardList.size() ; i++) {
                        Log.d(TAG, "onResponse: " + i);
                        cards.get(i).setImgPath(cardList.get(i).getImg_url());
                        cards.get(i).setCardColor(cardList.get(i).getColor());
                        Log.d(TAG, "onResponse: " + cardList.get(i).getImg_url());
                        Log.d(TAG, "onResponse: " + cardList.get(i).getColor());
                    }
//                    loading.dismiss();
                    Log.d(TAG, "onResponse: " + "finided");
                }
            }

            @Override
            public void onFailure(Call<GetCardsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: " + v.getId());
        switch (v.getId()) {
            case R.id.load_cards:
                getCard();
                break;
            case R.id.card1_front:
                cards.get(0).readyImage();
                break;
            case R.id.card2_front:
                cards.get(1).readyImage();
                break;
            case R.id.card3_front:
                cards.get(2).readyImage();
                break;
            case R.id.card4_front:
                cards.get(3).readyImage();
                break;
            case R.id.card5_front:
                cards.get(4).readyImage();
                break;
            case R.id.reset:
                reset();
                break;
            default:
                break;

        }
    }

    public void reset() {
        for (FlipImageView view:
             cards) {
            view.reset();
        }
    }
}
