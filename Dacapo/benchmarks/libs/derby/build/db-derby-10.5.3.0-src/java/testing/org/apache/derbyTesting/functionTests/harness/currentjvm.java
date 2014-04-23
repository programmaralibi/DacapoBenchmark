/*

   Derby - Class org.apache.derbyTesting.functionTests.harness.currentjvm

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package org.apache.derbyTesting.functionTests.harness;

import javolution.util.FastTable;
import java.util.StringTokenizer;

/**
  <p>This class is for whatever java is in the current classpath

 */

public class currentjvm extends jvm {

	public String getName() {return "currentjvm";}
    public currentjvm(boolean noasyncgc, boolean verbosegc, boolean noclassgc,
    long ss, long oss, long ms, long mx, String classpath, String prof,
    boolean verify, boolean noverify, boolean nojit, FastTable D) {
        super(noasyncgc,verbosegc,noclassgc,ss,oss,ms,mx,classpath,prof,
		verify,noverify,nojit,D);
    }
    // more typical use:
    public currentjvm(String classpath, FastTable D) {
        super(classpath,D);
    }
    // more typical use:
    public currentjvm(long ms, long mx, String classpath, FastTable D) {
        super(ms,mx,classpath,D);
    }
    // actual use
    public currentjvm() { }

    // return the command line to invoke this VM.  The caller then adds
    // the class and program arguments.
    public FastTable getCommandLine() 
    {
        StringBuffer sb = new StringBuffer();
        FastTable v = super.getCommandLine();
        appendOtherFlags(sb);
        String s = sb.toString();
        StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens())
        {
            v.add(st.nextToken());
        }
        return v;
    }

    public void appendOtherFlags(StringBuffer sb)
    {
        if (noasyncgc) sb.append(" -noasyncgc");
        if (verbosegc) sb.append(" -verbosegc");
        if (noclassgc) sb.append(" -noclassgc");
        if (ss>=0) {
          sb.append(" -ss");
          sb.append(ss);
        }
        if (oss>=0) {
          sb.append(" -oss");
          sb.append(oss);
        }
        if (ms>=0) {
          sb.append(" -ms");
          sb.append(ms);
        }
        if (mx>=0) {
          sb.append(" -mx");
          sb.append(mx);
        }
        if (classpath!=null) {
          sb.append(" -classpath ");
          sb.append(classpath);
        }
        if (prof!=null) {
          sb.append(" -prof:");
          sb.append(prof);
        }
        if (verify) sb.append(" -verify");
        if (noverify) sb.append(" -noverify");
        if (nojit) sb.append(" -nojit");
        if (D!=null)
          for (int i=0; i<D.size();i++) {
	        sb.append(" -D");
	        sb.append((String)(D.get(i)));
          }
    }

	public String getDintro() { return "-D"; }
}
