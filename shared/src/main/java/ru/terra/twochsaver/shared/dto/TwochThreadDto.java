
package ru.terra.twochsaver.shared.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "banned",
    "closed",
    "comment",
    "date",
    "email",
    "files",
    "lasthit",
    "name",
    "num",
    "op",
    "parent",
    "sticky",
    "subject",
    "tags",
    "timestamp",
    "trip",
    "trip_type",
    "unique_posters"
})
public class TwochThreadDto {

    @JsonProperty("banned")
    private Integer banned;
    @JsonProperty("closed")
    private Integer closed;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("date")
    private String date;
    @JsonProperty("email")
    private String email;
    @JsonProperty("files")
    private List<TwochFileDto> files = null;
    @JsonProperty("lasthit")
    private Integer lasthit;
    @JsonProperty("name")
    private String name;
    @JsonProperty("num")
    private String num;
    @JsonProperty("op")
    private Integer op;
    @JsonProperty("parent")
    private String parent;
    @JsonProperty("sticky")
    private Integer sticky;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("tags")
    private String tags;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("trip")
    private String trip;
    @JsonProperty("trip_type")
    private String tripType;
    @JsonProperty("unique_posters")
    private String uniquePosters;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("banned")
    public Integer getBanned() {
        return banned;
    }

    @JsonProperty("banned")
    public void setBanned(Integer banned) {
        this.banned = banned;
    }

    @JsonProperty("closed")
    public Integer getClosed() {
        return closed;
    }

    @JsonProperty("closed")
    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("files")
    public List<TwochFileDto> getFiles() {
        return files;
    }

    @JsonProperty("files")
    public void setFiles(List<TwochFileDto> files) {
        this.files = files;
    }

    @JsonProperty("lasthit")
    public Integer getLasthit() {
        return lasthit;
    }

    @JsonProperty("lasthit")
    public void setLasthit(Integer lasthit) {
        this.lasthit = lasthit;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("num")
    public String getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(String num) {
        this.num = num;
    }

    @JsonProperty("op")
    public Integer getOp() {
        return op;
    }

    @JsonProperty("op")
    public void setOp(Integer op) {
        this.op = op;
    }

    @JsonProperty("parent")
    public String getParent() {
        return parent;
    }

    @JsonProperty("parent")
    public void setParent(String parent) {
        this.parent = parent;
    }

    @JsonProperty("sticky")
    public Integer getSticky() {
        return sticky;
    }

    @JsonProperty("sticky")
    public void setSticky(Integer sticky) {
        this.sticky = sticky;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("tags")
    public String getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(String tags) {
        this.tags = tags;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("trip")
    public String getTrip() {
        return trip;
    }

    @JsonProperty("trip")
    public void setTrip(String trip) {
        this.trip = trip;
    }

    @JsonProperty("trip_type")
    public String getTripType() {
        return tripType;
    }

    @JsonProperty("trip_type")
    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    @JsonProperty("unique_posters")
    public String getUniquePosters() {
        return uniquePosters;
    }

    @JsonProperty("unique_posters")
    public void setUniquePosters(String uniquePosters) {
        this.uniquePosters = uniquePosters;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
