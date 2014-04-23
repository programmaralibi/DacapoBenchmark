/*

   Derby - Class org.apache.derbyTesting.functionTests.harness.Sed

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

/***
 * Sed.java
 *
 * This is a version of "sed" in Java for the Derby Function Tests,
 * written using the OROMatcher Perl5 regular expression classes.
 * The substitutions/deletions are based on the original kornshell tests.
 *
 ***/

import java.io.*;
import javolution.util.FastTable;
import org.apache.oro.text.regex.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.derbyTesting.functionTests.util.TestUtil;

public class Sed
{
	private	static	final	String	SQL_EXCEPTION_FILTERED_SUBSTITUTION = 
        "java.sql.SQLException:";

    public Sed()
    {
    }

    public static void main(String[] args) throws Exception {
        if (args == null || args.length != 2) {
            System.err.println("Usage: Sed sourcefile targetfile");
            System.exit(1);
        }
        File src = new File(args[0]);
        File tgt = new File(args[1]);
        new Sed().exec(src,tgt,null, false, false,false);
    }

    // The arguments should be the names of the input and output files
    public void exec
		(File srcFile, File dstFile, InputStream isSed, boolean isJCC, boolean isI18N, boolean isJDBC4)
        throws IOException
    {
    	String hostName = TestUtil.getHostName();
    	
        // FastTable for storing lines to be deleted
        FastTable deleteLines = new FastTable();
        deleteLines.add("^ij version.*$");
        deleteLines.add("^\\*\\*\\*\\* Test Run Started .* \\*\\*\\*\\*$");
        deleteLines.add("^\\*\\*\\*\\* Test Run Completed .* \\*\\*\\*\\*$");
        deleteLines.add("^ELAPSED TIME = [0-9]* milliseconds$");
        deleteLines.add("^\\^\\?$");
        //deleteLines.add("^\\.$"); // originally to remove lines with a dot
        deleteLines.add("^S.*ij> $");
        deleteLines.add("^ *$");
        deleteLines.add("^Server StackTrace:$");
        deleteLines.add("^\\[ *$");
        deleteLines.add("^\\] *$");
        deleteLines.add("^\\[$");
        deleteLines.add("^\\]$");
        deleteLines.add("^<not available>\\]$");
        deleteLines.add("^(.*at .*)\\(.*:[0-9].*\\)$");
        deleteLines.add("^(.*at .*)\\(*.java\\)$");
        deleteLines.add("^(.*at .*)\\(Compiled Code\\)$");
        deleteLines.add("^(.*at .*)\\(Interpreted Code\\)$");
        deleteLines.add("^(.*at .*)\\(Unknown Source\\)$");
        deleteLines.add("^(.*at .*)\\(Native Method\\)$");
        deleteLines.add("^\\tat $"); // rare case of incomplete stack trace line
        deleteLines.add("optimizer estimated cost");
        deleteLines.add("optimizer estimated row count");
        deleteLines.add("Using executables built for native_threads");
        deleteLines.add("Estimate of memory used");
        deleteLines.add("Size of merge runs");
        deleteLines.add("Number of merge runs");
        deleteLines.add("Sort type");
        deleteLines.add("Optimization started at .*$");
        deleteLines.add("WARNING 02000: No row was found for FETCH, UPDATE or DELETE");
        // deleteLines for stack traces from j9 jvm to match those above for other jvms
        deleteLines.add("Stack trace:");	
        deleteLines.add("^.*java/.*\\<init\\>\\(.*\\)V");
        deleteLines.add("^.*org/apache/derby/.*\\(.*\\).*$");	
        // next for j9 stack trace with jarfiles test run.
        deleteLines.add("^.*java/.*\\(.*\\).*$");
        deleteLines.add("^\\[.*db2jcc.jar\\] [0-9].[1-9] - .*$");	
        deleteLines.add("^\\[.*db2jcc_license_c.jar\\] [1-9].[0-9] - .*$");	
        deleteLines.add("^XSDB.*$");

		// JUnit noise
        deleteLines.add("^\\.*$");
        deleteLines.add("^Time: [0-9].*$");
        deleteLines.add("^OK \\(.*$");

        // FastTables for substitutions
        FastTable searchStrings = new FastTable();
        searchStrings.add("^Transaction:\\(.*\\) *\\|"); 
        searchStrings.add("^Read [0-9]* of [0-9]* bytes$");
        searchStrings.add("Directory .*connect.wombat.seg0");
        // Filter for constraint names - bug 5622 - our internal constraint names are too long. To be db2 compatible, we have reworked them.
        StringBuffer constraintNameFilter = new StringBuffer(); 
        constraintNameFilter.append("SQL[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        searchStrings.add(constraintNameFilter.toString());
        // Filter for uuids
        StringBuffer uuidFilter = new StringBuffer();
        uuidFilter.append("[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]-");
        uuidFilter.append("[0-9a-f][0-9a-f][0-9a-f][0-9a-f]-");
        uuidFilter.append("[0-9a-f][0-9a-f][0-9a-f][0-9a-f]-");
        uuidFilter.append("[0-9a-f][0-9a-f][0-9a-f][0-9a-f]-");
        uuidFilter.append("[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]");
        searchStrings.add(uuidFilter.toString());
        // Filter for timestamps
        StringBuffer timestampFilter = new StringBuffer();
        timestampFilter.append( "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] " );
        timestampFilter.append( "[0-9][0-9]:[0-9][0-9]:[0-9][0-9].[0-9]* *" );
        searchStrings.add( timestampFilter.toString() );
        // 3 digit year
        timestampFilter = new StringBuffer();
        timestampFilter.append( "[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] " );
        timestampFilter.append( "[0-9][0-9]:[0-9][0-9]:[0-9][0-9].[0-9]* *" );
        searchStrings.add( timestampFilter.toString() );
        // ibm13 year
        timestampFilter = new StringBuffer();
        timestampFilter.append( "[0-9]-[0-9][0-9]-[0-9][0-9] " );
        timestampFilter.append( "[0-9][0-9]:[0-9][0-9]:[0-9][0-9].[0-9]* *" );
        searchStrings.add( timestampFilter.toString() );
        // Filter remove transaction id's from deadlock messages
        searchStrings.add("^  Waiting XID : {.*}");
        searchStrings.add("^  Granted XID : .*$");
        searchStrings.add("^The selected victim is XID : .*");
        // Filters for build numbers
        searchStrings.add("(beta - )\\(([0-9]*)\\)");
        searchStrings.add("Level2CostEstimateImpl: .*");
        // Filter for xa tests for the numbers representing the db name (it can change)
        searchStrings.add("^Transaction ([0-9])* : \\(([0-9]*)\\,([0-9a-f]*)\\,([0-9a-f]*)\\)");
        // Filter for optimizer number for zindexesLevel1 test (due to a change in display width for the test)
        searchStrings.add("^Modifying access paths using optimizer .[0-9]*");
        searchStrings.add("CDWS[0-9]*");
        searchStrings.add("IXWS[0-9]*");
        // for j9, to eliminate intermittent failures due to this problem in j9:
        searchStrings.add("FAILED STACK MAP");
        if (isJCC)
        {
            searchStrings.add("[ ]*\\|");
            searchStrings.add("^--*");
        }

        //Filter to suppress absould paths in error message for roll forward recovery tests 
        searchStrings.add("Directory.*.wombat.already.exists");
        searchStrings.add("Directory.*.extinout/crwombatlog/log.*.exists");

        // Filter for "DB2ConnectionCorrelator" text that can be printed as
        // part of some JCC error messages.
        searchStrings.add("  DB2ConnectionCorrelator: [0-9A-Z.]*");
		// Filter for SAX exception name diffs between jvms.
        searchStrings.add("org.xml.sax.SAX.*$");
        // Filter out localhost, or hostName
        searchStrings.add(hostName);

		if ( isJDBC4 )
		{
			// Filters for the sql exception class names which appear in
			// exception messages. These are different in JDBC3 and JDBC4.
			searchStrings.add("java.sql.SQLDataException:");
			searchStrings.add("java.sql.SQLDataSetSyncException:");
			searchStrings.add("java.sql.SQLException:");
			searchStrings.add("java.sql.SQLFeatureNotSupportedException:");
			searchStrings.add("java.sql.SQLIntegrityConstraintViolationException:");
			searchStrings.add("java.sql.SQLInvalidAuthorizationSpecException:");
			searchStrings.add("java.sql.SQLNonTransientConnectionException:");
			searchStrings.add("java.sql.SQLNonTransientException:");
			searchStrings.add("java.sql.SQLRuntimeException:");
			searchStrings.add("java.sql.SQLSyntaxErrorException:");
			searchStrings.add("java.sql.SQLTimeoutException:");
			searchStrings.add("java.sql.SQLTransactionRollbackException:");
			searchStrings.add("java.sql.SQLTransientConnectionException:");
			searchStrings.add("java.sql.SQLTransientException:");

			// The JDBC4 error from the driver is a little chattier
			searchStrings.add("No suitable driver found for [0-9A-Za-z:]*");			
			searchStrings.add("No suitable driver;[0-9A-Za-z:=]*");			
			searchStrings.add("SQL Exception: No suitable driver");			

			// Timestamp diagnostic looks a little different under jdk16
			searchStrings.add("\\[\\.fffffffff\\]");			
		}
		
        FastTable subStrings = new FastTable();
        subStrings.add("Transaction:(XXX)|");
        subStrings.add("Read ... bytes");
        subStrings.add("Directory DBLOCATION/seg0");
        subStrings.add("xxxxGENERATED-IDxxxx");
        subStrings.add("xxxxFILTERED-UUIDxxxx");
        subStrings.add("xxxxxxFILTERED-TIMESTAMPxxxxx");
        subStrings.add("xxxxxxFILTERED-TIMESTAMPxxxxx");
        subStrings.add("xxxxxxFILTERED-TIMESTAMPxxxxx");
        // remove transaction id's from deadlock messages
        subStrings.add("  Waiting XID : {WWW,QQQ}");
        subStrings.add("  Granted XID : {GGG.QQQ}...");
        subStrings.add("The selected victim is XID : VVV");
        // sub build numbers
        subStrings.add("$1(xxXXxxFILTERED-BUILD-NUMBERxxXXxx)");
        subStrings.add("Level2CostEstimateImpl: xxXXxxFILTERED-INFORMATIONxxXXxx");
        // sub for db name in xa tests (it can change)
        subStrings.add("Transaction $1 : ($2,FILTERED,FILTERED)");
        // sub for optimizer number for zindexesLevel1 test
        subStrings.add("Modifying access paths using optimizer FILTERED_NUMBER");
        subStrings.add("CDWSno");
        subStrings.add("IXWSno"); 
        // for j9, to eliminate intermittent failures due to this problem in j9:
        subStrings.add("");
        // for JCC replace multiple blanks with one blank to handle differences
        // in display width
        if (isJCC)
        {
            subStrings.add(" |");
            subStrings.add("-----"); 
        }
        subStrings.add("Directory DBLOCATION/wombat already exists");
        subStrings.add("Directory 'extinout<sp>crwombatlog<sp>log' exists");
        // ignore the 'DB2ConnectionCorrelator' thing altogether.
        subStrings.add("");
		// Filter for SAX exception name diffs between jvms.
        subStrings.add("xxxFILTERED-SAX-EXCEPTIONxxx'.");
        // Filter out localhost, or hostName
        subStrings.add("xxxFILTERED_HOSTNAMExxx");

		if ( isJDBC4 )
		{
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);
			subStrings.add(SQL_EXCEPTION_FILTERED_SUBSTITUTION);

			subStrings.add("No suitable driver");
			subStrings.add("No suitable driver");
			subStrings.add("java.sql.SQLException: No suitable driver");

			subStrings.add(".fffffffff");
		}

		doWork(srcFile, dstFile, null, deleteLines, searchStrings, subStrings, isSed, isI18N);
        
    } // end exec

