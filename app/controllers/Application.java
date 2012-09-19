package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.ExpressionList;
import models.*;
import org.codehaus.jackson.map.ObjectMapper;
import play.*;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;

import java.io.StringWriter;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render());
    }

    public static Result search(String query) {
        String[] queryData = query.trim().split(" ");
        ExpressionList<GeoData> expressionList = Ebean.getServer("lib").find(GeoData.class).where();
        for(String aQuery : queryData){
            aQuery = aQuery.trim();
            if(aQuery.length() != 0)
                expressionList = expressionList.contains("pkiia", aQuery);
        }
        Map<String, Set<GeoData>> departmentMap = new TreeMap<String, Set<GeoData>>();
        for(GeoData data : expressionList.findSet()){
            data.clean();
            if(departmentMap.containsKey(data.department))
                departmentMap.get(data.department).add(data);
            else{
                Set<GeoData> geoDataSet = new TreeSet<GeoData>();
                geoDataSet.add(data);
                departmentMap.put(data.department, geoDataSet);
            }
        }
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writeValue(writer, departmentMap);
        }catch (Exception e){
            return internalServerError();
        }
        return ok(writer.toString());
    }

    public static Result getDetail(String id) {
        Set<AttachDocument> attachDocuments = Ebean.getServer("lib").find(AttachDocument.class).where().like("BH", "%" + id + "%").findSet();
        Set<AttachForm> attachForms = Ebean.getServer("lib").find(AttachForm.class).where().like("BH", "%" + id + "%").findSet();
        Set<AttachPicture> attachPictures = Ebean.getServer("lib").find(AttachPicture.class).where().like("BH", "%" + id + "%").findSet();
        Set<AttachElse> attachElses = Ebean.getServer("lib").find(AttachElse.class).where().like("BH", "%" + id + "%").findSet();
        Set<OtherData> otherDatas = Ebean.getServer("lib").find(OtherData.class).where().like("BH", "%" + id + "%").findSet();
        Map<String, Set<?>> detailMap = new TreeMap<String, Set<?>>();
        detailMap.put("attachdoc", attachDocuments);
        detailMap.put("attachform", attachForms);
        detailMap.put("attachpic", attachPictures);
        detailMap.put("attachelse", attachElses);
        detailMap.put("other", otherDatas);
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writeValue(writer, detailMap);
        }catch (Exception e){
            return internalServerError();
        }
        return ok(writer.toString());
    }
}