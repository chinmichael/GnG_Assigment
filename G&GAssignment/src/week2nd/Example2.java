package week2nd;

public class Example2 {
	
	public static class TextBuffer {
		private String content;
		
		public TextBuffer(String text) {
			this.content = text;
		}
		
		// ÜÁðíªµªìªÆª¤ªë¡¸content¡¹ªÎñéªË¡¢¡¸Ùþí®¡¹ª¬ù¼üÞÞÅªïªìªÆª¤ªëª«ªòÍªß©ª¹ªë
		public int countOf(char chr) {
			int cnt = 0;
			
			if(content == null || content.isEmpty()) {
				return cnt;
			}
			
			if('A' <= chr && chr <= 'Z') {
				chr = (char) (chr + 32);
			}
			
			final String lowered = content.toLowerCase();
			for(int i = 0; i < lowered.length(); i++) {
				char check = lowered.charAt(i);
				if(check == chr) cnt++;
			}
			
			return cnt;
		}
		
		public int[] countAlphabet() {
			int[] arrCnt = new int[26];
			
			if(content == null || content.isEmpty()) {
				return arrCnt;
			}
			
			final String lowered = content.toLowerCase();
			for(int i = 0; i < lowered.length(); i++) {
				char check = lowered.charAt(i);
				if('a' <= check && check <= 'z') {
					arrCnt[check - 'a']++;
				}
			}
			
			return arrCnt;
		}
	}
	
	public static void main(String[] args) {
		final String sampleText =
				"Four score and seven years ago our fathers brought forth " +
				"on this continent a new nation, conceived in liberty, " +
				"and dedicated to the proposition that all men are created equal.";
		
		final TextBuffer reader = new TextBuffer(sampleText);
		
		int[] counts = reader.countAlphabet();
		
		for(int i = 0; i < counts.length; i++) {
			char alp = (char) ('A' + i);
			System.out.printf("%c: %d\n", alp, counts[i]);
		}
	}
}
