package parser;

import java.io.IOException;
import java.util.List;

public class Main {
	private static List<HouseDeal> dealList;
	private static List<String> gugunList;
	private static String[] monthList = {"201901","201902","201903","201904","201905","201906",
											"201907","201908","201909","201910","201911","201912"};
	public static void main(String[] args) throws IOException {
		HouseDealParser hd = new HouseDealParser();
		HouseDealDao houseDealDao = new HouseDealDao();
		GugunDao gugunDao = new GugunDao();
		int num = 1;
		int list_size = 0;
		
		gugunList = gugunDao.selectAll();
		
		for(String gugun : gugunList) {
			for(String month : monthList) {
				while (true) {
					String xml = hd.getXml(num,gugun,month);
					dealList = hd.getHouseDeal(xml);
					if (dealList.size() == list_size) {
						break;
					}
					list_size = dealList.size();
					num++;
				}
				System.out.printf("진행중..(%d/1043)\n",dealList.size());
			}
		}
		
		for (HouseDeal deal : dealList) {
			houseDealDao.insert(deal);
		}
	}
}
