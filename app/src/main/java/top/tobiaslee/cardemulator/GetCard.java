package top.tobiaslee.cardemulator;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tobiaslee on 2017/6/4.
 */

public interface GetCard {
    @GET("get_cards")
    Call<GetCardsResponse> getCard();
}
