package com.twu.bean;

import com.twu.entity.HotSearch;

import java.util.ArrayList;
import java.util.Comparator;

public class HotSearches {
    private ArrayList<HotSearch> hotSearches;

    public HotSearches() {
        this.hotSearches = new ArrayList<>();
    }

    public void addHotSearch(String desc, Boolean isSuper) {
        hotSearches.add(new HotSearch(hotSearches.size()+1, desc, 0, isSuper));
    }

    public void addHotSearchTest(String desc, Boolean isSuper, Integer hotValue) {
        hotSearches.add(new HotSearch(hotSearches.size()+1, desc, hotValue, isSuper));
    }

    public void delHotSearch(Integer id) {
        HotSearch choose = null;
        for (HotSearch hotSearch: hotSearches) {
            if (hotSearch.getId().equals(id)) choose = hotSearch;
        }
        hotSearches.remove(choose);
    }

    public ArrayList<HotSearch> getHotSearches() {
        return hotSearches;
    }

    public void setHotSearches(ArrayList<HotSearch> hotSearches) {
        this.hotSearches = hotSearches;
    }

    public void vote(Integer votesNumber, HotSearch hotSearch) {
        hotSearch.vote(votesNumber);
        sortByHotValue();
    }

    public void sortByHotValue() {
        this.hotSearches.sort(new Comparator<HotSearch>() {
            @Override
            public int compare(HotSearch o1, HotSearch o2) {
                return o2.getHotValue() - o1.getHotValue();
            }
        });
    }

    public HotSearch getHotSearchByID(Integer ID) throws Exception {
        for(HotSearch hotSearch: hotSearches) {
            if(hotSearch.getId().equals(ID)) return hotSearch;
        }
        throw new Exception("未找到热搜ID为" + ID + "的热搜");
    }
}