    // This just does JCC changes on the output master file
    public void execJCC(InputStream is, File dstFile)
        throws IOException
    {
        // FastTable for storing lines to be deleted
        FastTable deleteLines = new FastTable();

        // FastTables for substitutions
        FastTable searchStrings = new FastTable();
        searchStrings.add("[ ]*\\|");
        searchStrings.add("^--*");

        FastTable subStrings = new FastTable();
        // true and false show up as 1 and 0 in JCC. 
        //because they have no boolean support
        subStrings.add(" |");
        subStrings.add("-----");

        doWork(null, dstFile, is, deleteLines, searchStrings, subStrings, null);

    }

    private void doWork(File srcFile, File dstFile, InputStream is, FastTable deleteLines, 
        FastTable searchStrings, FastTable subStrings, InputStream isSed)
        throws IOException
    {
        doWork(srcFile, dstFile, is, deleteLines, searchStrings, subStrings, isSed, false);
    }
		

    private void doWork(File srcFile, File dstFile, InputStream is, FastTable deleteLines, 
        FastTable searchStrings, FastTable subStrings, InputStream isSed, boolean isI18N)
        throws IOException
    {
		
        boolean lineDeleted = false;
        PatternMatcher matcher;
        Perl5Compiler pcompiler;
        PatternMatcherInput input;
        BufferedReader inFile;
        PrintWriter outFile;
        String result = "";
        String regex;
        FastTable delPatternFastTable = new FastTable();
        FastTable subPatternFastTable = new FastTable();

        // ---------------------------------
        // Try loading the sed properties if they exist (see jdbc_sed.properties as an example)
        if ( isSed != null )
        {
            Properties sedp = new Properties();

            sedp.load(isSed);
            for (Enumeration e = sedp.propertyNames(); e.hasMoreElements(); )
            {
                String key = (String)e.nextElement();
                if (key.equals("substitute"))
                {
                    String value = sedp.getProperty(key);
                    // value string contains a comma separated list of patterns
                    StringTokenizer st = new StringTokenizer(value,",");
                    String patternName = ""; 
                    String patName = ""; 
                    String subName = ""; 
                    while (st.hasMoreTokens())
                    {
                        patternName = st.nextToken();
                        // pattern;substitute
                        StringTokenizer st2 = new StringTokenizer(patternName,";");
                        patName = st2.nextToken();
                        subName = st2.nextToken();
                        if (!patName.equals("") && !subName.equals(""))
                        {
                            searchStrings.add(patName);
                            subStrings.add(subName);
                        }
                    //System.out.println("pattern = " + patName + " substitute " + subName);
                    }
                }
                else if (key.equals("delete"))
                {
                    String value = sedp.getProperty(key);
                    // value string contains a comma separated list of patterns
                    StringTokenizer st = new StringTokenizer(value,",");
                    String patternName = ""; 
                    while (st.hasMoreTokens())
                    {
                        patternName = st.nextToken();
                        deleteLines.add(patternName);
                    }
                }
            }
        }
        // ---------------------------------

        //Create Perl5Compiler and Perl5Matcher
        pcompiler = new Perl5Compiler();
        matcher = new Perl5Matcher();

        // Define the input and output files based on args
        if (is == null && isI18N) {
            // read UTF-8 encoded file
            InputStream fs = new FileInputStream(srcFile);
            inFile = new BufferedReader(new InputStreamReader(fs, "UTF-8"));
        } else if (is == null) {
            // read the file using the default encoding
            inFile = new BufferedReader(new FileReader(srcFile));
        } else {
            inFile = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        }
        outFile = new PrintWriter
        ( new BufferedWriter(new FileWriter(dstFile), 10000), true );

        // Attempt to compile the patterns for deletes
        for (int i = 0; i < deleteLines.size(); i++)
        {
            try
            {
                regex = (String)deleteLines.get(i);
                //System.out.println("The pattern: " + regex);
                Pattern pattern = pcompiler.compile(regex);
                if (pattern == null)
                    System.out.println("pattern is null");
                delPatternFastTable.add(pattern);
            }
            catch(MalformedPatternException e)
            {
                System.out.println("Bad pattern.");
                System.out.println(e.getMessage());
            }
        }

        // Attempt to compile the patterns for substitutes
        for (int i = 0; i < searchStrings.size(); i++)
        {
            try
            {
                regex = (String)searchStrings.get(i);
                //System.out.println("The pattern: " + regex);
                Pattern pattern = pcompiler.compile(regex);
                if (pattern == null)
                    System.out.println("pattern is null");
                subPatternFastTable.add(pattern);
            }
            catch(MalformedPatternException e)
            {
                System.out.println("Bad pattern.");
                System.out.println(e.getMessage());
            }
        }

        String str;
        int j;
        int lineCount = 0;
        // Read the input file
        while ( (str = inFile.readLine()) != null )
        {
            lineCount++;
        
            //System.out.println("***Line no: " + lineCount);
            //System.out.println("***Line is: " + str);
            lineDeleted = false;

            // First delete any nulls (Cafe 1.8 leaves nulls)
            if (str.length() == 1)
            {
                if (str.charAt(0) == (char) 0)
                {
                    // Skip this line, don't write it
                    //System.out.println("Skip this line...");
                    lineDeleted = true;
                }
            }

            // Now determine if & if so, replace, any non-ascii characters
            // We do this because non-ascii characters in .sql files will
            // result in different characters depending on encoding, and
            // encoding may be different on different os's
            if (isI18N)
            {
                boolean hasNonAscii = false;
                // check for any characters in the control range
                for (int si = 0; si < str.length(); si++)
                {
                    char c = str.charAt(si);
                    if (c < (char) 0x20 || c >= (char) 0x7f)
                    {
                        hasNonAscii = true;
                        break;
                    }
                }

                if (hasNonAscii)
                {
                    StringBuffer sb = new StringBuffer();
                    for (int si = 0; si < str.length(); si++)
                    {
                        char c = str.charAt(si);
                        if (c < (char) 0x20 || c >= (char) 0x7f)
                        {
                            sb.append(' ');
                            // Encoded Character:> ... <
                            sb.append("EnC:>");
                            sb.append((int) str.charAt(si));
                            sb.append("< ");
                        }
                        else
                            sb.append(c);
                    }
                    str = sb.toString();
                }
            }

            // Determine if this line should be deleted for delete pattern match
            if ( lineDeleted == false )
            {
                for (j = 0; j < delPatternFastTable.size(); j++)
                {
                    if ( matcher.contains( str, (Pattern)delPatternFastTable.get(j) ) )
                    {
                        //System.out.println("***Match found to delete line***");
                        String tmpp = ((Pattern)delPatternFastTable.get(j)).getPattern();
                        //System.out.println("***Pattern is: " + tmpp);

                        // In this case we are removing the line, so don't write it out
                        lineDeleted = true;
                        break;
                    }
                }
            }

            // Determine if any substitutions are needed
            if (lineDeleted == false)
            {
                Substitution substitution;
                StringSubstitution strsub = new StringSubstitution("");
                Perl5Substitution perlsub = new Perl5Substitution("");
                boolean subDone = false;
                for (j = 0; j < subPatternFastTable.size(); j++)
                {
                    input = new PatternMatcherInput(str);
                    Pattern patt = (Pattern)subPatternFastTable.get(j);
                    String pstr = patt.getPattern();
                    //System.out.println("Pattern string is " + pstr);
                    String sub = (String)subStrings.get(j);
                    if (sub.indexOf("$") > 0)
                    {
                        perlsub.setSubstitution(sub);
                        substitution = (Substitution)perlsub;
                    } else {
                        strsub.setSubstitution(sub);
                        substitution = (Substitution)strsub;
                    }
                    //System.out.println("Substitute str = " + sub);
                    if ( matcher.contains( input, patt ) )
                    {
                        MatchResult mr = matcher.getMatch();
                        //System.out.println("***Match found for substitute***");
                        // In this case we do a substitute
                        result = Util.substitute(matcher, patt, substitution, str,
                        Util.SUBSTITUTE_ALL);
                        //System.out.println("New string: " + result);
                        //outFile.println(result);
                        str = result;
                        subDone = true;
                    }
                }
                if (subDone)
                {
                    //System.out.println("write the subbed line");
                    outFile.println(result);
                }
                else
                {
                    //System.out.println("Write the str: " + str);
                    outFile.println(str);
                    outFile.flush();
                }
            }// end if
        } // end while
        inFile.close();
        outFile.flush();
        outFile.close();
    }// end doWork
}
