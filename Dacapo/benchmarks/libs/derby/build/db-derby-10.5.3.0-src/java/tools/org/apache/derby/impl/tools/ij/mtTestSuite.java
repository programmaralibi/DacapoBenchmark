/*

   Derby - Class org.apache.derby.impl.tools.ij.mtTestSuite

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

package org.apache.derby.impl.tools.ij;

import javolution.util.FastTable;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Math;

/**
 */
public class mtTestSuite
{
	private FastTable cases;
	private FastTable last;
	private FastTable init;
	private mtTime time;
	private int numThreads;
	private String rootDir = null;


	mtTestSuite(int numThreads, mtTime time, 
			FastTable initCases, FastTable testCases, FastTable finalCases)
	{
		this.numThreads = numThreads;
		this.time = time;
		this.cases = testCases;
		this.init = initCases;
		this.last = finalCases;
	}

	public void init()
	{
		boolean loadInitFailed = loadCases(init);
		boolean loadTestsFailed = loadCases(cases);
		boolean loadLastFailed = loadCases(last);

		if ((loadInitFailed == true) ||
			(loadTestsFailed == true) ||
			(loadLastFailed == true))
		{
			throw new Error("Initialization Error");
		}
	}

	/**
	** @return boolean indicates if there was a problem loading
	** 	the file
	*/
	private boolean loadCases(FastTable cases)
	{
		if (cases == null)
			return false;

		boolean gotError = false;
		Iterator e = cases.iterator();
		mtTestCase tcase;
 
		while (e.hasNext())
		{
			tcase = (mtTestCase)e.next();
			try
			{
				tcase.initialize(rootDir);
			}
			catch (Throwable t)
			{
				gotError = true;
			}
		}

		return gotError;
	}

	public void setRoot(String rootDir)
	{
		this.rootDir = rootDir;
	}

	public String getRoot()
	{
		return rootDir;
	}

	public int getNumThreads()
	{
		return numThreads;
	}

	public FastTable getCases()
	{
		return cases;
	}

	public FastTable getInitCases()
	{
		return init;
	}

	public FastTable getFinalCases()
	{
		return last;
	}

	public mtTime getTime()
	{
		return time;
	}

	public long getTimeMillis()
	{
		return ((time.hours * 360) +
				(time.minutes * 60) +
				(time.seconds)) * 1000;
	}

	public String toString()
	{
		String str;
		int	len;
		int i;
	
		str = "TEST CASES\nNumber of Threads: "+numThreads;
		str +="\nTime: "+time;
		str +="\nNumber of Initializers: "+init.size()+"\n";
		for (i = 0, len = init.size(); i < len; i++)
		{
			str += init.get(i).toString() + "\n";
		}

		str +="\nNumber of Cases: "+cases.size()+"\n";
		for (i = 0, len = cases.size(); i < len; i++)
		{
			str += cases.get(i).toString() + "\n";
		}

		str +="\nNumber of Final Cases: "+last.size()+"\n";
		for (i = 0, len = last.size(); i < len; i++)
		{
			str += last.get(i).toString() + "\n";
		}

		return str;
	}

	/*
	** Grab a test case.  Pick one randomly and
	** try to grab that case.  If we get it we are
	** done.  Otherwise, try try again.
	*/
	public mtTestCase grabTestCase() 
	{
		int numCases = cases.size();
		int caseNum;
		mtTestCase testCase;

		do
		{
			caseNum = (int)((java.lang.Math.random() * 1311) % numCases);
			testCase = (mtTestCase)cases.get(caseNum);
		}
		while (testCase.grab() == false);
	
		return testCase;	
	}
}
