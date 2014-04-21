/* Generated By:JavaCC: Do not edit this line. mtGrammar.java */
package org.apache.derby.impl.tools.ij;

import javolution.util.FastMap;
import javolution.util.FastTable;
import java.io.IOException;
import org.apache.derby.iapi.tools.i18n.*;

public class mtGrammar implements mtGrammarConstants {
        private LocalizedOutput currOut;
        private LocalizedInput currIn;


        mtGrammar() { }

//
// start of BNF rules
//
  final public mtTestSuite grammarStatement() throws ParseException {
        FastTable cases = null;
        FastTable init = null;
        FastTable last = null;
        int     threads = 0;
        mtTime time;
    threads = numThreads();
    time = runTime();
    switch (jj_nt.kind) {
    case INIT:
      init = init();
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    cases = cases();
    switch (jj_nt.kind) {
    case LAST:
      last = last();
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(0);
                {if (true) return new mtTestSuite(threads, time, init, cases, last);}
    throw new Error("Missing return statement in function");
  }

  final public int numThreads() throws ParseException {
        Token   t;
    jj_consume_token(THREADS);
    t = jj_consume_token(INT);
                int val = Integer.decode(t.image).intValue();
                {if (true) return val;}
    throw new Error("Missing return statement in function");
  }

  final public mtTime runTime() throws ParseException {
        mtTime time;
    jj_consume_token(RUNTIME);
    time = time();
                {if (true) return time;}
    throw new Error("Missing return statement in function");
  }

  final public FastTable cases() throws ParseException {
        FastTable testCases = new FastTable(5);
    label_1:
    while (true) {
      testCase(testCases);
      switch (jj_nt.kind) {
      case CASE:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_1;
      }
    }
                {if (true) return testCases;}
    throw new Error("Missing return statement in function");
  }

  final public void testCase(FastTable testCases) throws ParseException {
        Token   t;
        mtTestCase testCase = new mtTestCase();
    jj_consume_token(CASE);
    t = jj_consume_token(WORD);
    caseInfo(testCase);
                testCase.setName(t.image);
                testCases.addElement(testCase);
  }

  final public FastTable init() throws ParseException {
        FastTable testCases = new FastTable(5);
    label_2:
    while (true) {
      initCase(testCases);
      switch (jj_nt.kind) {
      case INIT:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
    }
                {if (true) return testCases;}
    throw new Error("Missing return statement in function");
  }

  final public void initCase(FastTable testCases) throws ParseException {
        Token   t;
        mtTestCase testCase = new mtTestCase();
    jj_consume_token(INIT);
    t = jj_consume_token(WORD);
    caseInfo(testCase);
                testCase.setName(t.image);
                testCases.addElement(testCase);
  }

  final public FastTable last() throws ParseException {
        FastTable testCases = new FastTable(1);
    label_3:
    while (true) {
      lastCase(testCases);
      switch (jj_nt.kind) {
      case LAST:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
    }
                {if (true) return testCases;}
    throw new Error("Missing return statement in function");
  }

  final public void lastCase(FastTable testCases) throws ParseException {
        Token   t;
        mtTestCase testCase = new mtTestCase();
    jj_consume_token(LAST);
    t = jj_consume_token(WORD);
    caseInfo(testCase);
                testCase.setName(t.image);
                testCases.addElement(testCase);
  }

  final public void caseInfo(mtTestCase testCase) throws ParseException {
        String  file;
        String  prop = null;
        int     weight = 50;
        FastMap errorList = null;
        String  description = null;
    file = scriptFile();
                testCase.setFile(file);
    switch (jj_nt.kind) {
    case PROPERTIES:
      prop = propFile();
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
                testCase.setPropFile(prop);
    switch (jj_nt.kind) {
    case WEIGHT:
      weight = weight();
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
                testCase.setWeight(weight);
    switch (jj_nt.kind) {
    case IGNOREERRORS:
      errorList = ignoreErrors();
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
                if (errorList == null)
                        errorList = new FastMap();

                testCase.setIgnoreErrors(errorList);
    switch (jj_nt.kind) {
    case DESCRIPTION:
      description = description();
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
                testCase.setDescription(description);
  }

  final public String scriptFile() throws ParseException {
        Token   t;
    jj_consume_token(FILE);
    t = jj_consume_token(WORD);
                {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public String propFile() throws ParseException {
        Token   t;
    jj_consume_token(PROPERTIES);
    t = jj_consume_token(WORD);
                {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public int weight() throws ParseException {
        Token   t;
    jj_consume_token(WEIGHT);
    t = jj_consume_token(INT);
                int val = Integer.decode(t.image).intValue();
                if (val > 100 || val < 1)
                {
                        System.out.println("LINE "+t.beginLine +": Weight '"+t.image
                                                        +"' is invalid, must be between 1..100"
                                                        +" -- defaulting to 50.");
                        val = 50;
                }
                {if (true) return val;}
    throw new Error("Missing return statement in function");
  }

  final public String description() throws ParseException {
        Token   t;
    jj_consume_token(DESCRIPTION);
    t = jj_consume_token(STRING);
                {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public FastMap ignoreErrors() throws ParseException {
        // note: we need a non-null errorlist even
        // if there is nothing in it
        FastMap errorList = new FastMap();
    jj_consume_token(IGNOREERRORS);
    getError(errorList);
    label_4:
    while (true) {
      switch (jj_nt.kind) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_4;
      }
      jj_consume_token(COMMA);
      getError(errorList);
    }
                {if (true) return errorList;}
    throw new Error("Missing return statement in function");
  }

  final public void getError(FastMap errorList) throws ParseException {
        String  s;
    s = word_or_int();
                if (errorList == null)
                {
                }
                errorList.put(s, new Integer(0));
  }

  final public String word_or_int() throws ParseException {
        Token   t;
    switch (jj_nt.kind) {
    case WORD:
      t = jj_consume_token(WORD);
                {if (true) return t.image;}
      break;
    case INT:
      t = jj_consume_token(INT);
                {if (true) return t.image;}
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

/*
** Possible formats:
**
** 	HH:MM:SS
** 	   MM:SS
** 	      SS
*/
  final public mtTime time() throws ParseException {
        Token h, m, s;
    if (jj_2_1(4)) {
      h = jj_consume_token(INT);
      jj_consume_token(COLON);
      m = jj_consume_token(INT);
      jj_consume_token(COLON);
      s = jj_consume_token(INT);
                {if (true) return new mtTime(
                                Integer.decode(h.image).intValue(),
                                Integer.decode(m.image).intValue(),
                                Integer.decode(s.image).intValue());}
    } else if (jj_2_2(2)) {
      m = jj_consume_token(INT);
      jj_consume_token(COLON);
      s = jj_consume_token(INT);
                {if (true) return new mtTime(
                                0,
                                Integer.decode(m.image).intValue(),
                                Integer.decode(s.image).intValue());}
    } else {
      switch (jj_nt.kind) {
      case INT:
        s = jj_consume_token(INT);
                {if (true) return new mtTime(
                                0,
                                0,
                                Integer.decode(s.image).intValue());}
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  final private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  final private boolean jj_3_1() {
    if (jj_scan_token(INT)) return true;
    if (jj_scan_token(COLON)) return true;
    if (jj_scan_token(INT)) return true;
    if (jj_scan_token(COLON)) return true;
    return false;
  }

  final private boolean jj_3_2() {
    if (jj_scan_token(INT)) return true;
    if (jj_scan_token(COLON)) return true;
    return false;
  }

  public mtGrammarTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token, jj_nt;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  public boolean lookingAhead = false;
  private boolean jj_semLA;
  private int jj_gen;
  final private int[] jj_la1 = new int[12];
  static private int[] jj_la1_0;
  static {
      jj_la1_0();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x2000,0x1000,0x400,0x2000,0x1000,0x20000,0x4000,0x10000,0x8000,0x800000,0x300000,0x100000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[2];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  public mtGrammar(java.io.InputStream stream) {
     this(stream, null);
  }
  public mtGrammar(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new mtGrammarTokenManager(jj_input_stream);
    token = new Token();
    token.next = jj_nt = token_source.getNextToken();
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    token.next = jj_nt = token_source.getNextToken();
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public mtGrammar(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new mtGrammarTokenManager(jj_input_stream);
    token = new Token();
    token.next = jj_nt = token_source.getNextToken();
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    token.next = jj_nt = token_source.getNextToken();
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public mtGrammar(mtGrammarTokenManager tm) {
    token_source = tm;
    token = new Token();
    token.next = jj_nt = token_source.getNextToken();
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(mtGrammarTokenManager tm) {
    token_source = tm;
    token = new Token();
    token.next = jj_nt = token_source.getNextToken();
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken = token;
    if ((token = jj_nt).next != null) jj_nt = jj_nt.next;
    else jj_nt = jj_nt.next = token_source.getNextToken();
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    jj_nt = token;
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  final private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }

  final public Token getNextToken() {
    if ((token = jj_nt).next != null) jj_nt = jj_nt.next;
    else jj_nt = jj_nt.next = token_source.getNextToken();
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private javolution.util.FastTable jj_expentries = new javolution.util.FastTable();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Enumeration e = jj_expentries.elements(); e.hasMoreElements();) {
        int[] oldentry = (int[])(e.nextElement());
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.addElement(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[25];
    for (int i = 0; i < 25; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 12; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 25; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, mtGrammarConstants.tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

  final private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 2; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  final private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
