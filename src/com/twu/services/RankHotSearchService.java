package com.twu.services;

import com.twu.bean.HotSearches;
import com.twu.bean.Rank;
import com.twu.entity.HotSearch;
import com.twu.entity.NormalUser;
import com.twu.exceptions.VoteNumberNotEnoughException;

import java.util.ArrayList;

public class RankHotSearchService {
    public static void printRank(HotSearches hotSearches) {
        ArrayList<HotSearch> orderedHotSearcheList = hotSearches.getHotSearches();
        orderedHotSearcheList.forEach(item -> {
            System.out.println(orderedHotSearcheList.indexOf(item) + 1 + " " + item.getDesc() + " 热度为" + item.getHotValue() + " ID为" + item.getId());
        });
    }

    public static void addOneHotSearch(HotSearches hotSearches, String desc) {
        hotSearches.addHotSearch(desc, false);
    }

    public static void addOneSuperHotSearch(HotSearches hotSearches, String desc) {
        hotSearches.addHotSearch(desc, true);
    }

    public static void vote(Integer votesNumber, NormalUser normalUser, HotSearch hotSearch, HotSearches hotSearches) throws VoteNumberNotEnoughException {
        normalUser.vote(votesNumber);
        hotSearches.vote(votesNumber, hotSearch);
    }

    public static void buyHotSearch(Integer money, NormalUser normalUser, Integer rankNumber, HotSearch hotSearch, Rank rank) throws Exception {
        normalUser.buyHotSearch(money);
        try {
            rank.buyHotSearch(money, rankNumber, hotSearch);
        } catch (Exception e) {
            normalUser.rollBackBuyHotSearch(money);
            throw e;
        }
    }
}
