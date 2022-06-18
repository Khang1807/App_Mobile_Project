package mobile_project.music_app_project.Service_API;

public class APIService {
    private static String base_url = "http://192.168.0.107:8000/api/";//get ip of my device with ipconfig and fill x.x
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}

