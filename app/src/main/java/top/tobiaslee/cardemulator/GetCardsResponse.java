package top.tobiaslee.cardemulator;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tobiaslee on 2017/6/4.
 */

public class GetCardsResponse {
    private List<CardsBean> cards;

    public List<CardsBean> getCards() {
        return cards;
    }

    public void setCards(List<CardsBean> cards) {
        this.cards = cards;
    }

    public static class CardsBean {
        /**
         * name : A-3型机械金刚
         * color : 0
         * skills : Battlecry(战吼：卡牌上场时立即生效的效果)Discover（发现：随机展示中立和本职业符合条件的三张卡牌，选择其中一项加入手牌）
         * img_url : http://img.dwstatic.com/ls/pic/card/Gorillabot A3.png
         * dscrb : 战吼：如果你控制任何机械，则发现一张机械牌
         * class : 中立
         * type : 随从
         * cost : 4
         * attack : 3
         * health : 4
         */

        private String name;
        private int color;
        private String skills;
        private String img_url;
        private String dscrb;
        @SerializedName("class")
        private String classX;
        private String type;
        private int cost;
        private int attack;
        private int health;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getSkills() {
            return skills;
        }

        public void setSkills(String skills) {
            this.skills = skills;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getDscrb() {
            return dscrb;
        }

        public void setDscrb(String dscrb) {
            this.dscrb = dscrb;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getAttack() {
            return attack;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }
    }
}
