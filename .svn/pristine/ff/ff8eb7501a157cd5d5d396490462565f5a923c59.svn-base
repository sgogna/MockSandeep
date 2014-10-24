/* Copyright 2009 EB2 International Limited */
package testproxy.connectors.B6;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testproxy.connectors.AbstractConnector;
import testproxy.servicemanagment.ServiceManager;

import com.sabre.ssw.proxy.message.converter.JsonConverter;
import com.sabre.ssw.proxy.message.converter.JsonConverterInterface;

import fileManagers.MainFileManager;

public class HotelsHttpConnector extends AbstractConnector
{
    private static final Logger LOG = LoggerFactory.getLogger(HotelsHttpConnector.class);
    private MainFileManager mainFileManager;

    @Override
    public boolean isProperConnector(HttpServletRequest request)
    {
        try
        {
            JsonConverterInterface converter = new JsonConverter();

            Map<String, String> properties = (Map<String, String>) converter.getMapFromJSON(request.getHeader("testId").split("@")[0]);
            if (properties.get("original_host").matches(".*HotelSessionRequest.*") || properties.get("original_host").matches(".*ReservationRequest.*"))
            {
                // System.out.println("Hotels");
                return true;
            }
        }
        catch (Exception e)
        {
            LOG.warn(e.getMessage());
        }
        return false;
    }

    @Override
    public String getEndpoint(HttpServletRequest request, ServiceManager serviceManager)
    {
        return serviceManager.getNodeByRequestType("Hotels").getEndpoint();
    }

    @Override
    public String getFileName(HttpServletRequest request)
    {
        JsonConverterInterface converter = new JsonConverter();

        Map<String, String> properties = (Map<String, String>) converter.getMapFromJSON(request.getHeader("testId").split("@")[0]);
        String text = properties.get("original_host");
        Pattern p = Pattern.compile(".*HotelSessionRequest\\+method%3D%22(.*?)%22%3E.*");
        Matcher m = p.matcher(text);
        if (m.find())
        {
            String name = text.substring(m.start(1), m.end(1));
            
            return "HOTELS_HotelSessionRequest_" + name;
        }
        return "HOTELS_ReservationRequest";
    }

//    @Override
//    public String getRequestFullName(String session, String requestName, ProxyMode mode)
//    {
//        String[] nameToChange = {"selectHotelInfoForHotel", "getHotelRoomAvailabilities"};
//        for (String text : nameToChange) {
//        if (requestName.contains(text))
//        {
//            int index = requestName.indexOf(text) + text.length();
//            String originalHotelInfo = requestName;
//            requestName = requestName.substring(0, index);
//            if (mode.equals(ProxyMode.REPLAY))
//            {
//                String session_path = super.getSessionsInfoManager().getPath(session);
//                String number = InfoFileManager.getInfo(mainFileManager.getBasicTestFolderPath(new File(session_path), null), originalHotelInfo);
//                if (number == null)
//                    return requestName;
//                return number;
//            }
//            else
//            {
//                String number = String.valueOf(super.getSessionsInfoManager().getCount(session, requestName));
//                requestName += "_" + number;
//                InfoFileManager.addInfo(super.getSessionsInfoManager().getPath(session), originalHotelInfo, requestName);
//            }
//            return requestName;
//        }}
//     
//            return super.getRequestFullName(session, requestName, mode);
//    }

    public MainFileManager getMainFileManager()
    {
        return mainFileManager;
    }

    public void setMainFileManager(MainFileManager mainFileManager)
    {
        this.mainFileManager = mainFileManager;
    }

}