package com.sabre.sabresonic.mockserver.core.service.impl;

import com.sabre.sabresonic.mockserver.core.exception.ServiceException;
import com.sabre.sabresonic.mockserver.core.generator.SoapBasedFilenameGenerator;
import com.sabre.sabresonic.mockserver.core.http.MockRequest;
import com.sabre.sabresonic.mockserver.core.http.MockResponse;
import com.sabre.sabresonic.mockserver.core.service.AbstractService;
import com.sabre.sabresonic.mockserver.core.service.FlowVariables;
import com.sabre.sabresonic.mockserver.core.service.beans.HostCommand;
import com.sabre.sabresonic.mockserver.core.service.beans.HostCommandFactory;
import com.sabre.sabresonic.mockserver.core.util.XPathUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MixedModeSOAPToFile extends AbstractService {

    private String request;
    private String response;
    private String basePath;
    private HostCommand hostCommand;
    private static final Logger LOG = LoggerFactory.getLogger(MixedModeSOAPToFile.class);




    public MixedModeSOAPToFile(final String basePath, final String response) {
        this.basePath = basePath;
        this.response = response;
    }

    @Override
    public void execute(final FlowVariables flowVariables) {
        super.execute(flowVariables);
        LOG.info("The Mode Set is AUTO Mode, If mock response is not found it will get it from downline system and record it for the first time!");
        if (response == null) {
            response = "response";
        }
        if (request == null) {
            request = "request";
        }

        try {
            MockRequest mockRequest = (MockRequest) flowVariables.parseExpression(this.request);
            String request = new String(mockRequest.getContent(), "UTF-8");

            String fileName = getSoapAction(request);
            LOG.info("Action Name is :::: " + fileName);
            if (!fileName.equals("SessionCreate") && !fileName.equals("SessionValidate"))
            {
            String basePathVal = null;
            if (basePath != null) {
                basePathVal = (String) flowVariables.parseExpression(this.basePath);
            }
            String reqFileNameVal;
            String resFileNameVal;



            LOG.info("Mock Request in AUTO Mode :::: " + request);

            request = XPathUtil.prettyFormat(request, 2);
            hostCommand = HostCommandFactory.create(request);
            LOG.debug("host command after sanitizing :::: " + hostCommand);
            LOG.debug("Response this.response " + this.response);
            if (hostCommand !=null && hostCommand.getCommand() != "")
            {
            resFileNameVal = fileName + "RS_" + hostCommand;
            reqFileNameVal = fileName + "RQ_" + hostCommand;
            }
            else
            {
                resFileNameVal = fileName + "RS";
                reqFileNameVal = fileName + "RQ";
            }

            File resfile = new File(generatePath(String.valueOf(resFileNameVal), basePathVal));
            if (!resfile.exists())
            {
                LOG.info("File not Found in AUTO MODE " +  resfile);
                MockResponse mockResponse = (MockResponse) flowVariables.parseExpression(this.response);
                String str = new String(mockResponse.getContent(), "UTF-8");
                LOG.debug("MockResponse in AUTO MODE is " +  str);
                File reqfile = new File(generatePath(String.valueOf(reqFileNameVal), basePathVal));
                FileUtils.writeByteArrayToFile(resfile, mockResponse.getContent());
                FileUtils.writeByteArrayToFile(reqfile, request.getBytes());
            }
            else
            {
                byte[] content = FileUtils.readFileToByteArray(resfile);
                LOG.info(content.toString());
                MockResponse mockResponse = new MockResponse();
                mockResponse.setContent(content);
                flowVariables.parseSetValueExpression(response, mockResponse);
            }
            }
            else
            {
                LOG.info("Mock Request " + fileName + " will always go to downline system for AUTO mode");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    protected String generatePath(String fileName, String basePath) {
        SoapBasedFilenameGenerator filenameGenerator = new SoapBasedFilenameGenerator();
        return filenameGenerator.generate(fileName, basePath);
    }

    protected String getSoapAction(String mockRequest)
    {
        String s = new String(mockRequest);
        int start = s.indexOf(":Action>");
        if (start < 0)
        {
            return getActionNameAfterBodyTag(s);
        }
        else
        {
            start += 8;
            int end = s.indexOf("<", start) - 2;
            return s.substring(start, end);
        }
    }

    private String getActionNameAfterBodyTag(String s)
    {
        int body = s.indexOf(":Body>");
        if (body > 0)
        {
            int endOfBody = body + 6;
            int maxCharOfActionName = 100;
            int canDidateActionNameEndIndex = endOfBody + maxCharOfActionName;
            String candidateActionName = s.substring(endOfBody, canDidateActionNameEndIndex).trim();
            Pattern pattern = Pattern.compile("[\\S]+");
            Matcher matcher = pattern.matcher(candidateActionName.replaceAll("<", ""));
            if (matcher.find())
            {
                String group = matcher.group();
                if (group.contains(":"))
                {
                    return group.substring(group.indexOf(":") + 1, group.length() - 2);
                }
                return group.substring(0, group.length() - 2);
            }
        }

        return null;
    }
}
