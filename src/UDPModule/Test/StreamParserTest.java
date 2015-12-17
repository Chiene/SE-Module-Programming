package UDPModule.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import UDPModule.Tool.StreamParser;

public class StreamParserTest {

	@Test
	public void testMsgCorrectFormat() {
		assertTrue(StreamParser.getMsgToken("ADD Item testetst").length == 3);
	}

	@Test
	public void testMsgFaliureFormatWithNoSpace() {
		assertTrue(StreamParser.getMsgToken("ADDItemtestetst").length == 3);
	}

	@Test
	public void testMsgFaliureFormatWithOneSpace() {
		assertTrue(StreamParser.getMsgToken("ADDItem testetst").length == 3);
	}

	@Test
	public void testMsgEmpty() {
		assertTrue(StreamParser.getMsgToken(" ").length == 3);
	}

}
