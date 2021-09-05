package POJO.RiotApi;

import java.util.List;

public class CurrentGameInfo {
    long gameId;
    String gameType;
    long gameStartTime;
    long mapId;
    long gameLength;
    String platformId;
    String gameMode;
    List <BannedChampion> bannedChampions;
    long gameQueueConfigId;
    Observer observers;
    List<CurrentGameParticipant> participants;

    public long getGameId() {
        return gameId;
    }

    public String getGameType() {
        return gameType;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public long getMapId() {
        return mapId;
    }

    public long getGameLength() {
        return gameLength;
    }

    public String getPlatformId() {
        return platformId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public long getGameQueueConfigId() {
        return gameQueueConfigId;
    }

    public Observer getObservers() {
        return observers;
    }

    public List<CurrentGameParticipant> getParticipants() {
        return participants;
    }
}
