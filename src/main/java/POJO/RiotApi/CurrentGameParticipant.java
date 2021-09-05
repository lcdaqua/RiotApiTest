package POJO.RiotApi;

import java.util.List;

public class CurrentGameParticipant {
    long championId;
    Perks perks;
    long profileIconId;
    boolean bot;
    long teamId;
    String summonerName;
    String summonerId;
    long spell1Id;
    long spell2Id;
    List<GameCustomizationObject> gameCustomizationObjects;

    public long getChampionId() {
        return championId;
    }

    public Perks getPerks() {
        return perks;
    }

    public long getProfileIconId() {
        return profileIconId;
    }

    public boolean isBot() {
        return bot;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public long getSpell1Id() {
        return spell1Id;
    }

    public long getSpell2Id() {
        return spell2Id;
    }

    public List<GameCustomizationObject> getGameCustomizationObjects() {
        return gameCustomizationObjects;
    }
}
