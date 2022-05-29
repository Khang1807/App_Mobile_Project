package mobile_project.music_app.Model;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class ResponseModel {

    @SerializedName("responseStatus")
    private String responseStatus;

    @SerializedName("statusText")
    private String statusText;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("content")
    private Object content;

//    @SerializedName("statusText")
//    private String message;


    public String getStatusText() {
        return statusText;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }


    public Object getContent() {
        return content;
    }

}
