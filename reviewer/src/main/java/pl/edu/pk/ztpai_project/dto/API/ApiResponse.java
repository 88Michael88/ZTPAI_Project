package pl.edu.pk.ztpai_project.dto.API;

public class ApiResponse {
	private String ApiKey;
    public ApiResponse(String ApiKey) {
        this.ApiKey = ApiKey;
    }

    public String getApiKey() { return ApiKey; }
    public void setApiKey(String apiKey) { ApiKey = apiKey; }
}
