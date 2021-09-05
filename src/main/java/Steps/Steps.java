package Steps;

import POJO.RiotApi.Champion;
import POJO.RiotApi.CurrentGameInfo;
import POJO.RiotApi.CurrentGameParticipant;
import POJO.RiotApi.Summoner;
import com.google.gson.Gson;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import io.restassured.RestAssured;


public class Steps {

    String baseUrlEuw = "https://euw1.api.riotgames.com";
    String baseUrlRu = "https://ru.api.riotgames.com";
    String apiKey = "?api_key=RGAPI-15803bde-8517-4c7b-96f4-86d406625188";
    String activeGameBySummonerId = "/lol/spectator/v4/active-games/by-summoner/";
    String summonerBySummonerId = "/lol/summoner/v4/summoners/";

    Gson gson = new Gson();

    HashMap <Integer, String> championsNameById = new HashMap<>();

    @Step("Парсим JSON на POJO")
    public HashMap <String, Champion> getJsonAsMap(String baseUrl, String summonerId, String apiKey){
        List championList = RestAssured.get(baseUrl + "/lol/champion-mastery/v4/champion-masteries/by-summoner/" + summonerId + apiKey).as(List.class);
        HashMap<String, Champion> championMap = new HashMap<>();

        for (int i = 0; i < championList.size(); i++){
            championMap.put(String.valueOf(i), gson.fromJson(championList.get(i).toString(), Champion.class));
        }

        return championMap;
    }

    @Step("Убираем проблемы из JSON ответа")
    public String removeSpaces(String jsonAsString, String startFieldName, String endFieldName){
        int startOfName = jsonAsString.indexOf(startFieldName);
        int endOfName = jsonAsString.lastIndexOf(endFieldName);
        String stringtoModify = jsonAsString.substring(startOfName, endOfName).replace(" ", "");
        String firstPart = jsonAsString.substring(0, startOfName);
        String lastPart = jsonAsString.substring(endOfName);
        return firstPart + stringtoModify + lastPart;
    }

    @Step("Получаем информацию об игре по summonerId")
    public ArrayList <Summoner> getCurrentGameSummonersBySummonerId(String summonerId) {
        ArrayList<String> summonersIds = new ArrayList<>();
        ArrayList <Summoner> summoners = new ArrayList<>();

        String currentGameInfoList = RestAssured.get(baseUrlRu + activeGameBySummonerId  + summonerId + apiKey).as(Object.class).toString();
        CurrentGameInfo currentGameInfo = gson.fromJson(removeSpaces(currentGameInfoList, "summonerName", "bot"), CurrentGameInfo.class);
        for (CurrentGameParticipant currentGameParticipant: currentGameInfo.getParticipants()) {
            summonersIds.add(currentGameParticipant.getSummonerId());
        }

        for (String summonerIdInList:  summonersIds) {
            summoners.add(RestAssured.get(baseUrlRu + summonerBySummonerId + summonerIdInList + apiKey).as(Summoner.class));
        }
        return summoners;
    }

