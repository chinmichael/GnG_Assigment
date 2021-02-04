package week3rd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Book {
	
	public static class BookData {
		private String title;
		private String writer;
		private String publisher;
		private Date pub_date;
		private String isbn_code;
		private long price;
		
		private boolean flg = true; 
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			if(title == null || title.isEmpty() || title.length() > 200) {
				flg = false;
			} else {
				this.title = title;
			}
		}
		
		public String getWriter() {
			return writer;
		}
		public void setWriter(String writer) {
			if(writer == null || writer.isEmpty()) {
				writer = "";
			}
			
			if(writer.length() > 100) {
				flg = false;	
			} else {
				this.writer = writer;
			}
		}
		
		public String getPublisher() {
			return publisher;
		}
		public void setPublisher(String publisher) {
			if(publisher == null || publisher.isEmpty()) {
				publisher = "";
			}
			
			if(publisher.length() > 100) {
				flg = false;	
			} else {
				this.publisher = publisher;
			}
		}
		
		public Date getPub_date() {
			return pub_date;
		}
		public void setPub_date(String input_date) {
			if(input_date == null || input_date.isEmpty()) {
				flg = false;
			} else {
				try {
					SimpleDateFormat dateFormatCheck = new SimpleDateFormat("yyyy/M/d");
					dateFormatCheck.setLenient(false);
					pub_date = dateFormatCheck.parse(input_date);
					
				} catch (Exception e) {
					flg = false;
					e.printStackTrace();
				}
			}
		}
		
		public String getIsbn_code() {
			return isbn_code;
		}
		public void setIsbn_code(String isbn_code) {
			if(isbn_code == null || isbn_code.isEmpty()) {
				this.isbn_code = "";
			} else {
				String check = isbn_code.replace("-", "");
				if(check.length() == 13) {
					this.isbn_code = check;
				} else {
					flg = false;
				}
			}
		}
		
		public long getPrice() {
			return price;
		}
		public void setPrice(String input_price) {	
			if(input_price == null) {
				flg = false;
			} else {
				long check = Long.parseLong(input_price);
				if(check < 0 || 100000000 < check) {
					flg = false;
				} else {
					price = check;
				}
			}
		}
		
		public boolean getFlg() {
			return flg;
		}
		public void setFlg(boolean flg) {
			this.flg = flg;
		}
	}
	
	public static class CsvUtil {
		public List<BookData> readCsv(String filePath) {
			List<BookData> list = new Vector<BookData>();
			File csv = new File(filePath);
			BufferedReader br = null;
			
			try {
				br = new BufferedReader(new FileReader(csv));
				Charset.forName("UTF-8");
				String line = "";
				
				while ((line = br.readLine()) != null) {
					String[] token = line.split(",");
					BookData inform = new BookData();
					
					if(token.length != 6) {
						inform.setFlg(false); // last price token : 必須
					} else {
						inform.setTitle(token[0]);
						inform.setWriter(token[1]);
						inform.setPublisher(token[2]);
						inform.setPub_date(token[3]);
						inform.setIsbn_code(token[4]);
						inform.setPrice(token[5]);
					}
					
					list.add(inform);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				try {
					if(br != null) br.close();
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			return list;
		}
	}
	
	public static void main(String[] args) {
		String filePath = "C:\\Users\\chinm\\Desktop\\books.csv";
		CsvUtil util = new CsvUtil();
		List<BookData> list = util.readCsv(filePath);
		SimpleDateFormat outDate = new SimpleDateFormat("yyyy年M月d日");
		DecimalFormat outPrice = new DecimalFormat("###,###");
		
		System.out.println("-------------------------------------");
		for(int i = 0; i < list.size(); i++) {
			BookData inform = list.get(i);
			if(inform.getFlg()) {
				System.out.println(inform.getTitle());
				System.out.println("著：" + inform.getWriter());
				System.out.println("出版：" + inform.getPublisher() + "(" + outDate.format(inform.getPub_date()) + ")");
				System.out.println("ISBN-13:" + inform.getIsbn_code());
				System.out.println("値段：" + outPrice.format(inform.getPrice()) + "円");
				
				if(i != list.size() - 1) {
					System.out.println("-------------------------------------");
				}
			}
		}
		System.out.println("-------------------------------------");
		
	}
}
