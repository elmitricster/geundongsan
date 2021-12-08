package parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HouseDealParser {
	private static int no = 1;
	private static String xmlstr = null;
	private static StringBuilder sb;
	private static List<HouseDeal> list;

	public HouseDealParser() throws IOException {
		list = new ArrayList<>();
	}

	//month = "201512"
	public String getXml(int num,String gugun,String month) {
		try {
			StringBuilder urlBuilder = new StringBuilder(
					"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*
																																 * URL
																																 */
			String key = "A1RNFfhN+QQ2zDlGtFaaspE9qX+q2MjqG/O9twyXHxVObZnBitQwJKJKWrwGyZ3F35qr5A0L9wNIuPYRAWeiSA==";
			urlBuilder.append(
					"?" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(gugun, "UTF-8")); /* 지역코드 */
			urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "="
					+ URLEncoder.encode(month, "UTF-8")); /* 계약월 */
			urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
					+ URLEncoder.encode(key, "UTF-8")); /* 공공데이터포털에서 받은 인증키 */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
					+ URLEncoder.encode(Integer.toString(num), "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */

			URL url = new URL(urlBuilder.toString());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			sb = new StringBuilder();

			String line;
			while ((line = rd.readLine()) != null) {
				// System.out.println(line);
				sb.append(line);
			}
			xmlstr = sb.toString();
			rd.close();
			conn.disconnect();
		} catch (Exception e) {
		}
		return xmlstr;
	}

	public List<HouseDeal> getHouseDeal(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // SAX와 동일하게 factory
			DocumentBuilder builder = factory.newDocumentBuilder();// factory로 빌더 생성
			// 문서 로딩 완료 --> 원하는 요소들 골라내기
			Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes())); // 이미 파싱 끝, 이미 메모리에 로링했음. 이제 우리가 원하는
																					// 녀석들만 찾아서 잘 쓰면 됌.
			// 최 상위 element //SAX는 핸들러를 쓰기 때문에 this도 같이줘야했음.
			Element root = doc.getDocumentElement(); // 루트 뽑아오는것.
			parse(root);
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static void parse(Element root) {
		NodeList nodelist = root.getElementsByTagName("item");
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i); //
			HouseDeal info = getHouseDeal(node, i); // 노드를갖고왔으니까 그 안에 있는 내용좀 봐보자.
			list.add(info);
		}
	}

	private static HouseDeal getHouseDeal(Node node, int num) {
		HouseDeal houseDeal = new HouseDeal();
		// TODO: node 정보를 이용해서 tradedto를 구성하고 반환하시오.
		NodeList list = node.getChildNodes(); // node의 자식들을 쭉 갖고옴.
		String sigungu = null;
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			if (child.getNodeName().equals("거래금액")) {
				houseDeal.setDealAmount(child.getTextContent()); // tradedto에 담아준다.
			} else if (child.getNodeName().equals("건축년도")) {
				houseDeal.setBuildYear(child.getTextContent());
			} else if (child.getNodeName().equals("년")) {
				houseDeal.setDealYear(child.getTextContent());
			} else if (child.getNodeName().equals("법정동")) {
				houseDeal.setDong(child.getTextContent());
			} else if (child.getNodeName().equals("법정동시군구코드")) {
				sigungu = child.getTextContent();
			} else if (child.getNodeName().equals("법정동읍면동코드")) {
				houseDeal.setCode(sigungu + child.getTextContent());
			} else if (child.getNodeName().equals("아파트")) {
				houseDeal.setAptName(child.getTextContent());
			} else if (child.getNodeName().equals("월")) {
				houseDeal.setDealMonth(child.getTextContent());
			} else if (child.getNodeName().equals("일")) {
				houseDeal.setDealDay(child.getTextContent());
			} else if (child.getNodeName().equals("전용면적")) {
				houseDeal.setArea(child.getTextContent());
			} else if (child.getNodeName().equals("지번")) {
				houseDeal.setJibun(child.getTextContent());
			} else if (child.getNodeName().equals("층")) {
				houseDeal.setFloor(child.getTextContent());
			}
		}
		houseDeal.setNo(no++);
		return houseDeal;
	}

	

}
