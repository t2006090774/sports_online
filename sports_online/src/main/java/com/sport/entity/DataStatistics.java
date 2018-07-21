package com.sport.entity;

import java.util.List;

import com.sport.entity.base.ColumnsNameAndContent;
import com.sport.entity.base.TitleAndContent;

/**
 * @author a_kai
 */
public class DataStatistics{
	
	private List<TitleAndContent> teamStats;
	private List<ColumnsNameAndContent> playerStats;

	
	public List<TitleAndContent> getTeamStats() {
		return teamStats;
	}
	public void setTeamStats(List<TitleAndContent> teamStats) {
		this.teamStats = teamStats;
	}
	public List<ColumnsNameAndContent> getPlayerStats() {
		return playerStats;
	}
	public void setPlayerStats(List<ColumnsNameAndContent> playerStats) {
		this.playerStats = playerStats;
	}
	
	
}