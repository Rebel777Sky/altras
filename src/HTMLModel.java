import java.util.ArrayList;
import java.util.Iterator;

public class HTMLModel {

		private ArrayList<String> m_list = new ArrayList<String>();
		private HTMLObserverModel m_observerModel;
		public void add(String str)
		{
			m_list.add(str);
			m_observerModel.update();
		}
		
		public ArrayList<String> get() {
			return m_list;
		}
		public String get_string() {
			String buff = "";

			for (Iterator<String> iterator = m_list.iterator(); iterator.hasNext();) {
				buff += iterator.next()+"\r\n";
			}
			
			return buff;
		}
		
		public String get_last_string() {
			return m_list.get(m_list.size() - 1)+"\r\n";
		}

		public HTMLObserverModel getM_observerModel() {
			return m_observerModel;
		}

		public void registerObserver(HTMLObserverModel observerModel) {
			this.m_observerModel = observerModel;
		}

		public void clearData() { 
		    m_list.clear();
		}
		
}
