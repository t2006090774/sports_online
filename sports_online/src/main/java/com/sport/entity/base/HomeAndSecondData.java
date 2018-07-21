package com.sport.entity.base;

/**
 * @author a_kai
 */
public class HomeAndSecondData<T> {
	
	private T homeData;
	private T secondData;

    public T getHomeData() {
        return homeData;
    }

    public void setHomeData(T homeData) {
        this.homeData = homeData;
    }

    public T getSecondData() {
        return secondData;
    }

    public void setSecondData(T secondData) {
        this.secondData = secondData;
    }
}