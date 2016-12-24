package servlet.backend;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static servlet.backend.auth.SecurityUtils.hasPermission;

//TODO ide settings
public class ConfigServlet extends HttpServlet {

    private JSONObject mJSONObject;

    private final Entity mEntity;

    public ConfigServlet() {
        super();
        final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        final Query query = new Query("config");
        final PreparedQuery pq = datastore.prepare(query);
        final Iterable<Entity> entities = pq.asIterable();
        final Iterator<Entity> iterator = entities.iterator();
        if (iterator.hasNext()) {
            mEntity = iterator.next();
            try {
                mJSONObject = new JSONObject((String) (mEntity.getProperties().get("value")));
            } catch (final JSONException e) {
                e.printStackTrace();
            }
        } else {
            mJSONObject = new JSONObject();
            mEntity = new Entity("config");
            mEntity.setProperty("value", mJSONObject.toString());
            datastore.put(mEntity);
        }
    }

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().print(mJSONObject.toString());
    }

    @Override
    public void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException {
        if (hasPermission(req, resp)) {
            final String value = req.getParameter("value");
            resp.setContentType("application/json");
            try {
                mJSONObject = new JSONObject(value);
                mEntity.setProperty("value", mJSONObject.toString());
                final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
                datastore.put(mEntity);
                resp.getWriter().write(new JSONObject().put("success", true).toString());
            } catch (final JSONException e) {
                try {
                    resp.getWriter().write(new JSONObject().put("error", e.toString()).toString());
                } catch (final JSONException e1) {

                }
            }
        }
    }
}