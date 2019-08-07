package com.lottchina.xdbao.protocol.message.order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PoolCondBean {
        /**
         * game_id : {"$in":[201,200]}
         */

        private GameIdBean game_id;

        public GameIdBean getGame_id() {
            return game_id;
        }

        public void setGame_id(GameIdBean game_id) {
            this.game_id = game_id;
        }

        public static class GameIdBean {
            @SerializedName("$in")
            private List<Integer> listGames;

            public List<Integer> getListGames() {
                return listGames;
            }

            public void setListGames(List<Integer> listGames) {
                this.listGames = listGames;
            }
        }
    }