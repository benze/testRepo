package org.apache.maven.scm.provider.svn;

/*
 * Copyright 2001-2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.codehaus.plexus.util.StringUtils;

/**
 * Command utilities for svn commands.
 *
 * @author <a href="mailto:jerome@coffeebreaks.org">Jerome Lacoste</a>
 * @version $Id$
 */
public class SvnCommandUtils
{

    /**
     * Add or overrides the username into a url with a svn+ssh scheme.
     *
     * Svn 1.3.1 doesn't use the username information specified by --username when the url
     * uses the svn+ssh scheme. This allows to fix it. See MRELEASE-35.
     *
     * @param url the url, not <code>null</code>
     * @param username the username, may be <code>null</code>
     * @return the fixed url
     * @throws NullPointerException if url is <code>null</code>
     */
    public static String fixUrl( String url, String username )
    {
        if ( ! StringUtils.isEmpty(username) && url.startsWith( "svn+ssh://" ) ) {
            // is there a username to override ? If so we cut after
            int idx = url.indexOf( '@' );
            int cutIdx = idx < 0 ?  "svn+ssh://".length() : idx + 1;
            url = "svn+ssh://" + username + "@" + url.substring( cutIdx );
        }
        return url;
    }
}
