import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HTMLController {
	
	private HTMLModel m_elements = new HTMLModel();
	private ArrayList<String> LinksSubDirect = new ArrayList<String>();
	private String urlTov;
	private String MainCatalog = "";
	
	public void get (String url) throws IOException {
		/*Document document = Jsoup.connect(url).get(); //"http://rebel777sky.t-70.ru"

		Elements elements = document.select("img");
		
		for (Element element : elements) {
			if (element.attr("src") != null)
			    m_elements.add(element.attr("src"));
     	}*/
		
		
		Document doc = Jsoup.connect("https://ilovemum.ru/catalog/").get();

		/*Elements newsHeadlines = doc.select("div.catalog__left a");
		newsHeadlines = newsHeadlines.select(".accordion-sub__link");
		for (Element headline : newsHeadlines) {
			if (headline.absUrl("href") != null)
			m_elements.add(headline.text() + " [ " + headline.absUrl("href") + " ]");
		}*/

		/*
		 * Парсируем первую страницу сайта с каталогом, получаем список разделы каталога в виде ссылок URL 
		 */
		Elements newsHeadlines = doc.select("div.catalog__left");
		newsHeadlines = newsHeadlines.select(".accordion__item");
		for (Element headline : newsHeadlines) {
			MainCatalog = headline.selectFirst("a").text();		
			Elements newsHeadlinesSub = headline.select("a");
			newsHeadlinesSub = newsHeadlinesSub.select(".accordion-sub__link");
			//m_elements.add("");
			for (Element headlineSub : newsHeadlinesSub) {
				if (headlineSub.absUrl("href") != null)
				//m_elements.add(MainCatalog + " - " + headlineSub.text() + " [ " + headlineSub.absUrl("href") + " ]");
				m_elements.add(headlineSub.absUrl("href"));
			}
		}
		
		/*
		 * Парсируем первую страницу раздела, получаем количество страниц в данном разделе в виде ссылок URL
		 */
		ArrayList<String> fff = new ArrayList<String>();
		fff = m_elements.get();
		for (Iterator<String> iterator = fff.iterator(); iterator.hasNext();) {
			Document doc1 = Jsoup.connect(iterator.next()).get();
			LinksSubDirect.add(doc1.baseUri());
			Elements newsPangeLinks = doc1.select("div.page-navigation a");
			for (Element elementPageLink : newsPangeLinks) {
				if (elementPageLink.attr("href") != null)
					
					if(!LinksSubDirect.contains("https://ilovemum.ru" + elementPageLink.attr("href")))
					LinksSubDirect.add("https://ilovemum.ru" + elementPageLink.attr("href"));
					//m_elements.add("https://ilovemum.ru" + elementPageLink.attr("href"));
			}		
		}

		/*
		 * Получаем ссылки на позиции
		 */
		if (LinksSubDirect.size() != 0)
			m_elements.clearData();
		for (Iterator<String> iterator = LinksSubDirect.iterator(); iterator.hasNext();) {
			Document doc1 = Jsoup.connect(iterator.next()).get();
			Elements newsHeadlines1 = doc1.select("ul.catalog__list.catalog-list.col3.bx_blue");
			newsHeadlines1 = newsHeadlines1.select(".catalog__item.catalog-item");
			for (Element headline1 : newsHeadlines1) {
				Elements newsHeadlines2 = headline1.select(".catalog__desc a");
				if (newsHeadlines2.attr("href") != null)
					m_elements.add("https://ilovemum.ru" + newsHeadlines2.attr("href"));
			}
		}

	}
				
		
	
	public HTMLModel getModel()
	{		
		return m_elements;
	}

}
