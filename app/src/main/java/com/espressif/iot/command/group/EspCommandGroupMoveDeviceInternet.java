package com.espressif.iot.command.group;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import com.espressif.iot.base.api.EspBaseApiUtil;
import com.espressif.iot.command.group.IEspCommandGroupMoveDeviceInternet;
import com.espressif.iot.type.net.HeaderPair;

public class EspCommandGroupMoveDeviceInternet implements IEspCommandGroupMoveDeviceInternet
{
    
    @Override
    public boolean doCommandMoveDeviceIntoGroupInternet(String userKey, long deviceId, long groupId,
        boolean reservePreGroup)
    {
        String headerKey = Authorization;
        String headerValue = Token + " " + userKey;
        HeaderPair header = new HeaderPair(headerKey, headerValue);
        
        try
        {
            JSONObject postJSON = new JSONObject();
            postJSON.put(KEY_DEVICE_ID, deviceId);
            postJSON.put(KEY_GROUP_ID, groupId);
            postJSON.put(KEY_RESERVE_PRE_GROUP, reservePreGroup);
            
            JSONObject resultJSON = EspBaseApiUtil.Post(URL_MOVE_DEVICE, postJSON, header);
            if (resultJSON != null)
            {
                int status = resultJSON.getInt(Status);
                return status == HttpStatus.SC_OK;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
}
