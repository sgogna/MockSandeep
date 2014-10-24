/* Copyright 2009 EB2 International Limited */
package testproxy.connectors;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import proxy.compare.CompareResult;
import proxy.compare.CompareStatus;
import proxy.compare.CopyManager;
import proxy.compare.FolderComparator;
import status.StatusCommunicationRequest;
import status.StatusCommunicationResponse;
import status.StatusCommunicationXMLSerializer;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.servicemanagment.ServiceManager;
import testproxy.sessionsinfo.SessionsInfoManager;

import com.sabre.ssw.proxy.compare.Comparator;
import com.sabre.ssw.proxy.compare.profile.Profile;
import com.sabre.ssw.proxy.compare.profile.ProfileFilesManager;
import com.sabre.ssw.proxy.defines.ProxyMode;

import fileManagers.InfoFileManager;
import fileManagers.MainFileManager;

public class EndTestConnector extends AbstractConnector
{

    // public static final String TEST_EXECUTION_STATUS = "correct";
    private static final Logger LOG = LoggerFactory.getLogger(EndTestConnector.class);
    private SessionsInfoManager sessionManager;
    private MainFileManager mainFileManager;
    private ProfileFilesManager profileFilesManager;
    private static final String DEFAULT_FAILURE_RESULT = "N/A";

    @Override
    public boolean isProperConnector(HttpServletRequest request)
    {
        String requestText = ((GenericRequestWrapper)request).getRequestString();
        if (requestText.contains("StatusCommunicationRequest"))
        {
            LOG.info("EndTestConnector");
            return true;
        }

        return false;
    }

    @Override
    public String getEndpoint(HttpServletRequest request, ServiceManager serviceManager)
    {
        return null;
    }

