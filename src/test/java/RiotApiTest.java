import POJO.RiotApi.Champion;
import POJO.RiotApi.CurrentGameInfo;
import POJO.RiotApi.CurrentGameParticipant;
import POJO.RiotApi.Summoner;
import Steps.Steps;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RiotApiTest {
    String baseUrlEuw = "https://euw1.api.riotgames.com";
    String baseUrlRu = "https://ru.api.riotgames.com";
    String apiKey = "?api_key=RGAPI-15803bde-8517-4c7b-96f4-86d406625188";
    String activeGameBySummonerId = "/lol/spectator/v4/active-games/by-summoner/";
    String summonerBySummonerId = "/lol/summoner/v4/summoners/";
    String summonerBySummonerName = "/lol/summoner/v4/summoners/by-name/";

    String summonerIdEuw = "p8fr9wGJMq_hvD4JjjcBwKmuw1muL_T9i9Zzd2HVq6NUjow";
    String summonerIdRu = "jQlcfdCrYyT5M6O0g9KC-UjL65KfRjUMSAF2iUJC7zSYgcA";
    String summonerName = "Queen von Satanа";

    Steps steps = new Steps();

    @Test(description = "Мапинг GET запроса на объекты")
    public void firstRiotApiTest() {
        HashMap <String, Champion> championHashMap = steps.getJsonAsMap(baseUrlRu, summonerIdRu, apiKey);
        Assert.assertTrue(championHashMap.size() > 100, "Ебать ты лоускилл");
    }

    @Test(description = "Выявление чемпиона с самым большим рейтингом мастерства")
    public void findMostMasteryChampion(){
        HashMap <String, Champion> championHashMap = steps.getJsonAsMap(baseUrlRu, summonerIdRu, apiKey);
        ArrayList <Integer> championsPointsList = new ArrayList<>();

        for (String key : championHashMap.keySet()){
            championsPointsList.add(championHashMap.get(key).getChampionPoints());
        }

        int mostPoints = Collections.max(championsPointsList);

        Assert.assertEquals(championHashMap.get("0").getChampionPoints(), mostPoints, "Это не самый наигранный чемпион. Правильный ответ " + championHashMap.get("0").getChampionId());
    }

    @Test(description = "Выявление чемпионов с самым высоким рейтингом мастерства у игроков в текущей игре")
    public void getMostMasteryChampionsInCurrentGame(){
        Gson gson = new Gson();
        Steps step = new Steps();

        ArrayList<String> summonersIds = new ArrayList<>();
        ArrayList <Summoner> summoners = new ArrayList<>();
        HashMap<Integer, String> championsNameById = step.championsMapById();

        String summonerJsonString = RestAssured.get(baseUrlRu + summonerBySummonerName + summonerName + apiKey).as(Object.class).toString();
        Summoner summoner = gson.fromJson(step.removeSpaces(summonerJsonString, "name", "profileIconId"), Summoner.class);

        String currentGameInfoList = RestAssured.get(baseUrlRu + activeGameBySummonerId  + summoner.getId() + apiKey).as(Object.class).toString();
        CurrentGameInfo currentGameInfo = gson.fromJson(step.removeSpaces(currentGameInfoList, "summonerName", "bot"), CurrentGameInfo.class);
        for (CurrentGameParticipant currentGameParticipant: currentGameInfo.getParticipants()) {
            summonersIds.add(currentGameParticipant.getSummonerId());
        }

        for (String summonerIdInList:  summonersIds) {
            summoners.add(RestAssured.get(baseUrlRu + summonerBySummonerId + summonerIdInList + apiKey).as(Summoner.class));
        }

        ArrayList <Summoner> summonersList = step.getCurrentGameSummonersBySummonerId(summonerIdRu);

        for (Summoner summonerFromList : summonersList) {
            System.out.println(summonerFromList.getName());
            HashMap <String, Champion> championHashMap = step.getJsonAsMap(baseUrlRu, summonerFromList.getId(), apiKey);
            System.out.println(championsNameById.get(championHashMap.get("0").getChampionId()));
            System.out.println("----------------------------------------------------------------------");
        }

        System.out.println("Code Complete");
    }
}
