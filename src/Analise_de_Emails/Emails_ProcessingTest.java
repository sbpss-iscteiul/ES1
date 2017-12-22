package Analise_de_Emails;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import txtreader.Email;
import txtreader.Rule;

class Emails_ProcessingTest {
	private ArrayList<Email> ham;
	private ArrayList<Email> spam;
	private ArrayList<Double> weights;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEmails_Processing() {
		Emails_Processing teste = new Emails_Processing(spam, ham, weights);
		assertEquals(ham, teste.getHam());
		assertEquals(spam, teste.getSpam());
		assertEquals(weights, teste.getWeights());
	}
	
	@Test
	void testSetSpam() {
		Emails_Processing a = new Emails_Processing(spam, ham, weights);
		a.setSpam(spam);
		assertEquals(ham, a.getHam());
	}
	@Test
	void testSetHam() {
		Emails_Processing a = new Emails_Processing(spam, ham, weights);
		a.setHam(spam);
		assertEquals(spam, a.getSpam());
	}
	@Test
	void testSetWeights() {
		Emails_Processing a = new Emails_Processing(spam, ham, weights);
		a.setWeights(weights);
		assertEquals(weights, a.getWeights());
		
	}
	

	@Test
	void testCalcFP() {
		Rule a= new Rule("BAYES_99", 0);
		Rule b= new Rule("FORGED_OUTLOOK_HTML", 1);
		Rule c= new Rule("FORGED_OUTLOOK_TAGS", 2);
		Rule d= new Rule("FREEMAIL_ENVFROM_END_DIGIT", 3);
		Rule e= new Rule("FREEMAIL_FROM", 4);
		Rule f= new Rule("FREEMAIL_REPLYTO", 5);
		Rule g= new Rule("HTML_MESSAGE", 6);
		Rule h= new Rule("MIME_HTML_ONLY", 7);
		Rule i= new Rule("MISSING_MIMEOLE2", 8);
		Rule j= new Rule("MSGID_OUTLOOK_INVALID", 9);
		Rule k= new Rule("MSOE_MID_WRONG_CASE", 10);
		Rule l= new Rule("NO_RDNS_DOTCOM_HELO", 11);
		Rule m= new Rule("RATWARE_MS_HASH", 12);
		Rule n= new Rule("RCVD_FAKE_HELO_DOTCOM", 13);
		Rule o= new Rule("SPF_FAIL", 14);
		ArrayList<Rule> p = new ArrayList<Rule>();
		p.add(a);p.add(b);p.add(c);p.add(d);p.add(e);p.add(f);p.add(g);p.add(h);p.add(i);
		p.add(j);p.add(k);p.add(l);p.add(m);p.add(n);p.add(o);
		ArrayList<Double> q = new ArrayList<Double>();
		q.add(2.0);q.add(5.0);q.add(-3.2);q.add(-5.0);q.add(4.0);q.add(1.0);q.add(-4.9);q.add(2.1);
		q.add(-1.0);q.add(-4.0);q.add(3.8);q.add(4.0);q.add(-4.9);q.add(-3.0);q.add(-4.9);
		
		Email spam1 = new Email("isto é spam");
		spam1.add_Rules(b);spam1.add_Rules(d);spam1.add_Rules(a);spam1.add_Rules(e);spam1.add_Rules(f);
		Email spam2 = new Email("isto é spam 2");
		spam2.add_Rules(k);spam2.add_Rules(l);spam2.add_Rules(i);
		Email ham1 = new Email("isto é ham");
		ham1.add_Rules(a); ham1.add_Rules(d);
		Email ham2 = new Email("isto é ham2");
		ham2.add_Rules(c); ham2.add_Rules(d); ham2.add_Rules(g);ham2.add_Rules(i); ham2.add_Rules(j);
		ArrayList<Email> spamList = new ArrayList<Email>();
		ArrayList<Email> hamList = new ArrayList<Email>();
		hamList.add(spam1);hamList.add(spam2);hamList.add(ham1);hamList.add(ham2);
		Emails_Processing teste = new Emails_Processing(spamList, hamList, q);
		assertEquals(2, teste.calcFP());
	}
	@Test
	void testCalcFN() {	
		Rule a= new Rule("BAYES_99", 0);
		Rule b= new Rule("FORGED_OUTLOOK_HTML", 1);
		Rule c= new Rule("FORGED_OUTLOOK_TAGS", 2);
		Rule d= new Rule("FREEMAIL_ENVFROM_END_DIGIT", 3);
		Rule e= new Rule("FREEMAIL_FROM", 4);
		Rule f= new Rule("FREEMAIL_REPLYTO", 5);
		Rule g= new Rule("HTML_MESSAGE", 6);
		Rule h= new Rule("MIME_HTML_ONLY", 7);
		Rule i= new Rule("MISSING_MIMEOLE2", 8);
		Rule j= new Rule("MSGID_OUTLOOK_INVALID", 9);
		Rule k= new Rule("MSOE_MID_WRONG_CASE", 10);
		Rule l= new Rule("NO_RDNS_DOTCOM_HELO", 11);
		Rule m= new Rule("RATWARE_MS_HASH", 12);
		Rule n= new Rule("RCVD_FAKE_HELO_DOTCOM", 13);
		Rule o= new Rule("SPF_FAIL", 14);
		ArrayList<Rule> p = new ArrayList<Rule>();
		p.add(a);p.add(b);p.add(c);p.add(d);p.add(e);p.add(f);p.add(g);p.add(h);p.add(i);
		p.add(j);p.add(k);p.add(l);p.add(m);p.add(n);p.add(o);
		ArrayList<Double> q = new ArrayList<Double>();
		q.add(2.0);q.add(5.0);q.add(-3.2);q.add(-5.0);q.add(4.0);q.add(1.0);q.add(-4.9);q.add(2.1);
		q.add(-1.0);q.add(-4.0);q.add(3.8);q.add(4.0);q.add(-4.9);q.add(-3.0);q.add(-4.9);
		
		Email spam1 = new Email("isto é spam");
		spam1.add_Rules(b);spam1.add_Rules(d);spam1.add_Rules(a);spam1.add_Rules(e);spam1.add_Rules(f);
		Email spam2 = new Email("isto é spam 2");
		spam2.add_Rules(k);spam2.add_Rules(l);spam2.add_Rules(i);
		Email ham1 = new Email("isto é ham");
		ham1.add_Rules(a); ham1.add_Rules(d);
		Email ham2 = new Email("isto é ham2");
		ham2.add_Rules(c); ham2.add_Rules(d); ham2.add_Rules(g);ham2.add_Rules(i); ham2.add_Rules(j);
		ArrayList<Email> spamList = new ArrayList<Email>();
		ArrayList<Email> hamList = new ArrayList<Email>();
		spamList.add(spam1);spamList.add(spam2);spamList.add(ham1);spamList.add(ham2);
		Emails_Processing teste = new Emails_Processing(spamList, hamList, q);
		assertEquals(2, teste.calcFN());
	}

}
