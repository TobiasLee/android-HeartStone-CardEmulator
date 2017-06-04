package top.tobiaslee.cardemulator;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL= "http://filthon.ralee.cc:9999";
    private static final String TAG= "MainActivity";

    private ArrayList<FlipImageView> cards = null ;

    @BindView(R.id.card1_front) ImageView c1_front;
    @BindView(R.id.card1_back) ImageView c1_back;

    @BindView(R.id.card2_front) ImageView c2_front;
    @BindView(R.id.card2_back) ImageView c2_back;

    @BindView(R.id.card3_front) ImageView c3_front;
    @BindView(R.id.card3_back) ImageView c3_back;

    @BindView(R.id.card4_front) ImageView c4_front;
    @BindView(R.id.card4_back) ImageView c4_back;

    @BindView(R.id.card5_front) ImageView c5_front;
    @BindView(R.id.card5_back) ImageView c5_back;

    @BindView(R.id.load_cards) Button loadCard;
    @OnClick(R.id.load_cards) void load (){
        reset();
        getCard();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        c1_front.setImageResource(R.drawable.card_back);
        c2_front.setImageResource(R.drawable.card_back);
        c3_front.setImageResource(R.drawable.card_back);
        c4_front.setImageResource(R.drawable.card_back);
        c5_front.setImageResource(R.drawable.card_back);

        cards = new ArrayList<>();

        FlipImageView c1 = new FlipImageView(MainActivity.this,
                c1_front, c1_back);
        cards.add(c1);

        FlipImageView c2 = new FlipImageView(MainActivity.this,
                c2_front, c2_back);
        cards.add(c2);

        FlipImageView c3 = new FlipImageView(MainActivity.this,
                c3_front, c3_back);
        cards.add(c3);

        FlipImageView c4 = new FlipImageView(MainActivity.this,
                c4_front, c4_back);
        cards.add(c4);

        FlipImageView c5 = new FlipImageView(MainActivity.this,
                c5_front, c5_back);
        cards.add(c5);
    }


    private void getCard() {

        final ProgressDialog loading = new ProgressDialog(MainActivity.this);
        loading.setMessage("加载中");
        loading.show();
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
                        cards.get(i).setImgPath(cardList.get(i).getImg_url());
                        cards.get(i).setCardColor(cardList.get(i).getColor());
                        cards.get(i).readyImage();
                        Log.d(TAG, "url " + cardList.get(i).getImg_url());
                        Log.d(TAG, "color " + cardList.get(i).getColor());
                    }
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<GetCardsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                loading.dismiss();
                Toast.makeText(MainActivity.this, "加载失败 请重试", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
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
