package com.twu.bean;

import com.twu.entity.HotSearch;

import java.util.ArrayList;
import java.util.Map;

public class Rank {
    private HotSearches hotSearches;
    private Map<Integer, Integer> rankNumberAndValue;

    public Rank(HotSearches hotSearches, Map<Integer, Integer> rankNumberAndValue) {
        this.hotSearches = hotSearches;
        this.rankNumberAndValue = rankNumberAndValue;
    }

    public HotSearches getHotSearches() {
        return hotSearches;
    }

    public void setHotSearches(HotSearches hotSearches) {
        this.hotSearches = hotSearches;
    }

    public Map<Integer, Integer> getRankNumberAndValue() {
        return rankNumberAndValue;
    }

    public void setRankNumberAndValue(Map<Integer, Integer> rankNumberAndValue) {
        this.rankNumberAndValue = rankNumberAndValue;
    }

    public void buyHotSearch(Integer money, Integer rankNumber, HotSearch hotSearch) throws Exception {
        if (rankNumber > this.hotSearches.getHotSearches().size()) throw new Exception("排行超出范围，当前最低排行为" + this.hotSearches.getHotSearches().size());
        if (!this.rankNumberAndValue.containsKey(rankNumber)) {
            buyHotSearch(rankNumber, hotSearch);
            this.rankNumberAndValue.put(rankNumber, money);
        } else {
            if (money > this.rankNumberAndValue.get(rankNumber)) {
                buyHotSearch(rankNumber, hotSearch);
            } else {
                throw new Exception("您的出价不足，当前排行价值为" + this.rankNumberAndValue.get(rankNumber));
            }
        }
    }

    private void buyHotSearch(Integer rankNumber, HotSearch hotSearch) {
        HotSearch oldHotSearch = hotSearches.getHotSearches().get(rankNumber-1);
        hotSearch.setHotValue(oldHotSearch.getHotValue());
        hotSearches.delHotSearch(oldHotSearch.getId());
        hotSearches.sortByHotValue();
    }
}
