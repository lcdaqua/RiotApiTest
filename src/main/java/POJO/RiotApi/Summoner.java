package POJO.RiotApi;

public class Summoner {
    String id;
    String accountId;
    String puuid;
    String name;
    int profileIconId;
    long revisionDate;
    long summonerLevel;

    public String getAccountId() {
        return accountId;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPuuid() {
        return puuid;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }
}