    @Step("Мапа чемпионов по id")
    public HashMap <Integer, String> championsMapById(){
        championsNameById.put(266, "Aatrox");
        championsNameById.put(103, "Ahri");
        championsNameById.put(84, "Akali");
        championsNameById.put(166, "Akshan");
        championsNameById.put(12, "Alistar");
        championsNameById.put(32, "Amumu");
        championsNameById.put(34, "Anivia");
        championsNameById.put(1, "Annie");
        championsNameById.put(523, "Aphelios");
        championsNameById.put(22, "Ashe");
        championsNameById.put(136, "Aurelion Sol");
        championsNameById.put(268, "Azir");
        championsNameById.put(432, "Bard");
        championsNameById.put(53, "Blitzcrank");
        championsNameById.put(63, "Brand");
        championsNameById.put(201, "Braum");
        championsNameById.put(51, "Caitlyn");
        championsNameById.put(164, "Camille");
        championsNameById.put(69, "Cassiopeia");
        championsNameById.put(31, "Cho'Gath");
        championsNameById.put(42, "Corki");
        championsNameById.put(122, "Darius");
        championsNameById.put(131, "Diana");
        championsNameById.put(119, "Draven");
        championsNameById.put(36, "Dr. Mundo");
        championsNameById.put(245, "Ekko");
        championsNameById.put(60, "Elise");
        championsNameById.put(28, "Evelynn");
        championsNameById.put(81, "Ezreal");
        championsNameById.put(9, "Fiddlesticks");
        championsNameById.put(114, "Fiora");
        championsNameById.put(105, "Fizz");
        championsNameById.put(3, "Galio");
        championsNameById.put(41, "Gangplank");
        championsNameById.put(86, "Garen");
        championsNameById.put(150, "Gnar");
        championsNameById.put(79, "Gragas");
        championsNameById.put(104, "Graves");
        championsNameById.put(887, "Gwen");
        championsNameById.put(120, "Hecarim");
        championsNameById.put(74, "Heimerdinger");
        championsNameById.put(420, "Illaoi");
        championsNameById.put(39, "Irelia");
        championsNameById.put(427, "Ivern");
        championsNameById.put(40, "Janna");
        championsNameById.put(59, "Jarvan IV");
        championsNameById.put(24, "Jax");
        championsNameById.put(126, "Jayce");
        championsNameById.put(202, "Jhin");
        championsNameById.put(222, "Jinx");
        championsNameById.put(145, "Kai'Sa");
        championsNameById.put(429, "Kalista");
        championsNameById.put(43, "Karma");
        championsNameById.put(30, "Karthus");
        championsNameById.put(38, "Kassadin");
        championsNameById.put(55, "Katarina");
        championsNameById.put(10, "Kayle");
        championsNameById.put(141, "Kayn");
        championsNameById.put(85, "Kennen");
        championsNameById.put(121, "Kha'Zix");
        championsNameById.put(203, "Kindred");
        championsNameById.put(240, "Kled");
        championsNameById.put(96, "Kog'Maw");
        championsNameById.put(7, "LeBlanc");
        championsNameById.put(64, "Lee Sin");
        championsNameById.put(89, "Leona");
        championsNameById.put(876, "Lillia");
        championsNameById.put(127, "Lissandra");
        championsNameById.put(236, "Lucian");
        championsNameById.put(117, "Lulu");
        championsNameById.put(99, "Lux");
        championsNameById.put(54, "Malphite");
        championsNameById.put(90, "Malzahar");
        championsNameById.put(57, "Maokai");
        championsNameById.put(11, "Master Yi");
        championsNameById.put(21, "Miss Fortune");
        championsNameById.put(62, "Wukong");
        championsNameById.put(82, "Mordekaiser");
        championsNameById.put(25, "Morgana");
        championsNameById.put(267, "Nami");
        championsNameById.put(75, "Nasus");
        championsNameById.put(111, "Nautilus");
        championsNameById.put(518, "Neeko");
        championsNameById.put(76, "Nidalee");
        championsNameById.put(56, "Nocturne");
        championsNameById.put(20, "Nunu & Willump");
        championsNameById.put(2, "Olaf");
        championsNameById.put(61, "Orianna");
        championsNameById.put(516, "Ornn");
        championsNameById.put(80, "Pantheon");
        championsNameById.put(78, "Poppy");
        championsNameById.put(555, "Pyke");
        championsNameById.put(246, "Qiyana");
        championsNameById.put(133, "Quinn");
        championsNameById.put(497, "Rakan");
        championsNameById.put(33, "Rammus");
        championsNameById.put(421, "Rek'Sai");
        championsNameById.put(526, "Rell");
        championsNameById.put(58, "Renekton");
        championsNameById.put(107, "Rengar");
        championsNameById.put(92, "Riven");
        championsNameById.put(68, "Rumble");
        championsNameById.put(13, "Ryze");
        championsNameById.put(360, "Samira");
        championsNameById.put(113, "Sejuani");
        championsNameById.put(235, "Senna");
        championsNameById.put(147, "Seraphine");
        championsNameById.put(875, "Sett");
        championsNameById.put(35, "Shaco");
        championsNameById.put(98, "Shen");
        championsNameById.put(102, "Shyvana");
        championsNameById.put(27, "Singed");
        championsNameById.put(14, "Sion");
        championsNameById.put(15, "Sivir");
        championsNameById.put(72, "Skarner");
        championsNameById.put(37, "Sona");
        championsNameById.put(16, "Soraka");
        championsNameById.put(50, "Swain");
        championsNameById.put(517, "Sylas");
        championsNameById.put(134, "Syndra");
        championsNameById.put(223, "Tahm Kench");
        championsNameById.put(163, "Taliyah");
        championsNameById.put(91, "Talon");
        championsNameById.put(44, "Taric");
        championsNameById.put(17, "Teemo");
        championsNameById.put(412, "Thresh");
        championsNameById.put(18, "Tristana");
        championsNameById.put(48, "Trundle");
        championsNameById.put(23, "Tryndamere");
        championsNameById.put(4, "Twisted Fate");
        championsNameById.put(29, "Twitch");
        championsNameById.put(77, "Udyr");
        championsNameById.put(6, "Urgot");
        championsNameById.put(110, "Varus");
        championsNameById.put(67, "Vayne");
        championsNameById.put(45, "Veigar");
        championsNameById.put(161, "Vel'Koz");
        championsNameById.put(254, "Vi");
        championsNameById.put(234, "Viego");
        championsNameById.put(112, "Viktor");
        championsNameById.put(8, "Vladimir");
        championsNameById.put(106, "Volibear");
        championsNameById.put(19, "Warwick");
        championsNameById.put(498, "Xayah");
        championsNameById.put(101, "Xerath");
        championsNameById.put(5, "Xin Zhao");
        championsNameById.put(157, "Yasuo");
        championsNameById.put(777, "Yone");
        championsNameById.put(83, "Yorick");
        championsNameById.put(350, "Yuumi");
        championsNameById.put(154, "Zac");
        championsNameById.put(238, "Zed");
        championsNameById.put(115, "Ziggs");
        championsNameById.put(26, "Zilean");
        championsNameById.put(142, "Zoe");
        championsNameById.put(143, "Zyra");
        return championsNameById;
    }
}
