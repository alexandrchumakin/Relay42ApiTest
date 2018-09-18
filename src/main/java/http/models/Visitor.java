package http.models;

public class Visitor {
    private String streamResponse;
    private String userId;

    public Visitor(String streamResponse, String userId){
        this.streamResponse = streamResponse;
        this.userId = userId;
    }

    public String getStreamResponse() {
        return streamResponse;
    }

    public String getUserId() {
        return userId;
    }
}
