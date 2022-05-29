package mobile_project.music_app.Service_API;

public class APIService {
    private static String base_url = "http://10.0.3.2:8000/api/";//get ip of my device with ipconfig and fill x.x
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}

