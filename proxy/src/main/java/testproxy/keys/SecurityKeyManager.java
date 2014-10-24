/* Copyright 2009 EB2 International Limited */
package testproxy.keys;

import java.util.ArrayList;
import java.util.List;

public class SecurityKeyManager
{
    private List<KeyInfo> keys = new ArrayList<KeyInfo>();

    public List<KeyInfo> getKeys()
    {
        return keys;
    }

    public void setKeys(List<KeyInfo> keys)
    {
        this.keys = keys;
    }

}