    @Override
    public String getFileName(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public int callRealService(HttpServletRequest request, HttpServletResponse response, String service, String testId, String sessionId, int connectTimeout, String filename)
    {
        OutputStream out = null;
        InputStream in = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try
        {
            in = request.getInputStream();
            StatusCommunicationRequest rq = unmarshalRequest(in, bos);

            FolderComparator comparator = new FolderComparator();
            String comparePath = sessionManager.getPath(sessionId);
            String recordPath = comparePath;
            ProxyMode mode = sessionManager.getProxyModeForSession(sessionId);

            if (mode != null && (mode == ProxyMode.COMPARE || mode == ProxyMode.REPLAY))
            {
                recordPath = mainFileManager.getBasicTestFolderPath(new File(comparePath), null);
            }

            // copying files to base dir if test passed, base dir not exist and
            // mode is COMPARE
            if (mode.equals(ProxyMode.COMPARE) && rq.isCorrect() && !(new File(recordPath)).exists())
            {
                LOG.info("Compare mode: no base test found. Files will be copyied.");
                InfoFileManager.addInfo(sessionManager.getPath(sessionId), InfoFileManager.TEST_EXECUTION_STATUS, "" + rq.isCorrect());
                if (!CopyManager.copyFilesInDirs(new File(comparePath), new File(recordPath)))
                {
                    LOG.error("Can't copy files from " + comparePath + " to " + recordPath);
                }
            }

            String profileName = rq.getComparator();
            CompareResult patternResult = null;
            Profile p = null;
            if (profileName == null || profileName.isEmpty())
            {
                LOG.warn("Comparator is not set. Default comparator (" + FolderComparator.NAME + ") will be use.");
                patternResult = comparator.compareDirs(recordPath, comparePath);
            }
            else
            {
                p = getProfileByName(profileName);
                if (p == null)
                {
                    LOG.error("Not found profile: " + profileName + "! Sending error mesage.");
                    // TODO error response
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Profile: \"" + profileName + "\" is undefined. Please check the profile list.");
                    return NO_RESPONSE_CODE;
                }
                else
                {
                    LOG.info("Comparision using profile: " + profileName);
                    patternResult = comparator.compareDirs(recordPath, comparePath, p.getIncludeList(), p.getExcludeList());
                }
            }
            out = response.getOutputStream();
            StatusCommunicationResponse rs = new StatusCommunicationResponse();
            if (patternResult == null)
            {
                LOG.info("Test: " + testId + " is not recorded! Nothing to compare!");
                LOG.info("Record path: " + recordPath);
                LOG.info("Compare path: " + comparePath);
                rs.setCorrect(false);
            }
            else
            {
                List<File> diffContextFiles = new ArrayList<File>();
                if (p != null)
                {
                    Comparator contextComparator = new Comparator();
                    for (File file : patternResult.getEqualFilesFromFirstDir())
                    {
                        if (!contextComparator.isEqualsFiles(file, MainFileManager.getEqualFileFromSecondDir(recordPath, comparePath, file), p))
                        {
                            diffContextFiles.add(file);
                        }
                    }
                }
                if (patternResult.getStatus() == CompareStatus.Equals && diffContextFiles.size() == 0)
                {
                    rs.setCorrect(true);
                }
                else
                {
                    rs.setCorrect(false);
                    rs.setDiffFiles(createDifferencesList(patternResult.getErrorFilesInSecondDir(), patternResult.getNewFilesInFirstDir(), patternResult.getNewFilesInSecondDir(), diffContextFiles));
                }

                LOG.info("Test: " + testId + " entering EndTest mode: " + sessionManager.getProxyModeForSession(sessionId) + " for session: " + sessionId);
                if (sessionManager.getProxyModeForSession(sessionId) == ProxyMode.COMPARE || sessionManager.getProxyModeForSession(sessionId) == ProxyMode.REPLAY)
                {
                    if (profileName == null || profileName.isEmpty())
                    {
                        InfoFileManager.addInfo(sessionManager.getPath(sessionId), InfoFileManager.COMPARE_ALGORITHM, FolderComparator.NAME);
                    }
                    else
                    {
                        InfoFileManager.addInfo(sessionManager.getPath(sessionId), InfoFileManager.COMPARE_ALGORITHM, profileName);
                    }

                    InfoFileManager.addInfo(sessionManager.getPath(sessionId), InfoFileManager.COMPARE_RESULT, "" + rs.isCorrect());

                    if (rq.isCorrect() && !rs.isCorrect() && rq.isFlowDiffFailsTest())
                    {
                        InfoFileManager.addInfo(sessionManager.getPath(sessionId), InfoFileManager.TEST_EXECUTION_STATUS, "" + false);
                        LOG.info("Test: " + testId + " EndTest request saved test status: " + false);
                        InfoFileManager.addInfo(sessionManager.getPath(sessionId), InfoFileManager.FAILURE_REASON, "Failed test because of failed compare.");
                    }
                    else
                    {
                        InfoFileManager.addInfo(sessionManager.getPath(sessionId), InfoFileManager.TEST_EXECUTION_STATUS, "" + rq.isCorrect());
                        LOG.info("Test: " + testId + " EndTest request saved test status: " + rq.isCorrect());
                        InfoFileManager.addInfo(sessionManager.getPath(sessionId), InfoFileManager.FAILURE_REASON, rq.isCorrect() ? DEFAULT_FAILURE_RESULT : rq.getFailureReason());
                    }
                    // } else {
                    // InfoFileManager.addInfo(sessionManager.getPath(sessionId),
                    // InfoFileManager.TEST_EXECUTION_STATUS, "" +
                    // rq.isCorrect());
                    // LOG.info("Test: " + testId +
                    // " EndTest request saved test status: " + rq.isCorrect());
                    // InfoFileManager.addInfo(sessionManager.getPath(sessionId),
                    // InfoFileManager.FAILURE_REASON, rq.getFailureReason());
                    // }

                    //
                    // InfoFileManager.addInfo(sessionManager.getPath(sessionId),
                    // InfoFileManager.TEST_EXECUTION_STATUS, "" +
                    // rq.isCorrect());
                    // LOG.info("Test: " + testId +
                    // " EndTest request saved test status: " + rq.isCorrect());
                    //
                    // String failureResult;
                    //
                    // if (!rq.isCorrect())
                    // {
                    // failureResult = rq.getFailureReason();
                    // }
                    // else
                    // {
                    // failureResult = DEFAULT_FAILURE_RESULT;
                    // }
                    // InfoFileManager.addInfo(sessionManager.getPath(sessionId),
                    // InfoFileManager.FAILURE_REASON, failureResult);

                }
            }
            LOG.info("Test: " + testId + " Returning from EndTest request. Compare status: " + rs.isCorrect());
            String msg = StatusCommunicationXMLSerializer.serializeResponse(rs);
            out.write(msg.getBytes());
        }
        catch (IOException exc)
        {
            LOG.error("InputStream/OutputStream opening error");
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException exc)
                {
                    LOG.error("OutputStream closing error");
                }
            }

            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException exc)
                {
                    LOG.error("InputStream closing error");
                }
            }

        }
        
        return DEFAULT_SUCCESS_CODE;

    }

    private Profile getProfileByName(String profileName)
    {
        List<Profile> profiles = profileFilesManager.readProfiles();
        for (Profile profile : profiles)
        {
            if (profile.getName().equals(profileName))
            {
                return profile;
            }
        }
        return null;
    }

    private StatusCommunicationRequest unmarshalRequest(InputStream in, ByteArrayOutputStream bos) throws IOException
    {
        int readed;
        while ((readed = in.read()) != -1)
        {
            bos.write(readed);
        }
        StatusCommunicationRequest rq = StatusCommunicationXMLSerializer.deserializeRequest(new String(bos.toByteArray()));
        return rq;
    }

    private List<String> createDifferencesList(List<File>... args)
    {
        List<String> differences = new LinkedList<String>();

        for (List<File> list : args)
        {
            for (File file : list)
            {
                differences.add(file.getName());
            }
        }

        return differences;
    }

    @Override
    public boolean isSpecialRequestAllowed()
    {
        return true;
    }

    @Override
    public String getSpecialFileName(HttpServletRequest request)
    {
        return "EndTestConnector";
    }

    public SessionsInfoManager getSessionManager()
    {
        return sessionManager;
    }

    public void setSessionManager(SessionsInfoManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }

    public MainFileManager getMainFileManager()
    {
        return mainFileManager;
    }

    public void setMainFileManager(MainFileManager mainFileManager)
    {
        this.mainFileManager = mainFileManager;
    }

    public ProfileFilesManager getProfileFilesManager()
    {
        return profileFilesManager;
    }

    public void setProfileFilesManager(ProfileFilesManager profileFilesManager)
    {
        this.profileFilesManager = profileFilesManager;
    }

}
