package com.wakdev.libs.commons;

import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* renamed from: com.wakdev.libs.commons.g */
public class C0494g {
    public static HashMap<String, String> m2083a(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap();
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                try {
                    hashMap.put(str, (String) jSONObject.get(str));
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
        }
        return hashMap;
    }
}
