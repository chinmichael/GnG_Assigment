package week1st;

public class Example1 {
	
	public static class TextBuffer {
		private String content;
		
		public TextBuffer(String text) {
			this.content = text;
		}
		
		/*
		 * 保存されている「content」の中に、「文字」は何回使われているのかを計算する
		 * @param chr 文字　（大文字、小文字の区別はない）
		 * @return contentの中に含まれている「文字」の数
		 */
		public int countOf(char chr) {
			int cnt = 0;
			
			if(content == null || content.isEmpty()) {
				return cnt;
			}
			
			if(65 <= chr && chr <= 90) {
				chr = (char) (chr + 32);
			}
			
			String checkContent = content.toLowerCase();
			
			for(int i = 0; i < content.length(); i++) {
				char check = checkContent.charAt(i);
				
				/*
				 * toLowerCase()メソードを使わない場合
				 * char check = content.charAt(i);
				 * if(65 <= check && check <= 90) {
				 * 		check = (char) (check + 32);
				 * }
				 */
				
				if(chr == check) cnt++;
			}
			
			return cnt;
		}
	}
	
	public static void main(String[] args) {
		final String sampleText =
				"Four score and seven years ago our fathers brought forth " +
				"on this continent a new nation, conceived in liberty, " +
				"and dedicated to the proposition that all men are created equal.";
		
		final TextBuffer reader = new TextBuffer(sampleText);
		
		final int countOfE = reader.countOf('e');
		final int countOfF = reader.countOf('F');
		final int countOfComma = reader.countOf(',');
		final int countOfSpace = reader.countOf(' ');
		
		System.out.println(sampleText);
		System.out.println("----------------");
		System.out.println("E: " + countOfE);
		System.out.println("F: " + countOfF);
		System.out.println("Comma: " + countOfComma);
		System.out.println("Space: " + countOfSpace);
	}

}
