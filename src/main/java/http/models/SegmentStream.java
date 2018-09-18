package http.models;

import common.Configurations;

import javax.json.Json;
import javax.json.JsonObject;

public class SegmentStream {
    private String pId;
    private int segmentId;

    public SegmentStream(String pId, String apiIdentifier){
        this.pId = pId;
        this.segmentId = Integer.parseInt(apiIdentifier.replace(String.format("%1$s_", Configurations.getValueByKey("shopNumber")), ""));
    }

    public String generateJson(){
        JsonObject jo = Json.createObjectBuilder()
                .add("added", Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                        .add("data", Json.createObjectBuilder())
                        .add("id", this.segmentId)))
                .add("removed", Json.createArrayBuilder())
                .add("modified", Json.createArrayBuilder())
                .add("pId", this.pId)
                .add("pcIds", Json.createArrayBuilder())
                .build();
        return jo.toString();
    }
}
