package com.sabre.sabresonic.mockserver.core.service.impl;

import com.sabre.sabresonic.mockserver.core.exception.ServiceException;
import com.sabre.sabresonic.mockserver.core.generator.SoapBasedFilenameGenerator;
import com.sabre.sabresonic.mockserver.core.generator.UriBasedFilenameGenerator;
import com.sabre.sabresonic.mockserver.core.http.MockRequest;
import com.sabre.sabresonic.mockserver.core.http.MockResponse;
import com.sabre.sabresonic.mockserver.core.message.datagrabbers.DataGrabber;
import com.sabre.sabresonic.mockserver.core.message.replacers.DataReplacer;
import com.sabre.sabresonic.mockserver.core.service.AbstractService;
import com.sabre.sabresonic.mockserver.core.service.FlowVariables;
import com.sabre.sabresonic.mockserver.core.service.beans.HostCommand;
import com.sabre.sabresonic.mockserver.core.service.beans.HostCommandFactory;
import com.sabre.sabresonic.mockserver.core.util.SpringBeanContainer;
import com.sabre.sabresonic.mockserver.core.util.XPathUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadSOAPFromFile extends AbstractService {

    private String basePath;
    private String request;
    private String response;
    private HostCommand hostCommand;
    private static final Logger LOG = LoggerFactory.getLogger(ReadSOAPFromFile.class);

    public ReadSOAPFromFile(final String basePath, final String request) {
        this.basePath = basePath;
        this.request = request;
    }

    @Override
    public void execute(final FlowVariables flowVariables) {
        super.execute(flowVariables);
        LOG.info("The Mode Set is REPLAY Mode!");

        if (response == null) {
            response = "response";
        }
        if (request == null) {
            request = "request";
        }

        try {

            MockRequest mockRequest = (MockRequest) flowVariables.parseExpression(this.request);
            String basePathVal = null;
            if (basePath != null) {
                basePathVal = (String) flowVariables.parseExpression(this.basePath);
            }
            LOG.info("basePathValue is :::::: " + basePathVal);
            String resFileNameVal;

            String request = new String(mockRequest.getContent(), "UTF-8");

            LOG.info("Mock Request in REPLAY Mode :::: " + request);
            String fileName = getSoapAction(request);
            request = XPathUtil.prettyFormat(request, 2);
            hostCommand = HostCommandFactory.create(request);
            LOG.info("host command after sanitizing :::: " + hostCommand);
            if (hostCommand !=null && hostCommand.getCommand() != "")
            {
                resFileNameVal = fileName + "RS_" + hostCommand;
            }
            else
            {
                resFileNameVal = fileName + "RS";
            }
            File resfile = new File(generatePath(String.valueOf(resFileNameVal), basePathVal));
            byte[] content = FileUtils.readFileToByteArray(resfile);
            String str = new String(content, "UTF-8");
            LOG.info("Mock Response in REPLAY Mode ::: " + str);
            MockResponse mockResponse = new MockResponse();
            mockResponse.setContent(content);
            flowVariables.parseSetValueExpression(response, mockResponse);

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
