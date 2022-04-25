package mobile_project.music_app.Service_API;

public class APIService {
    private static String base_url = "http://192.168.x.x:8000/api/auth/";//get ip of my device with ipconfig and fill x.x
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}

