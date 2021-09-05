package POJO.RiotApi;

public class Champion {

    int championId;
    int championLevel;
    int championPoints;
    long lastPlayTime;
    int championPointsSinceLastLevel;
    int championPointsUntilNextLevel;
    boolean chestGranted;
    int tokensEarned;
    String summonerId;

    public int getChampionId() {
        return championId;
    }

    public int getChampionLevel() {
        return championLevel;
    }

    public int getChampionPoints() {
        return championPoints;
    }

    public long getLastPlayTime() {
        return lastPlayTime;
    }

    public int getChampionPointsSinceLastLevel() {
        return championPointsSinceLastLevel;
    }

    public int getChampionPointsUntilNextLevel() {
        return championPointsUntilNextLevel;
    }

    public boolean isChestGranted() {
        return chestGranted;
    }

    public int getTokensEarned() {
        return tokensEarned;
    }

    public String getSummonerId() {
        return summonerId;
    }
}
