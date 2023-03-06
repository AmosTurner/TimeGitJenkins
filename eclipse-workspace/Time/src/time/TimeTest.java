package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {
	
	@ParameterizedTest
	@ValueSource(strings = { "00:00:15", "05:30:15", "23:23:15"})
	void testGetTotalSecondsGood(String candidate) {
		int seconds = Time.getSeconds(candidate);
		assertTrue("The seconds were not calculated properly", seconds==15);
	}
	
	@Test
	void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, ()-> {
			Time.getTotalSeconds("10:00");
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "05:30:00", "05:30:15", "05:30:59"})
	void testGetTotalMinutesGood(String candidate) {
		int minutes = Time.getTotalMinutes(candidate);
		assertTrue("The minutes were not calculated properly", minutes==30);
	}
	
	@Test
	void testGetTotalMinutesBad() {
		assertThrows(NumberFormatException.class, ()-> {
			Time.getTotalMinutes("23:&:23");
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "05:15:15", "05:59:59"})
	void testGetTotalHoursGood(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue("The hours were not calculated properly", hours==5);
	}
	
	@Test
	void testGetTotalHoursBad() {
		assertThrows(NumberFormatException.class, ()-> {
			Time.getTotalHours("@:59:59");
		});
	}
	
	@Test
	void getMillisecondsGood () {
		int milliseconds = Time.getMilliseconds("12:05:05:005");
		assertTrue("The milliseconds were not calculated properly", milliseconds==5);
	}
	
	@Test
	void getTotalMilliseconds () {
		int milliseconds = Time.getTotalMilliseconds("05:05:05:005");
		assertTrue("The milliseconds were not calculated properly", milliseconds==18305005);
	}

}